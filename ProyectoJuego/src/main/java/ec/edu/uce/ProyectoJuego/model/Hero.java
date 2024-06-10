package ec.edu.uce.ProyectoJuego.model;

import ec.edu.uce.ProyectoJuego.Interface.Drawable;
import ec.edu.uce.ProyectoJuego.Interface.Movable;

import java.awt.*;

public class Hero implements Drawable, Movable {

    private long id;
    private int life = 100;
    private int score = 0;
    private int x = 400, y = 500;
    private String name;
    private String password;
    private int currentLevelIndex = 0;
    private int numEnemies = 0;

    public Hero() {

    }

    public Hero(long id, int life, int score, String name, String password, int currentLevelIndex, int numEnemies) {
        this.id = id;
        this.life = life;
        this.score = score;
        this.name = name;
        this.password = password;
        this.currentLevelIndex = currentLevelIndex;
        this.numEnemies = numEnemies;
    }

    @Override
    public void draw(Graphics graphics) {
        int[] cord_x = {x, x + 40, x - 40};
        int[] cord_y = {y, y + 40, y + 40};

        graphics.setColor(Color.WHITE);
        graphics.fillPolygon(cord_x, cord_y, 3);
    }

    @Override
    public void moveUp(int variable) {

    }

    @Override
    public void moveDown(int variable) {

    }

    @Override
    public void moveLeft(int variable) {
        // Restar
        x -= variable;
        if (x < 5) {
            x = 5;
        }
    }

    @Override
    public void moveRight(int variable) {
        // Sumar
        x += variable;
        if (x > 780) {
            x = 780;
        }
    }

    public boolean collision(Rectangle other) {
        Rectangle heroBounds = new Rectangle(x - 20, y, 40, 50);
        return heroBounds.intersects(other);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCurrentLevelIndex() {
        return currentLevelIndex;
    }

    public void setCurrentLevelIndex(int currentLevelIndex) {
        this.currentLevelIndex = currentLevelIndex;
    }

    public int getNumEnemies() {
        return numEnemies;
    }

    public void setNumEnemies(int numEnemies) {
        this.numEnemies = numEnemies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "id=" + id +
                ", life=" + life +
                ", score=" + score +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
