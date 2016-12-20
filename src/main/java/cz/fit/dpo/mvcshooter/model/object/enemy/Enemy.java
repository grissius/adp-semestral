package cz.fit.dpo.mvcshooter.model.object.enemy;

import cz.fit.dpo.mvcshooter.model.geometry.Vector;
import cz.fit.dpo.mvcshooter.model.object.GameObject;
import cz.fit.dpo.mvcshooter.model.object.projectile.Projectile;
import cz.fit.dpo.mvcshooter.pattern.visitor.Visitor;

/**
 * Created by smolijar on 10/25/16.
 */
public abstract class Enemy extends GameObject {
    public Enemy(int w, int h) {
        size.setX(30);
        size.setY(29);
        this.location.setX(Math.random() * w);
        this.location.setY(Math.random() * h);
        this.bounded = false;
    }

    protected Enemy() {}

    public Enemy(Enemy enemy) {
        this.location = new Vector(enemy.location);
        this.size = new Vector(enemy.size);
        this.bounded = enemy.bounded;
        this.direction = new Vector(enemy.direction);
    }

    @Override
    abstract public Enemy clone();

    @Override
    public synchronized boolean move(int w, int h, float gravity) {
        turn((float) (Math.random())-0.5f);
        return super.move(w, h, gravity);
    }
}
