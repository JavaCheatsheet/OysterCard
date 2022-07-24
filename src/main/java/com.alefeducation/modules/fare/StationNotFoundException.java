package main.java.com.modules.fare;

public class StationNotFoundException extends NullPointerException {
    public StationNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
