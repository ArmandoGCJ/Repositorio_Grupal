package ec.edu.uce.ProyectoJuego.view;

import ec.edu.uce.ProyectoJuego.controller.Container;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener {
    private Container container;
    private Timer gameTimer;
    private Timer enemyBulletTimer;
    private boolean isPaused = false;
    private boolean gameActive = true;

    public GamePanel() {
        setBackground(Color.BLACK);
        setFocusable(true);
        container = new Container();
        addKeyListener(this);

        gameTimer = new Timer(1000 / 60, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPaused) {
                    container.update();
                    repaint();
                }
            }
        });
        gameTimer.start();

        enemyBulletTimer = new Timer(2500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPaused) {
                    container.setBulletEnemy();
                    repaint();
                }
            }
        });
        enemyBulletTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        container.draw(g);

        int yLinea = (int) (getHeight() * 0.76);
        g.setColor(Color.RED);
        g.drawLine(0, yLinea, getWidth(), yLinea);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        g.drawString("Score: " + container.score(), 670, 20);
        g.drawString("Hola amigo", 0, 20);

        g.setColor(Color.WHITE);
        g.drawRect(1, 25, container.life() + 3, 20);
        g.setColor(Color.GREEN);
        g.fillRect(3, 27, container.life(), 16);
        if (container.life() <= 0) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Times New Roman", Font.PLAIN, 62));
            g.drawString("GAME OVER", (getWidth() / 3), getHeight() / 2);
            gameActive = false;

        }

        if (isPaused) {
            g.setColor(Color.YELLOW);
            g.setFont(new Font("Times New Roman", Font.PLAIN, 62));
            g.drawString("PAUSED", (getWidth() / 3), getHeight() / 2);
        }


    }

    @Override
    public void keyTyped(KeyEvent e) {}

    private boolean isMovingLeft = false;
    private boolean isMovingRight = false;

    @Override
    public void keyPressed(KeyEvent e) {
        if (!gameActive)
            return;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                isMovingLeft = true;
                break;
            case KeyEvent.VK_D:
                isMovingRight = true;
                break;
            case KeyEvent.VK_SPACE:
                if (!isPaused) {
                    container.setBulletHero();
                }
                break;
            case KeyEvent.VK_ESCAPE:
                togglePause();
                break;
        }
        updateCharacterMovement();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                isMovingLeft = false;
                break;
            case KeyEvent.VK_D:
                isMovingRight = false;
                break;
        }
        updateCharacterMovement();
    }

    private void updateCharacterMovement() {
        int moveAmount = 20;
        if (!isPaused) {
            if (isMovingLeft) {
                container.moveLeft(moveAmount);
            }
            if (isMovingRight) {
                container.moveRight(moveAmount);
            }
            repaint();
        }
    }

    private void togglePause() {
        isPaused = !isPaused;
        repaint();
    }
}
