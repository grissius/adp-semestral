package cz.fit.dpo.mvcshooter.view;

import cz.fit.dpo.mvcshooter.model.object.*;
import cz.fit.dpo.mvcshooter.model.geometry.Vector;
import cz.fit.dpo.mvcshooter.model.object.enemy.Enemy;
import cz.fit.dpo.mvcshooter.model.object.projectile.Projectile;
import cz.fit.dpo.mvcshooter.model.object.sling.Sling;
import cz.fit.dpo.mvcshooter.model.object.pattern.visitor.Visitor;

/**
 * Created by smolijar on 12/19/16.
 */
public class GraphicsVisitor implements Visitor {
    private Vector location;
    private Vector size;
    private GraphicsDrawer.Image image;
    private float angle;

    @Override
    public void visitSling(Sling sling) {
        visitGameObject(sling);
        image = GraphicsDrawer.Image.CANNON;
    }

    @Override
    public void visitProjectile(Projectile projectile) {
        visitGameObject(projectile);
        image = GraphicsDrawer.Image.MISSILE;
    }

    @Override
    public void visitGameObject(GameObject gameObject) {
        location = gameObject.getCenter();
        size = gameObject.getSize();
        angle = gameObject.getAngle();
    }

    @Override
    public void visitSimpleEnemy(Enemy enemy) {
        visitGameObject(enemy);
        image = GraphicsDrawer.Image.ENEMY1;
    }

    @Override
    public void visitRealisticEnemy(Enemy enemy) {
        visitGameObject(enemy);
        image = GraphicsDrawer.Image.ENEMY2;
    }

    public Vector getLocation() {
        return location;
    }

    public Vector getSize() {
        return size;
    }

    public GraphicsDrawer.Image getImage() {
        return image;
    }

    public float getAngle() {
        return angle;
    }
}
