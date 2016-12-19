package cz.fit.dpo.mvcshooter.model.object.enemy;

import cz.fit.dpo.mvcshooter.model.object.GameObject;
import cz.fit.dpo.mvcshooter.pattern.visitor.Visitor;

/**
 * Created by smolijar on 10/25/16.
 */
public abstract class Enemy extends GameObject {
    public Enemy(int w, int h) {
        size.setX(30);
        size.setY(29);
        this.location.setX(Math.random() * w);
        this.location.setY(Math.random() * h);
        this.bounded = false;
    }

    @Override
    public synchronized boolean move(int w, int h) {
        turn((float) (Math.random())-0.5f);
        return super.move(w, h);
    }
}
