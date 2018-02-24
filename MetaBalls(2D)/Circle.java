package org.yourorghere;

import java.util.ArrayList;

public class Circle {

    double x, y;

    double speedX, speedY;
    ArrayList<Impulse> impulseS = new ArrayList<Impulse>();

    Circle(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void addImpulse(final double value, final double angle) {
        impulseS.add(new Impulse(value, angle));
    }
}
