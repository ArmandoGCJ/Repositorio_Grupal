package ec.edu.uce.ProyectoJuego.model;

import ec.edu.uce.ProyectoJuego.Interface.Drawable;
import ec.edu.uce.ProyectoJuego.Interface.IHitBox;
import ec.edu.uce.ProyectoJuego.Interface.Movable;

import java.awt.*;

public class Bullet implements Drawable, Movable, IHitBox {

    private int x, y;
    private int damage = 0;

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

    @Override
    public Rectangle getRectangle(int width, int heigth){
        Rectangle bulletBounds = new Rectangle(x - 4, y, width, heigth);
        return bulletBounds;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

}
