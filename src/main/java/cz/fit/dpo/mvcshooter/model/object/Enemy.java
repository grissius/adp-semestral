package cz.fit.dpo.mvcshooter.model.object;

import cz.fit.dpo.mvcshooter.pattern.visitor.Visitor;

/**
 * Created by smolijar on 10/25/16.
 */
public class Enemy extends GameObject {
    public Enemy(int w, int h) {
        size.setX(30);
        size.setY(29);
        this.location.setX(Math.random() * w);
        this.location.setY(Math.random() * h);
        this.bounded = false;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitEnemy(this);
    }

    @Override
    public synchronized boolean move(int w, int h) {
        this.direction.addX(Math.random() * 0.2 - 0.1);
        this.direction.addY(Math.random() * 0.2 - 0.1);
        return super.move(w, h);
    }
}
