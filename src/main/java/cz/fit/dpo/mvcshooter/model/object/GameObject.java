package cz.fit.dpo.mvcshooter.model.object;

/**
 * Created by smolijar on 10/25/16.
 */
public class GameObject {
    protected Vector size;
    protected Vector location;
    protected Vector direction;
    protected float velocity;

    public GameObject() {
        size = new Vector(0,0);
        location = new Vector(0,0);
        direction = new Vector(0,0);
    }

    public Vector getCenter() {
        return new Vector(location.getX() - size.getX() / 2, location.getY() - size.getY() / 2);
    }
}
