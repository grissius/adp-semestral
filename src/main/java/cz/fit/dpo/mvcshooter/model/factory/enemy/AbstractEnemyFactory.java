package cz.fit.dpo.mvcshooter.model.factory.enemy;

import cz.fit.dpo.mvcshooter.model.geometry.Vector;
import cz.fit.dpo.mvcshooter.model.object.enemy.Enemy;

/**
 * Created by smolijar on 12/19/16.
 */
public abstract class AbstractEnemyFactory {
    protected Vector battlefield;

    public AbstractEnemyFactory(Vector battlefield) {
        this.battlefield = battlefield;
    }

    public abstract Enemy create();
}
