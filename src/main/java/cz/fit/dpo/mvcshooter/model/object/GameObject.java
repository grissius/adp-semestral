package cz.fit.dpo.mvcshooter.model.object;

import cz.fit.dpo.mvcshooter.pattern.visitor.VisitableElement;
import cz.fit.dpo.mvcshooter.pattern.visitor.Visitor;

/**
 * Created by smolijar on 10/25/16.
 */
public class GameObject implements VisitableElement {
    protected Vector size;
    protected Vector location;
    protected Vector direction;
    protected float velocity;

    public GameObject() {
        size = new Vector(0,0);
        location = new Vector(0,0);
        direction = new Vector(0,0);
    }

    public synchronized Vector getCenter() {
        return new Vector(location.getX() - size.getX() / 2, location.getY() - size.getY() / 2);
    }

    public synchronized boolean move() {
        if(!direction.isZero()) {
            location.add(direction);
            return true;
        }
        return false;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitGameObject(this);
    }

    public boolean outOfField(int w, int h) {
        return location.getX() > w || location.getY() > h;
    }
}
