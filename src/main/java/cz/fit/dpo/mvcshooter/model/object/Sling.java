package cz.fit.dpo.mvcshooter.model.object;

/**
 * Created by smolijar on 10/25/16.
 */
public class Sling extends GameObject {
    public Sling() {
        super();
        size.setX(25);
        size.setY(69);
        location.setX(10);
        location.setY(250);
    }
    public void move(int value) {
        location.setY(location.getY()+value*3);
    }
}
