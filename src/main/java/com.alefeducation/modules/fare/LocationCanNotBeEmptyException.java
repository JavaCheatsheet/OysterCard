package main.java.com.modules.fare;

public class LocationCanNotBeEmptyException extends Exception {
    public LocationCanNotBeEmptyException(String errorMessage) {
        super(errorMessage);
    }
}
