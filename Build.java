/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere;

import java.awt.Color;
import javax.media.opengl.GL;
import static org.yourorghere.MetaBalls.gl;
import static org.yourorghere.MetaBalls.glut;

/**
 *
 * @author aptem
 */
public class Build {

    public static void buildSphere(double x, double y, double z, double size, Color color) {
        gl.glPushMatrix();
        gl.glTranslated(x, y, z);
        gl.glColor4f(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha() / 255f);
        glut.glutSolidSphere(size, 15, 15);
        gl.glPopMatrix();
    }

    public static void buildCube(double x, double y, double z, double size, Color color) {
        gl.glPushMatrix();
        gl.glTranslated(x, y, z);
        gl.glColor4f(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha() / 255f);
        if (Plasma.polygon) {
            glut.glutSolidCube((float) size);
        } else {
            glut.glutWireCube((float) size);
        }
        gl.glPopMatrix();
    }

    public static void buildTriangle(double[][] vertex, Color color) {
        gl.glPushMatrix();
        gl.glColor4f(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha() / 255f);
        double[] normal = Util.normal(vertex[0], vertex[1], vertex[2]);
        gl.glNormal3d(normal[0], -normal[1], normal[2]);
        gl.glBegin(GL.GL_POLYGON);
        for (double[] currentVertex : vertex) {
            gl.glVertex3d(currentVertex[0], currentVertex[1], currentVertex[2]);
        }
        gl.glEnd();
        gl.glPopMatrix();
    }

    public static void buildCircuit(double[][] vertex, Color color) {
        GL gl = MetaBalls.gl;
        gl.glPushMatrix();
        gl.glColor4f(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha() / 255f);
        gl.glNormal3d(1, 1, 1);
        gl.glBegin(GL.GL_LINE_LOOP);
        for (double[] vertex1 : vertex) {
            gl.glVertex3d(vertex1[0], vertex1[1], vertex1[2]);
        }
        gl.glEnd();
        gl.glPopMatrix();
    }

    public static void buildLine(double[][] vertex, Color color) {
        GL gl = MetaBalls.gl;
        gl.glPushMatrix();
        gl.glColor4f(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha() / 255f);
        gl.glNormal3d(1, 1, 1);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3d(vertex[0][0], vertex[0][1], vertex[0][2]);
        gl.glVertex3d(vertex[1][0], vertex[1][1], vertex[1][2]);
        gl.glEnd();
        gl.glPopMatrix();
    }

    public static void buildPoint(double[] vertex, Color color) {
        GL gl = MetaBalls.gl;
        gl.glPushMatrix();
        gl.glColor4f(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha() / 255f);
        gl.glBegin(GL.GL_POINTS);
        gl.glVertex3d(vertex[0], vertex[1], vertex[2]);
        gl.glEnd();
        gl.glPopMatrix();
    }
}
