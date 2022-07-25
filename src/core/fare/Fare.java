package core.fare;

import core.Utils;
import core.exception.LocationCanNotBeEmptyException;

public abstract class Fare {
    private String checkin;
    private String checkout;

    public Fare( String checkin, String checkout)
            throws LocationCanNotBeEmptyException {
        Utils.validateLocation(checkin);
        Utils.validateLocation(checkout);
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public String getCheckin() {
        return checkin;
    }

    public String getCheckout() {
        return checkout;
    }

}
