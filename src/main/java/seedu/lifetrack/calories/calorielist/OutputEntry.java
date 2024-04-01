package seedu.lifetrack.calories.calorielist;

import seedu.lifetrack.Entry;
import seedu.lifetrack.calories.Activity;

import java.time.LocalDate;

public class OutputEntry extends Entry {

    private Activity activity;
    private int calories;
    private boolean doesActivityExist = false;


    public OutputEntry(String description, int calories, LocalDate date) {
        super(description, date);
        this.calories = calories;
    }

    public OutputEntry(String description, int calories, LocalDate date, Activity activity) {
        super(description, date);
        this.activity = activity;
        this.calories = calories;
        this.doesActivityExist = true;
    }

    public Activity getActivity() {
        return activity;
    }

    public int getCalories() {
        return calories;
    }

    public String toString() {
        return String.format(super.toString() + ", Calories: " + calories);
    }

    public String toFileFriendlyString() {
        return String.format(super.toFileFriendlyString() + ";C_OUT;" + calories);
    }
}
