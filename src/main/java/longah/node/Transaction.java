package longah.node;

import java.math.BigDecimal;
import java.util.ArrayList;

import longah.util.DateTime;
import longah.util.MemberList;
import longah.exception.LongAhException;
import longah.exception.ExceptionMessage;
import longah.util.Subtransaction;

/**
 * Represents a transaction between two members.
 */
public class Transaction {
    private Member lender;
    private DateTime transactionTime = null;
    private ArrayList<Subtransaction> subtransactions = new ArrayList<>();

    /**
     * Constructs a new Transaction instance with the given user input and member list.
     * 
     * @param userInput The user input for the transaction.
     * @param memberList The list of members in the group.
     * @throws LongAhException If the user input is in an invalid format or value.
     */
    public Transaction(String userInput, MemberList memberList) throws LongAhException {
        parseTransaction(userInput, memberList);
    }

    /**
     * Constructs a new Transaction instance with the given lender, subtransactions and member list.
     * Used for storage methods only.
     * 
     * @param lender The member who lent the money in the transaction.
     * @param subtransactions The list of subtransactions in the transaction.
     * @param members The list of members in the group.
     * @throws LongAhException If the lender does not exist in the group.
     */
    public Transaction(Member lender, ArrayList<Subtransaction> subtransactions,
            MemberList members) throws LongAhException {
        parseTransaction(lender, subtransactions, members);
    }

    /**
     * Constructs a new Transaction instance with the given lender, subtransactions, member list, and transaction time.
     * Used for storage methods and for transaction records with time only.
     *
     * @param lender The member who lent the money in the transaction.
     * @param subtransactions The list of subtransactions in the transaction.
     * @param members The list of members in the group.
     * @throws LongAhException If the lender does not exist in the group.
     */
    public Transaction(Member lender, ArrayList<Subtransaction> subtransactions,
                       MemberList members, String transactionTime) throws LongAhException {
        parseTransaction(lender, subtransactions, members);
        try {
            this.transactionTime = new DateTime(transactionTime);
        } catch (LongAhException e) {
            throw new LongAhException(ExceptionMessage.INVALID_STORAGE_CONTENT);
        }
    }

    /**
     * Parses the user input to create a transaction.
     *
     * @param expression The user input for the transaction.
     * @param members The list of members in the group.
     * @throws LongAhException If the user input is in an invalid format or value.
     */
    public void parseTransaction(String expression, MemberList members) throws LongAhException {
        // User input format: [Lender] t/[transactionTime(opt)] p/[Borrower1] a/[amount1] p/[Borrower2] a/[amount2] ...

        String[] splitInput = expression.split("p/");
        if (splitInput.length < 2 || splitInput[0].isEmpty() || splitInput[1].contains(("t/"))) {
            // Minimum of 2 people as part of a transaction
            throw new LongAhException(ExceptionMessage.INVALID_TRANSACTION_FORMAT);
        }
        assert splitInput.length >= 2 : "Invalid transaction.";
        String lenderName;

        if (splitInput[0].contains("t/")) { 
            // Check presence of time component in expression
            String[] splitLenderTime = splitInput[0].split("t/", 2);
            lenderName = splitLenderTime[0].trim();
            this.transactionTime = new DateTime(splitLenderTime[1]);
        } else {
            lenderName = splitInput[0].trim();
        }
        this.lender = members.getMember(lenderName);

        // Check for existence of all parties involved in the transaction in the group.
        String borrowNameAmount;
        for (int i = 1; i < splitInput.length; i++) {
            borrowNameAmount = splitInput[i].trim();
            addBorrower(borrowNameAmount, members, this.lender);
        }
    }

    /**
     * Parses the transaction for storage purposes. Used for reading from storage.
     * 
     * @param lender The member who lent the money in the transaction.
     * @param subtransactions The list of subtransactions in the transaction.
     * @param members The list of members in the group.
     * @throws LongAhException If any of the members do not exist in the group.
     */
    public void parseTransaction(Member lender, ArrayList<Subtransaction> subtransactions, 
            MemberList members) throws LongAhException {
        // Exception is thrown if any of the members do not exist in the group
        if (!members.isMember(lender)) {
            throw new LongAhException(ExceptionMessage.INVALID_STORAGE_CONTENT);
        }
        for (Subtransaction subtransaction : subtransactions) {
            if (!members.isMember(subtransaction.getBorrower())) {
                throw new LongAhException(ExceptionMessage.INVALID_STORAGE_CONTENT);
            }
        }
        this.lender = lender;
        this.subtransactions = subtransactions;
    }

    /**
     * Adds a borrower to the subtransaction list.
     * 
     * @param expression The expression containing the borrower and amount borrowed.
     * @param memberList The list of members in the group.
     * @throws LongAhException If the expression is in an invalid format or value.
     */
    public void addBorrower(String expression, MemberList memberList, Member lender)
            throws LongAhException {
        String[] splitBorrower = expression.split("a/");
        if (splitBorrower.length != 2) {
            // Each person owing should have an amount specified
            // Feature may be changed in the future
            throw new LongAhException(ExceptionMessage.INVALID_TRANSACTION_FORMAT);
        }

        String borrowerName = splitBorrower[0].trim();
        // Exception is thrown if the borrower does not exist in the group
        Member borrower = memberList.getMember(borrowerName);
        Double amountBorrowed;

        // Exception is thrown if the borrower is the same as the lender
        if (borrower.equals(lender)) {
            throw new LongAhException(ExceptionMessage.INVALID_TRANSACTION_FORMAT);
        }
        assert !borrower.equals(lender) : "Lender cannot borrow from themselves.";
        
        try {
            amountBorrowed = Double.parseDouble(splitBorrower[1].trim());
        } catch (NumberFormatException e) {
            throw new LongAhException(ExceptionMessage.INVALID_TRANSACTION_VALUE);
        }

        // Exception is thrown if the amount borrowed has more than 2dp
        try {
            if (BigDecimal.valueOf(amountBorrowed).scale() > 2) {
                throw new LongAhException(ExceptionMessage.INVALID_TRANSACTION_VALUE);
            }
        } catch (NumberFormatException e) {
            throw new LongAhException(ExceptionMessage.BALANCE_OVERFLOW);
        }

        // Exception is thrown if the amount borrowed is not positive
        if (amountBorrowed <= 0) {
            throw new LongAhException(ExceptionMessage.INVALID_TRANSACTION_VALUE);
        }
        assert amountBorrowed > 0 : "Amount owed should be positive.";
        Subtransaction subtransaction = new Subtransaction(this.lender, borrower, amountBorrowed);
        this.subtransactions.add(subtransaction);
    }

    /**
     * Gets the member who is the lender in the transaction.
     *
     * @return The member who is the lender in the transaction
     */
    public Member getLender() {
        return this.lender;
    }

    /**
     * Gets the transaction time of the current transaction.
     *
     * @return The DateTime object representing the current transaction time
     */
    public DateTime getTransactionTime() {
        return this.transactionTime;
    }

    /**
     * Checks whether the input member name is the lender of a transaction.
     *
     * @param memberName String representation of member name to check
     * @return a boolean value determining whether the input name is the owner of the transaction
     */
    public boolean checkIsLender(String memberName) {
        return lender.isName(memberName);
    }

    /**
     * Checks whether the input member name is a borrower within the transaction
     *
     * @param memberName String representation of member name to check
     * @return a boolean value determining whether the input name is a borrower in the transaction
     */
    public boolean checkIsBorrower(String memberName) {
        for (Subtransaction subtransaction : this.subtransactions) {
            if (subtransaction.getBorrower().isName(memberName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether the input member name is involved in the transaction.
     *
     * @param memberName String representation of member name to check
     * @return a boolean value determining whether the input name is involved in the transaction
     */
    public boolean isInvolved(String memberName) {
        return checkIsLender(memberName) || checkIsBorrower(memberName);
    }

    /**
     * Returns a string representation of the transaction for printouts.
     * 
     * @return a string representation of the transaction
     */
    @Override
    public String toString() {
        String lender = "Lender: " + this.lender.getName() + "\n";
        String time = "";
        if (this.haveTime()) {
            assert transactionTime != null : "Invalid printouts for transactions without a transaction time";
            time = "Transaction time: " + this.transactionTime + "\n";
        }
        String borrower = "";
        int borrowerNo = 1;
        for (Subtransaction subtransaction : subtransactions) {
            Member member = subtransaction.getBorrower();
            double amount = subtransaction.getAmount();
            borrower += String.format("Borrower %d: %s Owed amount: $%,.2f\n",
                    borrowerNo, member.getName(), amount);
            borrowerNo++;
        }
        return (lender + time + borrower).trim();
    }

    /**
     * Returns a string representation of the transaction for storage.
     * 
     * @param delimiter The delimiter to separate the lender and borrowers.
     * @return a string representation of the transaction for storage
     */
    public String toStorageString(String delimiter) {
        String lender = this.lender.getName();
        String borrower = "";
        String time = "";
        if (this.haveTime()) {
            assert transactionTime != null : "Invalid storage for transactions without a transaction time";
            time = delimiter + transactionTime.toStorageString();
        }
        for (Subtransaction subtransaction : this.subtransactions) {
            String borrowerName = subtransaction.getBorrower().getName();
            double amount = subtransaction.getAmount();
            borrower += delimiter + borrowerName + delimiter + amount;
        }
        return lender + time + borrower;
    }

    /**
     * Returns the list of subtransactions in the transaction.
     * 
     * @return The list of subtransactions in the transaction.
     */
    public ArrayList<Subtransaction> getSubtransactions() {
        return this.subtransactions;
    }

    /**
     * Edits the specified transaction based on user input.
     *
     * @param expression The user input for editing the transaction.
     * @param memberList The list of members in the group.
     * @throws LongAhException If the transaction index is invalid or if the edit input is in an invalid format.
     */
    public void editTransaction(String expression, MemberList memberList) throws LongAhException {
        subtransactions.clear();
        parseTransaction(expression, memberList);
    }

    /**
     * Returns true if the transaction needs to be removed, false otherwise.
     * Deletes a member from the transaction.
     * 
     * @param member The member to be deleted from the transaction.
     * @return True if the transaction needs to be removed, false otherwise.
     */
    public boolean deleteMember(Member member) {
        // Delete transaction if member is lender
        if (lender.equals(member)) {
            return true;
        }

        // Delete subtransaction if member is borrower
        for (int i = 0; i < subtransactions.size(); i++) {
            Subtransaction subtransaction = subtransactions.get(i);
            if (subtransaction.getBorrower().equals(member)) {
                subtransactions.remove(i);
            }
        }

        // Delete transaction if no more subtransactions
        return subtransactions.isEmpty();
    }

    /**
     * Returns true if the transaction has a transaction time
     *
     * @return True if the transaction has a time, false otherwise
     */
    public boolean haveTime() {
        return transactionTime != null;
    }
}
