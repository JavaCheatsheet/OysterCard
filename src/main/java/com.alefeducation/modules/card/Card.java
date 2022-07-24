package main.java.com.alefeducation.modules.card;

public class Card {
    private int cardNumber;
    private boolean checkedIn;
    private double amount = 0.0;
    private final double MINIMUM_BALANCE = 3.20;

    public Card(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public double topUp(double topUpAmount) {
        return this.amount += topUpAmount;
    }

    public void charge(double fare) {

        if ( hasMinimumBalance(fare) )
            this.amount -= fare;
        else
            throw new BelowMinimumBalanceException(
                    "Your account does not have enough balance!");
    }

    private boolean hasMinimumBalance(double fare) {
        return  this.amount < fare ? false : true;
    }

    public void setCheckedIn(boolean checkin){
        this.checkedIn = checkin;
    }

    public double getAmount() {
        return this.amount;
    }

    public boolean getCheckedInStatus(){
        return this.checkedIn;
    }
}
