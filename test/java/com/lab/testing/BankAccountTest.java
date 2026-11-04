package com.lab.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Write your tests for BankAccount here.
 *
 * Requirements:
 *  - At least 5 test methods
 *  - Use @BeforeEach to create a fresh BankAccount before each test
 *  - Cover: deposit, withdraw, transfer, getSummary
 *  - Test both normal behavior AND invalid input (use assertThrows)
 *
 * Tip: if a test FAILS, that means you found a bug — that's the goal!
 */
class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    void setUp() {
        account = new BankAccount("Alice");
    }

    // --- Write your test methods below ---

    @Test
    @DisplayName("initial balance should be zero")
    void testInitialBalance() {
        // Arrange is handled by @BeforeEach

        // Act
        double initialBalance = account.getBalance();
        String owner = account.getOwner();

        // Assert
        assertNotNull(owner);
        assertEquals("Alice", owner);
        assertEquals(0.0, initialBalance, 0.001);
    }

    @Test
    @DisplayName("deposit should increase the balance")
    void testDeposit() {
        // Arrange
        double depositAmount = 100.0;

        // Act
        account.deposit(depositAmount);

        // Assert
        assertEquals(100.0, account.getBalance(), 0.001);
    }

    @Test
    @DisplayName("deposit of zero or negative should throw IllegalArgumentException")
    void testDepositInvalidAmount() {
        // Arrange - test values for zero and negative deposits
        double zeroAmount = 0.0;
        double negativeAmount = -50.0;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> account.deposit(zeroAmount));
        assertThrows(IllegalArgumentException.class, () -> account.deposit(negativeAmount));
    }

    @Test
    @DisplayName("withdraw should reduce the balance")
    void testWithdraw() {
        // Arrange
        account.deposit(100.0);

        // Act
        account.withdraw(40.0);

        // Assert
        assertEquals(60.0, account.getBalance(), 0.001);
    }

    @Test
    @DisplayName("withdrawing more than balance should throw IllegalArgumentException")
    void testOverdraft() {
        // Arrange
        account.deposit(50.0);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(100.0));
    }

    @Test
    @DisplayName("transfer should move money from one account to another")
    void testTransfer() {
        // Arrange
        account.deposit(100.0);
        BankAccount bob = new BankAccount("Bob");

        // Act
        account.transfer(bob, 40.0);

        // Assert
        assertAll("Transfer updates both accounts",
            () -> assertEquals(60.0, account.getBalance(), 0.001),
            () -> assertEquals(40.0, bob.getBalance(), 0.001)
        );
    }

    @Test
    @DisplayName("getSummary should return 'Owner: X, Balance: Y'")
    void testGetSummary() {
        // Arrange
        account.deposit(100.0);

        // Act
        String summary = account.getSummary();

        // Assert
        assertEquals("Owner: Alice, Balance: 100.0", summary);
        assertTrue(summary.startsWith("Owner:"));
        assertFalse(summary.startsWith("Balance:"));
    }
}
