package ec.edu.uce.ProyectoExamen.model;

import java.awt.*;


public class Hero extends Role implements Drawable,Movable {

    private String name;
    private int life;
    private int score;
    private int x = 400, y = 500;

    public Hero() {
        super(3);
    }

    public Hero(String name, int life, int score) {
        this.name = name;
        this.life = life;
        this.score = score;
    }
    @Override
    public void draw(Graphics graphics) {
        int[] cord_x = { x, x + 40, x - 40 };
        int[] cord_y = { y, y + 40, y + 40 };
        graphics.setColor(Color.WHITE);
        graphics.fillPolygon(cord_x, cord_y, 3);
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
        if (x < 11) {
            x = 11;
        }
    }

    @Override
    public void moveRight(int variable) {
        // Sumar
        x += variable;
        if (x > 772) {
            x = 772;
        }
    }
}
