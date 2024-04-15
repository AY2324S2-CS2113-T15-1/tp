package longah.util;

import longah.exception.ExceptionMessage;
import longah.exception.LongAhException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents objects where the time element is concerned.
 */
public class DateTime {
    //@@author FeathersRe
    private LocalDateTime dateTime;

    /**
     * Constructs a new DateTime object based on a String representation of the date time expression.
     *
     * @param dateTimeExpression String representation of a date time expression
     * @throws LongAhException If the date time expression does not follow the intended date time format
     */
    public DateTime(String dateTimeExpression) throws LongAhException {
        try {
            this.dateTime = LocalDateTime.parse(dateTimeExpression.trim(),
                    DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw new LongAhException(ExceptionMessage.INVALID_TIME_FORMAT);
        }
        if (isFuture()) {
            throw new LongAhException(ExceptionMessage.INVALID_TIME_INPUT);
        }
    }

    /**
     * Getter method to get the dateTime object associated with the current object instance. Currently used within the
     * class only.
     *
     * @return Returns the dateTime object associated with the current instance.
     */
    private LocalDateTime getDateTime() {
        return this.dateTime;
    }

    /**
     * Determines whether the input DateTime object has a date that is before the current object.
     *
     * @param dateTimeToCompare the reference DateTime object to be compared with
     * @return true if the input DateTime object has a date before the current object. false otherwise
     */
    public boolean isBefore(DateTime dateTimeToCompare) {
        return this.dateTime.isBefore(dateTimeToCompare.getDateTime());
    }

    /**
     * Determines whether the input DateTime object has a date that is after the current object.
     *
     * @param dateTimeToCompare the reference DateTime object to be compared with
     * @return true if the input DateTime object has a date after the current object. false otherwise
     */
    public boolean isAfter(DateTime dateTimeToCompare) {
        return this.dateTime.isAfter(dateTimeToCompare.getDateTime());
    }

    /**
     * Determines whether the input DateTime object has a date that is equal to the current object
     *
     * @param dateTimeToCompare the reference DateTime object to be compared with
     * @return true if the input DateTime object has a date equal to the current object. false otherwise
     */
    public boolean isEqual(DateTime dateTimeToCompare) {
        return this.dateTime.isEqual(dateTimeToCompare.getDateTime());
    }

    /**
     * Determines whether the existing object has a future dateTime. This should only be used within the class to
     * reject invalid time entries.
     *
     * @return true if the object has a future dateTime. false otherwise
     */
    private boolean isFuture() {
        return this.dateTime.isAfter(LocalDateTime.now());
    }

    /**
     * Converts the date time object into a String expression suitable for storage
     *
      * @return A string representation of the date time object suitable for storage
     */
    public String toStorageString() {
        return this.dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }

    /**
     * Converts the date time object into a String expression suitable for printing
     *
     * @return A string representation of the date time object suitable for printing
     */
    @Override
    public String toString() {
        return this.dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }

}
