package cz.fit.dpo.mvcshooter.model.object;

import cz.fit.dpo.mvcshooter.model.geometry.Vector;
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
    protected boolean bounded = true;
    private float angle = 0;

    public GameObject() {
        size = new Vector(0,0);
        location = new Vector(0,0);
        direction = new Vector(0,0);
    }

    public synchronized Vector getCenter() {
        return new Vector(location.getX() - size.getX() / 2, location.getY() - size.getY() / 2);
    }

    public synchronized boolean move(int w, int h, float gravity) {
        if(!direction.isZero()) {
            Vector lastLocation = new Vector(location);
            location.add(direction);
            if(bounded && outOfField(w, h)) {
                location = lastLocation;
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean collides(GameObject object) {
        return location.distance(object.location) < (Math.min(size.getX(), size.getY()) + Math.min(object.size.getX(), object.size.getY())) / 2;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitGameObject(this);
    }

    public boolean outOfField(int w, int h) {
        return location.getX() > w || location.getX() < 0 || location.getY() > h || location.getY() < 0;
    }

    public Vector getLocation() {
        return location;
    }

    public Vector getSize() {
        return size;
    }

    public void turn(float value) {
        this.angle += value / 10;
    }

    public float getAngle() {
        return angle;
    }

    public String getDisplayAngle() {
        return "" + (int)(angle == 0 ? angle : - Math.toDegrees(angle) % 360) + "°";
    }
}
