package Fare;

import java.util.HashMap;

public class Fare {

    String checkInStation;
    String checkOutStation;

    Fare( String checkInStation, String checkOutStation ) {
        this.checkInStation = checkInStation;
        this.checkOutStation = checkOutStation;
    }

    public double getFair() {
        int checkInZone = 0;
        int checkOutZone = 0;

        try {
            int[] checkInCheckOutZones = getCheckInCheckOutZones();
            checkInZone = checkInCheckOutZones[0];
            checkOutZone = checkInCheckOutZones[1];

            // Anywhere in Zone 1
            if ( checkInZone == checkOutZone && checkInZone == 1)
                return 2.5;

            // Any three zones - £3.20
            else if (( checkInZone == 1 && checkOutZone == 3)
                    || ( checkInZone == 3 && checkOutZone == 1 ))
                return 3.2;

                // Any two zones excluding zone 1
            else if ( checkInZone > 1 && checkOutZone > 1 )
                return 2.25;

                // Any two zones including zone 1
            else if (( checkInZone == 1 && checkOutZone > 1)
                    || ( checkInZone > 1 && checkOutZone == 1 ))
                return 3.5;


        } catch ( StationNotFoundException e ) {
            System.out.println(e.getMessage());
        }

        return 0;
    }

    public int[] getCheckInCheckOutZones() throws StationNotFoundException {
        HashMap<String, Integer> zoneStation = new HashMap<>();
        zoneStation.put("Holborn", 1);
        zoneStation.put("EarlsCourt", 1);
        zoneStation.put("Wimbledon", 3);
        zoneStation.put("Hammersmith", 2);

        try {
            return new int[] {
                    zoneStation.get(checkInStation),
                    zoneStation.get(checkOutStation)
            };
        } catch (NullPointerException e) {
                throw new StationNotFoundException("Station Not Found!");
        }
    }

}
class StationNotFoundException extends NullPointerException {
    public StationNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}