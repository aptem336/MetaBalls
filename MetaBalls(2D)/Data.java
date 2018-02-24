package org.yourorghere;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

public class Data {

    final static int SIZE = 80;
    final static Dimension SIZES = new Dimension(SIZE, SIZE);

    static boolean stepChanged = true;

    static Color circC = new Color(255, 0, 0, 255);
    static Color metaC = new Color(238, 238, 224, 255);
    static Color gridC = new Color(255, 255, 255, 100);

    static int countStep = 30;
    static int tempCountStep = 30;
    static int typeCalc = 2;
    static int speedCoef = 5;
    static int sizeOfBall = 20;
    static int countOfBall = 5;

    static ArrayList<Circle> Balls = new ArrayList<Circle>();

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
        Circle temp = new Circle(0, 0);
        temp.addImpulse(100, Math.random() * 360);
        Balls.add(temp);
    }

    private static void removeBall() {
        Balls.remove(Balls.size() - 1);
    }
}
