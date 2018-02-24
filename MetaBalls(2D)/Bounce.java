package org.yourorghere;

public class Bounce {

    public static void Bounce(Circle thisBall) {
        reboundOfWall(thisBall);
        move(thisBall);
    }

    private static void reboundOfWall(Circle thisBall) {
        if (Math.abs(thisBall.x) + Data.sizeOfBall >= Data.SIZES.width) {
            thisBall.speedX = -thisBall.speedX;
        }
        if (Math.abs(thisBall.y) + Data.sizeOfBall >= Data.SIZES.height) {
            thisBall.speedY = -thisBall.speedY;
        }
    }

    private static void move(Circle thisBall) {
        double IX = 0, IY = 0;
        for (Impulse impulse : thisBall.impulseS) {
            IX += impulse.x;
            IY += impulse.y;
        }
        thisBall.impulseS.clear();
        thisBall.speedX += IX / 1000;
        thisBall.speedY += IY / 1000;
        thisBall.x += thisBall.speedX * Data.speedCoef;
        thisBall.y += thisBall.speedY * Data.speedCoef;
    }
}
