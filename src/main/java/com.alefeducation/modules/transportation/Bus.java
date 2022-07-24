package main.java.com.alefeducation.modules.transportation;

import main.java.com.alefeducation.modules.card.BelowMinimumBalanceException;
import main.java.com.alefeducation.modules.card.Card;

public class Bus {
    private Card card;
    public static final String CHECKIN_SUCCESSFUL = "Check In Successful!";
    public static final String CHECKOUT_SUCCESSFUL = "Check Out Successful!";
    public static final String CHECKIN_FAIL = "Checkin Failed! Your Account Balance Below £1.8.";
    public static final String CHECKOUT_DUE = "Due Bus Fare £3.2 Deducted!";

    public Bus(Card card) {
        this.card = card;
    }

    public void checkin() {

        if ( card.getCheckedInStatus()
                && this.card.getAmount() > 3.2 ) {
            this.card.charge(3.2);
            System.out.println(CHECKOUT_DUE);
        }

        if ( this.card.getAmount() > 3.2 ) {
            this.card.setCheckedIn(true);
            System.out.println(CHECKIN_SUCCESSFUL);

        } else
            throw new BelowMinimumBalanceException(CHECKIN_FAIL);
    }

    public void checkout() {
        this.card.setCheckedIn(false);
        this.card.charge(1.8);
        System.out.println(CHECKOUT_SUCCESSFUL);
    }
}
