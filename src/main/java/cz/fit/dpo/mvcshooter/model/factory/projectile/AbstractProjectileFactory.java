package cz.fit.dpo.mvcshooter.model.factory.projectile;

import cz.fit.dpo.mvcshooter.model.object.enemy.Enemy;
import cz.fit.dpo.mvcshooter.model.object.projectile.Projectile;
import cz.fit.dpo.mvcshooter.model.object.sling.Sling;

/**
 * Created by smolijar on 12/19/16.
 */
public abstract class AbstractProjectileFactory {
    public abstract Projectile create(Sling sling, int power);
}
