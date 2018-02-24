package org.yourorghere;

public class Util {

    final static double PLUS90 = Math.toRadians(90);

    public static double[][] realVertexS(double[][] offset, double x, double y) {
        double[][] vertex = new double[offset.length][2];
        for (int i = 0; i < offset.length; i++) {
            vertex[i] = vertex(offset[i], x, y);
        }
        return vertex;
    }

    private static double[] vertex(double[] offset, double x, double y) {
        double[] vertex = new double[2];
        vertex[0] = offset[0] + x;
        vertex[1] = offset[1] + y;
        return vertex;
    }

    public static double[][] inscribedInOval(double w, double h, int countStep, double startAngle) {
        double[][] offset = new double[countStep][2];
        w /= Math.cos(Util.PLUS90 * 2 / countStep);
        h /= Math.cos(Util.PLUS90 * 2 / countStep);
        for (int i = 0; i < countStep; i++) {
            double angle = (Math.toRadians((double) i / countStep * 360d + startAngle));
            offset[i][0] = round((Math.cos(angle) * w), 5);
            offset[i][1] = round((Math.sin(angle) * h), 5);
        }
        return offset;
    }

    public static double[][] correctPolygon(double w, double h, int countVertex) {
        if (countVertex % 2 != 0) {
            return inscribedInOval(w, h, countVertex, 0);
        } else {
            return inscribedInOval(w, h, countVertex, 360 / (2 * countVertex));
        }
    }

    public static double round(double value, int scale) {
        return Math.round(value * Math.pow(10, scale)) / Math.pow(10, scale);
    }
}
