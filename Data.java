/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author aptem
 */
public class Data {

    final static int SIZE = 120;
    final static Dimension SIZES = new Dimension(SIZE, SIZE, SIZE);

    static boolean stepChanged = true;

    static Color circC = new Color(255, 0, 0, 255);
    static Color metaC = new Color(238, 238, 224, 255);
    static Color gridC = new Color(255, 255, 255, 100);

    static int countStep = 50;
    static int tempCountStep = 50;
    static int typeCalc = 2;
    static int speedCoef = 5;
    static int sizeOfBall = 30;
    static int countOfBall = 3;

    static int angleH = 60;
    static int angleV = 90;
    static int lenToCenter = 500;

    public static double xView, zView, yView;

    static ArrayList<Sphere> Balls = new ArrayList<Sphere>();

    public static void dataSynchronized() {
        if (stepChanged) {
            countStep = tempCountStep;
            Plasma.calcGrid();
            stepChanged = false;
        }
        if (Data.Balls.size() < countOfBall) {
            addBall();
        } else if (Data.Balls.size() > countOfBall) {
            removeBall();
        }
    }

    private static void addBall() {
        Sphere temp = new Sphere(0, 0, 0);
        temp.addImpulse(100, Math.random() * 360, Math.random() * 360);
        Balls.add(temp);
    }

    private static void removeBall() {
        Balls.remove(Balls.size() - 1);
    }
}
