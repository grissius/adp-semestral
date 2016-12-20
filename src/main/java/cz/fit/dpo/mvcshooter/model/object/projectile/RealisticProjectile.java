package cz.fit.dpo.mvcshooter.model.object.projectile;

import cz.fit.dpo.mvcshooter.model.geometry.Vector;

/**
 * Created by smolijar on 10/25/16.
 */
public class RealisticProjectile extends Projectile {
    public RealisticProjectile(Vector from, float angle, int power) {
        super(from, angle, power);
    }

    @Override
    public synchronized boolean move(int w, int h, float gravity) {
        this.direction.addY(gravity / 10);
        return super.move(w, h, gravity);
    }
}
