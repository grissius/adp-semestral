package cz.fit.dpo.mvcshooter.model.object.sling.state;

import cz.fit.dpo.mvcshooter.model.object.projectile.Projectile;
import cz.fit.dpo.mvcshooter.model.object.sling.Sling;

import java.util.Arrays;
import java.util.List;

/**
 * Created by smolijar on 12/20/16.
 */
public class DoubleShootingState implements SlingState {
    @Override
    public List<Projectile> fire(Sling sling, int power) {
        float range = 0.3f;
        return Arrays.asList(
                sling.getProjectileFactory().create(sling.getLocation(), sling.getAngle() + range/2, power),
                sling.getProjectileFactory().create(sling.getLocation(), sling.getAngle() - range/2, power)
        );
    }
}
