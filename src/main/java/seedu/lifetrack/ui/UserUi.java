package seedu.lifetrack.ui;

public class UserUi {

    public static void printUserCaloriesRequired(int caloriesRequired) {
        System.out.println("\t You need to consume " + caloriesRequired + " calories per day to hit your goals!");
    }

    public static void printUserCalorieProgress(int caloriesConsumed, int caloriesRequired, String progressBar,
                                                int percentage) {
        System.out.print("\t Calories:\n");
        System.out.printf("\t You have consumed " + caloriesConsumed + " out of your goal of "
                + caloriesRequired + " so far.\n");
        System.out.printf("\t %s %d%%\n\n", progressBar, percentage);
    }

    public static void printUserHydrationProgress(int hydrationConsumed, int hydrationRequired, String progressBar,
                                                  int percentage) {
        System.out.print("\t Hydration:\n");
        System.out.printf("\t You have consumed " + hydrationConsumed + " out of your goal of "
                + hydrationRequired + " so far.\n");
        System.out.printf("\t %s %d%%\n", progressBar, percentage);
    }

    public static void printUserSetUpComplete(String name){
        System.out.println("\t Hello, " + name + "! Thank you for completing the setup :)");
    }
}
