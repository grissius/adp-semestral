package cz.fit.dpo.mvcshooter.model.factory.projectile;

import cz.fit.dpo.mvcshooter.model.geometry.Vector;
import cz.fit.dpo.mvcshooter.model.object.projectile.Projectile;
import cz.fit.dpo.mvcshooter.model.object.projectile.SimpleProjectile;

/**
 * Created by smolijar on 12/19/16.
 */
public class SimpleProjectileFactory extends AbstractProjectileFactory {
    @Override
    public Projectile create(Vector from, float angle, int power) {
        return new SimpleProjectile(from, angle, power);
    }
}
