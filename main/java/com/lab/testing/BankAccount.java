package com.lab.testing;

/**
 * A simple bank account class.
 * This class contains intentional bugs — your job is to find them through testing!
 */
public class BankAccount {

    private String owner;
    private double balance;

    public BankAccount(String owner) {
        this.owner = owner;
        this.balance = 0.0;
    }

    /**
     * Deposits an amount into the account.
     * Should throw IllegalArgumentException if amount <= 0.
     *
     * BUG #1: Does NOT validate the amount — accepts zero and negative values.
     */
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero");
        }
        balance += amount;
    }

    /**
     * Withdraws an amount from the account.
     * Should throw IllegalArgumentException if amount > balance (overdraft).
     *
     * BUG #2: Wrong condition — allows overdraft.
     */
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Cannot withdraw more than balance");
        }
        balance -= amount;
    }

    /**
     * Transfers an amount from this account to the target account.
     * Should reduce this account's balance AND increase target's balance.
     *
     * BUG #3: Only withdraws from this account — never deposits to target.
     */
    public void transfer(BankAccount target, double amount) {
        this.withdraw(amount);
        target.deposit(amount);
    }

    /**
     * Returns a summary string in the format: "Owner: X, Balance: Y"
     *
     * BUG #4: Returns fields in the WRONG order — "Balance: X, Owner: Y"
     */
    public String getSummary() {
        return "Owner: " + owner + ", Balance: " + balance;
    }

    public double getBalance() {
        return balance;
    }

    public String getOwner() {
        return owner;
    }
}
