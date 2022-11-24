package it.gianni.numberschecker.exception;

public class CSVException extends RuntimeException {

    public CSVException(String message) {
        super(message);
    }

    public CSVException(String message, Throwable cause) {
        super(message, cause);
    }
}
