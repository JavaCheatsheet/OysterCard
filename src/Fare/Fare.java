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
            checkInZone = getZone(this.checkInStation);
            checkOutZone = getZone(this.checkOutStation);

            // Anywhere in Zone 1
            if ( checkInZone == checkOutZone && checkInZone == 1)
                return 2.5;

            // Any two zones including zone 1
            else if ( ( ( checkInZone == 1 || checkOutZone > 1)
                        || ( checkInZone > 1 || checkOutZone == 1) ))
                return 3.5;

        } catch ( StationNotFoundException e ) {
            System.out.println(e.getMessage());
        }

        return 0;
    }

    public int getZone(String station) throws StationNotFoundException {
        HashMap<String, Integer> zoneStation = new HashMap<>();
        zoneStation.put("Holborn", 1);
        zoneStation.put("EarlsCourt", 1);
        zoneStation.put("Wimbledon", 3);

        try {
            return zoneStation.get(station);
        } catch (NullPointerException e) {
                throw new StationNotFoundException("Station " + station + " Not Found!");
        }
    }

}
class StationNotFoundException extends Exception {
    public StationNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}