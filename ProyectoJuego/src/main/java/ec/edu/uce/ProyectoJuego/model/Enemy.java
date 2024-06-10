package ec.edu.uce.ProyectoJuego.model;

import ec.edu.uce.ProyectoJuego.Interface.Drawable;
import ec.edu.uce.ProyectoJuego.Interface.IHitBox;
import ec.edu.uce.ProyectoJuego.Interface.Movable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy implements Drawable, Movable, IHitBox {
    private int life = 100;

    final int SCREEN_WIDTH = 500;
    final int SCREEN_HEIGHT = 100;

    private List<Point> positions;
    private List<Integer> directions;
    private int width;
    private int height;

    // Constructor por defecto
    public Enemy() {
        this(50, 30); // Llamada al nuevo constructor con tamaño por defecto
    }

    // Nuevo constructor con tamaño personalizado
    public Enemy(int width, int height) {
        this.width = width;
        this.height = height;
        positions = new ArrayList<>();
        directions = new ArrayList<>();

        // Creacion de enemigos en posiciones aleatorias.
        Random random = new Random();
        int x = random.nextInt(SCREEN_WIDTH);
        int y = random.nextInt(SCREEN_HEIGHT);
        positions.add(new Point(x, y));

        int direction = random.nextBoolean() ? -1 : 1;
        directions.add(direction);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        for (Point position : positions) {
            int[] xPoints = {position.x, position.x + width, position.x + width, position.x + width / 2, position.x};
            int[] yPoints = {position.y, position.y, position.y + height, position.y + height / 3, position.y + height};
            g.fillPolygon(xPoints, yPoints, 5);

            // Dibujar la barra de vida
            int barWidth = width;
            int barHeight = 5;
            int lifeBarWidth = (int) ((life / (float) 100) * barWidth);
            g.setColor(Color.RED);
            g.fillRect(position.x, position.y - barHeight - 2, barWidth, barHeight); // Fondo de la barra de vida
            g.setColor(Color.GREEN);
            g.fillRect(position.x, position.y - barHeight - 2, lifeBarWidth, barHeight); // Vida restante
        }
    }

    @Override
    public void moveUp(int variable) {

    }

    @Override
    public void moveDown(int variable) {
        for (int i = 0; i < positions.size(); i++) {
            Point position = positions.get(i);
            position.setLocation(position.getX(), position.getY() + variable);
        }
    }

    @Override
    public void moveLeft(int variable) {
        for (int i = 0; i < positions.size(); i++) {
            Point position = positions.get(i);
            position.setLocation(position.getX() - variable, position.getY());
        }
    }

    @Override
    public void moveRight(int variable) {
        for (int i = 0; i < positions.size(); i++) {
            Point position = positions.get(i);
            position.setLocation(position.getX() + variable, position.getY());
        }
    }

    public void move() {
        for (int i = 0; i < positions.size(); i++) {
            Point position = positions.get(i);
            int currentDirection = directions.get(i);

            // Cambiar de dirección al alcanzar los bordes
            if (position.getX() <= 0 || position.getX() >= 735) {
                currentDirection *= -1;
                directions.set(i, currentDirection);
            }
            // Mover en la dirección actual
            position.setLocation(position.getX() + currentDirection, position.getY());
        }
    }

    @Override
    public Rectangle getRectangle(int width, int height) {
        Rectangle enemy = null;
        for (Point position : positions) {
            enemy = new Rectangle(position.x, position.y - 20, width, height);
        }
        return enemy;
    }

    public void receiveDamage(int damage) {
        this.life -= damage;
        if (this.life <= 0) {
            // Lógica para eliminar o destruir al enemigo
            // Podrías marcar al enemigo para su eliminación o removerlo de la lista en el controlador del juego
        }
    }

    public int getLife() {
        return life;
    }

    public List<Point> getPositions() {
        return positions;
    }

    // Method to get the y-coordinate of the first position
    public int getY() {
        if (!positions.isEmpty()) {
            return positions.get(0).y;
        }
        return -1; // or some default value indicating no positions
    }
}
