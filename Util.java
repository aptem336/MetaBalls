package org.yourorghere;

public class Util {

    final static double PLUS90 = Math.toRadians(90);

    public static void calcCam() {
        Data.xView = Data.lenToCenter * Math.sin(Math.toRadians(Data.angleV)) * Math.cos(Math.toRadians(Data.angleH));
        Data.zView = Data.lenToCenter * Math.sin(Math.toRadians(Data.angleV)) * Math.sin(Math.toRadians(Data.angleH));
        Data.yView = Data.lenToCenter * Math.cos(Math.toRadians(Data.angleV));
    }

    public static double round(double value, int scale) {
        return Math.round(value * Math.pow(10, scale)) / Math.pow(10, scale);
    }

    public static double[] normal(double[] x, double[] y, double[] z) {
        double[] normal = new double[3];
        normal[0] = ((y[1] - x[1]) * (z[2] - x[2])) - ((z[1] - x[1]) * (y[2] - x[2]));
        normal[1] = ((y[0] - x[0]) * (z[2] - x[2])) - ((z[0] - x[0]) * (y[2] - x[2]));
        normal[2] = ((y[0] - x[0]) * (z[1] - x[1])) - ((z[0] - x[0]) * (y[1] - x[1]));
        return normal;
    }
}
