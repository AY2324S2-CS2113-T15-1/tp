package longah.handler;

import org.junit.jupiter.api.Test;

import longah.util.MemberList;
import longah.util.TransactionList;
import longah.exception.LongAhException;
import longah.exception.ExceptionMessage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.File;
import java.io.FileWriter;

public class StorageHandlerTest {
    /**
     * Helper method to remove a directory and its contents.
     * 
     * @param dir The file to be removed
     */
    public void deleteDir(File dir) {
        File[] contents = dir.listFiles();
        if (contents != null) {
            for (File file : contents) {
                deleteDir(file);
            }
        }
        dir.delete();
    }

    /**
     * Tests the successful file creation when the StorageHandler is constructed.
     */
    @Test
    public void storageHandlerConstructor_fileCreationSuccess() {
        try {
            File f = new File("./data/test_grp1");
            File g;
            deleteDir(f);
            MemberList members = new MemberList();
            TransactionList transactions = new TransactionList();
            new StorageHandler(members, transactions, "test_grp1");
            g = new File("./data/test_grp1/members.txt");
            assertTrue(g.exists());
            g = new File("./data/test_grp1/transactions.txt");
            assertTrue(g.exists());
            // Delete test folders after completion
            deleteDir(f);
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Tests the successful loading of members data from the file.
     */
    @Test
    public void loadMembersData_dataLoaded_success() {
        try {
            File f = new File("./data/test_grp2");
            deleteDir(f);
            MemberList members1 = new MemberList();
            TransactionList transactions1 = new TransactionList();
            StorageHandler storage1 = new StorageHandler(members1, transactions1, "test_grp2");
            members1.addMember("Alice", 10);
            members1.addMember("Bob", -10);
            storage1.saveMembersData();
            MemberList members2 = new MemberList();
            TransactionList transactions2 = new TransactionList();
            new StorageHandler(members2, transactions2, "test_grp2");
            String expected = "Alice: $10.00\nBob: -$10.00";
            assertEquals(expected, members2.listMembers());
            // Delete test folders after completion
            deleteDir(f);
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Tests the unsuccessful loading of transactions data from the file.
     */
    @Test
    public void loadMembersData_invalidMembersData_exceptionThrown() {
        File f = new File("./data/test_grp3");
        try {
            deleteDir(f);
            MemberList members1 = new MemberList();
            TransactionList transactions1 = new TransactionList();
            StorageHandler storage1 = new StorageHandler(members1, transactions1, "test_grp3");
            members1.addMember("Alice", 10);
            storage1.saveMembersData();
            MemberList members2 = new MemberList();
            TransactionList transactions2 = new TransactionList();
            new StorageHandler(members2, transactions2, "test_grp3");
            fail();
        } catch (LongAhException e) {
            boolean isMessage = LongAhException.isMessage(e, ExceptionMessage.STORAGE_FILE_CORRUPTED);
            assertTrue(isMessage);
            // Delete test folders after completion
            f = new File("./data/test_grp3");
            deleteDir(f);
        }
    }

    /**
     * Tests the unsuccessful loading of transactions data from the file.
     */
    @Test
    public void loadMembersData_invalidTransactionData_exceptionThrown() {
        File f = new File("./data/test_grp4");
        File g;
        try {
            deleteDir(f);
            MemberList members1 = new MemberList();
            TransactionList transactions1 = new TransactionList();
            new StorageHandler(members1, transactions1, "test_grp4");
            g = new File("./data/test_grp4/transactions.txt");
            FileWriter fw = new FileWriter(g);
            fw.write("LOREM IPSUM");
            fw.close();
            MemberList members2 = new MemberList();
            TransactionList transactions2 = new TransactionList();
            new StorageHandler(members2, transactions2, "test_grp4");
            fail();
        } catch (LongAhException e) {
            boolean isMessage = LongAhException.isMessage(e, ExceptionMessage.INVALID_STORAGE_CONTENT);
            assertTrue(isMessage);
            // Delete test folders after completion
            f = new File("./data/test_grp4");
            deleteDir(f);
        } catch (Exception e) {
            // Filewriter error
            fail();
        }
    }
}
