package cz.fit.dpo.mvcshooter.model.factory.projectile;

import cz.fit.dpo.mvcshooter.model.geometry.Vector;
import cz.fit.dpo.mvcshooter.model.object.projectile.Projectile;
import cz.fit.dpo.mvcshooter.model.object.projectile.RealisticProjectile;

/**
 * Created by smolijar on 12/19/16.
 */
public class RealisticProjectileFactory extends AbstractProjectileFactory {
    @Override
    public Projectile create(Vector from, float angle, int power) {
        return new RealisticProjectile(from, angle, power);
    }
}
