package org.yourorghere;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Setting extends JFrame {

    private static JButton ok;
    private static JButton controls;

    private static JSlider countStepSlider;
    private static JSlider typeSlider;
    private static JSlider speedSlider;
    private static JSlider sizeSlider;
    private static JSlider countSlider;

    private static JLabel countStepLabel;
    private static JLabel typeLabel;
    private static JLabel speedLabel;
    private static JLabel sizeLabel;
    private static JLabel countLabel;

    private final static Dimension SLIDER_SIZE = new Dimension(300, 20);
    private final static Dimension LABEL_SIZE = new Dimension(100, 20);

    public Setting() {
        initComponent();
    }

    public static void readData() {
        countStepSlider.setValue(Data.countStep);
        typeSlider.setValue(Data.typeCalc);
        speedSlider.setValue((int) (Data.speedCoef));
        sizeSlider.setValue((int) (Data.sizeOfBall));
        countSlider.setValue(Data.countOfBall);
        setText();
    }

    private static void setText() {
        countStepLabel.setText("CountStep   " + Data.countStep);
        typeLabel.setText("TypeCalc   " + Data.typeCalc);
        speedLabel.setText("SpeedBalls   " + (int) (Data.speedCoef));
        sizeLabel.setText("SizeBalls   " + (int) (Data.sizeOfBall));
        countLabel.setText("CountBalls   " + Data.countOfBall);
    }

    private void initComponent() {
        setType(Type.UTILITY);
        setTitle("Setting");
        setLayout(null);
        setSize(500, 400);
        setVisible(false);
        setResizable(false);
        setLocationRelativeTo(null);

        ok = new JButton("OK");
        ok.setSize(90, 25);
        ok.setLocation((getWidth() - ok.getWidth()) / 2, getHeight() - ok.getHeight() - 30);
        ok.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
                hide();
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        });
        controls = new JButton("Controls");
        controls.setSize(90, 25);
        controls.setLocation((getWidth() - controls.getWidth()) / 2, ok.getY() - controls.getHeight());
        controls.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
                JOptionPane.showMessageDialog(null,
                        " MouseWheel - CountStep \n"
                        + " 1, 2, 3 - TypeCalc \n"
                        + " Q <> E - SpeedBalss \n"
                        + " A <> D - SizeBalls \n"
                        + " W <> H - CountBalls \n", "Controls", 1);
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        });

        add(controls);
        add(ok);
        repaint();

        countStepSlider = new JSlider();
        countStepSlider.setSize(SLIDER_SIZE);
        countStepSlider.setLocation((getWidth() - countStepSlider.getWidth()) / 2, countStepSlider.getHeight() + 20);
        countStepSlider.setMinimum(10);
        countStepSlider.setMaximum(200);
        countStepSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                Data.tempCountStep = countStepSlider.getValue();
                countStepLabel.setText("CountStep " + Data.tempCountStep);
                Data.stepChanged = true;
            }
        });
        countStepLabel = new JLabel();
        countStepLabel.setSize(LABEL_SIZE);
        countStepLabel.setLocation((getWidth() - countStepLabel.getWidth()) / 2, countStepSlider.getY() - 25);

        typeSlider = new JSlider();
        typeSlider.setSize(SLIDER_SIZE);
        typeSlider.setLocation((getWidth() - typeSlider.getWidth()) / 2, countStepSlider.getY() + countStepSlider.getHeight() + typeSlider.getHeight() + 20);
        typeSlider.setMinimum(0);
        typeSlider.setMaximum(2);
        typeSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                Data.typeCalc = typeSlider.getValue();
                typeLabel.setText("TypeCalc   " + Data.typeCalc);
            }
        });
        typeLabel = new JLabel();
        typeLabel.setSize(LABEL_SIZE);
        typeLabel.setLocation((getWidth() - typeLabel.getWidth()) / 2, typeSlider.getY() - 25);

        speedSlider = new JSlider();
        speedSlider.setSize(SLIDER_SIZE);
        speedSlider.setLocation((getWidth() - speedSlider.getWidth()) / 2, typeSlider.getY() + typeSlider.getHeight() + speedSlider.getHeight() + 20);
        speedSlider.setMinimum(0);
        speedSlider.setMaximum(20);
        speedSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                Data.speedCoef = speedSlider.getValue();
                speedLabel.setText("SpeedBalls   " + Data.speedCoef);
            }
        });
        speedLabel = new JLabel();
        speedLabel.setSize(LABEL_SIZE);
        speedLabel.setLocation((getWidth() - speedLabel.getWidth()) / 2, speedSlider.getY() - 25);

        sizeSlider = new JSlider();
        sizeSlider.setSize(SLIDER_SIZE);
        sizeSlider.setLocation((getWidth() - sizeSlider.getWidth()) / 2, speedSlider.getY() + speedSlider.getHeight() + sizeSlider.getHeight() + 20);
        sizeSlider.setMinimum(1);
        sizeSlider.setMaximum(50);
        sizeSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                Data.sizeOfBall = sizeSlider.getValue();
                sizeLabel.setText("SizeBalls   " + Data.sizeOfBall);
            }
        });
        sizeLabel = new JLabel();
        sizeLabel.setSize(LABEL_SIZE);
        sizeLabel.setLocation((getWidth() - sizeLabel.getWidth()) / 2, sizeSlider.getY() - 25);

        countSlider = new JSlider();
        countSlider.setSize(SLIDER_SIZE);
        countSlider.setLocation((getWidth() - countSlider.getWidth()) / 2, sizeSlider.getY() + sizeSlider.getHeight() + countSlider.getHeight() + 20);
        countSlider.setMinimum(1);
        countSlider.setMaximum(50);
        countSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                Data.countOfBall = countSlider.getValue();
                countLabel.setText("CountBalls   " + Data.countStep);
            }
        });
        countLabel = new JLabel();
        countLabel.setSize(LABEL_SIZE);
        countLabel.setLocation((getWidth() - countLabel.getWidth()) / 2, countSlider.getY() - 25);

        add(countStepSlider);
        add(typeSlider);
        add(speedSlider);
        add(sizeSlider);
        add(countSlider);

        add(countStepLabel);
        add(typeLabel);
        add(speedLabel);
        add(sizeLabel);
        add(countLabel);

        repaint();
    }
}
