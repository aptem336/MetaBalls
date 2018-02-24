package org.yourorghere;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.media.opengl.GLAutoDrawable;

public class Listener implements KeyListener, MouseListener, MouseWheelListener {

    Point loc = new Point(0, 0);

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_1) {
            Data.typeCalc = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_2) {
            Data.typeCalc = 1;
        }
        if (e.getKeyCode() == KeyEvent.VK_3) {
            Data.typeCalc = 2;
        }
        if (e.getKeyCode() == KeyEvent.VK_CAPS_LOCK) {
            MetaBalls.draw = !MetaBalls.draw;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            MetaBalls.run = !MetaBalls.run;
        }
    }

    public void mouseWheelMoved(MouseWheelEvent e) {
        int sign = e.getWheelRotation();
        Data.tempCountStep = Math.max(10, Data.tempCountStep - (10 * sign));
        Data.tempCountStep = Math.min(200, Data.tempCountStep);
        Data.stepChanged = true;
    }

    public void mouseReleased(MouseEvent e) {
        Setting.readData();
        MetaBalls.setting.show();
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_Q) {
            Data.speedCoef = Math.max(0, Data.speedCoef - 1);
        }
        if (e.getKeyCode() == KeyEvent.VK_E) {
            Data.speedCoef = Math.min(20, Data.speedCoef + 1);
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            Data.sizeOfBall = Math.max(1, Data.sizeOfBall - 1);
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            Data.sizeOfBall = Math.min(50, Data.sizeOfBall + 1);
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            Data.countOfBall = Math.max(1, Data.countOfBall - 1);
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            Data.countOfBall = Math.min(50, Data.countOfBall + 1);
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void init(GLAutoDrawable drawable) {
    }

    public void display(GLAutoDrawable drawable) {
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

}
