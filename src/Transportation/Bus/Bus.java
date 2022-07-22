package Transportation.Bus;

import Card.Card;
import Card.BelowMinimumBalanceException;

public class Bus {
    private Card card;
    public static final String CHECKIN_SUCCESSFUL = "Check In Successful!";
    public static final String CHECKOUT_SUCCESSFUL = "Check Out Successful!";
    public static final String CHECKIN_FAIL = "Checkin Failed! Your Account Balance Below £1.8.";
    public static final String CHECKOUT_DUE = "Due Bus Fare £1.8 Deducted!";

    public Bus(Card card) {
        this.card = card;
    }

    public void checkin() {

        if ( card.getCheckedInStatus()
                && this.card.getAmount() > 1.8 ) {
            this.card.charge(1.8);
            System.out.println(CHECKOUT_DUE);
        }

        if ( this.card.getAmount() > 1.8 ) {
            this.card.setCheckedIn(true);
            System.out.println(CHECKIN_SUCCESSFUL);

        } else
            throw new BelowMinimumBalanceException(CHECKIN_FAIL);
    }

    public void checkout() {
        this.card.setCheckedIn(false);
        this.card.charge(1.8);
    }
}
