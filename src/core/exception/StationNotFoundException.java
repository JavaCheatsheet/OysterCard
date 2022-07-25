package core.exception;

public class StationNotFoundException extends NullPointerException {
    public StationNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
