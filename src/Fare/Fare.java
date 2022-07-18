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
        int checkInZone = getZone(this.checkInStation);
        int checkOutZone = getZone(this.checkOutStation);

        if ( checkInZone == checkOutZone && checkInZone == 1)
            return 2.5;

        return 0;
    }

    public int getZone(String station) {
        HashMap<String, Integer> zoneStation = new HashMap<>();
        zoneStation.put("Holborn", 1);
        zoneStation.put("EarlsCourt", 1);

        return zoneStation.get(station);
    }

}
