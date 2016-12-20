package cz.fit.dpo.mvcshooter.model.object.projectile;

import cz.fit.dpo.mvcshooter.model.geometry.Vector;
import cz.fit.dpo.mvcshooter.model.object.GameObject;
import cz.fit.dpo.mvcshooter.model.object.projectile.strategy.ProjectileStrategy;
import cz.fit.dpo.mvcshooter.model.object.sling.Sling;
import cz.fit.dpo.mvcshooter.pattern.visitor.Visitor;

/**
 * Created by smolijar on 10/25/16.
 */
public class Projectile extends GameObject {
    ProjectileStrategy strategy;

    public Projectile(Vector from, float angle, int power, ProjectileStrategy strategy) {
        super();
        size.setX(30);
        size.setY(29);
        location = new Vector(from);
        direction.setX(power*Math.cos(angle));
        direction.setY(power*Math.sin(angle));
        bounded = false;
        this.strategy = strategy;
    }

    private Projectile() {}

    @Override
    public Projectile clone() {
        Projectile projectile = new Projectile();
        projectile.setAs(this);
        projectile.strategy = this.strategy;
        return projectile;
    }

    @Override
    public synchronized boolean move(int w, int h, float gravity) {
        this.direction.add(strategy.getDirection(gravity));
        return super.move(w, h, gravity);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitProjectile(this);
    }
}
