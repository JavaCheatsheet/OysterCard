package test.java.integration;

import main.java.com.alefeducation.modules.card.Card;
import main.java.com.alefeducation.modules.card.BelowMinimumBalanceException;
import main.java.com.alefeducation.modules.transportation.Bus;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

public class BusTest {
    @Test
    public void givenBalance2_UserDidNtCheckout_ChecksInFails() {
        Card card = new Card(UUID.randomUUID());
        card.topUp(new BigDecimal(2));
        card.setCheckin(true);

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
        Card card = new Card(UUID.randomUUID());
        card.topUp(new BigDecimal(20));
        card.setCheckin(true);

        Bus bus = new Bus(card);
        bus.checkin();

        BigDecimal expectedAmt = new BigDecimal(20).subtract(
                new BigDecimal(3.2))
                .setScale(2, RoundingMode.HALF_UP);

        Assert.assertTrue(card.isCheckin());
        Assert.assertTrue(card.getAmount().equals(expectedAmt));
    }

    @Test
    public void givenAmount20_CheckoutSuccessful() {
        Card card = new Card(UUID.randomUUID());
        card.topUp(new BigDecimal(20));

        Bus bus = new Bus(card);
        bus.checkin();
        bus.checkout();

        BigDecimal expectedAmt = new BigDecimal(20)
                .subtract(new BigDecimal(1.8))
                .setScale(2, RoundingMode.HALF_UP);

        Assert.assertFalse(card.isCheckin());
        Assert.assertTrue(card.getAmount().equals(expectedAmt));
    }

    @Test
    public void givenAmount0_CheckinFails() {
        Card card = new Card(UUID.randomUUID());
        card.topUp(new BigDecimal(0));

        Bus bus = new Bus(card);

        Exception exception = Assert.assertThrows(
                BelowMinimumBalanceException.class, () -> {
                    bus.checkin();
                });

        String actualMessage = exception.getMessage();

        Assert.assertTrue(actualMessage.contains(Bus.CHECKIN_FAIL));
    }

}

