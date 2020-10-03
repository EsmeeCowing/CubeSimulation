//Checklist FOR SUMMER: organize it into something generalizable MAKE A COPY, camera = moving the world the opposite way

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class CubeSimulation
{
    private MainCanvas mainCanvas;

    public static void main(String[] args){
        new CubeSimulation();
    }

    CubeSimulation() {
        mainCanvas = new MainCanvas();

        JPanel mainPanel = new JPanel();
        mainPanel.add(mainCanvas);

        JFrame myJFrame = new JFrame("Cube!");
        myJFrame.setResizable(false);
        myJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myJFrame.add(mainPanel);
        myJFrame.pack();
        myJFrame.setLocationRelativeTo(null);
        myJFrame.setVisible(true);

        mainCanvas.draw();
    }

    ///////////////////////////////////////////////////////////////////

    class MainCanvas extends ImageCanvas implements KeyListener
    {
        double width;
        double height;
        Cube playerCube;
        double tx;
        double ty;
        double tz;
        double tStep;
        double s;
        double sStep;
        double ax;
        double ay;
        double az;
        double aStep;

        MainCanvas() {
            super(600,600);
            addKeyListener(this);
            width = 600;
            height = 600;
            playerCube = new Cube(4);
            tx = 0;
            ty = 0;
            tz = 20;
            tStep = 0.1;
            s = 1;
            sStep = 1.02;
            ax = 0;
            ay = 0;
            az = 0;
            aStep = 0.02;
        }

        public void draw() {
            clear();
            Graphics2D pen = getPen();
            playerCube.draw(pen, tx, ty, tz, s, ax, ay, az);
            display();
        }

        public void resized(){
            draw();
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER){
                tz -= 3*tStep;
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE){
                tz += 3*tStep;
            }
            if (e.getKeyCode() == KeyEvent.VK_UP){
                ty += tStep;
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN){
                ty -= tStep;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT){
                tx += tStep;
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT){
                tx -= tStep;
            }
            if (e.getKeyCode() == KeyEvent.VK_D){
                ay -= aStep;
            }
            if (e.getKeyCode() == KeyEvent.VK_A){
                ay += aStep;
            }
            if (e.getKeyCode() == KeyEvent.VK_W){
                ax += aStep;
            }
            if (e.getKeyCode() == KeyEvent.VK_S){
                ax -= aStep;
            }
            if (e.getKeyCode() == KeyEvent.VK_E){
                az -= aStep;
            }
            if (e.getKeyCode() == KeyEvent.VK_Q){
                az += aStep;
            }
//            if (e.getKeyCode() == KeyEvent.VK_){ //todo: fix this
//                s*= sStep;
//            }
//            if (e.getKeyCode() == KeyEvent.VK_MINUS){
//                s*= 1/sStep;
//            }

            draw();

        }

        public void keyTyped(KeyEvent e) {}
        public void keyReleased(KeyEvent e) {}
    }



}