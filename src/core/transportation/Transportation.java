package core.transportation;

import core.helper.StringHelper;
import core.exception.BelowMinimumBalanceException;
import core.card.Card;
import core.exception.LocationCanNotBeEmptyException;

import java.math.BigDecimal;

public abstract class Transportation {
    private String checkin;
    private String checkout;
    private Card card;

    public static final String CHECKIN_SUCCESSFUL = "Check in successful.";
    public static final String CHECKOUT_SUCCESSFUL = "Check out successful.";
    public static final String CHECKIN_FAIL = "Checkin failed. Your account balance below £3.20.";
    public static final String CHECKOUT_DUE = "Due fare £3.20 deducted.";

    protected Transportation(Card card) {
        this.card = card;
    }

    public void checkin(String location)
            throws BelowMinimumBalanceException,
            LocationCanNotBeEmptyException {

        if ( card.isCheckin()
                && card.hasMinimumBalance(
                card.MINIMUM_BALANCE)) {
            card.charge(card.MINIMUM_BALANCE);

            System.out.println(CHECKOUT_DUE);
        }

        if ( card.hasMinimumBalance(
                card.MINIMUM_BALANCE )) {
            card.setCheckin(true);
            setCheckin(location);
            System.out.println(CHECKIN_SUCCESSFUL);

        } else
            throw new BelowMinimumBalanceException(CHECKIN_FAIL);
    }

    public void checkout(String location)
            throws LocationCanNotBeEmptyException {
        setCheckout(location);
        this.card.charge(getFare());
        this.card.setCheckin(false);
        System.out.println(CHECKOUT_SUCCESSFUL);
    }

    public BigDecimal getFare()
            throws LocationCanNotBeEmptyException {
        return new BigDecimal(3.2);
    };

    public void setCheckin(String location)
            throws LocationCanNotBeEmptyException {
        StringHelper.validateLocation(location);
        checkin = location;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckout(String location)
            throws LocationCanNotBeEmptyException {
        StringHelper.validateLocation(location);
        checkout = location;
    }

    public String getCheckout() {
        return checkout;
    }

}
