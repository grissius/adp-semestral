package cz.fit.dpo.mvcshooter.model.object;

import cz.fit.dpo.mvcshooter.model.geometry.Vector;
import cz.fit.dpo.mvcshooter.model.object.enemy.Enemy;
import cz.fit.dpo.mvcshooter.model.object.projectile.Projectile;
import cz.fit.dpo.mvcshooter.model.object.sling.Sling;
import cz.fit.dpo.mvcshooter.model.object.pattern.visitor.Visitor;

/**
 * Created by smolijar on 12/20/16.
 */
public class MockLocationVisitor implements Visitor {

    private Vector location;

    public Vector getLocation() {
        return location;
    }

    public void setLocation(Vector location) {
        this.location = location;
    }

    @Override
    public void visitSling(Sling sling) {
        visitGameObject(sling);
    }

    @Override
    public void visitProjectile(Projectile projectile) {
        visitGameObject(projectile);
    }

    @Override
    public void visitGameObject(GameObject gameObject) {
        location = new Vector(gameObject.location);
    }

    @Override
    public void visitSimpleEnemy(Enemy enemy) {
        visitGameObject(enemy);
    }

    @Override
    public void visitRealisticEnemy(Enemy enemy) {
        visitGameObject(enemy);
    }
}
