package seedu.lifetrack.system.parser;

import seedu.lifetrack.Entry;
import seedu.lifetrack.hydration.hydrationlist.HydrationEntry;
import seedu.lifetrack.system.exceptions.InvalidInputException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static seedu.lifetrack.system.exceptions.ErrorMessages.getIncorrectVolumeInputMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getHydrationEmptyDescriptionMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getHydrationIncorrectOrderMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getHydrationMissingKeywordMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getHydrationNegativeIntegerVolumeMessage;

public class ParserHydration {
    private static final int VOLUME_IDX = 1;
    private static final int DATE_IDX = 2;
    private static final int HYDRATION_ADD_PADDING = 13;

    /**
     * Parses a string input to create a Liquid object representing liquid intake.
     *
     * This method expects the input string to follow a specific format, where the
     * beverage name and quantity are separated by the delimiters 'b/' and 'v/'.
     * The method extracts these components and creates a Liquid object.
     * If any part of the input is missing or empty, an InvalidInputException is thrown.
     *
     * @param input the input string to be parsed, containing beverage name and quantity
     * @return a Liquid object representing liquid intake
     * @throws InvalidInputException if the input string is missing components or
     *                              contains empty fields
     */
    public static Entry parseHydrationInput(String input) throws InvalidInputException {
        
        int volumeIndex = input.indexOf("v/");
        int dateIndex = input.indexOf("d/");

        checkKeywordsExist(dateIndex, volumeIndex);
        assert volumeIndex != -1 : "The v/ keyword should exist!";
        assert dateIndex != -1 : "The d/ keyword should exist!";

        checkKeywordsCorrectlyOrdered(dateIndex, volumeIndex);
        assert volumeIndex < dateIndex : "The v/ keyword must appear before date/ in the input!";

        String[] parts = input.split("v/|d/");
        String description = getDescriptionFromInput(input, volumeIndex);
        String strVolume = parts[VOLUME_IDX].trim();
        String strDate = parts[DATE_IDX].trim();

        checkInputsAreNonEmpty(description, strVolume, strDate);
        assert description != "" : "The description field should be a non-empty string!";
        assert strVolume != "" : "The volume field should be a non-empty string!";
        assert strDate != "" : "The date field should be a non-empty string!";

        int volume = getIntegerVolumeFromInput(strVolume);
        checkVolumeIsPositiveInteger(volume);
        assert volume > 0 : "Volume value must be a positive integer!";

        //@@author rexyyong
        // Convert strDate from type String to date of type LocalDate
        LocalDate date = null;
        try {
            date = getLocalDateFromInput(strDate);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Invalid date format");
        }
        //@@author

        return makeNewInputEntry(description, volume, date);
    }

    private static HydrationEntry makeNewInputEntry(String description, int volume, LocalDate date) {

        return new HydrationEntry(description, volume, date);
    }

    //@@author rexyyong
    public static LocalDate getLocalDateFromInput(String strDate) throws DateTimeParseException {
        LocalDate date = LocalDate.parse(strDate);
        return date;
    }
    //@@author

    private static int getIntegerVolumeFromInput(String strVolume) {
        int volume = 0;
        try {
            volume = Integer.parseInt(strVolume);
        } catch (NumberFormatException e) {
            System.out.println(getIncorrectVolumeInputMessage());
        }
        return volume;
    }

    private static void checkVolumeIsPositiveInteger(int volume) throws InvalidInputException {
        if (volume <= 0) {
            throw new InvalidInputException(getHydrationNegativeIntegerVolumeMessage());
        }
    }

    private static void checkInputsAreNonEmpty(String description, String strVolume, String date)
            throws InvalidInputException {
        //check if the description, calories or date fields are empty
        if (description.isEmpty() || strVolume.isEmpty() || date.isEmpty()) {
            throw new InvalidInputException(getHydrationEmptyDescriptionMessage());
        }
    }

    private static String getDescriptionFromInput(String inputString, int volumeIndex) {
        String description;
        description = inputString.substring(HYDRATION_ADD_PADDING, volumeIndex).trim();
        return description;
    }

    private static void checkKeywordsCorrectlyOrdered( int dateIndex, int volumeIndex) throws InvalidInputException {
        if (volumeIndex >= dateIndex) {
            throw new InvalidInputException(getHydrationIncorrectOrderMessage());
        }
    }

    private static void checkKeywordsExist(int dateIndex, int volumeIndex) throws InvalidInputException {
        //check that v/ and date/ keywords exist in the input, else throw exception
        if (dateIndex == -1 || volumeIndex == -1) {
            throw new InvalidInputException(getHydrationMissingKeywordMessage());
        }
    }
}
