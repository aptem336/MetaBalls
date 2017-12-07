/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere;

import com.sun.opengl.util.Animator;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GLCanvas;
import javax.swing.JFrame;

/**
 *
 * @author aptem
 */
public class Interface extends JFrame {

    static Listener myListener;
    static GLCanvas canvas;
    static Animator animator;

    public Interface() {
        init();
    }

    private void init() {
        setType(Type.UTILITY);
        setSize(1000, 1000);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

        myListener = new Listener();
        canvas = new GLCanvas();
        canvas.addKeyListener(myListener);
        canvas.addMouseListener(myListener);
        canvas.addMouseWheelListener(myListener);
        canvas.addGLEventListener(new MetaBalls());
        canvas.setBounds(0, 0, getWidth(), getHeight() - 30);
        animator = new Animator(canvas);

        add(canvas);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new Thread(new Runnable() {
                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        animator.start();
    }

}
