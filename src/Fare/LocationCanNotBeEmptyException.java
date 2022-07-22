package Fare;

public class LocationCanNotBeEmptyException extends Exception {
    public LocationCanNotBeEmptyException(String errorMessage) {
        super(errorMessage);
    }
}
