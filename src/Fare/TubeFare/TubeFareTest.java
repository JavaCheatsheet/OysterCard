package Fare.TubeFare;

import Fare.StationNotFoundException;
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

 Any one zone outside zone 1 £2.00 : Toonsvil to Wimbelson
 Let's add a new station name Toonsvil in zone 3.

 Any two zones including zone 1 £3.00: Holborn to Hammersmith

 Any two zones excluding zone 1 £2.25: Earl’s Court to Wimbledon or Hammersmith to Wimbledon

 Any three zones £3.20 : Holborn to Wimbledon
 *
 */

public class TubeFareTest {

    @Test
    // Any two zones excluding zone 1 £2.25
    public void givenMinimumBalanceTravelFromEarlscourtToWimbledon(){
        TubeFare fare = new TubeFare("Earlscourt", "Wimbledon");
        double expectedTubeTubeFare = fare.getFair();
        double epsilon = 0.000001d;

        Assert.assertEquals(2.25, expectedTubeTubeFare, epsilon);
    }

    @Test
    // Any two zones including zone 1 £3.00
    public void givenMinimumBalanceTravelFromHolbornToHammersmith(){
        TubeFare fare = new TubeFare("Holborn", "Hammersmith");
        double expectedTubeFare = fare.getFair();
        double epsilon = 0.000001d;

        Assert.assertEquals(3.00, expectedTubeFare, epsilon);
    }

    @Test
    public void givenDubaiStationThatDoesNotExistThrowsException() {
        Exception exception = Assert.assertThrows(
                StationNotFoundException.class, () -> {
                    TubeFare fare = new TubeFare("Dubai", "Hammersmith");
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

        TubeFare fare = new TubeFare("Hammersmith", "Wimbledon");
        double expectedTubeFare = fare.getFair();
        double epsilon = 0.000001d;

        Assert.assertEquals(2.25, expectedTubeFare, epsilon);
    }

    @Test
    // Any three zones £3.20
    public void givenMinimumBalanceTravelFromHolbornToWimbledon(){

        TubeFare fare = new TubeFare("Holborn", "Wimbledon");
        double expectedTubeFare = fare.getFair();
        double epsilon = 0.000001d;

        Assert.assertEquals(3.20, expectedTubeFare, epsilon);
    }

    @Test
    // Anywhere in Zone 1 £2.50
    public void givenMinimumBalanceTravelFromHolbornToEarlsCourt(){

        TubeFare fare = new TubeFare("Holborn", "Earlscourt");
        double expectedTubeFare = fare.getFair();
        double epsilon = 0.000001d;

        Assert.assertEquals(2.5, expectedTubeFare, epsilon);
    }

    @Test
    // Any one zone outside zone 1 £2.00
    public void givenMinimumBalanceTravelFromToonsvilToWimbelson(){

        TubeFare fare = new TubeFare("Toonsvil", "Wimbledon");
        double expectedTubeFare = fare.getFair();
        double epsilon = 0.000001d;

        Assert.assertEquals(2.00, expectedTubeFare, epsilon);
    }
}