package Fare.BusFare;

import Fare.LocationCanNotBeEmptyException;

public class BusFare {
    private String checkIn;
    private String checkOut;

    public BusFare( String checkIn, String checkOut ) throws LocationCanNotBeEmptyException {

        if ( checkIn.isBlank() || checkIn.isEmpty()
        || checkOut.isBlank() || checkOut.isEmpty() )
            throw new LocationCanNotBeEmptyException("Checkin Or Checkout Can Not Be Empty!");

        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public double getFair() {
            return 1.8;
    }
}
