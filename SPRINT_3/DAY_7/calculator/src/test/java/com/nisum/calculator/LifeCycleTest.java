package com.nisum.calculator;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LifeCycleTest {

    @BeforeAll
    void beforeAll() {
        System.out.println("@BeforeAll - Executed once before all tests");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("@BeforeEach - Executed before each test");
    }

    @Test
    void testOne() {
        System.out.println("@Test - testOne executed");
    }

    @Test
    void testTwo() {
        System.out.println("@Test - testTwo executed");
    }

    @AfterEach
    void afterEach() {
        System.out.println("@AfterEach - Executed after each test");
    }

    @AfterAll
    void afterAll() {
        System.out.println("@AfterAll - Executed once after all tests");
    }
}

