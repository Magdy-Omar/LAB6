package com.lab.testing;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple shopping cart class.
 * This class contains intentional bugs — your job is to find them through testing!
 */
public class ShoppingCart {

    private List<String> items;
    private List<Double> prices;

    public ShoppingCart() {
        this.items = new ArrayList<>();
        this.prices = new ArrayList<>();
    }

    /**
     * Adds an item with a given price to the cart.
     * Should throw IllegalArgumentException if price <= 0.
     *
     * BUG #5: Does NOT validate the price — accepts zero and negative values.
     */
    public void addItem(String name, double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero");
        }
        items.add(name);
        prices.add(price);
    }

    /**
     * Removes an item from the cart by name.
     * Should remove both the item name AND its price from their lists.
     *
     * BUG #6: Removes from 'items' list but does NOT remove from 'prices' list.
     */
    public void removeItem(String name) {
        int index = items.indexOf(name);
        if (index >= 0) {
            items.remove(index);
            prices.remove(index);
        }
    }

    /**
     * Returns the total price after applying a discount percentage.
     * Formula: total * (1 - discountPercent / 100.0)
     *
     * BUG #7: Uses integer division — discountPercent / 100 is always 0 for values < 100.
     */
    public double getTotalWithDiscount(int discountPercent) {
        double total = getTotal();
        return total * (1 - discountPercent / 100.0);
    }

    /**
     * Returns the total price of all items in the cart.
     */
    public double getTotal() {
        double total = 0.0;
        for (double price : prices) {
            total += price;
        }
        return total;
    }

    /**
     * Returns the number of items in the cart.
     */
    public int getItemCount() {
        return items.size();
    }

    /**
     * Returns true if the cart has no items.
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }
}
