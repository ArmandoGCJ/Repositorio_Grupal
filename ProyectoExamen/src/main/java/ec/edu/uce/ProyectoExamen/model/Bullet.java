package ec.edu.uce.ProyectoExamen.model;

import java.awt.*;

public class Bullet implements Drawable,Movable{

    private int x, y;

    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Graphics graphics) {

        graphics.setColor(Color.WHITE);
        graphics.fillOval(x - 4, y, 7, 13);
        graphics.drawOval(x - 4, y, 7, 13);
    }

    @Override
    public void moveUp(int variable) {
        y -= variable;
        if (y < -15) {
            y = -15;
        }
    }

    @Override
    public void moveDown(int variable) {

    }

    @Override
    public void moveLeft(int variable) {

    }

    @Override
    public void moveRight(int variable) {

    }

}
