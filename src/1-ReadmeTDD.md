# Test-Driven Development (TDD) in Java

This project demonstrates the use of **Test-Driven Development (TDD)** in Java using **JUnit 5**. TDD is a development methodology where you write tests **before** writing the actual implementation. This approach helps ensure your code behaves as expected and is easy to maintain and refactor.

---

## What is TDD?

**TDD (Test-Driven Development)** is a cycle based on three simple steps:

1. **Write a failing test** based on a specific example or behaviour.
2. **Write the minimum code** needed to make the test pass.
3. **Refactor the code** while keeping all tests green.

This is known as the **Red → Green → Refactor** cycle.

---

## Core idea of TDD

The **main goal of TDD is not to test code — it's to drive design** by thinking in terms of **examples and outcomes**:

* You start by asking: *“What should this function do for a given input?”*
* You **don’t focus on how to implement it** yet.
* You explore **expected behaviour** first — the implementation comes second.

Each test should validate **one single piece of logic** — a specific behaviour, case, or constraint. This keeps tests focused, meaningful, and easier to debug when they fail. If a test fails, you immediately know **what part of the logic is broken**.

By thinking through examples and edge cases up front, you design **better, more predictable APIs and logic**.

---

## Why use TDD?

* Helps catch bugs early
* Encourages simple, modular design
* Builds confidence when refactoring
* Leads to high-quality tests by default
* Forces you to think about requirements, not implementation

---

## Project structure

This is a standard Maven project:

* `src/main/java`: production code
* `src/test/java`: test code

Example:

```
src/
├── main/
│   └── java/
│       └── io/github/josemanuel/
│           └── Main.java
└── test/
    └── java/
        └── io/github/josemanuel/
            └── ValidateISBNTest.java
```

---

## Tools used

* Java 17
* JUnit 5 (Jupiter)
* Maven

---

## How to run the tests

In IntelliJ:

* Right-click the test class → **Run**

From the terminal:

```
mvn test
```

---

## Sample test (starting point)

```java
@Test
public void shouldReturnTrueForValidISBN() {
    ValidateISBN validator = new ValidateISBN();
    boolean result = validator.isValid("9780134685991");
    assertTrue(result);
}
```

This test expresses *expected behaviour*. Once it fails (as it should at first), you write only the code needed to make it pass.
