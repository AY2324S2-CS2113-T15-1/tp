package longah.commands;

import longah.exception.ExceptionMessage;
import longah.exception.LongAhException;
import longah.node.Group;

public class SwitchCommand extends Command {
    /**
     * Constructor for SwitchCommand.
     *
     * @param commandString The command string.
     * @param taskExpression The task expression.
     */
    public SwitchCommand(String commandString, String taskExpression) {
        super(commandString, taskExpression);
    }

    /**
     * Executes the switch command.
     *
     * @param group The group to execute the command on.
     */
    public void execute(Group group) throws LongAhException {
        if (this.taskExpression.isEmpty()) {
            throw new LongAhException(ExceptionMessage.INVALID_SWITCH_GROUP_COMMAND);
        }
    }

    /**
     * Returns whether the current command is a switch command or not.
     *
     * @return True if the command is a switch command, false otherwise.
     */
    @Override
    public boolean isSwitch() {
        return true;
    }
}
