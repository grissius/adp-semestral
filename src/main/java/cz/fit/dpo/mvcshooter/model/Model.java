package cz.fit.dpo.mvcshooter.model;

import cz.fit.dpo.mvcshooter.model.factory.enemy.AbstractEnemyFactory;
import cz.fit.dpo.mvcshooter.model.factory.enemy.RealisticEnemyFactory;
import cz.fit.dpo.mvcshooter.model.factory.enemy.SimpleEnemyFactory;
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
    private int cooked = 0;

    private static enum Mode {
        REALISTIC,
        SIMPLE
    }

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
        this.cooked = 1;
    }

    public void cookingRelease() {
        fire(cooked);
        cooked = 0;
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

    public Sling getSling() {
        return sling;
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

    private boolean removeAddObjects() {
        List<Projectile> projectilesToRemove = new ArrayList<>();
        for (Projectile p : projectiles) {
            if (p.outOfField(getWidth(), getHeight())) {
                projectilesToRemove.add(p);
            }
        }
        projectiles.removeAll(projectilesToRemove);

        List<Enemy> enemiesToRemove = new ArrayList<>();
        for (Enemy e : enemies) {
            if (e.outOfField(getWidth(), getHeight())) {
                enemiesToRemove.add(e);
            }
        }

        for (Projectile p : projectiles) {
            for (Enemy e : enemies) {
                if (p.collides(e)) {
                    enemiesToRemove.add(e);
                    score++;
                }
            }
        }
        enemies.removeAll(enemiesToRemove);
        for (int i = 0; i < enemiesToRemove.size(); ++i) {
            this.enemies.add(enemyFactory.create());
        }
        return (projectilesToRemove.size() + enemiesToRemove.size()) != 0;
    }

    public void tick() {
        boolean changed = false;
        // move
        for (GameObject o : getObjects()) {
            changed |= o.move(getWidth(), getHeight(), gravity);
        }

        // remove projectiles and enemies, replanish enemies
        changed |= removeAddObjects();

        if(cooked != 0) {
            cooked++;
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
        for (int i = 0; i < cooked; i++) {
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
