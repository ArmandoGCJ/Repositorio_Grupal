package ec.edu.uce.ProyectoJuego.controller;

import ec.edu.uce.ProyectoJuego.model.Bullet;
import ec.edu.uce.ProyectoJuego.model.Enemy;
import ec.edu.uce.ProyectoJuego.model.Hero;
import ec.edu.uce.ProyectoJuego.model.Level;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Container {

    private Hero hero;
    private List<Enemy> enemies;
    private List<Bullet> bullets;
    private List<Bullet> bulletsEnemies;
    private List<Level> levels;
    private int currentLevelIndex = 0;

    public Container() {
        hero = new Hero();
        enemies = new ArrayList<>();
        bullets = new ArrayList<>();
        bulletsEnemies = new ArrayList<>();
        levels = new ArrayList<>();

        initializeLevels();
        loadLevel(1);
    }

    private void initializeLevels() {
        levels.add(new Level(5));  // Nivel 1 con 5 enemigos
        levels.add(new Level(10)); // Nivel 2 con 10 enemigos
        // Puedes agregar más niveles según sea necesario
    }
    private void loadLevel(int levelIndex) {
        enemies.clear();
        Level currentLevel = levels.get(levelIndex);
        for (int i = 0; i < currentLevel.getEnemyCount(); i++) {
            enemies.add(new Enemy());
        }
    }

    public void nextLevel() {
        System.out.println(levels.size());
        if (currentLevelIndex < levels.size() - 1) {
            currentLevelIndex++;
            loadLevel(currentLevelIndex);
        } else {
            // Aquí puedes agregar lógica para manejar la finalización del juego
            System.out.println("¡Has completado todos los niveles!");
        }
    }

    public void update() {
        moveDown(1);
        moveUp(10);
        checkCollisions();
        if (enemies.isEmpty()) {
            System.out.println("Avanzando al siguiente nivel...");
            nextLevel();
        }
    }

    public void setBulletHero() {
        bullets.add(new Bullet(hero.getX(), hero.getY()));
    }

    public void setBulletEnemy() {
        //Obtenemos la posicion de mis enemigos para que disparen
        for (Enemy enemy : enemies) {
            for (Point position : enemy.getPositions()) {
                bulletsEnemies.add(new Bullet((int) position.getX() + 25, (int) position.getY()));
            }
        }
    }

    public void draw(Graphics graphics) {
        hero.draw(graphics);

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).draw(graphics);
        }
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).draw(graphics);
        }
        for (int i = 0; i < bulletsEnemies.size(); i++) {
            bulletsEnemies.get(i).draw(graphics);
        }
    }

    public void moveLeft(int variable) {
        hero.moveLeft(variable);

    }

    public void moveRight(int variable) {
        hero.moveRight(variable);

    }

    public void moveDown(int variable) {
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).moveDown(variable);
            enemies.get(i).move();
        }
    }

    public void moveUp(int variable) {
        //Mueve hacia arriba
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).moveUp(variable);
        }
        //Mueve hacia abajo
        for (Bullet bullet : bulletsEnemies) {
            bullet.moveDown(variable);
        }
    }
    public void checkCollisions() {
        // Colisiones entre balas del héroe y enemigos
        Iterator<Bullet> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            Rectangle bulletBounds = new Rectangle(bullet.getX() - 4, bullet.getY(), 7, 13);

            for (Enemy enemy : enemies) {
                List<Point> positions = enemy.getPositions();
                for (Iterator<Point> positionIterator = positions.iterator(); positionIterator.hasNext();) {
                    Point position = positionIterator.next();
                    Rectangle enemyBounds = new Rectangle(position.x, position.y, 50, 30);
                    if (enemyBounds.intersects(bulletBounds)) {
                        bulletIterator.remove();
                        positionIterator.remove();
                        hero.setScore(hero.getScore() + 5);
                        break; // Salir del bucle interno después de eliminar la bala y el enemigo
                    }
                }
            }
        }

        // Colisiones entre balas de los enemigos y el héroe
        Iterator<Bullet> enemyBulletIterator = bulletsEnemies.iterator();
        while (enemyBulletIterator.hasNext()) {
            Bullet bullet = enemyBulletIterator.next();
            Rectangle bulletBounds = new Rectangle(bullet.getX() - 4, bullet.getY(), 7, 13);

            if (hero.collidesWith(bulletBounds)) {
                enemyBulletIterator.remove();
                hero.setLife(hero.getLife() - 5);
                if (hero.getLife() <= 0) {
                    hero.setLife(0);
                }
            }
        }

        // Colisiones entre el héroe y los enemigos
        Rectangle heroBounds = new Rectangle(hero.getX(), hero.getY(), 30, 60);
        for (Enemy enemy : enemies) {
            if (enemy.checkCollisionWith(heroBounds)) {
                hero.setLife(hero.getLife() - 10);
                if (hero.getLife() <= 0) {
                    hero.setLife(0);
                }
            }
        }
    }


    public int score() {
        return hero.getScore();
    }

    public int life() {
        return hero.getLife();
    }


}
