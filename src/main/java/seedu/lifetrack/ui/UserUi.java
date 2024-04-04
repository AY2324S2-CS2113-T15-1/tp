package seedu.lifetrack.ui;

public class UserUi {

    public static void printUserCaloriesRequired(int caloriesRequired) {
        System.out.println("\t You need to consume " + caloriesRequired + " calories per day to hit your goals!");
    }

    public static void printUserCalorieProgress(int caloriesConsumed, int caloriesRequired, String progressBar,
                                                int percentage) {
        System.out.print("\t Calories:\n");
        System.out.printf("\t You have consumed " + caloriesConsumed + "calories out of your goal of "
                + caloriesRequired + "calories so far.\n");
        System.out.printf("\t %s %d%%\n\n", progressBar, percentage);
    }

    public static void printUserHydrationProgress(int hydrationConsumed, int hydrationRequired, String progressBar,
                                                  int percentage) {
        System.out.print("\t Hydration:\n");
        System.out.printf("\t You have consumed " + hydrationConsumed + "ml out of your goal of "
                + hydrationRequired + "ml so far.\n");
        System.out.printf("\t %s %d%%\n", progressBar, percentage);
    }

    public static void printUserSetUpComplete(String name, int caloriesRequired){
        System.out.println("\t Hello, " + name + "! Thank you for completing the setup :)");
        printUserCaloriesRequired(caloriesRequired);
    }
    public static void printNoUserYetMessage(){
        System.out.println("\t\t Please set up your profile first!");
    }
}
