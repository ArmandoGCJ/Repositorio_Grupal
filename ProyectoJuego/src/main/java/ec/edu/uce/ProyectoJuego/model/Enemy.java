package ec.edu.uce.ProyectoJuego.model;

import java.awt.*;
import java.util.Random;

public class Enemy extends Role implements Drawable, Movable {
    private int life;
    private int x = 100, y = 100;

    final int SCREEN_WIDTH = 600;
    final int SCREEN_HEIGHT = 100;

    Random r = new Random();
/*= {r.nextInt(SCREEN_WIDTH)*x, x + 50, x + 50, x + 25, x}*/
/*= {r.nextInt(SCREEN_HEIGHT)*y, y, y + 30, y + 10, y + 30}*/
    int[] cord_x ;
    int[] cord_y ;

    private boolean movingRight = true;

    public Enemy() {
// Generar posiciones aleatorias dentro del rango especificado
        this.x = r.nextInt(501) + 100; // Entre 100 y 600
        this.y = r.nextInt(101); // Entre 0 y 100
        // También puedes ajustar la generación de las coordenadas del polígono
        this.cord_x = new int[]{this.x, this.x + 50, this.x + 50, this.x + 25, this.x};
        this.cord_y = new int[]{this.y, this.y, this.y + 30, this.y + 10, this.y + 30};
    }

    public Enemy(int life) {
        super(5);
        this.life = life;

    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.GREEN);
        graphics.fillPolygon(cord_x, cord_y, 5);
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
