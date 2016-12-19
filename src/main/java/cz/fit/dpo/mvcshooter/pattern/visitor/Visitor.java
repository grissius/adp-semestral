package cz.fit.dpo.mvcshooter.pattern.visitor;

import cz.fit.dpo.mvcshooter.model.object.Enemy;
import cz.fit.dpo.mvcshooter.model.object.GameObject;
import cz.fit.dpo.mvcshooter.model.object.Projectile;
import cz.fit.dpo.mvcshooter.model.object.Sling;

/**
 * Created by smolijar on 12/19/16.
 */
public interface Visitor {
    public void visitSling(Sling sling);
    public void visitProjectile(Projectile projectile);
    public void visitGameObject(GameObject gameObject);
    public void visitEnemy(Enemy enemy);
}
