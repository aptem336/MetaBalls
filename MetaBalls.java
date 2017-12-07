package org.yourorghere;

import com.sun.opengl.util.GLUT;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

public class MetaBalls implements GLEventListener {

    static GL gl;
    static GLU glu;
    static GLUT glut;
    static Interface Interface;
    static Setting setting;

    static long millis1;
    static long millis0;
    static int fps;
    static int frames = 0;

    static boolean draw = false;
    static boolean run = true;

    public static void main(String[] args) {
        Interface = new Interface();
        setting = new Setting();
        Plasma.calcGrid();
    }

    public void init(GLAutoDrawable drawable) {
        gl = drawable.getGL();
        glu = new GLU();
        glut = new GLUT();
        System.err.println("INIT GL IS: " + gl.getClass().getName());

        gl.glEnable(GL.GL_LIGHTING);
        gl.glEnable(GL.GL_LIGHT0);
        gl.glLightiv(GL.GL_LIGHT0, GL.GL_POSITION, new int[]{1, 1, 1, 0}, 0);

        gl.glEnable(GL.GL_COLOR_MATERIAL);
        gl.glEnable(GL.GL_NORMALIZE);

        gl.glAlphaFunc(GL.GL_GREATER, 0);
        gl.glEnable(GL.GL_BLEND);
        gl.glEnable(GL.GL_ALPHA_TEST);
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
        gl.glEnable(GL.GL_DEPTH_TEST);

        gl.setSwapInterval(1);
        gl.glClearColor(0.3f, 0.3f, 0.3f, 0.0f);
        gl.glShadeModel(GL.GL_SMOOTH);
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        final double h = (double) width / (double) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 10000.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void display(GLAutoDrawable drawable) {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        gl.glTranslatef(0, 0, 0);

        Util.calcCam();
        glu.gluLookAt(Data.xView, Data.yView, Data.zView, 0, 0, 0, 0, 0.5, 0);

//<editor-fold defaultstate="collapsed" desc="Надписи">
        gl.glColor4f(1f, 1f, 1f, 0.6f);
        gl.glWindowPos2i(20, 20);
        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "Click to open a setting and controls");
        gl.glWindowPos2i(drawable.getWidth() - 150, drawable.getHeight() - 20);
        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "Caps    - visible");
        gl.glWindowPos2i(drawable.getWidth() - 150, drawable.getHeight() - 40);
        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "Space  - pause");
        gl.glWindowPos2i(drawable.getWidth() - 150, drawable.getHeight() - 60);
        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "Arrows - camera");
        gl.glWindowPos2i(drawable.getWidth() - 150, drawable.getHeight() - 80);
        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "Escape - reset");
        gl.glWindowPos2i(drawable.getWidth() - 150, drawable.getHeight() - 100);
        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "Enter - typeDraw");

        gl.glColor4f(1f, 1f, 0f, 0.6f);
        gl.glWindowPos2i(20, drawable.getHeight() - 20);
        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "CountStep:  " + Data.countStep);
        gl.glWindowPos2i(20, drawable.getHeight() - 40);
        String typeCalcName = "";
        switch (Data.typeCalc) {
            case 0:
                typeCalcName = "Blocks";
                break;
            case 1:
                typeCalcName = "Without LI";
                break;
            case 2:
                typeCalcName = "With LI";
                break;
        }
        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "TypeCalc:  " + typeCalcName);
        gl.glWindowPos2i(20, drawable.getHeight() - 60);
        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "SpeedBalls:  " + Data.speedCoef);
        gl.glWindowPos2i(20, drawable.getHeight() - 80);
        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "SizeBalls:  " + Data.sizeOfBall);
        gl.glWindowPos2i(20, drawable.getHeight() - 100);
        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "CountBalls:  " + Data.countOfBall);

        frames++;
        millis1 = System.currentTimeMillis();
        if (millis1 - millis0 >= 1000) {
            fps = frames;
            millis0 = millis1;
            frames = 0;
        }
        gl.glWindowPos2i(drawable.getWidth() - 90, 20);
        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "FPS:  " + fps);
//</editor-fold>

        for (Sphere sphere : Data.Balls) {
            if (run) {
                Bounce.Bounce(sphere);
            }
            if (draw) {
                Build.buildSphere(sphere.x, sphere.y, sphere.z, Data.sizeOfBall, Data.circC);
            }
        }
        Plasma.draw();
        Data.dataSynchronized();

        gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}
