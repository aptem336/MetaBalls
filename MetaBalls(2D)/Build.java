package org.yourorghere;

import java.awt.Color;
import javax.media.opengl.GL;
import static org.yourorghere.MetaBalls.gl;

public class Build {

    public static void buildObject(double[][] vertex, Color color) {
        gl.glPushMatrix();
        gl.glColor3f(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f);
        gl.glBegin(GL.GL_POLYGON);
        for (double[] vertex1 : vertex) {
            gl.glVertex2d(vertex1[0], vertex1[1]);
        }
        gl.glEnd();
        gl.glPopMatrix();
    }

    public static void buildCircuit(double[][] vertex, Color color) {
        GL gl = MetaBalls.gl;
        gl.glPushMatrix();
        gl.glColor4f(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha() / 255f);
        gl.glBegin(GL.GL_LINE_LOOP);
        for (double[] vertex1 : vertex) {
            gl.glVertex2d(vertex1[0], vertex1[1]);
        }
        gl.glEnd();
        gl.glPopMatrix();
    }

    public static void buildLine(double[][] vertex, Color color) {
        GL gl = MetaBalls.gl;
        gl.glPushMatrix();
        gl.glColor4f(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha() / 255f);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2d(vertex[0][0], vertex[0][1]);
        gl.glVertex2d(vertex[1][0], vertex[1][1]);
        gl.glEnd();
        gl.glPopMatrix();
    }

    public static void buildPoint(double[] vertex, Color color) {
        GL gl = MetaBalls.gl;
        gl.glPushMatrix();
        gl.glColor4f(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha() / 255f);
        gl.glBegin(GL.GL_POINTS);
        gl.glVertex2d(vertex[0], vertex[1]);
        gl.glEnd();
        gl.glPopMatrix();
    }
}
