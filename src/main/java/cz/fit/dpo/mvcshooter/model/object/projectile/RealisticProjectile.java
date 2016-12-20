package cz.fit.dpo.mvcshooter.model.object.projectile;

import cz.fit.dpo.mvcshooter.model.object.sling.Sling;

/**
 * Created by smolijar on 10/25/16.
 */
public class RealisticProjectile extends Projectile {
    public RealisticProjectile(Sling sling, int power) {
        super(sling, power);
    }

    @Override
    public synchronized boolean move(int w, int h, float gravity) {
        this.direction.addY(gravity / 10);
        return super.move(w, h, gravity);
    }
}
