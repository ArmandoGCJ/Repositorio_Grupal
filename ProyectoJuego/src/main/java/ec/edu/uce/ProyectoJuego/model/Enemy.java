package ec.edu.uce.ProyectoJuego.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy implements Drawable, Movable {
    private int life = 100;

    final int SCREEN_WIDTH = 600;
    final int SCREEN_HEIGHT = 100;

    private List<Point> positions;
    private List<Integer> directions;


    public Enemy() {
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
            int[] xPoints = {position.x, position.x + 50, position.x + 50, position.x + 25, position.x};
            int[] yPoints = {position.y, position.y, position.y + 30, position.y + 10, position.y + 30};
            g.fillPolygon(xPoints, yPoints, 5);
        }
    }

    @Override
    public void moveUp(int variable) {

    }

    @Override
    public void moveDown(int variable) {
        for (int i = 0; i < positions.size(); i++) {
            Point position = positions.get(i);
            position.setLocation(position.getX(), position.getY() + (variable *0.45f));
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

    public boolean checkCollisionWith(Rectangle other) {
        for (Point position : positions) {
            Rectangle enemyBounds = new Rectangle((int) position.getX(), (int) position.getY(), 50, 30);
            if (enemyBounds.intersects(other)) {
                return true;
            }
        }
        return false;
    }


    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public List<Point> getPositions() {
        return positions;
    }

}
