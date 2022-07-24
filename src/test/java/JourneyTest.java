import Card.Card;
import Transportation.Bus.Bus;
import Transportation.Tube.Tube;
import org.junit.Assert;
import org.junit.Test;

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
 * The maximum possible fare is therefore £3.20.
 */
public class JourneyTest {

    @Test
//    1. Tube Holborn to Earl’s Court
//    2. 328 bus from Earl’s Court to Chelsea
//    3. Tube Earl’s court to Hammersmith
    public void given30Pounds_TravelFromHolbornToHammersmith(){

        double epsilon = 0.001d;
        int cardNumber = Integer.MAX_VALUE;
        Card card = new Card(cardNumber);
        card.topUp(30);

        Tube tube = new Tube(card);

        tube.checkin("Holborn");
        tube.checkout("Earlscourt");

        System.out.println(card.getAmount());

        Bus bus = new Bus(card);
        bus.checkin();
        bus.checkout();

        System.out.println(card.getAmount());

        tube.checkin("Earlscourt");
        tube.checkout("Hammersmith");

        double expectedRemaningBalance = 30 - 2.5 - 1.8 - 3;

        System.out.println(card.getAmount());

        Assert.assertFalse(card.getCheckedInStatus());
        Assert.assertEquals(expectedRemaningBalance, card.getAmount(), epsilon);
    }

    @Test
    // Any one zone outside zone 1 £2.00
    public void givenAmount3_CheckinAtToonsvil(){

        double epsilon = 0.000001d;

        String checkinStation = "Toonsvil";
        int cardNumber = Integer.MAX_VALUE;
        Card card = new Card(cardNumber);
        card.topUp(3.2);
        Tube tube = new Tube(card);
        tube.checkin(checkinStation);

        double expected = 3.20;
        String actual = tube.getCheckinStation();

        Assert.assertTrue(checkinStation.equals(actual));
        Assert.assertTrue(card.getCheckedInStatus());
        Assert.assertEquals(expected, card.getAmount(), epsilon);
    }

}
