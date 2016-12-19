package cz.fit.dpo.mvcshooter.view;

import cz.fit.dpo.mvcshooter.model.object.GameObject;
import cz.fit.dpo.mvcshooter.model.object.Sling;
import cz.fit.dpo.mvcshooter.model.object.Vector;

import java.awt.Graphics;
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
        
    
    public void drawGameObject(Graphics g, GameObject object) {
        Vector center = object.getCenter();
        g.drawImage(cannonImage, 
                (int)center.getX(),
                (int)center.getY(), null);
    }
}
