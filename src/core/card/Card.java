package core.card;

import core.exception.BelowMinimumBalanceException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

public class Card {
    protected UUID cardNumber;
    protected boolean checkin = false;
    protected BigDecimal amount = new BigDecimal(3.20);
    public final BigDecimal MINIMUM_BALANCE = new BigDecimal(3.20)
            .setScale(2, RoundingMode.HALF_UP);

    public Card(UUID uuid) {
        cardNumber = uuid;

        // Fetch real data when it is stored
        amount = new BigDecimal(0);
    }

    public void topUp(BigDecimal topUpAmount) {
        amount = amount.add(topUpAmount);
    }

    public void charge(BigDecimal fare) {

        if ( hasMinimumBalance(fare) )
            amount = amount.subtract(fare);
        else
            throw new BelowMinimumBalanceException(
                    "Your account does not have enough balance!");
    }

    public boolean hasMinimumBalance(BigDecimal fare) {
        return  amount.compareTo(fare) >= 0 ? true : false;
    }

    public void setCheckin(boolean checkin){
        this.checkin = checkin;
    }

    public boolean isCheckin(){
        return this.checkin;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount.setScale(2, RoundingMode.HALF_UP);
    }

    public UUID createCard(){
        setAmount(MINIMUM_BALANCE);
        cardNumber = UUID.randomUUID();
        return cardNumber;
    }

}
