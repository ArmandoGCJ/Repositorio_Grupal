package ec.edu.uce.ProyectoExamen.model;

import java.awt.*;

public class Enemy extends Role implements Drawable,Movable {
    private int life;

    int [] cord_x = {100, 200, 200, 150, 100};
    int [] cord_y = {100, 100, 300, 250, 300};

    private boolean movingRight = true;

    public Enemy(int randomX, int randomY) {

        cord_x[0] = randomX;
        cord_x[1] = randomX + 100;
        cord_x[2] = randomX + 100;
        cord_x[3] = randomX + 50;
        cord_x[4] = randomX;

        cord_y[0] = randomY;
        cord_y[1] = randomY;
        cord_y[2] = randomY + 50;
        cord_y[3] = randomY + 25;
        cord_y[4] = randomY + 50;
    }

    public Enemy() {
        super(5);
    }

    public Enemy(int life) {
        this.life = life;

    }
    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.GREEN);
        graphics.fillPolygon(cord_x,cord_y,5);
    }

    @Override
    public void moveUp(int variable) {

    }

    @Override
    public void moveDown(int variable) {
        for (int i = 0; i < cord_y.length; i++) {
            cord_y[i] = cord_y[i] + variable;
        }
    }

    @Override
    public void moveLeft(int variable) {
        for (int i = 0; i < cord_x.length; i++) {
            if (cord_x[i] - variable < 0) { // Si se sale del borde izquierdo
                // Cambiar la dirección del movimiento
                movingRight = true;
                // Mantenerlo en el borde izquierdo
                cord_x[i] = 0;
            } else {
                cord_x[i] = cord_x[i] - variable;
            }
        }
    }

    @Override
    public void moveRight(int variable) {
        for (int i = 0; i < cord_x.length; i++) {
            if (cord_x[i] + variable > 800 - 20) { // Si se sale del borde derecho, donde 50 es el ancho del enemigo
                // Cambiar la dirección del movimiento
                movingRight = false;
                // Mantenerlo en el borde derecho
                cord_x[i] = 800 - 20;
            } else {
                cord_x[i] = cord_x[i] + variable;
            }
        }
    }

    public void move(int variable) {
        if (movingRight) {
            moveRight(variable);
        } else {
            moveLeft(variable);
        }
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
}
