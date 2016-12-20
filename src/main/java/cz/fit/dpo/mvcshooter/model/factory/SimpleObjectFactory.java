package cz.fit.dpo.mvcshooter.model.factory;

import cz.fit.dpo.mvcshooter.model.geometry.Vector;
import cz.fit.dpo.mvcshooter.model.object.enemy.Enemy;
import cz.fit.dpo.mvcshooter.model.object.enemy.SimpleEnemy;
import cz.fit.dpo.mvcshooter.model.object.projectile.Projectile;
import cz.fit.dpo.mvcshooter.model.object.projectile.strategy.SimpleStrategy;

/**
 * Created by smolijar on 12/19/16.
 */
public class SimpleObjectFactory extends AbstractObjectFactory {

    public SimpleObjectFactory(Vector battlefield) {
        super(battlefield);
        strategy = new SimpleStrategy();
    }

    @Override
    public Enemy creteEnemy() {
        return new SimpleEnemy(battlefield);
    }

    @Override
    public Projectile createProjectile(Vector from, float angle, int power) {
        return new Projectile(from, angle, power, strategy);
    }
}
