//package main.java.com.alefeducation.modules.transportation;
//
//import main.java.com.alefeducation.modules.card.BelowMinimumBalanceException;
//import main.java.com.alefeducation.modules.card.Card;
//import main.java.com.alefeducation.modules.fare.TubeFare;
//
//public abstract class Transportation {
//    private Card card;
//    private fare Fare;
//    private String checkin;
//    private String checkout;
//
//    public static final String CHECKIN_SUCCESSFUL = "Check In Successful!";
//    public static final String CHECKOUT_SUCCESSFUL = "Check Out Successful!";
//    public static final String CHECKIN_FAIL = "Checkin Failed! Your Account Balance Below £3.20.";
//    public static final String CHECKOUT_DUE = "Due Bus Fare £3.20 Deducted!";
//
//    public Transportation(Card card) {
//        this.card = card;
//    }
//
//    public void checkin(String checkinStation) {
//
//        if ( this.card.getAmount() < 3.20 )
//            throw new BelowMinimumBalanceException(CHECKIN_FAIL);
//
//            // Failed to checkout previously
//        else if ( card.getCheckedInStatus()
//                && this.card.getAmount() > 3.20 ) {
//            this.card.charge(3.20);
//
//            System.out.println(CHECKOUT_DUE);
//        }
//
//        else if ( this.card.getAmount() >= 3.20 ) {
//            this.checkinStation = checkinStation;
//            this.card.setCheckedIn(true);
//
//            System.out.println(CHECKIN_SUCCESSFUL);
//        }
//    }
//}
