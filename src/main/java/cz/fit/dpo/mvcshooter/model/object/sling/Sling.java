package cz.fit.dpo.mvcshooter.model.object.sling;

import cz.fit.dpo.mvcshooter.model.object.GameObject;
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
    public synchronized boolean move(int w, int h, float gravity) {
        direction.diminish(1.2);
        return super.move(w, h, gravity);
    }

    public synchronized void carry(int value) {
        direction.addY(3*value);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitSling(this);
    }

}
