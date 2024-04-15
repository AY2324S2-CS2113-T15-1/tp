package longah.node;

import longah.util.MemberList;
import longah.util.TransactionList;
import longah.exception.LongAhException;
import longah.handler.StorageHandler;
import longah.exception.ExceptionMessage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;


public class TransactionTest {
    /**
     * Tests the successful creation of a transaction with balances correctly updated.
     */
    @Test
    public void transactionConstructor_transaction_success() {
        try {
            Group group = new Group("testGroup");
            MemberList memberList = group.getMemberList();
            TransactionList transactionList = group.getTransactionList();
            memberList.addMember("Alice");
            memberList.addMember("Bob");
            Transaction transaction = new Transaction("Alice p/Bob a/5", memberList);
            transactionList.addTransaction(transaction);
            group.updateTransactionSolution();
            Member lender = transaction.getLender();
            assertEquals("Alice", lender.getName());
            assertEquals(5.0, lender.getBalance());
            Member borrower = memberList.getMember("Bob");
            assertEquals(-5.0, borrower.getBalance());
            StorageHandler.deleteDir(new File("./data/testGroup"));
        } catch (LongAhException e) {
            fail();
        }
    }

    /**
     * Tests the unsuccessful creation of a transaction with < 2 persons involved.
     */
    @Test
    public void transactionConstructor_invalidFormat_exceptionThrown() {
        try {
            MemberList memberList = new MemberList();
            memberList.addMember("Alice");
            memberList.addMember("Bob");
            new Transaction("Alice a/5", memberList);
            fail();
        } catch (LongAhException e) {
            String expectedString = ExceptionMessage.INVALID_TRANSACTION_FORMAT.getMessage();
            assertEquals(expectedString, e.getMessage());
        }
    }

    /**
     * Tests the unsuccessful creation of a transaction due to overflow
     */
    @Test
    public void transactionConstructor_overflowAmount_exceptionThrown() {
        try {
            Group group = new Group("testGroup");
            MemberList memberList = group.getMemberList();
            TransactionList transactionList = group.getTransactionList();
            memberList.addMember("Alice");
            memberList.addMember("Bob");
            transactionList.addTransaction("Alice p/Bob a/1e309", memberList, group);
            fail();
        } catch (LongAhException e) {
            String expectedString = ExceptionMessage.BALANCE_OVERFLOW.getMessage();
            assertEquals(expectedString, e.getMessage());
            StorageHandler.deleteDir(new File("./data/testGroup"));
        }
    }

    /**
     * Tests the unsuccessful creation of a transaction with invalid arguments.
     */
    @Test
    public void transactionConstructor_invalidArguments_exceptionThrown() {
        try {
            MemberList memberList = new MemberList();
            memberList.addMember("Alice");
            memberList.addMember("Bob");
            new Transaction("Alice p/Bob a/c", memberList);
            fail();
        } catch (LongAhException e) {
            String expectedString = ExceptionMessage.INVALID_TRANSACTION_VALUE.getMessage();
            assertEquals(expectedString, e.getMessage());
        }
    }

    /**
     * Tests the unsuccessful creation of a transaction with an incorrect decimal format.
     */
    @Test
    public void transactionConstructor_incorrectDecimalFormat_exceptionThrown() {
        try {
            Group group = new Group("testGroup");
            MemberList memberList = group.getMemberList();
            TransactionList transactionList = group.getTransactionList();
            memberList.addMember("Alice");
            memberList.addMember("Bob");
            transactionList.addTransaction("Alice p/Bob a/1.1.1", memberList, group);
            fail();
        } catch (LongAhException e) {
            String expectedString = ExceptionMessage.INVALID_TRANSACTION_VALUE.getMessage();
            assertEquals(expectedString, e.getMessage());
            StorageHandler.deleteDir(new File("./data/testGroup"));
        }
    }

    /**
     * Tests the unsuccessful creation of a transaction with an incorrect decimal point count.
     */
    @Test
    public void transactionConstructor_incorrectDecimalPoint_exceptionThrown() {
        try {
            Group group = new Group("testGroup");
            MemberList memberList = group.getMemberList();
            TransactionList transactionList = group.getTransactionList();
            memberList.addMember("Alice");
            memberList.addMember("Bob");
            transactionList.addTransaction("Alice p/Bob a/1.1111", memberList, group);
            fail();
        } catch (LongAhException e) {
            String expectedString = ExceptionMessage.INVALID_TRANSACTION_VALUE.getMessage();
            assertEquals(expectedString, e.getMessage());
            StorageHandler.deleteDir(new File("./data/testGroup"));
        }
    }

    /**
     * Tests the unsuccessful creation of a transaction with invalid Date Time format.
     */
    @Test
    public void addTransactionTime_invalidTimeFormat_exceptionThrown() {
        try {
            MemberList memberList = new MemberList();
            memberList.addMember("Jack");
            memberList.addMember("Jane");
            new Transaction("Jack t/2359 p/Jane a/200", memberList);
            fail();
        } catch (LongAhException e) {
            String expected = ExceptionMessage.INVALID_TIME_FORMAT.getMessage();
            assertEquals(expected, e.getMessage());
        }
    }

    /**
     * Tests the unsuccessful creation of a transaction with an invalid command to denote amount.
     */
    @Test
    public void addBorrower_invalidAmountCommand_exceptionThrown() {
        try {
            MemberList memberList = new MemberList();
            memberList.addMember("Alice");
            memberList.addMember("Bob");
            Member lender = memberList.getMember("Alice");
            Transaction transaction = new Transaction("Alice p/Bob a/5", memberList);
            transaction.addBorrower("Bob b/5", memberList, lender);
            fail();
        } catch (LongAhException e) {
            String expectedString = ExceptionMessage.INVALID_TRANSACTION_FORMAT.getMessage();
            assertEquals(expectedString, e.getMessage());
        }
    }

    /**
     * Tests the unsuccessful creation of a transaction with an amount value specified in words
     * rather than a double value.
     */
    @Test
    public void addBorrower_invalidAmountValue_exceptionThrown() {
        try {
            MemberList memberList = new MemberList();
            memberList.addMember("Alice");
            memberList.addMember("Bob");
            Member lender = memberList.getMember("Alice");
            Transaction transaction = new Transaction("Alice p/Bob a/5", memberList);
            transaction.addBorrower("Bob a/five", memberList, lender);
            fail();
        } catch (LongAhException e) {
            String expectedString = ExceptionMessage.INVALID_TRANSACTION_VALUE.getMessage();
            assertEquals(expectedString, e.getMessage());
        }
    }

    /**
     * Tests the unsuccessful creation of a transaction with a negative amount
     * value for person that owes.
     */
    @Test
    public void addBorrower_negativeAmountValue_exceptionThrown() {
        try {
            MemberList memberList = new MemberList();
            memberList.addMember("Alice");
            memberList.addMember("Bob");
            Member lender = memberList.getMember("Alice");
            Transaction transaction = new Transaction("Alice p/Bob a/5", memberList);
            transaction.addBorrower("Bob a/-5", memberList, lender);
            fail();
        } catch (LongAhException e) {
            String expectedString = ExceptionMessage.INVALID_TRANSACTION_VALUE.getMessage();
            assertEquals(expectedString, e.getMessage());
        }
    }

    /**
     * Tests the successful checking of whether a person is involved in a transaction.
     */
    @Test
    public void isInvolved_validInput_success() {
        try {
            MemberList memberList = new MemberList();
            memberList.addMember("Alice");
            memberList.addMember("Bob");
            Transaction transaction = new Transaction("Alice p/Bob a/5", memberList);
            assertEquals(true, transaction.isInvolved("Alice"));
            assertEquals(true, transaction.isInvolved("Bob"));
            assertEquals(false, transaction.isInvolved("Charlie"));
        } catch (LongAhException e) {
            fail();
        }
    }
}
