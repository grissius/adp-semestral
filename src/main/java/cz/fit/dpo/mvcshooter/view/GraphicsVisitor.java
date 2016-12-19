package cz.fit.dpo.mvcshooter.view;

import cz.fit.dpo.mvcshooter.model.object.*;
import cz.fit.dpo.mvcshooter.pattern.visitor.Visitor;

import java.awt.image.BufferedImage;

/**
 * Created by smolijar on 12/19/16.
 */
public class GraphicsVisitor implements Visitor {
    private Vector location;
    private GraphicsDrawer.Image image;

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
    }

    @Override
    public void visitEnemy(Enemy enemy) {
        visitGameObject(enemy);
        image = GraphicsDrawer.Image.ENEMY1;
    }

    public int getX() {
        return (int)location.getX();
    }

    public int getY() {
        return (int)location.getY();
    }

    public GraphicsDrawer.Image getImage() {
        return image;
    }
}
