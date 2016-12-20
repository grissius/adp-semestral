package cz.fit.dpo.mvcshooter.model.object;

import cz.fit.dpo.mvcshooter.model.geometry.Vector;
import cz.fit.dpo.mvcshooter.model.object.sling.Sling;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by smolijar on 12/20/16.
 */
public class GameObjectTest {
    @Test
    public void visitingTest() throws Exception {
        MockLocationVisitor visitor = new MockLocationVisitor();
        Sling sling = new Sling();
        sling.accept(visitor);
        double initialY = visitor.getLocation().getY();
        sling.carry(1);
        sling.move(500, 500, 0);
        sling.accept(visitor);
        double movedY = visitor.getLocation().getY();
        assert initialY != movedY;
    }

    @Test
    public void testCollision() throws Exception {
        GameObject ractangle1 = new GameObject();
        GameObject ractangle2 = new GameObject();
        ractangle1.location = new Vector(0.5,0.5);
        ractangle1.size = new Vector(1,1);
        ractangle2.location = new Vector(1.5,1.5);
        assert ractangle1.collides(ractangle2) == false;
        ractangle2.size = new Vector(1,1);
        ractangle2.location = new Vector(1,1);
        assert ractangle1.collides(ractangle2) == true;
    }
}