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
import cz.fit.dpo.mvcshooter.model.object.projectile.RealisticProjectile;
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
    private AbstractProjectileFactory projectileFactory;

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

    public void turnRealistic() {
        this.enemyFactory = new RealisticEnemyFactory(getWidth(), getHeight());
        this.projectileFactory = new RealisticProjectileFactory();
    }

    public void turnSimple() {
        this.enemyFactory = new SimpleEnemyFactory(getWidth(), getHeight());
        this.projectileFactory = new SimpleProjectileFactory();
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

    public void fire() {
        this.projectiles.add(projectileFactory.create(getSling()));
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
                    System.out.println("Score");
                }
            }
        }

        enemies.removeAll(removedEnemies);
        for (int i = 0; i < removedEnemies.size(); ++i) {
            this.enemies.add(enemyFactory.create());
        }

        if (changed) {
//            System.out.print('.');
            notifyObservers();
        }
    }
}
