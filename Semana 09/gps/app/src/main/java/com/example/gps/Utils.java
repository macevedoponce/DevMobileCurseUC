package com.example.gps;

public class Utils {

    public static double rad(double x) {
        return x * Math.PI / 180;
    }
    public  static double  getDistance(double p1lat, double p1long, double p2lat, double p2long) {
        long R = 6378137; // Earth’s mean radius in meter
        double dLat = rad(p2lat - p1lat);
        double dLong = rad(p2long - p1long);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(rad(p1lat)) * Math.cos(rad(p2lat)) *
                        Math.sin(dLong / 2) * Math.sin(dLong / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c;
        return d;
    };

}
