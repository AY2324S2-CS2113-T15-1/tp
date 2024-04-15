package longah.node;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;

import org.junit.jupiter.api.Test;

import longah.exception.ExceptionMessage;
import longah.handler.StorageHandler;
import longah.util.MemberList;
import longah.util.TransactionList;

public class GroupTest {
    /**
     * Tests the successful file creation when the StorageHandler is constructed.
     */
    @Test
    public void groupConstructor_validName_success() {
        try {
            Group group = new Group("TestGroup1");
            assertEquals("TestGroup1", group.getGroupName());
            File f = new File("./data/TestGroup1");
            StorageHandler.deleteDir(f);
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Tests the exception thrown when an invalid group name is provided.
     */
    @Test
    public void groupConstructor_invalidName_exceptionThrown() {
        try {
            new Group("TestGroup1!");
            fail();
        } catch (Exception e) {
            assertEquals(ExceptionMessage.INVALID_GROUP_NAME.getMessage(), e.getMessage());
        }
    }

    /**
     * Tests the exception thrown when a group name exceeds the character limit.
     */
    @Test
    public void groupConstructor_nameExceedsLimit_exceptionThrown() {
        try {
            new Group("TestGroup1TestGroup1TestGroup1TestGroup1TestGroup1TestGroup1TestGroup1TestGroup1TestGroup1");
            fail();
        } catch (Exception e) {
            assertEquals(ExceptionMessage.CHAR_LIMIT_EXCEEDED.getMessage(), e.getMessage());
        }
    }

    /**
     * Tests the successful execution of settle up
     */
    @Test
    public void settleUp_valid_success() {
        try {
            Group group = new Group("TestGroup1");
            MemberList members = group.getMemberList();
            TransactionList transactions = group.getTransactionList();
            members.addMember("Alice");
            members.addMember("Bob");
            String expression = "Alice p/Bob a/10";
            transactions.addTransaction(expression, members, group);
            group.settleUp("Bob");
            assertEquals("Alice: $0.00\nBob: $0.00", members.listMembers());
            File f = new File("./data/TestGroup1");
            StorageHandler.deleteDir(f);
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Tests successful listing of all debts
     */
    @Test
    public void listDebts_valid_success() {
        try {
            Group group = new Group("TestGroup1");
            MemberList members = group.getMemberList();
            TransactionList transactions = group.getTransactionList();
            members.addMember("Alice");
            members.addMember("Bob");
            String expression = "Alice p/Bob a/10";
            transactions.addTransaction(expression, members, group);
            assertEquals("Best Way to Solve Debts:\nBob owes Alice $10.00", group.listDebts());
            File f = new File("./data/TestGroup1");
            StorageHandler.deleteDir(f);
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Tests the exception thrown when there are no debts to be solved
     */
    @Test
    public void listDebts_noDebts_exceptionThrown() {
        try {
            Group group = new Group("TestGroup1");
            group.listDebts();
            fail();
        } catch (Exception e) {
            assertEquals(ExceptionMessage.TRANSACTIONS_SUMMED_UP.getMessage(), e.getMessage());
        }
    }

    /**
     * Tests the successful execution of listing an individual's debts
     */
    @Test
    public void listIndivDebt_valid_success() {
        try {
            Group group = new Group("TestGroup1");
            MemberList members = group.getMemberList();
            TransactionList transactions = group.getTransactionList();
            members.addMember("Alice");
            members.addMember("Bob");
            String expression = "Alice p/Bob a/10";
            transactions.addTransaction(expression, members, group);
            assertEquals("Bob owes Alice $10.00", group.listIndivDebt("Alice"));
            File f = new File("./data/TestGroup1");
            StorageHandler.deleteDir(f);
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Tests the exception thrown when the individual has no debts
     */
    @Test
    public void listIndivDebt_noDebts_exceptionThrown() {
        try {
            Group group = new Group("TestGroup1");
            MemberList members = group.getMemberList();
            members.addMember("Alice");
            group.listIndivDebt("Alice");
            fail();
        } catch (Exception e) {
            assertEquals(ExceptionMessage.TRANSACTIONS_SUMMED_UP.getMessage(), e.getMessage());
        }
    }
}
