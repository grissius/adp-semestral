package cz.fit.dpo.mvcshooter.model;

import cz.fit.dpo.mvcshooter.model.object.Sling;

/**
 * Created by smolijar on 10/25/16.
 */
public class Model {
    private float gravity;
    private Sling sling;

    public Model() {
        this.gravity = 0;
        this.sling = new Sling();
    }

    public float getGravity() {
        return gravity;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    public Sling getSling() {
        return sling;
    }

    public void setSling(Sling sling) {
        this.sling = sling;
    }

    public void tick() {
        System.out.println('.');
    }
}
