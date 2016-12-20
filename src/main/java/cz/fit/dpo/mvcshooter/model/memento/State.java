package cz.fit.dpo.mvcshooter.model.memento;

import cz.fit.dpo.mvcshooter.model.Model;
import cz.fit.dpo.mvcshooter.model.geometry.Vector;
import cz.fit.dpo.mvcshooter.model.object.enemy.Enemy;
import cz.fit.dpo.mvcshooter.model.object.projectile.Projectile;
import cz.fit.dpo.mvcshooter.model.object.sling.Sling;

import java.util.List;

/**
 * Created by smolijar on 12/20/16.
 */
public class State {
    private Sling sling;
    private float gravity;
    private List<Projectile> projectiles;
    private List<Enemy> enemies;
    private Model.Mode mode;
    private int score;
    private Vector battlefield;

    public Sling getSling() {
        return sling;
    }

    public void setSling(Sling sling) {
        this.sling = sling;
    }

    public float getGravity() {
        return gravity;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    public List<Projectile> getProjectiles() {
        return projectiles;
    }

    public void setProjectiles(List<Projectile> projectiles) {
        this.projectiles = projectiles;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    public Model.Mode getMode() {
        return mode;
    }

    public void setMode(Model.Mode mode) {
        this.mode = mode;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Vector getBattlefield() {
        return battlefield;
    }

    public void setBattlefield(Vector battlefield) {
        this.battlefield = battlefield;
    }
}
