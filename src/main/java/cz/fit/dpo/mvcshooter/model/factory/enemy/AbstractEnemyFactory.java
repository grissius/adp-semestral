package cz.fit.dpo.mvcshooter.model.factory.enemy;

import cz.fit.dpo.mvcshooter.model.object.enemy.Enemy;

/**
 * Created by smolijar on 12/19/16.
 */
public abstract class AbstractEnemyFactory {
    protected int w;
    protected int h;

    public AbstractEnemyFactory(int w, int h) {
        this.w = w;
        this.h = h;
    }

    public abstract Enemy create();
}
