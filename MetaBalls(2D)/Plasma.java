package org.yourorghere;

public class Plasma {

    private static double[][][] mP;
    private static double[][] mV;
    private static double step;
    private static boolean interpolate;

    public static void draw() {
        switch (Data.typeCalc) {
            case 0:
                Plasma.blocks();
                break;
            case 1:
                interpolate = false;
                Plasma.marchingSquares();
                break;
            case 2:
                interpolate = true;
                Plasma.marchingSquares();
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
                double[] P = mP[i][j];
                if (mV[i][j] >= 1) {
                    Build.buildCircuit(Util.realVertexS(Util.correctPolygon(step, step, 4), P[0], P[1]), Data.metaC);
                }
            }
        }
    }

    private static void marchingSquares() {
        if (MetaBalls.draw) {
            drawGrid();
        }
        calcFuncValues();
        for (int i = 0; i < Data.countStep; i++) {
            for (int j = 0; j < Data.countStep; j++) {
                buildSquare(i, j);
            }
        }
    }

    private static double[] interpolate(double loc1[], double[] loc2, double value1, double value2) {
        if (!interpolate) {
            return new double[]{(loc1[0] + loc2[0]) / 2, (loc1[1] + loc2[1]) / 2};
        } else {
            double[] P = new double[2];
            if (Math.abs(1 - value1) < 0.0005) {
                return loc1;
            } else if (Math.abs(1 - value2) < 0.0005) {
                return loc2;
            } else if (Math.abs(value1 - value2) < 0.0005) {
                return new double[]{(loc1[0] + loc2[0]) / 2, (loc1[1] + loc2[1]) / 2};
            } else {
                double value = (1 - value2) / (value1 - value2);
                P[0] = loc2[0] + value * (loc1[0] - loc2[0]);
                P[1] = loc2[1] + value * (loc1[1] - loc2[1]);
            }
            return P;
        }
    }

    private static int cubeIndex(double[] grid) {
        int cubeIndex = 0;
        if (grid[0] < 1) {
            cubeIndex |= 1;
        }
        if (grid[1] < 1) {
            cubeIndex |= 2;
        }
        if (grid[2] < 1) {
            cubeIndex |= 4;
        }
        if (grid[3] < 1) {
            cubeIndex |= 8;
        }
        return cubeIndex;
    }

    private static double[][] gridPoints(int i, int j) {
        double[][] gridP = new double[4][2];
        gridP[0] = mP[i][j];
        gridP[1] = mP[i + 1][j];
        gridP[2] = mP[i + 1][j + 1];
        gridP[3] = mP[i][j + 1];

        return gridP;
    }

    private static double[] gridValues(int i, int j) {
        double[] gridV = new double[4];
        gridV[0] = mV[i][j];
        gridV[1] = mV[i + 1][j];
        gridV[2] = mV[i + 1][j + 1];
        gridV[3] = mV[i][j + 1];
        return gridV;
    }

    private static void buildSquare(int i, int j) {
        double[][] gridP = gridPoints(i, j);
        double[] gridV = gridValues(i, j);
        int cubeIndex = cubeIndex(gridV);
        int edges = Tables.edgeTable[cubeIndex];
        if (edges != 0) {
            double[][] vertList = new double[4][2];
            if ((edges & 1) > 0) {
                vertList[0] = interpolate(gridP[0], gridP[1], gridV[0], gridV[1]);
            }
            if ((edges & 2) > 0) {
                vertList[1] = interpolate(gridP[1], gridP[2], gridV[1], gridV[2]);
            }
            if ((edges & 4) > 0) {
                vertList[2] = interpolate(gridP[2], gridP[3], gridV[2], gridV[3]);
            }
            if ((edges & 8) > 0) {
                vertList[3] = interpolate(gridP[3], gridP[0], gridV[3], gridV[0]);
            }
            double[][][] lines = new double[2][2][2];
            int nlines = 0;
            for (i = 0; Tables.lineTable[cubeIndex][i] != -1; i += 2) {
                lines[nlines][0] = vertList[Tables.lineTable[cubeIndex][i]];
                lines[nlines][1] = vertList[Tables.lineTable[cubeIndex][i + 1]];
                nlines++;
            }
            for (j = 0; j < nlines; j++) {
                Build.buildLine(lines[j], Data.metaC);
            }

        }
    }

    public static void calcGrid() {
        step = (double) (Data.SIZES.width * 2d / Data.countStep);
        mP = new double[Data.countStep + 1][Data.countStep + 1][2];
        for (int i = 0; i < Data.countStep + 1; i++) {
            for (int j = 0; j < Data.countStep + 1; j++) {
                double[] P = {i * step - Data.SIZES.width, j * step - Data.SIZES.height};
                mP[i][j] = P;
            }
        }
    }

    private static void calcFuncValues() {
        mV = new double[Data.countStep + 1][Data.countStep + 1];
        for (int i = 0; i < Data.countStep + 1; i++) {
            for (int j = 0; j < Data.countStep + 1; j++) {
                mV[i][j] = func(mP[i][j]);
            }
        }
    }

    private static void drawGrid() {
        double countDraw = 11;
        double stepDraw = Data.SIZE * 2 / (countDraw - 1);
        for (int i = 0; i < countDraw; i++) {
            Build.buildLine(new double[][]{{i * stepDraw - Data.SIZES.width, -Data.SIZES.height}, {i * stepDraw - Data.SIZES.width, Data.SIZES.height}}, Data.gridC);
        }
        for (int i = 0; i < countDraw; i++) {
            Build.buildLine(new double[][]{{-Data.SIZES.width, i * stepDraw - Data.SIZES.height}, {Data.SIZES.width, i * stepDraw - Data.SIZES.height}}, Data.gridC);
        }
    }

    private static double func(double[] pointLoc) {
        double sum = 0;
        for (Circle Ball : Data.Balls) {
            sum += Math.pow(Data.sizeOfBall - 2, 2) / ((Math.pow(pointLoc[0] - Ball.x, 2) + Math.pow(pointLoc[1] - Ball.y, 2)));
        }
        return sum;
    }
}
