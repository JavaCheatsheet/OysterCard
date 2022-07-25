package main.java.com.alefeducation.modules.transportation;

import main.java.com.alefeducation.modules.card.BelowMinimumBalanceException;
import main.java.com.alefeducation.modules.card.Card;
import main.java.com.alefeducation.modules.fare.TubeFare;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Tube {
    private Card card;
    private TubeFare tubeFare;
    private String checkin;
    private String checkout;

    public static final String CHECKIN_SUCCESSFUL = "Check In Successful!";
    public static final String CHECKOUT_SUCCESSFUL = "Check Out Successful!";
    public static final String CHECKIN_FAIL = "Checkin Failed! Your Account Balance Below £3.20.";
    public static final String CHECKOUT_DUE = "Due Bus Fare £3.20 Deducted!";

    public Tube(Card card) {
        this.card = card;
    }

    public void checkin(String station) {

        if ( card.isCheckin()
                && card.hasMinimumBalance(
                card.MINIMUM_BALANCE)) {
            card.charge(card.MINIMUM_BALANCE);
            System.out.println(CHECKOUT_DUE);
        }

        if ( card.hasMinimumBalance(
                card.MINIMUM_BALANCE )) {
            card.setCheckin(true);
            setCheckin(station);
            System.out.println(CHECKIN_SUCCESSFUL);

        } else
            throw new BelowMinimumBalanceException(CHECKIN_FAIL);
    }

    public void checkout(String station) {
        checkout = station;
        try {
             this.tubeFare = new TubeFare(
                    checkin,
                    checkout );

            card.charge(new BigDecimal(tubeFare.getFair())
                    .setScale(2, RoundingMode.HALF_UP));

            this.card.setCheckin(false);

            System.out.println(CHECKOUT_SUCCESSFUL);

        } catch (Exception e ) {
            System.out.println(e.getMessage());
        }
    }
    public void setCheckin(String station) {
         checkin = station;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckout(String station) {
        checkout = station;
    }

    public String getCheckout() {
        return checkout;
    }
}
