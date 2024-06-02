package ec.edu.uce.ProyectoJuego.model;

import java.awt.*;


public class Hero implements Drawable, Movable {

    private String name;
    private int life = 100;
    private int score = 0;
    private int x = 400, y = 500;

    public Hero() {

    }

    public Hero(String name, int life, int score) {
        this.name = name;
        this.life = life;
        this.score = score;
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

    public boolean collidesWith(Rectangle other) {
        Rectangle heroBounds = new Rectangle(x - 20, y, 40, 50);
        return heroBounds.intersects(other);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
