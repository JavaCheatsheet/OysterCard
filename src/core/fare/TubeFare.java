package core.fare;

import core.exception.LocationCanNotBeEmptyException;
import core.exception.StationNotFoundException;

import java.util.HashMap;

public class TubeFare extends Fare implements IFare {

    public TubeFare(String checkin, String checkout)
            throws LocationCanNotBeEmptyException {
        super(checkin, checkout);
    }

    public double getFair()
            throws StationNotFoundException {
        int checkInZone, checkOutZone;

        int[] checkInCheckOutZones = getCheckInCheckOutZones();
        checkInZone = checkInCheckOutZones[0];
        checkOutZone = checkInCheckOutZones[1];

//            Anywhere in Zone 1 £2.50
        if ( checkInZone == checkOutZone
                && checkInZone == 1 )
            return 2.5;

//            Any one zone outside zone 1 £2.00
        else if ( checkInZone == checkOutZone
                && checkInZone != 1 )
            return 2.0;

//            Any three zones - £3.20
        else if (( checkInZone == 1 && checkOutZone == 3)
                || ( checkInZone == 3 && checkOutZone == 1 ))
            return 3.2;

//            Any two zones excluding zone 1 £2.25
        else if ( checkInZone > 1 && checkOutZone > 1 )
            return 2.25;

//            Any two zones including zone 1 £3.00
        else if (( checkInZone == 1 && checkOutZone > 1)
                || ( checkInZone > 1 && checkOutZone == 1 ))
            return 3.00;

        return 3.2;
    }

    public int[] getCheckInCheckOutZones() 
            throws StationNotFoundException {
        HashMap<String, Integer> zoneStation = new HashMap<>();
        zoneStation.put("Holborn", 1);
        zoneStation.put("Earlscourt", 2);
        zoneStation.put("Bugszilla", 2);
        zoneStation.put("Hammersmith", 2);
        zoneStation.put("Toonsvil", 3);
        zoneStation.put("Wimbledon", 3);

        try {
            if ( getCheckout().equals("Earlscourt")
                && zoneStation.get(getCheckin()) < 2 )
                    return new int[] { 1, 1 };

            else if ( getCheckin().equals("Earlscourt")
            && zoneStation.get(getCheckin()) == 2 ) {
                return new int[] { 1, zoneStation.get(getCheckin())};
            }
            return new int[] {
                    zoneStation.get(getCheckin()),
                    zoneStation.get(getCheckout())
            };
        } catch (NullPointerException e) {
                throw new StationNotFoundException("Station Not Found!");
        }
    }

}
