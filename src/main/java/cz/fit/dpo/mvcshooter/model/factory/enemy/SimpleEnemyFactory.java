package cz.fit.dpo.mvcshooter.model.factory.enemy;

import cz.fit.dpo.mvcshooter.model.geometry.Vector;
import cz.fit.dpo.mvcshooter.model.object.enemy.Enemy;
import cz.fit.dpo.mvcshooter.model.object.enemy.SimpleEnemy;

/**
 * Created by smolijar on 12/19/16.
 */
public class SimpleEnemyFactory extends AbstractEnemyFactory {

    public SimpleEnemyFactory(Vector battlefield) {
        super(battlefield);
    }

    @Override
    public Enemy create() {
        return new SimpleEnemy(battlefield);
    }
}
