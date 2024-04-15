package longah.handler;

import longah.exception.ExceptionMessage;
import longah.exception.LongAhException;

public class NameHandler {
    private static final int MAX_NAME_LENGTH = 50;
    private static final String NAME_REGEX = "[A-Za-z0-9]+";
    private static final String ERROR = "Error";

    /**
     * Checks the validity of a name.
     * Names must be alphanumeric and not exceed the character limit.
     * 
     * @param name The name to be checked.
     * @throws LongAhException If the name is invalid.
     */
    public static void checkNameConditions(String name) throws LongAhException {
        // Check if group name is fully alphanumeric
        if (!name.matches(NAME_REGEX)) {
            throw new LongAhException(ERROR);
        }
        // Check if name exceeds character limit
        if (name.length() > MAX_NAME_LENGTH) {
            throw new LongAhException(ExceptionMessage.CHAR_LIMIT_EXCEEDED);
        }
    }
    
    /**
     * Checks the validity of a member name.
     * 
     * @param name The name of the member.
     * @throws LongAhException If the name is invalid.
     */
    public static void checkMemberNameValidity(String name) throws LongAhException {
        try {
            checkNameConditions(name);
        } catch (LongAhException e) {
            if (e.getMessage().equals(ERROR)) {
                throw new LongAhException(ExceptionMessage.INVALID_MEMBER_NAME);
            } else {
                throw e;
            }
        }
    }

    /**
     * Checks the validity of a group name.
     * 
     * @param name The name of the group.
     * @throws LongAhException If the name is invalid.
     */
    public static void checkGroupNameValidity(String name) throws LongAhException {
        try {
            checkNameConditions(name);
        } catch (LongAhException e) {
            if (e.getMessage().equals(ERROR)) {
                throw new LongAhException(ExceptionMessage.INVALID_GROUP_NAME);
            } else {
                throw e;
            }
        }
    }
}
