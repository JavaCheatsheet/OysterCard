package Transportation.Tube;

import Card.Card;
import Card.BelowMinimumBalanceException;
import Fare.TubeFare.TubeFare;

public class Tube {
    private Card card;
    private TubeFare tubeFare;
    private String checkinStation;
    private String checkoutStation;

    public static final String CHECKIN_SUCCESSFUL = "Check In Successful!";
    public static final String CHECKOUT_SUCCESSFUL = "Check Out Successful!";
    public static final String CHECKIN_FAIL = "Checkin Failed! Your Account Balance Below £3.20.";
    public static final String CHECKOUT_DUE = "Due Bus Fare £3.20 Deducted!";

    public Tube(Card card) {
        this.card = card;
    }

    public void checkin(String checkinStation) {

        if ( this.card.getAmount() < 3.20 )
            throw new BelowMinimumBalanceException(CHECKIN_FAIL);

        // Failed to checkout previously
        else if ( card.getCheckedInStatus()
                && this.card.getAmount() > 3.20 ) {
            this.card.charge(3.20);

            System.out.println(CHECKOUT_DUE);
        }

        else if ( this.card.getAmount() >= 3.20 ) {
            this.checkinStation = checkinStation;
            this.card.setCheckedIn(true);

            System.out.println(CHECKIN_SUCCESSFUL);
        }
    }

    public void checkout(String checkoutStation) {
        this.checkoutStation = checkoutStation;
        double fare = 0.001d;

        try {
             this.tubeFare = new TubeFare(
                    this.checkinStation,
                    this.checkoutStation );
            fare = tubeFare.getFair();
            this.card.setCheckedIn(false);
            card.charge(fare);

        } catch (Exception e ) {
            System.out.println(e.getMessage());
        }
    }

    public String getCheckinStation() {
        return this.checkinStation;
    }

    public String getCheckoutStation() {
        return this.checkoutStation;
    }
}
