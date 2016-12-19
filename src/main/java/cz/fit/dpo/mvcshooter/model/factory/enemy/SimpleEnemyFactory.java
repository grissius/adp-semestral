package cz.fit.dpo.mvcshooter.model.factory.enemy;

import cz.fit.dpo.mvcshooter.model.object.enemy.Enemy;
import cz.fit.dpo.mvcshooter.model.object.enemy.SimpleEnemy;

/**
 * Created by smolijar on 12/19/16.
 */
public class SimpleEnemyFactory extends AbstractEnemyFactory {

    public SimpleEnemyFactory(int w, int h) {
        super(w, h);
    }

    @Override
    public Enemy createEnemy() {
        return new SimpleEnemy(w, h);
    }
}
