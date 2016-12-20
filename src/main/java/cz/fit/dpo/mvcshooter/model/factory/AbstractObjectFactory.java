package cz.fit.dpo.mvcshooter.model.factory;

import cz.fit.dpo.mvcshooter.model.geometry.Vector;
import cz.fit.dpo.mvcshooter.model.object.enemy.Enemy;
import cz.fit.dpo.mvcshooter.model.object.projectile.Projectile;
import cz.fit.dpo.mvcshooter.model.object.projectile.strategy.ProjectileStrategy;

/**
 * Created by smolijar on 12/19/16.
 */
public abstract class AbstractObjectFactory {
    protected Vector battlefield;
    protected ProjectileStrategy strategy;

    public AbstractObjectFactory(Vector battlefield) {
        this.battlefield = battlefield;
    }

    public abstract Enemy creteEnemy();

    public abstract Projectile createProjectile(Vector from, float angle, int power);

}
