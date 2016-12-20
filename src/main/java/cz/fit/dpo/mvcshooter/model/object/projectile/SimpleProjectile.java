package cz.fit.dpo.mvcshooter.model.object.projectile;

import cz.fit.dpo.mvcshooter.model.object.GameObject;
import cz.fit.dpo.mvcshooter.model.object.sling.Sling;
import cz.fit.dpo.mvcshooter.pattern.visitor.Visitor;

/**
 * Created by smolijar on 10/25/16.
 */
public class SimpleProjectile extends Projectile {
    public SimpleProjectile(Sling sling) {
        super(sling);
    }
}
