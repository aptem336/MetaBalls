/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere;

/**
 *
 * @author aptem
 */
public class Impulse {

    public double x;
    public double y;
    public double z;

    Impulse(double value, double angleV, double angleH) {
        x = (double) (value * Math.sin(Math.toRadians(angleH)) * Math.cos(Math.toRadians(angleV)));
        y = (double) (value * Math.sin(Math.toRadians(angleH)) * Math.sin(Math.toRadians(angleV)));
        z = (double) (value * Math.cos(Math.toRadians(angleH)));
    }
}
