package cz.fit.dpo.mvcshooter.model.factory;

import cz.fit.dpo.mvcshooter.model.geometry.Vector;
import cz.fit.dpo.mvcshooter.model.object.enemy.Enemy;
import cz.fit.dpo.mvcshooter.model.object.enemy.RealisticEnemy;
import cz.fit.dpo.mvcshooter.model.object.projectile.Projectile;
import cz.fit.dpo.mvcshooter.model.object.projectile.strategy.RealisticStrategy;

/**
 * Created by smolijar on 12/19/16.
 */
public class RealisticObjectFactory extends AbstractObjectFactory {

    public RealisticObjectFactory(Vector battlefield) {
        super(battlefield);
        strategy = new RealisticStrategy();
    }

    @Override
    public Enemy creteEnemy() {
        return new RealisticEnemy(battlefield);
    }

    @Override
    public Projectile createProjectile(Vector from, float angle, int power) {
        return new Projectile(from, angle, power, strategy);
    }
}
