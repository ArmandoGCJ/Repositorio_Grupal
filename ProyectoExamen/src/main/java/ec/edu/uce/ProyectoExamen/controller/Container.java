package ec.edu.uce.ProyectoExamen.controller;

import ec.edu.uce.ProyectoExamen.model.Bullet;
import ec.edu.uce.ProyectoExamen.model.Enemy;
import ec.edu.uce.ProyectoExamen.model.Hero;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Container {

    Hero hero = new Hero();

    List<Enemy> enemies = new ArrayList<>();
    List<Bullet> bullets = new ArrayList<>();

    public Container() {

        for (int i = 0; i<5;i++) {
            enemies.add(new Enemy()); // Suponiendo que 3 sea la vida inicial de cada enemigo
        }
    }

    public void setBullet(){

        bullets.add(new Bullet(hero.getX(),hero.getY()));

    }

    public void draw(Graphics graphics) {
        hero.draw(graphics);

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).draw(graphics);
        }
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).draw(graphics);
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
            enemies.get(i).move(var++);
            enemies.get(i).moveDown((int) (var*0.25f));
        }
    }

    public void moveUp(int variable) {
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).moveUp(variable);
        }
    }

    public int score(){
       return hero.getScore();
    }

    public int life(){
        return hero.getLife();
    }


}
