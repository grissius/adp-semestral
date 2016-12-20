package cz.fit.dpo.mvcshooter.model.object.sling.state;

import cz.fit.dpo.mvcshooter.model.geometry.Vector;
import cz.fit.dpo.mvcshooter.model.object.projectile.Projectile;
import cz.fit.dpo.mvcshooter.model.object.sling.Sling;

import java.util.List;

/**
 * Created by smolijar on 12/20/16.
 */
public interface SlingState {
    public List<Projectile> fire(Sling sling, int power);
}
