package Fare;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * Station Zone(s)
 * Holborn 1
 * Earl’s Court 1, 2
 * Hammersmith 2
 * Wimbledon 3
 *
 *
 Anywhere in Zone 1 £2.50 : Holburn to Earl’s Court (Given)

 Any one zone outside zone 1 £2.00 : Earl’s Court to Hammersmith

 Any two zones including zone 1 £3.00: Holborn to Hammersmith

 Any two zones excluding zone 1 £2.25: Earl’s Court to Wimbledon or Hammersmith to Wimbledon

 Any three zones £3.20 : Holborn to Wimbledon
 *
 */

public class FareTest {

    @Test
    // Any two zones excluding zone 1 £2.25
    public void givenMinimumBalanceTravelFromEarlscourtToWimbledon(){
        Fare fare = new Fare("Earlscourt", "Wimbledon");
        double expectedFare = fare.getFair();
        double epsilon = 0.000001d;

        Assert.assertEquals(2.25, expectedFare, epsilon);
    }

    @Test
    // Any two zones including zone 1 £3.00
    public void givenMinimumBalanceTravelFromHolbornToHammersmith(){
        Fare fare = new Fare("Holborn", "Hammersmith");
        double expectedFare = fare.getFair();
        double epsilon = 0.000001d;

        Assert.assertEquals(3.00, expectedFare, epsilon);
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
    // Any two zones excluding zone 1 £2.25
    public void givenMinimumBalanceTravelFromHammersmithToWimbledon(){

        Fare fare = new Fare("Hammersmith", "Wimbledon");
        double expectedFare = fare.getFair();
        double epsilon = 0.000001d;

        Assert.assertEquals(2.25, expectedFare, epsilon);
    }

    @Test
    // Any three zones £3.20
    public void givenMinimumBalanceTravelFromHolbornToWimbledon(){

        Fare fare = new Fare("Holborn", "Wimbledon");
        double expectedFare = fare.getFair();
        double epsilon = 0.000001d;

        Assert.assertEquals(3.20, expectedFare, epsilon);
    }

    @Test
    // Anywhere in Zone 1 £2.50
    public void givenMinimumBalanceTravelFromHolbornToEarlsCourt(){

        Fare fare = new Fare("Holborn", "Earlscourt");
        double expectedFare = fare.getFair();
        double epsilon = 0.000001d;

        Assert.assertEquals(2.5, expectedFare, epsilon);
    }

    @Test
    // Any one zone outside zone 1 £2.00
    public void givenMinimumBalanceTravelFromEarlsCourtToHammersmith(){

        Fare fare = new Fare("Earlscourt", "Hammersmith");
        double expectedFare = fare.getFair();
        double epsilon = 0.000001d;

        Assert.assertEquals(2.00, expectedFare, epsilon);
    }
}