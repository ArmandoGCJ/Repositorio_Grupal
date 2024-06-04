package ec.edu.uce.ProyectoJuego.model;

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
        y += variable;
        if (y > 570) {
            y = 570;
        }
    }

    @Override
    public void moveLeft(int variable) {

    }

    @Override
    public void moveRight(int variable) {

    }

    public Rectangle collidesWithBullet() {
        Rectangle bulletBounds = new Rectangle(x - 4, y, 7, 13);
        return bulletBounds;
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
