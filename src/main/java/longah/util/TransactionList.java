package longah.util;

import java.util.ArrayList;

import longah.handler.UI;
import longah.node.Group;
import longah.node.Member;
import longah.node.Transaction;
import longah.exception.LongAhException;
import longah.exception.ExceptionMessage;

/**
 * Represents a list of transactions.
 */
public class TransactionList {
    private ArrayList<Transaction> transactions = new ArrayList<>();

    /**
     * Adds a transaction to the list.
     *
     * @param transaction The transaction to add.
     */
    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    /**
     * Adds a transaction to the list with the specified expression and member list.
     *
     * @param expression The expression of the transaction to add.
     * @param memberList The member list of the transaction to add.
     * @throws LongAhException If the expression is invalid.
     */
    public void addTransaction(String expression, MemberList memberList)
             throws LongAhException {
        Transaction toAddTransaction = new Transaction(expression, memberList);
        this.transactions.add(toAddTransaction);
        UI.showMessage("Transaction added successfully!");
        UI.showMessage(toAddTransaction.toString());
    }

    /**
     * Adds a transaction to the list with the specified expression and member list.
     *
     * @param expression The expression of the transaction to add.
     * @param memberList The member list of the transaction to add.
     * @param group The group of the transaction to add.
     * @throws LongAhException If the expression is invalid.
     */
    public void addTransaction(String expression, MemberList memberList, Group group)
            throws LongAhException {
        Transaction toAddTransaction = new Transaction(expression, memberList);
        this.transactions.add(toAddTransaction);
        try {
            group.updateTransactionSolution();
        } catch (LongAhException e) {
            this.transactions.remove(toAddTransaction);
            throw e;
        }
        UI.showMessage("Transaction added successfully!");
        UI.showMessage(toAddTransaction.toString());
    }

    /**
     * Returns the size of the transaction list.
     *
     * @return The size of the transaction list.
     */
    public int getTransactionListSize() {
        return this.transactions.size();
    }

    /**
     * Removes a transaction from the list by index.
     *
     * @param indexString The index of the transaction to remove.
     * @throws LongAhException If the index is invalid.
     */
    public void remove(String indexString) throws LongAhException {
        int index = Integer.parseInt(indexString) - 1;
        if (index < 0 || index >= this.transactions.size()) {
            throw new LongAhException(ExceptionMessage.INVALID_INDEX);
        }
        Transaction removedTransaction = this.transactions.remove(index);
        UI.showMessage("Transaction #" + indexString + " removed successfully.");
        UI.showMessage(removedTransaction.toString());
    }

    /**
     * Clears all transactions from the list.
     * @param memberList The member list to clear balances from.
     */
    public void clear(MemberList memberList) {
        this.transactions.clear();
        memberList.clearBalances();
        UI.showMessage("All transaction records have been cleared.");
    }

    /**
     * Gets the list of transactions.
     *
     * @return The list of transactions.
     */
    public ArrayList<Transaction> getTransactions() {
        return this.transactions;
    }

    /**
     * Returns a String printout the list of transactions stored in the system.
     */
    public String listTransactions() throws LongAhException {
        int transactionListSize = getTransactionListSize();
        if (transactionListSize == 0) {
            throw new LongAhException(ExceptionMessage.NO_TRANSACTION_FOUND);
        }
        int index = 1;
        String outString = "";
        for (Transaction transaction : this.transactions) {
            outString = outString + String.format("%d.\n%s", index, transaction) + "\n";
            index++;
        }
        return outString.trim();
    }

    /**
     * Printout the list of transactions which the member name is involved as the
     * transaction lender
     *
     * @param lenderName User input containing the name of person to search for
     * @param members The member list to search for the name in
     * @return Returns a String printout of the required list of transactions
     */
    public String findLender(String lenderName, MemberList members) throws LongAhException {
        if (!members.isMember(lenderName)) {
            throw new LongAhException(ExceptionMessage.MEMBER_NOT_FOUND);
        }
        int index = 1;
        int printCount = 0;
        String outString = String.format("%s is a lender in the following list of transaction(s).", lenderName) + "\n";
        for (Transaction transaction : this.transactions) {
            if (transaction.isLender(lenderName)) {
                outString = outString + String.format("%d.\n%s", index, transaction) + "\n";
                printCount++;
            }
            index++;
        }
        if (printCount == 0) {
            throw new LongAhException(ExceptionMessage.TRANSACTIONS_SUMMED_UP);
        }
        return outString.trim();
    }

    /**
     * Printout the list of transactions which the member name is involved as the
     * transaction lender
     *
     * @param borrowerName User input containing the name of person to search for
     * @param members The member list to search for the name in
     * @return Returns a String printout of the required list of transactions
     */
    public String findBorrower(String borrowerName, MemberList members) throws LongAhException {
        if (!members.isMember(borrowerName)) {
            throw new LongAhException(ExceptionMessage.MEMBER_NOT_FOUND);
        }
        int index = 1;
        int printCount = 0;
        String outString =
                String.format("%s is a borrower in the following list of transaction(s).", borrowerName) + "\n";
        for (Transaction transaction : this.transactions) {
            if (transaction.checkIsBorrower(borrowerName)) {
                outString = outString + String.format("%d.\n%s", index, transaction) + "\n";
                printCount++;
            }
            index++;
        }
        if (printCount == 0) {
            throw new LongAhException(ExceptionMessage.TRANSACTIONS_SUMMED_UP);
        }
        return outString.trim();
    }

    /**
     * Printout the list of transactions which the member name is involved as the
     * transaction lender
     *
     * @param name User input containing the name of person to search for
     * @param members The member list to search for the name in
     * @return Returns a String printout of the required list of transactions
     */
    public String findTransactions(String name, MemberList members) throws LongAhException {
        if (!members.isMember(name)) {
            throw new LongAhException(ExceptionMessage.MEMBER_NOT_FOUND);
        }
        int index = 1;
        int printCount = 0;
        String outString = String.format("%s is a part of the following list of transaction(s).", name) + "\n";
        for (Transaction transaction : this.transactions) {
            if (transaction.isInvolved(name)) {
                outString = outString + String.format("%d.\n%s", index, transaction) + "\n";
                printCount++;
            }
            index++;
        }
        if (printCount == 0) {
            throw new LongAhException(ExceptionMessage.TRANSACTIONS_SUMMED_UP);
        }
        return outString.trim();
    }

    //@@author FeathersRe
    /**
     * Filters and return the list of transactions matching the input transaction time
     *
     * @param dateTime String expression of the date time to filter
     * @return String representation of the list of transactions matching the input transaction time
     * @throws LongAhException If there are no matching transactions
     */
    public String filterTransactionsEqualToDateTime(String dateTime) throws LongAhException {
        DateTime dateTimeToCompare = new DateTime(dateTime);
        int index = 0;
        int printCount = 0;
        String outString = "The following list of transactions matches with the time " + dateTimeToCompare + ".\n";
        for (Transaction transaction : this.transactions) {
            try {
                index++;
                if (transaction.getTransactionTime().isEqual(dateTimeToCompare)) {
                    outString = outString + String.format("%d.\n%s", index, transaction) + "\n";
                    printCount++;
                }
            } catch (NullPointerException e) {
                // Skip the transaction if the transaction time is not set
                continue;
            }
        }
        if (printCount == 0) {
            throw new LongAhException(ExceptionMessage.NO_TRANSACTION_FOUND);
        }
        return outString.trim();
    }

    /**
     * Filters and return the list of transactions before the input transaction time
     *
     * @param dateTime String expression of the date time to filter
     * @return String representation of the list of transactions before the input transaction time
     * @throws LongAhException If there are no matching transactions
     */
    public String filterTransactionsBeforeDateTime(String dateTime) throws LongAhException {
        DateTime dateTimeToCompare = new DateTime(dateTime);
        int index = 0;
        int printCount = 0;
        String outString = "The following list of transactions is before the time " + dateTimeToCompare + ".\n";
        for (Transaction transaction : this.transactions) {
            try {
                index++;
                if (transaction.getTransactionTime().isBefore(dateTimeToCompare)) {
                    outString = outString + String.format("%d.\n%s", index, transaction) + "\n";
                    printCount++;
                }
            } catch (NullPointerException e) {
                // Skip the transaction if the transaction time is not set
                continue;
            }
        }
        if (printCount == 0) {
            throw new LongAhException(ExceptionMessage.NO_TRANSACTION_FOUND);
        }
        return outString.trim();
    }

    /**
     * Filters and return the list of transactions after the input transaction time
     *
     * @param dateTime String expression of the date time to filter
     * @return String representation of the list of transactions before the input transaction time
     * @throws LongAhException If there are no matching transactions
     */
    public String filterTransactionsAfterDateTime(String dateTime) throws LongAhException {
        DateTime dateTimeToCompare = new DateTime(dateTime);
        int index = 0;
        int printCount = 0;
        String outString = "The following list of transactions is after the time " + dateTimeToCompare + ".\n";
        for (Transaction transaction : this.transactions) {
            try {
                index++;
                if (transaction.getTransactionTime().isAfter(dateTimeToCompare)) {
                    outString = outString + String.format("%d.\n%s", index, transaction) + "\n";
                    printCount++;
                }
            } catch (NullPointerException e) {
                // Skip the transaction if the transaction time is not set
                continue;
            }
        }
        if (printCount == 0) {
            throw new LongAhException(ExceptionMessage.NO_TRANSACTION_FOUND);
        }
        return outString.trim();
    }

    /**
     * Filters and return the list of transactions between two date times
     *
     * @param fromDateTime String expression of the earlier bound of the date time period to filter
     * @param toDateTime String expression of the later bound of the date time to filter
     * @return String representation of the list of transactions before the input transaction time
     * @throws LongAhException If the date time filter is invalid or there is no transaction found
     */
    public String filterTransactionsBetweenDateTime(String fromDateTime, String toDateTime) throws LongAhException {
        DateTime fromDateTimeToCompare = new DateTime(fromDateTime);
        DateTime toDateTimeToCompare = new DateTime(toDateTime);
        if (toDateTimeToCompare.isBefore(fromDateTimeToCompare)) {
            throw new LongAhException(ExceptionMessage.INVALID_DATE_TIME_FILTER);
        }
        int index = 0;
        int printCount = 0;
        String outString = "The following list of transactions is between the time " + fromDateTimeToCompare +
                " and " + toDateTimeToCompare + ".\n";
        for (Transaction transaction : this.transactions) {
            try {
                index++;
                if (transaction.getTransactionTime().isAfter(fromDateTimeToCompare) &&
                        transaction.getTransactionTime().isBefore(toDateTimeToCompare)) {
                    outString = outString + String.format("%d.\n%s", index, transaction) + "\n";
                    printCount++;
                }
            } catch (NullPointerException e) {
                // Skip the transaction if the transaction time is not set
                continue;
            }
        }
        if (printCount == 0) {
            throw new LongAhException(ExceptionMessage.NO_TRANSACTION_FOUND);
        }
        return outString.trim();
    }
    //@@author

    /**
     * Edits a transaction from the list by index with new expression.
     * 
     * @param expression The new expression to edit the transaction with.
     * @param memberList The member list to edit the transaction with.
     * @throws LongAhException If the index is invalid or if the edit input is in an invalid format.
     */
    public void editTransactionList(String expression, MemberList memberList) throws LongAhException {
        String[] indexTransactionSplice = expression.split(" ", 2);
        if (indexTransactionSplice.length != 2) {
            throw new LongAhException(ExceptionMessage.INVALID_EDIT_COMMAND);
        }

        try {
            int index = Integer.parseInt(indexTransactionSplice[0]) - 1;
            if (index < 0 || index >= transactions.size()) {
                throw new LongAhException(ExceptionMessage.INVALID_INDEX);
            }
            transactions.get(index).editTransaction(indexTransactionSplice[1], memberList);
            UI.showMessage("Transaction #" + (index + 1) + " edited successfully.");
            UI.showMessage(transactions.get(index).toString());
        } catch (NumberFormatException e) {
            throw new LongAhException(ExceptionMessage.INVALID_INDEX);
        }
    }

    /**
     * Printout the list of transactions which a person is involved as a borrower
     *
     * @param borrowerName containing the String representation of the name of person to search for
     * @return Returns a String printout of the required list of transactions
     */
    public String findDebts(String borrowerName) throws LongAhException {
        String outString = String.format("%s is involved as the payee in the following list of transactions."
                , borrowerName) + "\n";
        int index = 1;
        int printCount = 0;
        for (Transaction transaction : this.transactions) {
            if (transaction.checkIsBorrower(borrowerName)) {
                outString = outString + String.format("%d.\n%s", index, transaction) + "\n";
                printCount++;
            }
            index++;
        }
        if (printCount == 0) {
            throw new LongAhException(ExceptionMessage.TRANSACTIONS_SUMMED_UP);
        }
        return outString.trim();
    }

    /**
     * Deletes a member from all transactions in the list.
     * 
     * @param name The name of the member to delete.
     * @param members The list of members to delete from.
     * @throws LongAhException If the member is not found in the list.
     */
    public void deleteMember(String name, MemberList members) throws LongAhException {
        Member member = members.getMember(name);
        int size = transactions.size();
        for (int i = 0; i < size; i++) {
            boolean isDiscard = transactions.get(i).deleteMember(member);
            if (isDiscard) {
                transactions.remove(i);
                size--;
                i--;
            }
        }
    }
}
