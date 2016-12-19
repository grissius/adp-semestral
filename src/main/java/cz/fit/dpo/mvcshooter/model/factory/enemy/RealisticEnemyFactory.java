package cz.fit.dpo.mvcshooter.model.factory.enemy;

import cz.fit.dpo.mvcshooter.model.object.enemy.Enemy;
import cz.fit.dpo.mvcshooter.model.object.enemy.RealisticEnemy;

/**
 * Created by smolijar on 12/19/16.
 */
public class RealisticEnemyFactory extends AbstractEnemyFactory {

    public RealisticEnemyFactory(int w, int h) {
        super(w, h);
    }

    @Override
    public Enemy createEnemy() {
        return new RealisticEnemy(w, h);
    }
}
