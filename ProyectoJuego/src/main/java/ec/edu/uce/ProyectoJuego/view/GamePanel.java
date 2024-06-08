package ec.edu.uce.ProyectoJuego.view;

import ec.edu.uce.ProyectoJuego.controller.Container;
import ec.edu.uce.ProyectoJuego.model.User;

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
    JLabel scoreLabel;
    JLabel nameLabel;
    User u = new User();

    public GamePanel() {
        setBackground(Color.BLACK);
        setFocusable(true);
        setLayout(null);
        container = new Container();
        addKeyListener(this);

        scoreLabel = new JLabel("Score: " + container.score());
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("Times New Roman", Font.PLAIN,20));
        Dimension size = scoreLabel.getPreferredSize();
        scoreLabel.setBounds(800 - size.width-60, 0, size.width, size.height);
        add(scoreLabel);

        nameLabel = new JLabel();
        nameLabel.setText(u.getName());
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Times New Roman", Font.PLAIN,20));
        Dimension sizeN = nameLabel.getPreferredSize();
        nameLabel.setBounds(30, 0, size.width, size.height);
        add(nameLabel);


        //Tiempo de refresco del juego
        gameTimer = new Timer(1000 / 60, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPaused) {
                    //Mover hacia abajo los enemigos
                    container.moveDown((int) 0.9f);
                    //Mover hacia abajo y hacia arriba las balas
                    container.moveUpAndDown(10);

                    container.serverConnection();

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

      /*  g.setColor(Color.WHITE);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        g.drawString("Score: " + container.score(), 670, 20);
        g.drawString("Hola amigo", 0, 20);*/

        g.setColor(Color.WHITE);
        g.drawRect(1, 25, 103, 20);
        g.setColor(Color.GREEN);
        g.fillRect(3, 27, container.lifeHero(), 16);
        if (container.lifeHero() <= 0) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Times New Roman", Font.PLAIN, 62));
            g.drawString("GAME OVER", (getWidth() / 3 ), getHeight() / 2);
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
