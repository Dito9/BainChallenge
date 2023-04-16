package bain.interview.DistanceService;

public class GeoPoint {

    private double lat;
    private double lon;

    public GeoPoint(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }
    public void setLat(double lat) {
        this.lat = lat;
    }
    public double getLon() {
        return lon;
    }
    public void setLon(double lon) {
        this.lon = lon;
    }

    //Calculate distance using latitude/longitude with haversine Formula
    //https://en.wikipedia.org/wiki/Haversine_formula
    //https://gist.github.com/vananth22/888ed9a22105670e7a4092bdcf0d72e4
    public Double calculateDistance(GeoPoint destinationPoint) {
        
        if (destinationPoint == null ) return null;
        
        double destLat = destinationPoint.lat;
        double sourceLat = this.lat;

        double destLon = destinationPoint.lon;
        double sourceLon = this.lon;
        
        final int R = 6371; // Radius of the earth
        
        double latDistance = Math.toRadians(destLat - sourceLat);
        double lonDistance = Math.toRadians(destLon - sourceLon);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                    + Math.cos(Math.toRadians(sourceLat)) * Math.cos(Math.toRadians(destLat))
                    * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c;
        
        return distance;
 
    }    
}