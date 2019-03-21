package pawelkuruc.issInfo.Controller;

import pawelkuruc.issInfo.Model.ISSData;

public abstract class ISSDataCalculator {

    public static double calculateDistance(ISSData issInitialStatus, ISSData issCurrentStatus){

        double lat1 = Double.parseDouble(issInitialStatus.getIssPosition().getLatitude());
        double lon1 = Double.parseDouble(issInitialStatus.getIssPosition().getLongitude());

        double lat2 = Double.parseDouble(issCurrentStatus.getIssPosition().getLatitude());
        double lon2 = Double.parseDouble(issCurrentStatus.getIssPosition().getLongitude());

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        return distance;
    }

    public static double calculateVelocity(ISSData reading1, ISSData reading2){
        double distance = calculateDistance(reading1,reading2);
        double time = (reading2.getTimestamp() - reading1.getTimestamp());

        return distance/time;
    }
}
