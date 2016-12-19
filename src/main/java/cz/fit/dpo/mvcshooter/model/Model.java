package cz.fit.dpo.mvcshooter.model;

import cz.fit.dpo.mvcshooter.model.object.Sling;
import cz.fit.dpo.mvcshooter.pattern.observer.Subject;

import javax.security.auth.callback.Callback;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 * Created by smolijar on 10/25/16.
 */
public class Model extends Subject {
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

    public synchronized Sling getSling() {
        return sling;
    }

    public synchronized void setSling(Sling sling) {
        this.sling = sling;
    }

    public void tick() {
        if(getSling().move()) {
            System.out.print('.');
            notifyObservers();
        }
    }
}
