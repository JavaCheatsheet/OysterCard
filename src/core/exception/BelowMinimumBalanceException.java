package core.exception;

public class BelowMinimumBalanceException extends NullPointerException {
    public BelowMinimumBalanceException(String errorMessage) {
        super(errorMessage);
    }
}
