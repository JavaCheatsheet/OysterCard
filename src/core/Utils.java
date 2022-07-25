package core;

import core.exception.LocationCanNotBeEmptyException;

public class Utils {
    public static final String LOCATION_EMPTY = "Location cannot be empty.";

    public static void validateLocation(String location)
            throws LocationCanNotBeEmptyException {

        if ( location.isBlank() || location.isEmpty() )
            throw new LocationCanNotBeEmptyException(LOCATION_EMPTY);
    }
}
