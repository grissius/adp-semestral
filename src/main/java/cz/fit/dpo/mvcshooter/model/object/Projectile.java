package cz.fit.dpo.mvcshooter.model.object;

import cz.fit.dpo.mvcshooter.pattern.visitor.Visitor;

/**
 * Created by smolijar on 10/25/16.
 */
public class Projectile extends GameObject {
    public Projectile(Sling sling) {
        super();
        size.setX(30);
        size.setY(29);
        location.setX(sling.location.getX());
        location.setY(sling.location.getY());
        direction.setX(4*Math.cos(sling.getAngle()));
        direction.setY(4*Math.sin(sling.getAngle()));
        bounded = false;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitProjectile(this);
    }
}
