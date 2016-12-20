package cz.fit.dpo.mvcshooter.model.object.sling.state;

import cz.fit.dpo.mvcshooter.model.object.projectile.Projectile;
import cz.fit.dpo.mvcshooter.model.object.sling.Sling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by smolijar on 12/20/16.
 */
public class SingleShootingState implements SlingState {
    @Override
    public List<Projectile> fire(Sling sling, int power) {
        return Arrays.asList(sling.getProjectileFactory().create(sling.getLocation(), sling.getAngle(), power));
    }
}
