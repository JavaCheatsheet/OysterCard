package core.fare;

import core.exception.LocationCanNotBeEmptyException;

public class BusFare extends Fare implements IFare {
    public BusFare(String checkin, String checkout)
            throws  LocationCanNotBeEmptyException {
        super(checkin, checkout);
    }

    public double getFair() {
            return 1.8;
    }
}
