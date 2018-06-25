package com.thinvent.zhhd.common.exception;


public class ArgumentNullException extends ArgumentException {
    private static final long serialVersionUID = -2557516902474466380L;

    private final static String ERROR_MESSAGE = "Argument [%s] is null.";

    public ArgumentNullException(String argumentName) {
        this(argumentName, null);
    }

    public ArgumentNullException(String argumentName, Throwable e) {
        super(String.format(ERROR_MESSAGE, argumentName), e);
    }
}
