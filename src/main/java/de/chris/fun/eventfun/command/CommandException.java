package de.chris.fun.eventfun.command;

public class CommandException extends RuntimeException {

    private static final long serialVersionUID = 8216106299270337345L;

    public CommandException(String message) {
        super(message);
    }

    public CommandException() {
        super();
    }

    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandException(Throwable cause) {
        super(cause);
    }

}
