
# Mini‑Project “90 % TDD Toolkit” – Full Walk‑Through & Design Patterns for Easy Testing

A single Maven workspace that keeps **unit tests fast, isolated and reliable** by combining:

* Interfaces + constructor injection
* SRP (Single‑Responsibility Principle)
* Five classic test doubles – Dummy | Stub | Fake | Mock | Spy
* Wrappers for third‑party code (e‑mail, PDF, clock)
* Clock abstractions to freeze time in tests
* Literal assertions & Mockito `verify` (no tautologies)
* A pure ISBN‑10 validator (independent algorithm)
* A tiny `App` launcher to demo everything manually

The domain is deliberately small (books + ISBN lookup) so you can clearly see how test doubles interact with production code.

---

## 1. Why Even Bother?

* **Loose coupling** – swap a live REST/JDBC call for an in‑memory map in *one* line.
* **Fast feedback** – each unit test finishes in <5 ms; run them on every save.
* **Refactor safety** – a single red test pinpoints exactly which rule you broke.
* **Living docs** – a test named `exactLocatorFromStub()` reads like a requirement.
* **Extendable architecture** – adding ISBN‑13 or a real REST client does not invalidate existing tests.

Any Java code that touches SMTP, Stripe, Postgres, Kafka or the system clock benefits from these patterns.

---

## 2. Package Layout (High‑Level Architecture)

```
io.github.josemanuel
├─ App.java                        ← CLI launcher (demo only)
├─ core                            ← Pure domain / business logic
│   ├─ Book.java
│   ├─ ExternalISBNDataService.java
│   ├─ StockManager.java
│   └─ validation
│       └─ ValidateISBN.java       ← Pure ISBN‑10 checksum
├─ clock                           ← Time abstraction (freeze time)
│   ├─ Clock.java
│   ├─ SystemClock.java
│   └─ FixedClock.java
├─ email                           ← E‑mail wrapper
│   ├─ EmailService.java
│   ├─ OrderService.java
│   └─ SendGridEmailService.java
├─ documentexport                  ← PDF export wrapper
│   ├─ DocumentExporter.java
│   ├─ PdfExporter.java
│   ├─ FakeExporter.java
│   └─ InvoiceService.java
├─ infrastructure
│   └─ isbn
│       └─ RealExternalISBNDataService.java  ← Placeholder (not used in tests)
└─ doubles                         ← Reusable test doubles (prod never imports)
    ├─ DummyISBNService.java
    ├─ StubISBNService.java
    └─ FakeInMemoryISBNService.java
```

**Separation of concerns:**

* `core` contains pure logic (stable, testable).
* `infrastructure` contains real integrations (can evolve separately).
* `doubles` holds *only* test doubles, preventing accidental production use.
* Wrappers (`clock`, `email`, `documentexport`) show the same interface/injection technique in different contexts.

---

## 3. Core Domain & Contracts

```
Book                    // immutable value object (no setters)
ExternalISBNDataService // interface supplying Book data
StockManager            // calculates a warehouse locator code
ValidateISBN            // independent ISBN‑10 validator (pure)
```

Anything implementing `ExternalISBNDataService`—real REST client, file reader, in‑memory fake, Mockito mock—plugs into `StockManager` with zero code changes.

---

## 4. The Five Test‑Double Types (Concept & Implementation)

### 4.1 Conceptual Definitions

| Double    | Core Idea                                                                      | When to Use                                                           |
| --------- | ------------------------------------------------------------------------------ | --------------------------------------------------------------------- |
| **Dummy** | Passed in but never used; returns `null` / empty values.                       | Satisfy constructor or force an error path.                           |
| **Stub**  | Pre‑programmed return values, no behaviour or memory.                          | One deterministic assertion (“oracle”).                               |
| **Fake**  | Lightweight working implementation (e.g., `HashMap`) approximating production. | Multiple scenarios / offline tests without real I/O.                  |
| **Mock**  | Object with expectations; you verify interaction (call count, args, order).    | Need to assert *how* code collaborates, not just the output.          |
| **Spy**   | Wraps a real instance; executes real logic and records interactions.           | Need real behaviour *plus* verification (e.g., caching, call counts). |

> **Progression:** Dummy → Stub → Fake → Spy/Mock. Prefer the simplest that expresses the requirement; only escalate when output assertions are insufficient.

### 4.2 Concrete Classes in This Project

| Type  | Class / Technique                     | Role in Suite                                                       |
| ----- | ------------------------------------- | ------------------------------------------------------------------- |
| Dummy | `DummyISBNService`                    | Always returns `null`; triggers “ISBN not found” error branch.      |
| Stub  | `StubISBNService`                     | Hard‑coded Book; used for the exact `"1315"` locator assertion.     |
| Fake  | `FakeInMemoryISBNService`             | In‑memory mini‑DB; supports multiple ISBNs offline.                 |
| Mock  | `mock(ExternalISBNDataService.class)` | Verifies `lookup()` is called exactly once (no hidden loops).       |
| Spy   | `spy(realFake)`                       | Uses the fake’s real data while allowing `verify(spy).lookup("1")`. |

All are injected through constructors—`StockManager` never creates dependencies itself.

---

## 5. Business Rule – `StockManager`

```java
public String getLocatorCode(String isbn) {
    Book book = dataService.lookup(isbn);          // external boundary (double)
    if (book == null) throw new IllegalArgumentException("ISBN not found");

    String last = isbn.substring(isbn.length() - 1);
    return last + book.getAuthor().length() + book.getTitle().length();
}
```

Once `Book` is fetched, the calculation is **pure** (no I/O, no time) → trivial literal assertions.

---

## 6. Unit‑Test Suite (Zero Tautologies)

| Test class / method                                     | What it Proves                                   | Double Used   |
| ------------------------------------------------------- | ------------------------------------------------ | ------------- |
| `StockManagerDummyTest.throwsWhenBookMissing()`         | Guard clause & literal exception message         | Dummy         |
| `StockManagerStubTest.exactLocator()`                   | Algorithm correctness `"1315"` for ISBN `111...` | Stub          |
| `StockManagerFakeTest.dante/steinbeckLocator()`         | Multi‑row logic offline (`"6517"` & `"6915"`)    | Fake          |
| `StockManagerMockAndSpyTest.mockEnsuresSingleLookup()`  | Interaction count = 1                            | Mock          |
| `StockManagerMockAndSpyTest.spyCountsCallsOnRealFake()` | Real logic + call verification                   | Spy           |
| `OrderServiceMockTest.emailSentOnce()`                  | Wrapper pattern verifies e‑mail side‑effect      | Mock          |
| `ClockTest.fixedClockAlwaysSame()`                      | Time frozen via lambda                           | Lambda Clock  |
| `InvoiceServiceFakeTest.exporterFlagIsTrue()`           | Heavy PDF avoided; delegation occurred           | FakeExporter  |
| `ValidateISBNTest.validates10()`                        | Pure ISBN‑10 checksum across multiple cases      | (no double)   |
| `App` (manual run)                                      | Smoke test combining validator + StockManager    | Stub (manual) |

All assertions use **hand‑calculated constants** or `verify(...)`; no tautological “A equals A” patterns.

---

## 7. Supporting Patterns in the Codebase

* **Constructor Injection** – no `new` inside business classes → easy swapping.
* **SRP** – each class does one job (computation, data fetch, send email, export PDF).
* **Pure Functions** – isolate logic (locator math, checksum) from side effects.
* **Wrappers** – `EmailService`, `DocumentExporter`, `Clock` prevent vendor lock‑in.
* **Clock Abstraction** – optionally freeze today’s date with `FixedClock` or a lambda.
* **Infrastructure Boundary** – `RealExternalISBNDataService` isolated under `infrastructure/isbn` and excluded from tests to avoid accidental network calls.

---

## 8. End‑to‑End Mini‑Example: Invoice Export

1. `DocumentExporter` interface (boundary).
2. `InvoiceService` depends on it via constructor.
3. Test injects `FakeExporter`; after calling `exportInvoice()`, the `exported` flag is `true`.
4. No disk/PDF I/O; test runs instantly.

Pattern formula: **wrap → inject → fake → assert**.

---

## 9. Quick Reference – Patterns vs. Problems

| Future Pain                             | Apply This Pattern Today                        |
| --------------------------------------- | ----------------------------------------------- |
| Slow tests (network / database)         | Interface + Fake/Mock via constructor injection |
| Multi‑responsibility “god” classes      | SRP – split into smaller cohesive units         |
| Third‑party SDK keeps changing          | Wrapper (Adapter) interface                     |
| Time‑dependent / random flakiness       | Clock / Random wrappers                         |
| Many alternative behaviours (JSON/XML…) | Strategy via interface implementations          |
| Hard to refactor safely                 | Pure functions + literal assertions             |

---

## 10. Running Everything

**POM dependencies** (test scope):

```xml
<dependency>
  <groupId>org.junit.jupiter</groupId>
  <artifactId>junit-jupiter</artifactId>
  <version>5.13.2</version>
  <scope>test</scope>
</dependency>
<dependency>
  <groupId>org.mockito</groupId>
  <artifactId>mockito-core</artifactId>
  <version>5.11.0</version>
  <scope>test</scope>
</dependency>
<dependency>
  <groupId>org.assertj</groupId>
  <artifactId>assertj-core</artifactId>
  <version>3.25.3</version>
  <scope>test</scope>
</dependency>
```

Run tests:

```bash
mvn clean test
```

Manual demo (CLI):

```bash
mvn compile exec:java -Dexec.mainClass=io.github.josemanuel.App
```

---

## 11. Next Steps

1. Add ISBN‑13 with a Strategy (start with a failing test).
2. Introduce Pitest mutation testing to prove assertions are meaningful.
3. Freeze randomness (`RandomService` interface) similar to `Clock`.
4. GitHub Actions CI: run `mvn clean verify` on every PR.
5. Expose REST endpoints (Spring Boot) and add contract tests (Spring Cloud Contract).

---

### Key Take‑Away

One small interface, five lightweight doubles, and disciplined constructor injection create a **complete testing laboratory**: verify outputs, confirm interactions, and refactor fearlessly—without slow infrastructure or flaky asserts.
This is **90 % of professional TDD** distilled into one concise, extensible project.