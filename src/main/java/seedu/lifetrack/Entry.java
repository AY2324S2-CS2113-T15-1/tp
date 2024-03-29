package seedu.lifetrack;

import java.time.LocalDate;

public abstract class Entry {

    private String description;
    private LocalDate date;

    public Entry(String description, LocalDate date){
        this.description = description;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public String toString() {
        return String.format("\t Date: " + date + ", Description: " + description);
    }

    public String toFileFriendlyString() {
        return String.format(date + ";" + description);
    }
}
