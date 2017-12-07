/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere;

import java.util.ArrayList;

/**
 *
 * @author aptem
 */
public class Sphere {

    double x, y, z;

    double speedX, speedY, speedZ;
    ArrayList<Impulse> impulseS = new ArrayList<Impulse>();

    Sphere(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void addImpulse(final double value, final double angleV, double angleH) {
        impulseS.add(new Impulse(value, angleV, angleH));
    }
}
