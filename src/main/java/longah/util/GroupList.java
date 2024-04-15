package longah.util;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import longah.exception.ExceptionMessage;
import longah.exception.LongAhException;
import longah.handler.StorageHandler;
import longah.handler.UI;
import longah.node.Group;

/**
 * Represents a list of groups in the LongAh application.
 */
public class GroupList {
    private static final String GROUP_LIST_FILE_PATH = "./data/groupList.txt";
    private static Group activeGroup = null;
    private static ArrayList<Group> groupList = new ArrayList<>();

    /**
     * Constructor for GroupList.
     *
     * @throws LongAhException If an I/O exception occurs.
     */
    public GroupList() throws LongAhException {
        StorageHandler.initDir();
        if (!Files.exists(Paths.get(GROUP_LIST_FILE_PATH)) || groupList == null) {
            createGroup();
        } else {
            loadGroupList();
            getGroupList();
            UI.showMessage("Defaulting to the first group.");
            UI.showMessage("You are now managing: " + groupList.get(0).getGroupName());
            activeGroup = groupList.get(0);
        }
    }

    /**
     * Returns the active group.
     *
     * @return The active group.
     */
    public static Group getActiveGroup() {
        return activeGroup;
    }

    /**
     * Switches the active group to the specified group.
     *
     * @param newGroup The new group to switch to.
     */
    public static void switchActiveGroup(Group newGroup) {
        activeGroup = newGroup;
    }

    /**
     * Checks if there is at least 1 valid group in the group list.
     * If there is no group, prompt user to create a new group.
     */
    public static void createGroup() throws LongAhException {
        if (groupList.isEmpty()) {
            UI.showMessage("No groups found. Please give a name for your first group or enter " + 
                    "'exit' or 'close' to exit LongAh.");
            String groupName = UI.getUserInput();
            if (groupName.equals("exit") || groupName.equals("close")) {
                UI.exit();
            }
            Group newGroup = new Group(groupName);
            groupList.add(newGroup);
            try {
                Files.write(Paths.get(GROUP_LIST_FILE_PATH), groupName.getBytes());
            } catch (IOException e) {
                UI.showMessage(ExceptionMessage.STORAGE_FILE_CORRUPTED.getMessage());
            }
            activeGroup = newGroup;
            UI.showMessage("Created group: " + groupName);
            UI.showMessage("You are now managing: " + activeGroup.getGroupName());
        }
    }

    /**
     * Loads the group list from the file.
     * 
     * @throws LongAhException If an I/O exception occurs.
     */
    public static void loadGroupList() throws LongAhException {
        String[] data;
        try {
            data = new String(Files.readAllBytes(Paths.get(GROUP_LIST_FILE_PATH))).split("\n");
            if (data.length == 0) {
                throw new LongAhException(ExceptionMessage.EMPTY_GROUP_LIST);
            }
        } catch (IOException e) {
            throw new LongAhException(ExceptionMessage.IO_EXCEPTION);
        }

        for (String groupName : data) {
            try {
                groupList.add(new Group(groupName));
            } catch (LongAhException e) {
                UI.showMessage(ExceptionMessage.STORAGE_FILE_CORRUPTED.getMessage());
            }
        }
    }

    /**
     * Returns the group list.
     *
     * @return The group list as a string.
     * @throws LongAhException If an I/O exception occurs.
     */
    public static String getGroupList() throws LongAhException {
        // did not use exceptions here as I want to return an empty string
        if (groupList.isEmpty()) {
            return "Group list is empty.";
        }
        try {
            int index = 1;
            String[] data = new String(Files.readAllBytes(Paths.get(GROUP_LIST_FILE_PATH))).split("\n");
            String groupListString = "";
            for (String groupName : data) {
                groupListString += index + ". "  + groupName + "\n";
                index++;
            }
            return groupListString;
        } catch (IOException e) {
            throw new LongAhException(ExceptionMessage.IO_EXCEPTION);
        }
    }

    /**
     * Adds a group to the group list and saves to the txt file.
     *
     * @param group The group to add.
     * @throws LongAhException If the group is already in the list.
     */
    public static void addGroup(Group group) throws LongAhException {
        if (isGroup(group.getGroupName())) {
            throw new LongAhException(ExceptionMessage.DUPLICATE_GROUP);
        }
        groupList.add(group);
        saveGroupList();
        UI.showMessage("Added group: " + group.getGroupName());
    }

    /**
     * Deletes a group from the group list and saves to the txt file.
     *
     * @param groupName The group to delete.
     * @throws LongAhException If the group is not found or if error occurs when deleting group folder
     */
    public static void deleteGroup(String groupName) throws LongAhException {
        if (!GroupList.isGroup(groupName)) {
            throw new LongAhException(ExceptionMessage.GROUP_NOT_FOUND);
        }
        Group group = getGroup(groupName);
        groupList.remove(group);
        saveGroupList();
        UI.showMessage("Remaining groups:");
        UI.showMessage(getGroupList());
        // delete the group folder
        try {
            Files.deleteIfExists(Paths.get("./data/" + groupName + "/members.txt"));
            Files.deleteIfExists(Paths.get("./data/" + groupName + "/transactions.txt"));
            Files.deleteIfExists(Paths.get("./data/" + groupName));
        } catch (IOException e) {
            throw new LongAhException(ExceptionMessage.IO_EXCEPTION);
        }
        // if there is only group that was left is deleted
        if (groupList.isEmpty()) {
            UI.showMessage("Deleted group: " + groupName);
            createGroup();
        } else if (activeGroup.getGroupName().equals(groupName)) {
            activeGroup = groupList.get(0);
            UI.showMessage("You have deleted the active group that you were managing.");
            UI.showMessage("Defaulting back to the first group in the list.");
            UI.showMessage("You are now managing: " + activeGroup.getGroupName());
        } else {
            UI.showMessage("Deleted group: " + groupName);
        }
    }


    /**
     * Returns the group with a specified name.
     * 
     * @param name The name of the group.
     * @return The group with a specified name.
     */
    public static Group getGroup(String name) throws LongAhException {
        for (Group group : groupList) {
            if (group.getGroupName().equals(name)) {
                return group;
            }
        }
        throw new LongAhException(ExceptionMessage.GROUP_NOT_FOUND);
    }

    public static boolean isEmpty() {
        return groupList.isEmpty();
    }

    /**
     * Returns the size of the group list.
     * 
     * @return The size of the group list.
     */
    public int getSize() {
        return groupList.size();
    }

    /**
     * Checks if the group is in the list.
     * 
     * @param groupName The name of the group.
     * @return True if the group is in the list, false otherwise.
     */
    public static boolean isGroup(String groupName) {
        for (Group group : groupList) {
            if (group.getGroupName().equals(groupName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Saves the group list to the txt file.
     * 
     * @throws LongAhException If an I/O exception occurs.
     */
    public static void saveGroupList() throws LongAhException {
        try {
            FileWriter writer = new FileWriter(GROUP_LIST_FILE_PATH);
            for (Group group : groupList) {
                writer.write(group.getGroupName() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new LongAhException(ExceptionMessage.IO_EXCEPTION);
        }
    }
}
