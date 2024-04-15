package longah.handler;

import java.util.Scanner;

/**
 * The UI class handles user interaction by displaying messages and reading user input.
 */
public class UI {
    private static final String SEPARATOR = "____________________________________________________________";
    private static Scanner scanner = new Scanner(System.in);

    // @@author haowern98
    /**
     * Displays the welcome message along with ASCII art.
     */
    public static void showWelcomeMessage() {
        UI.showMessage(" /$$                                      /$$$$$$  /$$       /$$");
        UI.showMessage("| $$                                     /$$__  $$| $$      | $$");
        UI.showMessage("| $$        /$$$$$$  /$$$$$$$   /$$$$$$ | $$  \\ $$| $$$$$$$ | $$");
        UI.showMessage("| $$       /$$__  $$| $$__  $$ /$$__  $$| $$$$$$$$| $$__  $$| $$");
        UI.showMessage("| $$      | $$  \\ $$| $$  \\ $$| $$  \\ $$| $$__  $$| $$  \\ $$|__/");
        UI.showMessage("| $$      | $$  | $$| $$  | $$| $$  | $$| $$  | $$| $$  | $$");
        UI.showMessage("| $$$$$$$$|  $$$$$$/| $$  | $$|  $$$$$$$| $$  | $$| $$  | $$ /$$");
        UI.showMessage("|________/ \\______/ |__/  |__/ \\____  $$|__/  |__/|__/  |__/|__/");
        UI.showMessage("                               /$$  \\ $$");
        UI.showMessage("                              |  $$$$$$/");
        UI.showMessage("                               \\______/");
        UI.showMessage("Thanks for choosing LongAh! Never worry about owing money during the Year of the Dragon!");
    }

    /**
     * Displays the exit message.
     */
    public static void exit() {
        showMessage("Goodbye! Hope to see you again soon!");
        System.exit(0);
    }

    /**
     * Displays the command prompt.
     */
    public static void showCommandPrompt() {
        showMessage("Enter command: ", false);
    }

    /**
     * Reads the user input.
     *
     * @return The user input as a String.
     */
    public static String getUserInput() {
        if (!scanner.hasNextLine()) {
            System.exit(0);
        }
        return scanner.nextLine().trim();
    }

    /**
     * Displays a message.
     *
     * @param message The message to display.
     */
    public static void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays a message.
     *
     * @param message The message to display.
     * @param newLine Whether to print a new line after the message.
     */
    public static void showMessage(String message, boolean newLine) {
        if (newLine) {
            System.out.println(message);
        } else {
            System.out.print(message);
        }
    }

    /**
     * Checks if there is another line of input.
     * Used for text ui testing.
     *
     * @return true if there is another line of input, false otherwise.
     */
    public static boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    /**
     * Prints a separator.
     */
    public static void printSeparator() {
        showMessage(SEPARATOR);
    }

    /**
     * Prints an empty line.
     */
    public static void printEmptyLine() {
        showMessage("");
    }
}
