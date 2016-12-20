package cz.fit.dpo.mvcshooter.model.factory.enemy;

import cz.fit.dpo.mvcshooter.model.geometry.Vector;
import cz.fit.dpo.mvcshooter.model.object.enemy.Enemy;
import cz.fit.dpo.mvcshooter.model.object.enemy.RealisticEnemy;

/**
 * Created by smolijar on 12/19/16.
 */
public class RealisticEnemyFactory extends AbstractEnemyFactory {

    public RealisticEnemyFactory(Vector battlefield) {
        super(battlefield);
    }

    @Override
    public Enemy create() {
        return new RealisticEnemy(battlefield);
    }
}
