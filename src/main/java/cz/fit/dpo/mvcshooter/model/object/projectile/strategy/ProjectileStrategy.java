package cz.fit.dpo.mvcshooter.model.object.projectile.strategy;

import cz.fit.dpo.mvcshooter.model.geometry.Vector;

/**
 * Created by smolijar on 12/20/16.
 */
public interface ProjectileStrategy {
    public Vector getDirection(float gravity);
}
