package ec.edu.uce.ProyectoJuego.controller;

import ec.edu.uce.ProyectoJuego.model.Bullet;
import ec.edu.uce.ProyectoJuego.model.Enemy;
import ec.edu.uce.ProyectoJuego.model.Hero;
import ec.edu.uce.ProyectoJuego.model.Level;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Container {

    private Hero hero;
    private List<Enemy> enemies;
    private List<Bullet> bullets;
    private List<Bullet> bulletsEnemies;
    private List<Level> levels;
    private int currentLevelIndex = 0;
    private boolean levelMessageDisplayed = false;
    private boolean delayActive = false;

    public Container() {
        hero = new Hero();
        enemies = new ArrayList<>();
        bullets = new ArrayList<>();
        bulletsEnemies = new ArrayList<>();
        levels = new ArrayList<>();

        initializeLevels();
        loadLevel(currentLevelIndex);
    }

    private void initializeLevels() {
        levels.add(new Level(5));  // Nivel 1 con 5 enemigos
        levels.add(new Level(10)); // Nivel 2 con 10 enemigos
        levels.add(new Level(1)); // Nivel 3 con 1 enemigos
        // Puedes agregar más niveles según sea necesario
    }

    private void loadLevel(int levelIndex) {
        if (levelIndex >= levels.size()) {
            System.out.println("¡Has completado todos los niveles!");
            return; // No hacer nada si no hay más niveles
        }

        Level currentLevel = levels.get(levelIndex);
        enemies.clear(); // Limpiar enemigos del nivel anterior
        bullets.clear(); // Limpiar balas del nivel anterior
        bulletsEnemies.clear();

        for (int i = 0; i < currentLevel.getEnemyCount(); i++) {
            if (levelIndex == 2) { // Nivel 3 (índice 2)
                enemies.add(new Enemy(125, 75));
            } else {
                enemies.add(new Enemy());
            }
        }
    }

    public void nextLevel() {
        if (currentLevelIndex < levels.size() - 1) { // Cambiar <= a <
            currentLevelIndex++;
            levelMessageDisplayed = true;
            delayActive = true;
            // Configurar un retraso antes de cargar el siguiente nivel
            int delay = 2000; // 2000 milisegundos = 2 segundos
            Timer timer = new Timer(delay, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    loadLevel(currentLevelIndex);
                    levelMessageDisplayed = false;
                    delayActive = false;
                }
            });
            timer.setRepeats(false); // No repetir, ejecutar una vez
            timer.start();
        }
    }

    public void update() {
        if (!delayActive) {
            chechCollisionsBullet();
            chechCollisionsBulletEnemies();
            if (enemies.isEmpty() && !levelMessageDisplayed) {
                nextLevel();
            }
        }
    }

    public void setBulletHero() {
        bullets.add(new Bullet(hero.getX(), hero.getY()));
    }

    public void setBulletEnemy() {
        //Añado las balas para los enemigos segun el nivel
        for (Enemy enemy : enemies) {
            for (Point position : enemy.getPositions()) {
                if (currentLevelIndex == 0) {
                    //nivel 1
                    bulletsEnemies.add(new Bullet((int) position.getX() + 25, (int) position.getY()));

                } else if (currentLevelIndex == 1) {
                    //nivel 2
                    bulletsEnemies.add(new Bullet((int) position.getX(), (int) position.getY()));
                    bulletsEnemies.add(new Bullet((int) position.getX() + 50, (int) position.getY()));
                } else {
                    //nivel 3
                    bulletsEnemies.add(new Bullet((int) position.getX() + 120, (int) position.getY() + 60));
                    bulletsEnemies.add(new Bullet((int) position.getX(), (int) position.getY() + 60));
                    bulletsEnemies.add(new Bullet((int) position.getX() + 60, (int) position.getY() + 15));
                }
            }
        }
    }

    public void draw(Graphics graphics) {
        hero.draw(graphics);

        for (Enemy enemy : enemies) {
            enemy.draw(graphics);
        }
        for (Bullet bullet : bullets) {
            bullet.draw(graphics);
        }
        for (Bullet bullet : bulletsEnemies) {
            bullet.draw(graphics);
        }

        int yLinea = (int) (graphics.getClipBounds().height * 0.76);
        graphics.setColor(Color.RED);
        graphics.drawLine(0, yLinea, graphics.getClipBounds().width, yLinea);

        if (levelMessageDisplayed) {
            graphics.setColor(Color.WHITE);
            graphics.setFont(new Font("Times New Roman", Font.PLAIN, 62));
            graphics.drawString("Next Level " + currentLevelIndex, (graphics.getClipBounds().width / 3), graphics.getClipBounds().height / 2);
        } else if (enemies.isEmpty() && currentLevelIndex >= levels.size() - 1) {
            graphics.setColor(Color.WHITE);
            graphics.setFont(new Font("Times New Roman", Font.PLAIN, 62));
            graphics.drawString("You win", (graphics.getClipBounds().width / 3), graphics.getClipBounds().height / 2);
        }

    }

    public void moveLeft(int variable) {
        hero.moveLeft(variable);

    }

    public void moveRight(int variable) {
        hero.moveRight(variable);

    }

    public void moveDown(int variable) {
        for (Enemy enemy:enemies) {
            enemy.moveDown(variable);
            enemy.move();
        }
    }

    public void moveUpAndDown(int variable) {
        //Mueve hacia arriba
        for (Bullet bullet : bullets) {
            bullet.moveUp(variable);
        }
        //Mueve hacia abajo
        for (Bullet bullet : bulletsEnemies) {
            bullet.moveDown(variable);
        }
    }

    public void chechCollisionsBullet() {
        List<Bullet> bulletsToRemove = new ArrayList<>();
        List<Enemy> enemiesToRemove = new ArrayList<>();

        Iterator<Bullet> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();

            if (currentLevelIndex == 0) {
                for (Enemy enemy : enemies) {
                    if (enemy.getRectangle(50, 20).intersects(bullet.getRectangle(7, 13))) {
                        bulletsToRemove.add(bullet);
                        bullet.setDamage(100);
                        enemy.receiveDamage(bullet.getDamage());
                        if (enemy.getLife() <= 0) {
                            enemiesToRemove.add(enemy);
                        }
                        hero.setScore(hero.getScore() + 5);
                        break; // Salir del bucle interno después de marcar la bala y el enemigo para eliminación
                    }
                }
            } else if (currentLevelIndex == 1) {
                for (Enemy enemy : enemies) {
                    if (enemy.getRectangle(50, 20).intersects(bullet.getRectangle(7, 13))) {
                        bulletsToRemove.add(bullet);
                        bullet.setDamage(34);
                        enemy.receiveDamage(bullet.getDamage());
                        if (enemy.getLife() <= 0) {
                            enemiesToRemove.add(enemy);
                        }
                        hero.setScore(hero.getScore() + 10);
                        break; // Salir del bucle interno después de marcar la bala y el enemigo para eliminación
                    }
                }
            } else {
                for (Enemy enemy : enemies) {
                    if (enemy.getRectangle(125, 50).intersects(bullet.getRectangle(7, 13))) {
                        bulletsToRemove.add(bullet);
                        if (hero.getLife() >= 75) {
                            bullet.setDamage(15);
                            enemy.receiveDamage(bullet.getDamage());
                            if (enemy.getLife() <= 0) {
                                enemiesToRemove.add(enemy);
                            }
                            hero.setScore(hero.getScore() + 15);
                        } else if (hero.getLife() > 50 || hero.getLife() < 75) {
                            bullet.setDamage(10);
                            enemy.receiveDamage(bullet.getDamage());
                            if (enemy.getLife() <= 0) {
                                enemiesToRemove.add(enemy);
                            }
                        } else if (hero.getLife() < 50) {
                            bullet.setDamage(5);
                            enemy.receiveDamage(bullet.getDamage());
                            if (enemy.getLife() <= 0) {
                                enemiesToRemove.add(enemy);
                            }
                        }
                        break; // Salir del bucle interno después de marcar la bala y el enemigo para eliminación
                    }
                }

            }

        }
        // Eliminar balas y enemigos marcados
        bullets.removeAll(bulletsToRemove);
        enemies.removeAll(enemiesToRemove);

    }

    public void chechCollisionsBulletEnemies() {
        // Colisiones entre balas de los enemigos y el héroe
        Iterator<Bullet> enemyBulletIterator = bulletsEnemies.iterator();
        while (enemyBulletIterator.hasNext()) {
            Bullet bullet = enemyBulletIterator.next();

            if (hero.collision(bullet.getRectangle(7, 13))) {
                enemyBulletIterator.remove();
                if (currentLevelIndex == 0) {
                    hero.setLife(hero.getLife() - 5);
                    if (hero.getLife() <= 0) {
                        hero.setLife(0);
                    }

                } else if (currentLevelIndex == 1) {
                    hero.setLife(hero.getLife() - 10);
                    if (hero.getLife() <= 0) {
                        hero.setLife(0);
                    }
                } else {
                    hero.setLife(hero.getLife() - 15);
                    if (hero.getLife() <= 0) {
                        hero.setLife(0);
                    }
                }
            }
        }
    }

    public int score() {
        return hero.getScore();
    }

    public int lifeHero() {
        return hero.getLife();
    }

    public int sizeNivel() {
        return levels.size();
    }

    public int sizeEnemy() {
        return enemies.size();
    }

    public int getCurrentLevelIndex() {
        return currentLevelIndex;
    }

}
