package Fare;

import org.junit.Assert;
import org.junit.Test;

public class FareTest {

    @Test
    public void givenMinimumBalanceTravelFromWimbledonToHolborn(){

        Fare fare = new Fare("Wimbledon", "Holborn");
        double actualFare = fare.getFair();
        double epsilon = 0.000001d;

        Assert.assertEquals(3.5, actualFare, epsilon);
    }

    @Test
    public void givenMinimumBalanceTravelFromHolbornToEarlsCourt(){

        Fare fare = new Fare("Holborn", "EarlsCourt");
        double actualFare = fare.getFair();
        double epsilon = 0.000001d;

        Assert.assertEquals(2.5, actualFare, epsilon);
    }
}