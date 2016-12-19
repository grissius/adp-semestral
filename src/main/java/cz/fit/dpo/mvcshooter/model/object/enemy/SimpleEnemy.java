package cz.fit.dpo.mvcshooter.model.object.enemy;

import cz.fit.dpo.mvcshooter.pattern.visitor.Visitor;

/**
 * Created by smolijar on 12/19/16.
 */
public class SimpleEnemy extends Enemy {
    public SimpleEnemy(int w, int h) {
        super(w,h);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitSimpleEnemy(this);
    }
}
