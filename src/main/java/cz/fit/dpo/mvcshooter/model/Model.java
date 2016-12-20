package cz.fit.dpo.mvcshooter.model;

import cz.fit.dpo.mvcshooter.model.factory.enemy.AbstractEnemyFactory;
import cz.fit.dpo.mvcshooter.model.factory.enemy.RealisticEnemyFactory;
import cz.fit.dpo.mvcshooter.model.factory.enemy.SimpleEnemyFactory;
import cz.fit.dpo.mvcshooter.model.factory.projectile.AbstractProjectileFactory;
import cz.fit.dpo.mvcshooter.model.factory.projectile.RealisticProjectileFactory;
import cz.fit.dpo.mvcshooter.model.factory.projectile.SimpleProjectileFactory;
import cz.fit.dpo.mvcshooter.model.object.GameObject;
import cz.fit.dpo.mvcshooter.model.object.enemy.Enemy;
import cz.fit.dpo.mvcshooter.model.object.projectile.Projectile;
import cz.fit.dpo.mvcshooter.model.object.sling.Sling;
import cz.fit.dpo.mvcshooter.pattern.observer.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smolijar on 10/25/16.
 */
public class Model extends Subject {
    private float gravity;
    private int width = 500;
    private int height = 500;
    private Sling sling;
    private List<Projectile> projectiles;
    private List<Enemy> enemies;
    private AbstractEnemyFactory enemyFactory;
    private Mode mode;
    private int score = 0;
    private long fireCookingStart;

    private static enum Mode {
        REALISTIC,
        SIMPLE
    }

    ;

    public Model() {
        this.gravity = 1;
        this.sling = new Sling();
        this.projectiles = new ArrayList<>();
        this.enemies = new ArrayList<>();
        turnRealistic();
        for (int i = 0; i < 10; ++i) {
            this.enemies.add(enemyFactory.create());
        }
    }

    public void resetObjects() {
        projectiles.clear();
        enemies.clear();
        notifyObservers();
    }

    public void addEnemy() {
        enemies.add(enemyFactory.create());
        notifyObservers();
    }

    public void swapMode() {
        if (mode == Mode.REALISTIC) {
            turnSimple();
        } else {
            turnRealistic();
        }
        notifyObservers();
    }

    public void startCooking() {
        this.fireCookingStart = System.currentTimeMillis();
    }

    private int getFirepower() {
        if(fireCookingStart == 0) {
            return 0;
        }
        return (int)(System.currentTimeMillis() - this.fireCookingStart) / 100 + 2;
    }

    public void cookingRelease() {
        fire(getFirepower());
        fireCookingStart = 0;
    }

    private void turnRealistic() {
        mode = Mode.REALISTIC;
        this.enemyFactory = new RealisticEnemyFactory(getWidth(), getHeight());
        this.getSling().setProjectileFactory(new RealisticProjectileFactory());
    }

    private void turnSimple() {
        mode = Mode.SIMPLE;
        this.enemyFactory = new SimpleEnemyFactory(getWidth(), getHeight());
        this.getSling().setProjectileFactory(new SimpleProjectileFactory());
    }

    public float getGravity() {
        return gravity;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    public Sling getSling() {
        return sling;
    }

    public void setSling(Sling sling) {
        this.sling = sling;
    }

    public void fire(int firePower) {
        this.projectiles.addAll(getSling().fire(firePower));
    }

    public synchronized List<GameObject> getObjects() {
        List<GameObject> objects = new ArrayList<>(projectiles);
        objects.addAll(enemies);
        objects.add(getSling());
        return objects;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void tick() {
        boolean changed = false;
        for (GameObject o : getObjects()) {
            changed |= o.move(getWidth(), getHeight(), gravity);
        }

        List<Projectile> removedProjectiles = new ArrayList<>();
        for (Projectile p : projectiles) {
            if (p.outOfField(getWidth(), getHeight())) {
                removedProjectiles.add(p);
            }
        }
        projectiles.removeAll(removedProjectiles);

        List<Enemy> removedEnemies = new ArrayList<>();
        for (Enemy e : enemies) {
            if (e.outOfField(getWidth(), getHeight())) {
                removedEnemies.add(e);
            }
        }

        for (Projectile p : projectiles) {
            for (Enemy e : enemies) {
                if (p.collides(e)) {
                    removedEnemies.add(e);
                    score++;
                }
            }
        }

        enemies.removeAll(removedEnemies);
        for (int i = 0; i < removedEnemies.size(); ++i) {
            this.enemies.add(enemyFactory.create());
        }

        if (changed) {
            notifyObservers();
        }
    }

    public String createHudText() {
        String msg = "";

        msg += getSling().getUserMsg();
        msg += "\n";
        msg += "Firepower: ";
        for (int i = 0; i < getFirepower(); i++) {
            msg += "#";
        }
        msg += "\n";

        if (mode == Mode.REALISTIC) {
            msg += "Realistic mode";
        } else if (mode == Mode.SIMPLE) {
            msg += "Simple mode";
        }
        msg += "\n";
        msg += "Score: " + score;
        msg += "\n";
        return msg;
    }

    public void swapSling() {
        getSling().swapStates();
    }
}
