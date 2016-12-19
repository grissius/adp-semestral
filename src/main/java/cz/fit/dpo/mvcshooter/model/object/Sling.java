package cz.fit.dpo.mvcshooter.model.object;

import cz.fit.dpo.mvcshooter.pattern.visitor.Visitor;

/**
 * Created by smolijar on 10/25/16.
 */
public class Sling extends GameObject {
    public Sling() {
        super();
        size.setX(25);
        size.setY(69);
        location.setX(10);
        location.setY(250);
    }

    @Override
    public synchronized boolean move(int w, int h) {
        direction.diminish();
        return super.move(w, h);
    }

    public synchronized void carry(int value) {
        direction.addY(3*value);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitSling(this);
    }
}
