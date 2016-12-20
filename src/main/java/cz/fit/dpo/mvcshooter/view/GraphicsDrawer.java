package cz.fit.dpo.mvcshooter.view;

import cz.fit.dpo.mvcshooter.model.object.GameObject;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Ondrej Stuchlik
 */
public class GraphicsDrawer {
    private static final int INFO_X = 5;
    private static final int INFO_Y = 15;

    public static enum Image {
        CANNON,
        ENEMY1,
        ENEMY2,
        MISSILE,
        COLLISION
    }

    private BufferedImage cannonImage;
    private BufferedImage enemyImage1;
    private BufferedImage enemyImage2;
    private BufferedImage missileImage;
    private BufferedImage collisionImage;

    public GraphicsDrawer() {
        try {
            cannonImage = ImageIO.read(getClass().getResourceAsStream("/images/cannon.png"));
            enemyImage1 = ImageIO.read(getClass().getResourceAsStream("/images/enemy1.png"));
            enemyImage2 = ImageIO.read(getClass().getResourceAsStream("/images/enemy2.png"));
            missileImage = ImageIO.read(getClass().getResourceAsStream("/images/missile.png"));
            collisionImage = ImageIO.read(getClass().getResourceAsStream("/images/collision.png"));
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }


    private BufferedImage getImage(Image image) {
        switch (image) {
            case CANNON:
                return cannonImage;
            case ENEMY1:
                return enemyImage1;
            case ENEMY2:
                return enemyImage2;
            case MISSILE:
                return missileImage;
            case COLLISION:
                return collisionImage;
        }
        return collisionImage;
    }
    
    public void drawGameObject(Graphics g, GameObject object) {
        GraphicsVisitor visitor = new GraphicsVisitor();
        object.accept(visitor);

        // image rotation
        double rotationRequired = visitor.getAngle();
        double locationX = visitor.getSize().getX()/2;
        double locationY = visitor.getSize().getY()/2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        g.drawImage(op.filter(getImage(visitor.getImage()), null), (int)visitor.getLocation().getX(), (int)visitor.getLocation().getY(), null);
    }
}
