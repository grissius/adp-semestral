package cz.fit.dpo.mvcshooter.model.object.pattern.visitor;

import cz.fit.dpo.mvcshooter.model.object.enemy.Enemy;
import cz.fit.dpo.mvcshooter.model.object.GameObject;
import cz.fit.dpo.mvcshooter.model.object.projectile.Projectile;
import cz.fit.dpo.mvcshooter.model.object.sling.Sling;

/**
 * Created by smolijar on 12/19/16.
 */
public interface Visitor {
    public void visitSling(Sling sling);
    public void visitProjectile(Projectile projectile);
    public void visitGameObject(GameObject gameObject);
    public void visitSimpleEnemy(Enemy enemy);
    public void visitRealisticEnemy(Enemy enemy);
}
