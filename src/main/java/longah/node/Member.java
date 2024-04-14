package longah.node;

import java.util.regex.Pattern;

import longah.exception.LongAhException;
import longah.exception.ExceptionMessage;

/**
 * Represents a member in the LongAh application.
 */
public class Member {
    private static final int MAX_NAME_LENGTH = 50;
    
    private String name;
    private double balance;

    /**
     * Constructs a new Member instance with the given name and zero balance.
     *
     * @param name The name of the member.
     * @throws LongAhException If the name is invalid.
     */
    public Member(String name) throws LongAhException {
        checkNameValidity(name);
        this.name = name;
        this.balance = 0.0;
    }

    /**
     * Constructs a new Member instance with the given name and balance.
     * Used for storage methods.
     *
     * @param name The name of the member.
     * @param balance The balance of the member.
     * @throws LongAhException If the name is invalid.
     */
    public Member(String name, double balance) throws LongAhException {
        checkNameValidity(name);
        this.name = name;
        this.balance = balance;
    }

    /**
     * Checks if the name is valid.
     * 
     * @param name The name to check.
     * @throws LongAhException If the name is not alphanumeric.
     */
    public void checkNameValidity(String name) throws LongAhException {
        // Check if name is fully alphanumeric
        if (!Pattern.matches("[A-Za-z0-9]+", name)) {
            throw new LongAhException(ExceptionMessage.INVALID_MEMBER_NAME);
        }
        // Check if name exceeds character limit
        if (name.length() > MAX_NAME_LENGTH) {
            throw new LongAhException(ExceptionMessage.CHAR_LIMIT_EXCEEDED);
        }
    }

    /**
     * Sets the name of the member.
     * 
     * @param name The name of the member.
     * @throws LongAhException If the name is invalid.
     */
    public void setName(String name) throws LongAhException {
        checkNameValidity(name);
        this.name = name;
    }

    /**
     * Adds the specified amount to the member's balance.
     *
     * @param amount The amount to add to the balance.
     */
    public synchronized void addToBalance(double amount) throws LongAhException {
        if (amount <= 0) {
            throw new LongAhException(ExceptionMessage.INVALID_TRANSACTION_VALUE);
        }
        if (this.balance + amount == Double.POSITIVE_INFINITY) {
            throw new LongAhException(ExceptionMessage.BALANCE_OVERFLOW);
        }
        this.balance += amount;
    }

    /**
     * Subtracts the specified amount from the member's balance.
     *
     * @param amount The amount to subtract from the balance.
     */
    public synchronized void subtractFromBalance(double amount) throws LongAhException {
        if (amount <= 0) {
            throw new LongAhException(ExceptionMessage.INVALID_TRANSACTION_VALUE);
        }
        if (this.balance - amount == Double.NEGATIVE_INFINITY) {
            throw new LongAhException(ExceptionMessage.BALANCE_OVERFLOW);
        }
        this.balance -= amount;
    }

    /**
     * Gets the current balance of the member.
     *
     * @return The balance of the member.
     */
    public double getBalance() {
        return this.balance;
    }

    /**
     * Returns a string representation of the member, including name and balance.
     *
     * @return A string representation of the member.
     */
    @Override
    public String toString() {
        double rounded = (double)Math.round(this.balance * 100) / 100;
        String roundedString = String.format("%.2f", rounded);

        if (this.balance >= 0) {
            return this.name + ": $" + roundedString;
        }
        // Remove the negative sign
        roundedString = roundedString.substring(1);
        return (this.name + ": -$" + roundedString).trim();
    }

    /**
     * Returns a string representation of the member for storage.
     * 
     * @param delimiter The delimiter to separate the name and balance.
     * @return A string representation of the member for storage.
     */
    public String toStorageString(String delimiter) {
        double rounded = (double)Math.round(this.balance * 100) / 100;
        String roundedString = String.format("%.2f", rounded);
        return this.name + delimiter + roundedString;
    }

    /**
     * Gets the name of the member.
     *
     * @return The name of the member.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Used to check whether the input String matches the name of a member.
     * 
     * @param memberName String representation of a member name
     * @return A boolean value checking whether the input matches with name.
     */
    public boolean isName(String memberName) {
        return name.equals(memberName);
    }

    /**
     * Clears the balance of the member.
     */
    public void clearBalance() {
        this.balance = 0;
    }
}
