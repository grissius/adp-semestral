package cz.fit.dpo.mvcshooter.model.factory.projectile;

import cz.fit.dpo.mvcshooter.model.geometry.Vector;
import cz.fit.dpo.mvcshooter.model.object.projectile.Projectile;
import cz.fit.dpo.mvcshooter.model.object.projectile.strategy.ProjectileStrategy;
import cz.fit.dpo.mvcshooter.model.object.projectile.strategy.SimpleStrategy;

/**
 * Created by smolijar on 12/19/16.
 */
public class SimpleProjectileFactory extends AbstractProjectileFactory {

    private ProjectileStrategy strategy;

    public SimpleProjectileFactory() {
        strategy = new SimpleStrategy();
    }

    @Override
    public Projectile create(Vector from, float angle, int power) {
        return new Projectile(from, angle, power, strategy);
    }
}
