package org.yourorghere;

public class Impulse {

    public double x;
    public double y;
    public double value;
    public double angle;

    Impulse(double value, double angle) {
        this.angle = angle;
        this.value = value;
        x = (double) (value * Math.cos(Math.toRadians(angle)));
        y = (double) (value * Math.sin(Math.toRadians(angle)));
    }
}
