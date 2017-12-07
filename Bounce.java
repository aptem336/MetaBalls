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
public class Bounce {

    public static void Bounce(Sphere thisBall) {
        reboundOfWall(thisBall);
        move(thisBall);
    }

    private static void reboundOfWall(Sphere thisBall) {
        if (Math.abs(thisBall.x) + Data.sizeOfBall >= Data.SIZES.width - 20) {
            thisBall.speedX = -thisBall.speedX;
        }
        if (Math.abs(thisBall.y) + Data.sizeOfBall >= Data.SIZES.height - 20) {
            thisBall.speedY = -thisBall.speedY;
        }
        if (Math.abs(thisBall.z) + Data.sizeOfBall >= Data.SIZES.depth - 20) {
            thisBall.speedZ = -thisBall.speedZ;
        }
    }

    private static void move(Sphere thisBall) {
        double IX = 0, IY = 0, IZ = 0;
        for (Impulse impulse : thisBall.impulseS) {
            IX += impulse.x;
            IY += impulse.y;
            IZ += impulse.z;
        }
        thisBall.impulseS.clear();
        thisBall.speedX += IX / 1000;
        thisBall.speedY += IY / 1000;
        thisBall.speedZ += IZ / 1000;
        thisBall.x += thisBall.speedX * Data.speedCoef;
        thisBall.y += thisBall.speedY * Data.speedCoef;
        thisBall.z += thisBall.speedZ * Data.speedCoef;
    }
}
