package Fare.BusFare;

import Fare.LocationCanNotBeEmptyException;
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
 *
 */

public class BusFareTest {
    @Test
    public void givenDubaiStationThatDoesNotExistThrowsException() {
        Exception exception = Assert.assertThrows(
                LocationCanNotBeEmptyException.class, () -> {
                    BusFare fare = new BusFare("", "Hammersmith");
                });

        String expectedMessage = "Checkin Or Checkout Can Not Be Empty!";
        String actualMessage = exception.getMessage();
        System.out.println(actualMessage);
        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void givenMinimumBalanceTravelFromEarlscourtToWimbledon() throws LocationCanNotBeEmptyException {
        BusFare fare = new BusFare("Earlscourt", "Wimbledon");
        double expectedTubeTubeFare = fare.getFair();
        double epsilon = 0.000001d;

        Assert.assertEquals(1.8, expectedTubeTubeFare, epsilon);
    }
}