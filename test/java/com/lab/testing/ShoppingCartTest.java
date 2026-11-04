package com.lab.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Write your tests for ShoppingCart here.
 *
 * Requirements:
 *  - At least 4 test methods
 *  - Use @BeforeEach to create a fresh ShoppingCart before each test
 *  - Cover: addItem, removeItem, getTotal, getTotalWithDiscount
 *  - Test both normal behavior AND invalid input (use assertThrows)
 *
 * Tip: if a test FAILS, that means you found a bug — that's the goal!
 */
class ShoppingCartTest {

    private ShoppingCart cart;

    @BeforeEach
    void setUp() {
        cart = new ShoppingCart();
    }

    // --- Write your test methods below ---

    @Test
    @DisplayName("new cart should be empty")
    void testNewCartIsEmpty() {
        // Arrange is handled by @BeforeEach

        // Act & Assert
        assertNotNull(cart);
        assertTrue(cart.isEmpty());
        assertEquals(0, cart.getItemCount());
        assertEquals(0.0, cart.getTotal(), 0.001);
    }

    @Test
    @DisplayName("adding items should increase item count")
    void testAddItem() {
        // Arrange is handled by @BeforeEach

        // Act
        cart.addItem("Apple", 1.50);
        cart.addItem("Banana", 0.75);

        // Assert
        assertFalse(cart.isEmpty());
        assertEquals(2, cart.getItemCount());
    }

    @Test
    @DisplayName("adding item with zero or negative price should throw")
    void testAddItemInvalidPrice() {
        // Arrange is handled by @BeforeEach

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> cart.addItem("Free item", 0.0));
        assertThrows(IllegalArgumentException.class, () -> cart.addItem("Invalid item", -5.0));
    }

    @Test
    @DisplayName("getTotal should return the sum of all item prices")
    void testGetTotal() {
        // Arrange
        cart.addItem("Book", 20.0);
        cart.addItem("Pen", 5.0);

        // Act
        double total = cart.getTotal();

        // Assert
        assertEquals(25.0, total, 0.001);
    }

    @Test
    @DisplayName("removeItem should decrease the total price")
    void testRemoveItemUpdatesTotal() {
        // Arrange
        cart.addItem("Laptop", 1000.0);
        cart.addItem("Mouse", 50.0);

        // Act
        cart.removeItem("Laptop");

        // Assert
        assertAll("Cart state after removing item",
            () -> assertEquals(1, cart.getItemCount()),
            () -> assertEquals(50.0, cart.getTotal(), 0.001)
        );
    }

    @Test
    @DisplayName("getTotalWithDiscount should apply the discount correctly")
    void testDiscount() {
        // Arrange
        cart.addItem("Headphones", 100.0);

        // Act
        double discounted = cart.getTotalWithDiscount(10);

        // Assert
        assertEquals(90.0, discounted, 0.001);
    }
}
