package by.gsu.epamlab.exceptions;

public class ValidateRunTimeException extends RuntimeException {
    public ValidateRunTimeException() {
    }

    public ValidateRunTimeException(String message) {
        super(message);
    }

    public ValidateRunTimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidateRunTimeException(Throwable cause) {
        super(cause);
    }

    public ValidateRunTimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
