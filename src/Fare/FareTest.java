package Fare;

import org.junit.Assert;
import org.junit.Test;

public class FareTest {

    @Test
    public void givenMinimumBalanceTravelFromWimbledonToHammersmith(){

        Fare fare = new Fare("Wimbledon", "Hammersmith");
        double expectedFare = fare.getFair();
        double epsilon = 0.000001d;

        Assert.assertEquals(2.25, expectedFare, epsilon);
    }

    @Test
    public void givenMinimumBalanceTravelFromWimbledonToHolborn(){

        Fare fare = new Fare("Wimbledon", "Holborn");
        double expectedFare = fare.getFair();
        double epsilon = 0.000001d;

        Assert.assertEquals(3.5, expectedFare, epsilon);
    }

    @Test
    public void givenMinimumBalanceTravelFromHolbornToEarlsCourt(){

        Fare fare = new Fare("Holborn", "EarlsCourt");
        double expectedFare = fare.getFair();
        double epsilon = 0.000001d;

        Assert.assertEquals(2.5, expectedFare, epsilon);
    }
}