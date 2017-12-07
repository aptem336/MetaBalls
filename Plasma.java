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
public class Plasma {

    public static boolean polygon = true;

    private static double step;

    private static double[][][][] mP;
    private static double[][][] mV;

    private static boolean interpolate = true;

    private static final double[][] GRIDP = new double[8][2];
    private static final double[] GRIDV = new double[8];

    private static int cubeIndex;

    private static final double[][][] TRIANGLES = new double[12][3][3];
    private static final double[][] VERTLIST = new double[12][3];

    public static void draw() {
        calcGrid();
        switch (Data.typeCalc) {
            case 0:
                Plasma.blocks();
                break;
            case 1:
                interpolate = false;
                Plasma.marchingCubes();
                break;
            case 2:
                interpolate = true;
                Plasma.marchingCubes();
                break;
        }
    }

    private static void blocks() {
        if (MetaBalls.draw) {
            drawGrid();
        }
        calcFuncValues();
        for (int i = 0; i < Data.countStep + 1; i++) {
            for (int j = 0; j < Data.countStep + 1; j++) {
                for (int k = 0; k < Data.countStep + 1; k++) {
                    double[] P = mP[i][j][k];
                    if (mV[i][j][k] >= 1) {
                        Build.buildCube(P[0], P[1], P[2], step, Data.metaC);
                    }
                }
            }
        }
    }

    private static void marchingCubes() {
        if (MetaBalls.draw) {
            drawGrid();
        }
        calcFuncValues();
        for (int i = 0; i < Data.countStep; i++) {
            for (int j = 0; j < Data.countStep; j++) {
                for (int k = 0; k < Data.countStep; k++) {
                    buildCube(i, j, k);
                }
            }
        }
    }

    private static double[] interpolate(double loc1[], double[] loc2, double value1, double value2) {
        if (!interpolate) {
            return new double[]{(loc1[0] + loc2[0]) / 2, (loc1[1] + loc2[1]) / 2, (loc1[2] + loc2[2]) / 2};
        } else {
            double[] P = new double[3];
            if (Math.abs(1 - value1) < 0.0005) {
                return loc1;
            } else if (Math.abs(1 - value2) < 0.0005) {
                return loc2;
            } else if (Math.abs(value1 - value2) < 0.0005) {
                return loc1;
            } else {
                double value = (1 - value1) / (value2 - value1);
                P[0] = loc1[0] + value * (loc2[0] - loc1[0]);
                P[1] = loc1[1] + value * (loc2[1] - loc1[1]);
                P[2] = loc1[2] + value * (loc2[2] - loc1[2]);
            }
            return P;
        }
    }

    private static void buildCube(int i, int j, int k) {
        gridPoints(i, j, k);
        gridValues(i, j, k);
        cubeIndex();
        int edges = Tables.edgeTable[cubeIndex];
        if (edges != 0) {
            if ((edges & 1) > 0) {
                VERTLIST[0] = interpolate(GRIDP[0], GRIDP[1], GRIDV[0], GRIDV[1]);
            }
            if ((edges & 2) > 0) {
                VERTLIST[1] = interpolate(GRIDP[1], GRIDP[2], GRIDV[1], GRIDV[2]);
            }
            if ((edges & 4) > 0) {
                VERTLIST[2] = interpolate(GRIDP[2], GRIDP[3], GRIDV[2], GRIDV[3]);
            }
            if ((edges & 8) > 0) {
                VERTLIST[3] = interpolate(GRIDP[3], GRIDP[0], GRIDV[3], GRIDV[0]);
            }
            if ((edges & 16) > 0) {
                VERTLIST[4] = interpolate(GRIDP[4], GRIDP[5], GRIDV[4], GRIDV[5]);
            }
            if ((edges & 32) > 0) {
                VERTLIST[5] = interpolate(GRIDP[5], GRIDP[6], GRIDV[5], GRIDV[6]);
            }
            if ((edges & 64) > 0) {
                VERTLIST[6] = interpolate(GRIDP[6], GRIDP[7], GRIDV[6], GRIDV[7]);
            }
            if ((edges & 128) > 0) {
                VERTLIST[7] = interpolate(GRIDP[7], GRIDP[4], GRIDV[7], GRIDV[4]);
            }
            if ((edges & 256) > 0) {
                VERTLIST[8] = interpolate(GRIDP[0], GRIDP[4], GRIDV[0], GRIDV[4]);
            }
            if ((edges & 512) > 0) {
                VERTLIST[9] = interpolate(GRIDP[1], GRIDP[5], GRIDV[1], GRIDV[5]);
            }
            if ((edges & 1024) > 0) {
                VERTLIST[10] = interpolate(GRIDP[2], GRIDP[6], GRIDV[2], GRIDV[6]);
            }
            if ((edges & 2048) > 0) {
                VERTLIST[11] = interpolate(GRIDP[3], GRIDP[7], GRIDV[3], GRIDV[7]);
            }
            int ntriang = 0;
            for (i = 0; Tables.triangleTable[cubeIndex][i] != -1; i += 3) {
                TRIANGLES[ntriang][0] = VERTLIST[Tables.triangleTable[cubeIndex][i]];
                TRIANGLES[ntriang][1] = VERTLIST[Tables.triangleTable[cubeIndex][i + 1]];
                TRIANGLES[ntriang][2] = VERTLIST[Tables.triangleTable[cubeIndex][i + 2]];
                ntriang++;
            }
            for (j = 0; j < ntriang; j++) {
                if (polygon) {
                    Build.buildTriangle(TRIANGLES[j], Data.metaC);
                } else {
                    Build.buildCircuit(TRIANGLES[j], Data.metaC);
                }
            }
        }
    }

    private static void gridPoints(int i, int j, int k) {
        GRIDP[0] = mP[i][j][k];
        GRIDP[1] = mP[i + 1][j][k];
        GRIDP[2] = mP[i + 1][j][k + 1];
        GRIDP[3] = mP[i][j][k + 1];

        GRIDP[4] = mP[i][j + 1][k];
        GRIDP[5] = mP[i + 1][j + 1][k];
        GRIDP[6] = mP[i + 1][j + 1][k + 1];
        GRIDP[7] = mP[i][j + 1][k + 1];
    }

    private static void gridValues(int i, int j, int k) {
        GRIDV[0] = mV[i][j][k];
        GRIDV[1] = mV[i + 1][j][k];
        GRIDV[2] = mV[i + 1][j][k + 1];
        GRIDV[3] = mV[i][j][k + 1];

        GRIDV[4] = mV[i][j + 1][k];
        GRIDV[5] = mV[i + 1][j + 1][k];
        GRIDV[6] = mV[i + 1][j + 1][k + 1];
        GRIDV[7] = mV[i][j + 1][k + 1];
    }

    private static void cubeIndex() {
        cubeIndex = 0;
        if (GRIDV[0] > 1) {
            cubeIndex |= 1;
        }
        if (GRIDV[1] > 1) {
            cubeIndex |= 2;
        }
        if (GRIDV[2] > 1) {
            cubeIndex |= 4;
        }
        if (GRIDV[3] > 1) {
            cubeIndex |= 8;
        }
        if (GRIDV[4] > 1) {
            cubeIndex |= 16;
        }
        if (GRIDV[5] > 1) {
            cubeIndex |= 32;
        }
        if (GRIDV[6] > 1) {
            cubeIndex |= 64;
        }
        if (GRIDV[7] > 1) {
            cubeIndex |= 128;
        }
    }

    public static void calcGrid() {
        step = Data.SIZES.width * 2d / Data.countStep;
        mP = new double[Data.countStep + 1][Data.countStep + 1][Data.countStep + 1][3];
        for (int i = 0; i < Data.countStep + 1; i++) {
            for (int j = 0; j < Data.countStep + 1; j++) {
                for (int k = 0; k < Data.countStep + 1; k++) {
                    double[] P = {i * step - Data.SIZES.width, j * step - Data.SIZES.height, k * step - Data.SIZES.depth};
                    mP[i][j][k] = P;
                }
            }
        }
    }

    private static void calcFuncValues() {
        mV = new double[Data.countStep + 1][Data.countStep + 1][Data.countStep + 1];
        for (int i = 0; i < Data.countStep + 1; i++) {
            for (int j = 0; j < Data.countStep + 1; j++) {
                for (int k = 0; k < Data.countStep + 1; k++) {
                    mV[i][j][k] = func(mP[i][j][k]);
                }
            }
        }
    }

    private static void drawGrid() {
        double countDraw = 11;
        double stepDraw = Data.SIZE * 2 / (countDraw - 1);
        for (int i = 0; i < countDraw; i++) {
            for (int j = 0; j < countDraw; j++) {
                Build.buildLine(new double[][]{{-Data.SIZES.width, i * stepDraw - Data.SIZES.height, j * stepDraw - Data.SIZES.depth}, {Data.SIZES.width, i * stepDraw - Data.SIZES.height, j * stepDraw - Data.SIZES.depth}}, Data.gridC);
            }
        }
        for (int i = 0; i < countDraw; i++) {
            for (int j = 0; j < countDraw; j++) {
                Build.buildLine(new double[][]{{i * stepDraw - Data.SIZES.width, -Data.SIZES.height, j * stepDraw - Data.SIZES.depth}, {i * stepDraw - Data.SIZES.width, Data.SIZES.height, j * stepDraw - Data.SIZES.depth}}, Data.gridC);
            }
        }
        for (int i = 0; i < countDraw; i++) {
            for (int j = 0; j < countDraw; j++) {
                Build.buildLine(new double[][]{{i * stepDraw - Data.SIZES.width, j * stepDraw - Data.SIZES.height, -Data.SIZES.depth}, {i * stepDraw - Data.SIZES.width, j * stepDraw - Data.SIZES.height, Data.SIZES.depth}}, Data.gridC);
            }
        }
    }

    private static double func(double[] pointLoc) {
        double sum = 0;
        for (Sphere Ball : Data.Balls) {
            sum += Math.pow(Data.sizeOfBall, 2) / ((Math.pow(pointLoc[0] - Ball.x, 2) + Math.pow(pointLoc[1] - Ball.y, 2) + (Math.pow(pointLoc[2] - Ball.z, 2))));
        }
        return sum;
    }
}
