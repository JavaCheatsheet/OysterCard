package test.java.integration;

import main.java.com.alefeducation.modules.card.Card;
import main.java.com.alefeducation.modules.transportation.Tube;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

public class TubeTest {

    @Test
    // Any one zone outside zone 1 £2.00
    public void givenAmount3dot2_CheckinAtToonsvil_CheckoutAtWimbelson(){
        String checkinStation = "Toonsvil";
        String checkoutStation = "Wimbledon";

        Card card = new Card(UUID.randomUUID());
        card.topUp(new BigDecimal(3.2));

        Tube tube = new Tube(card);
        tube.checkin(checkinStation);
        tube.checkout(checkoutStation);

        BigDecimal expectedAmt = new BigDecimal(3.2)
                .subtract(new BigDecimal(2))
                .setScale(2, RoundingMode.HALF_UP);
        Assert.assertTrue(card.getAmount().equals(expectedAmt));

        String actualCheckoutStation = tube.getCheckin();
        String actualCheckinStation = tube.getCheckout();

        Assert.assertTrue(checkinStation.equals(actualCheckoutStation));
        Assert.assertTrue(checkoutStation.equals(actualCheckinStation));
        Assert.assertFalse(card.isCheckin());

    }

    @Test
    public void givenAmount3dot2_CheckinAtToonsvil(){
        String checkinStation = "Toonsvil";

        Card card = new Card(UUID.randomUUID());
        BigDecimal topup = new BigDecimal(3.2)
                .setScale(2, RoundingMode.HALF_UP);
        card.topUp(topup);

        Tube tube = new Tube(card);
        tube.checkin(checkinStation);

//        String actualCheckinStation = tube.getCheckin();
//        Assert.assertTrue(checkinStation.equals(actualCheckinStation));
//        Assert.assertTrue(card.isCheckin());

        Assert.assertTrue(topup.equals(card.getAmount()));
    }

}
