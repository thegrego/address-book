package address_book;

public class ProcessingException extends RuntimeException {
    public ProcessingException(Throwable e) {
        super(e);
    }

    public ProcessingException(String message) {
        super(message);
    }

    public ProcessingException(String message, Throwable cause) {
        super(message, cause);
    }

    protected ProcessingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
