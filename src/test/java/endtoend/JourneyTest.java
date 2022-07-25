package test.java.endtoend;

import core.card.Card;
import core.exception.LocationCanNotBeEmptyException;
import core.transportation.Bus;
import core.transportation.Tube;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

/**
 * Stations and zones:
 * Station Zone(s)
 * Holborn 1
 * Earl’s Court 1, 2
 * Wimbledon 3
 * Hammersmith 2
 * Toonsvil 3
 *
 * Fares:
 * Journey Fare
 * Anywhere in Zone 1 £2.50
 * Any one zone outside zone 1 £2.00
 * Any two zones including zone 1 £3.00
 * Any two zones excluding zone 1 £2.25
 * Any three zones £3.20
 * Any bus journey £1.80
 *
 * The maximum possible main.java.com.modules.fare is therefore £3.20.
 */
public class JourneyTest {

    @Test
//    1. Tube Holborn to Earl’s Court
//    2. 328 bus from Earl’s Court to Chelsea
//    3. Tube Earl’s court to Hammersmith
    public void given30Pounds_TravelFromHolbornToHammersmith()
            throws LocationCanNotBeEmptyException {

        Card card = new Card(UUID.randomUUID());
        card.topUp(new BigDecimal(30));

        Tube tube = new Tube(card);
        tube.checkin("Holborn");
        tube.checkout("Earlscourt");

        System.out.println(card.getAmount());

        Bus bus = new Bus(card);
        bus.checkin(" Earlscourt");
        bus.checkout("Chelsea");

        System.out.println(card.getAmount());

        tube.checkin("Earlscourt");
        tube.checkout("Hammersmith");

        BigDecimal expectedRemaningBalance =
                new BigDecimal(30)
                        .subtract(new BigDecimal(2.5))
                        .subtract(new BigDecimal(1.8))
                        .subtract(new BigDecimal(3))
                        .setScale(2, RoundingMode.HALF_UP);

        System.out.println(card.getAmount());

        Assert.assertTrue(expectedRemaningBalance.equals(card.getAmount()));
        Assert.assertFalse(card.isCheckin());
    }
}
