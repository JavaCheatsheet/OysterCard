package Fare;

import org.junit.Assert;
import org.junit.Test;

public class FareTest {
    @Test
    public void givenMinimumBalanceTravelFromHolbornToHammersmith(){
        Fare fare = new Fare("Holborn", "Wimbledon");
        double expectedFare = fare.getFair();
        double epsilon = 0.000001d;

        Assert.assertEquals(3.2, expectedFare, epsilon);
    }

    @Test
    public void givenDubaiStationThatDoesNotExistThrowsException() {
        Exception exception = Assert.assertThrows(
                StationNotFoundException.class, () -> {
                    Fare fare = new Fare("Dubai", "Hammersmith");
                    fare.getCheckInCheckOutZones();
                });

        String expectedMessage = "Station Not Found!";
        String actualMessage = exception.getMessage();
        System.out.println(actualMessage);
        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }

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