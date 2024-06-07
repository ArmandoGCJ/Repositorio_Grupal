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

    public GamePanel() {
        setBackground(Color.BLACK);
        setFocusable(true);
        container = new Container();
        addKeyListener(this);

        //Tiempo de refresco del juego
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

        //Tiempo para que los enemigos disparen
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
        if (keysPressed.contains(KeyEvent.VK_A)&& !isPaused) {
            container.moveLeft(10);
        }
        if (keysPressed.contains(KeyEvent.VK_D)&& !isPaused) {
            container.moveRight(10);
        }
        repaint();
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
        g.drawRect(1, 25, container.lifeHero() + 3, 20);
        g.setColor(Color.GREEN);
        g.fillRect(3, 27, container.lifeHero(), 16);
        if (container.lifeHero() <= 0) {
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
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!gameActive)
            return;
        keysPressed.add(e.getKeyCode());

        if (e.getKeyCode() == KeyEvent.VK_SPACE && !isPaused) {
            container.setBulletHero();
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
