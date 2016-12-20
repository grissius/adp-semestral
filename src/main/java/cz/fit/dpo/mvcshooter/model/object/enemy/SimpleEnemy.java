package cz.fit.dpo.mvcshooter.model.object.enemy;

import cz.fit.dpo.mvcshooter.model.geometry.Vector;
import cz.fit.dpo.mvcshooter.model.object.pattern.visitor.Visitor;

/**
 * Created by smolijar on 12/19/16.
 */
public class SimpleEnemy extends Enemy {
    public SimpleEnemy(Vector battlefield) {
        super(battlefield);
    }

    protected SimpleEnemy() {}


    @Override
    public void accept(Visitor visitor) {
        visitor.visitSimpleEnemy(this);
    }

    @Override
    public Enemy clone() {
        SimpleEnemy enemy = new SimpleEnemy();
        enemy.setAs(this);
        return enemy;
    }
}
