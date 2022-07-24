package test.java.integration;

import main.java.com.alefeducation.modules.card.Card;
import main.java.com.alefeducation.modules.card.BelowMinimumBalanceException;
import main.java.com.alefeducation.modules.transportation.Bus;
import org.junit.Assert;
import org.junit.Test;

public class BusTest {
    @Test
    public void givenBalance2_UserDidNtCheckout_ChecksInFails() {
        double epsilon = 0.000001d;

        int cardNumber = Integer.MAX_VALUE;
        Card card = new Card(cardNumber);
        card.topUp(2);
        card.setCheckedIn(true);

        Bus bus = new Bus(card);
        Exception exception = Assert.assertThrows(
                BelowMinimumBalanceException.class, () -> {
                    bus.checkin();
                });

        String actualMessage = exception.getMessage();
        System.out.println(actualMessage);
        Assert.assertTrue(actualMessage.contains(Bus.CHECKIN_FAIL));
    }

    @Test
    public void givenBalance20_UserDidNtCheckout_ChecksInAgain() {
        double epsilon = 0.000001d;

        int cardNumber = Integer.MAX_VALUE;
        Card card = new Card(cardNumber);
        card.topUp(20);
        card.setCheckedIn(true);

        Bus bus = new Bus(card);
        bus.checkin();

        double expected = 20 - 3.2;

        Assert.assertTrue(card.getCheckedInStatus());
        Assert.assertEquals(expected, card.getAmount(), epsilon);
    }

    @Test
    public void givenAmount20_CheckoutSuccessful() {
        double epsilon = 0.000001d;

        int cardNumber = Integer.MAX_VALUE;
        Card card = new Card(cardNumber);
        card.topUp(20);

        Bus bus = new Bus(card);
        bus.checkin();

        double expected = 20 - 1.8;
        bus.checkout();
        double actual = card.getAmount();

        Assert.assertFalse(card.getCheckedInStatus());
        Assert.assertEquals(expected, actual, epsilon);
    }

    @Test
    public void givenAmount0_CheckinFails() {
        double epsilon = 0.000001d;
        int cardNumber = Integer.MAX_VALUE;
        Card card = new Card(cardNumber);
        card.topUp(0);

        Bus bus = new Bus(card);

        Exception exception = Assert.assertThrows(
                BelowMinimumBalanceException.class, () -> {
                    bus.checkin();
                });

        String actualMessage = exception.getMessage();

        Assert.assertTrue(actualMessage.contains(Bus.CHECKIN_FAIL));
    }

    @Test
    public void givenMinBalanceCheckinSuccessful() {
        double epsilon = 0.000001d;

        int cardNumber = Integer.MAX_VALUE;
        Card card = new Card(cardNumber);
        card.topUp(20);

        Bus bus = new Bus(card);
        bus.checkin();

        Assert.assertTrue(card.getCheckedInStatus());
    }
}
