package ec.edu.uce.ProyectoExamen.controller;

import ec.edu.uce.ProyectoExamen.model.Enemy;
import ec.edu.uce.ProyectoExamen.model.Hero;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Container {

    Hero hero = new Hero();

    Enemy enemy = new Enemy();

    final int SCREEN_WIDTH = 600;
    final int SCREEN_HEIGHT = 100;

    List<Enemy> enemies = new ArrayList<>();
    Random r = new Random();

    public Container() {
        for (int i = 0; i<10;i++) {
            enemies.add(new Enemy(r.nextInt(SCREEN_WIDTH),r.nextInt(SCREEN_HEIGHT))); // Suponiendo que 3 sea la vida inicial de cada enemigo
        }
    }

    public void draw(Graphics graphics) {
        hero.draw(graphics);
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).draw(graphics);
        }
    }

    public void moveLeft(int variable) {
        hero.moveLeft(variable);

    }

    public void moveRight(int variable) {
        hero.moveRight(variable);

    }

    public void moveDown(int var) {
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).moveDown(var);
            enemies.get(i).move((int) (var+0.5f));

        }

    }

    public int score(){
       return hero.getScore();
    }

    public int life(){

        return hero.getLife();
    }

    public void moveUp(int variable) {
        /*for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).moveUp(variable);
        }*/

    }
}
