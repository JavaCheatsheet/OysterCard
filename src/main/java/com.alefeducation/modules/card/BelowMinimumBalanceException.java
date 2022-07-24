package main.java.com.alefeducation.modules.card;

public class BelowMinimumBalanceException extends NullPointerException {
    public BelowMinimumBalanceException(String errorMessage) {
        super(errorMessage);
    }
}
