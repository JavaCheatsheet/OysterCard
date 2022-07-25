package test.java.unit;

import core.card.Card;
import core.exception.BelowMinimumBalanceException;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

public class CardTest {

    @Test
    public void givenCardHas20Charge3() {
        Card card = new Card(UUID.randomUUID());
        BigDecimal expectedAmount = new BigDecimal(20)
                .subtract(new BigDecimal(3))
                .setScale(2, RoundingMode.HALF_UP);
        card.topUp(new BigDecimal(20));
        card.charge(new BigDecimal(3));

        Assert.assertTrue(card.getAmount().equals(expectedAmount));
    }

    @Test
    public void givenCardHasMinAmt_ThrowBelowMinException() {
        Card card = new Card(UUID.randomUUID());
        BigDecimal expectedAmount = new BigDecimal(0);
        card.topUp(expectedAmount);

        Exception exception = Assert.assertThrows(
            BelowMinimumBalanceException.class, () -> {
                    card.charge(new BigDecimal(3));
            });

        String expectedMessage = "Your account does not have enough balance!";
        String actualMessage = exception.getMessage();
        System.out.println(actualMessage);
        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void givenCardHasNoBalanceTopUp30Pounds() {
        Card card = new Card(UUID.randomUUID());
        BigDecimal expectedAmt = new BigDecimal(30)
                .setScale(2, RoundingMode.HALF_UP);
        card.topUp(expectedAmt);

        BigDecimal actualAmt = card.getAmount();

        Assert.assertTrue(actualAmt.equals(expectedAmt));
    }

}