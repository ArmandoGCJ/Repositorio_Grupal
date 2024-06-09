package ec.edu.uce.ProyectoJuego.view;

import ec.edu.uce.ProyectoJuego.controller.Container;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class GamePanel extends JPanel implements KeyListener {
    private Container container;
    private Timer gameTimer;
    private Timer enemyBulletTimer;
    private boolean isPaused = false;
    private boolean gameActive = true;
    private Set<Integer> keysPressed = new HashSet<>();
    private Timer timer;
    private final int RED_LINE_Y = 400; // y-coordinate for the red line

    public GamePanel() {
        setBackground(Color.BLACK);
        setFocusable(true);
        requestFocusInWindow(); // Ensure the panel gets focus
        addKeyListener(this);
        container = new Container();
        container.serverConnection();

        // Game timer for refreshing the game
        gameTimer = new Timer(1000 / 60, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPaused) {
                    container.moveDown((int) 1f);
                    container.moveUpAndDown(10);
                    container.update();

                    //Actualizar estado del Jugador
                    container.updateUserData();

                    // Check if any enemy has crossed the red line
                    if (container.isEnemyBeyondLine(RED_LINE_Y)) {
                        gameActive = false;
                    }

                    repaint();
                }
            }
        });
        gameTimer.start();


        // Timer for enemy bullets
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
        timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateGame();
            }
        });
        timer.start();
    }

    private void updateGame() {
        if (!gameActive)
            return;
        if (keysPressed.contains(KeyEvent.VK_A) && !isPaused) {
            container.moveLeft(10);
        }
        if (keysPressed.contains(KeyEvent.VK_D) && !isPaused) {
            container.moveRight(10);
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        container.draw(g);

        // Draw the red line
        g.setColor(Color.RED);
        g.drawLine(0, RED_LINE_Y, getWidth(), RED_LINE_Y);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        g.drawString("Score: " + container.score(), 670, 20);

        g.setColor(Color.WHITE);
        g.drawRect(1, 25, 103, 20);
        g.setColor(Color.GREEN);
        g.fillRect(3, 27, container.lifeHero(), 16);
        if (container.lifeHero() <= 0 || !gameActive) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Times New Roman", Font.PLAIN, 62));
            g.drawString("GAME OVER", (getWidth() / 3), getHeight() / 2);
            gameActive = false;
        }

        if (isPaused) {
            g.setColor(Color.YELLOW);
            g.setFont(new Font("Times New Roman", Font.PLAIN, 62));
            g.drawString("PAUSED", (int) (getWidth() / 3), getHeight() / 2);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!gameActive)
            return;
        keysPressed.add(e.getKeyCode());

        if (e.getKeyCode() == KeyEvent.VK_SPACE && !isPaused) {
            container.setBulletHero();
            repaint();
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            togglePause();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keysPressed.remove(e.getKeyCode());
    }

    private void togglePause() {
        isPaused = !isPaused;
        repaint();
    }
}
