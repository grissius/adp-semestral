package cz.fit.dpo.mvcshooter.model;

import cz.fit.dpo.mvcshooter.model.object.Enemy;
import cz.fit.dpo.mvcshooter.model.object.GameObject;
import cz.fit.dpo.mvcshooter.model.object.Projectile;
import cz.fit.dpo.mvcshooter.model.object.Sling;
import cz.fit.dpo.mvcshooter.pattern.observer.Subject;

import javax.security.auth.callback.Callback;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

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

    public Model() {
        this.gravity = 0;
        this.sling = new Sling();
        this.projectiles = new ArrayList<>();
        this.enemies = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            this.enemies.add(new Enemy(width, height));
        }
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
        this.projectiles.add(new Projectile(getSling()));
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
        for (GameObject o: getObjects()) {
            changed |= o.move(getWidth(), getHeight());
        }

        List<Projectile> removedProjectiles = new ArrayList<>();
        for (Projectile p: projectiles) {
            if(p.outOfField(getWidth(), getHeight())) {
                removedProjectiles.add(p);
            }
        }
        projectiles.removeAll(removedProjectiles);

        List<Enemy> removedEnemies = new ArrayList<>();
        for (Enemy e: enemies) {
            if(e.outOfField(getWidth(), getHeight())) {
                removedEnemies.add(e);
            }
        }

        for (Projectile p: projectiles) {
            for (Enemy e: enemies) {
                if (p.collides(e)) {
                    removedEnemies.add(e);
                    System.out.println("Score");
                }
            }
        }

        enemies.removeAll(removedEnemies);
        for (int i = 0; i < removedEnemies.size(); ++i) {
            this.enemies.add(new Enemy(width, height));
        }


        if(changed) {
//            System.out.print('.');
            notifyObservers();
        }
    }
}
