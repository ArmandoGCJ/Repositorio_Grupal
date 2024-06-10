package ec.edu.uce.Persistence.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "User")
public class User {
    @Id
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    @Column
    private int life;
    @Column
    private int score;
    @Column
    private int currentLevelIndex;
    @Column
    private int numEnemies;

    public User() {
    }

    public User(String name, String password, int life, int score, int currentLevelIndex, int numEnemies) {
        this.name = name;
        this.password = password;
        this.life = life;
        this.score = score;
        this.currentLevelIndex = currentLevelIndex;
        this.numEnemies = numEnemies;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int getNumEnemies() {
        return numEnemies;
    }

    public void setNumEnemies(int numEnemies) {
        this.numEnemies = numEnemies;
    }

    public int getCurrentLevelIndex() {
        return currentLevelIndex;
    }

    public void setCurrentLevelIndex(int currentLevelIndex) {
        this.currentLevelIndex = currentLevelIndex;
    }
}