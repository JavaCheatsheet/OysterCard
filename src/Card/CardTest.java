package Card;

import org.junit.Assert;
import org.junit.Test;

public class CardTest {

    @Test
    public void givenCardHasNoBalanceTopUp30Pounds() {
        double epsilon = 0.000001d;
        Card wallet = new Card(Integer.MAX_VALUE);
        double expectedAmount = wallet.topUp(30);
        Assert.assertEquals(30, expectedAmount, epsilon);
    }

    @Test
    public void givenCardHas20Charge3() {
        double epsilon = 0.000001d;
        Card wallet = new Card(Integer.MAX_VALUE);
        wallet.topUp(20);
        double expectedAmount = wallet.charge(3);
        Assert.assertEquals(17, expectedAmount, epsilon);
    }

    @Test
    public void givenCardHasMinAmtThrowBelowMinException() {
        double epsilon = 0.000001d;
        Card wallet = new Card(Integer.MAX_VALUE);
        wallet.topUp(2);

        Exception exception = Assert.assertThrows(
            BelowMinimumBalanceException.class, () -> {
                    wallet.charge(3);
            });

        String expectedMessage = "Your account does not have enough balance!";
        String actualMessage = exception.getMessage();
        System.out.println(actualMessage);
        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }
}