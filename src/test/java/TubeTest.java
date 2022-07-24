import Card.Card;
import Transportation.Tube.Tube;
import org.junit.Assert;
import org.junit.Test;

public class TubeTest {

    @Test
    // Any one zone outside zone 1 £2.00
    public void givenAmount3_CheckinAtToonsvil_CheckoutAtWimbelson(){

        double epsilon = 0.001d;

        String checkinStation = "Toonsvil";
        String checkoutStation = "Wimbledon";
        int cardNumber = Integer.MAX_VALUE;
        Card card = new Card(cardNumber);
        card.topUp(3.2);

        Tube tube = new Tube(card);
        tube.checkin(checkinStation);
        tube.checkout(checkoutStation);
        double remainingBalance = card.getAmount();

        String actualCheckoutStation = tube.getCheckinStation();
        String actualCheckinStation = tube.getCheckoutStation();
        double expectedRemaningBalance = 3.2 - 2;

        Assert.assertTrue(checkinStation.equals(actualCheckoutStation));
        Assert.assertTrue(checkoutStation.equals(actualCheckinStation));
        Assert.assertFalse(card.getCheckedInStatus());
        Assert.assertEquals(expectedRemaningBalance, remainingBalance, epsilon);
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
