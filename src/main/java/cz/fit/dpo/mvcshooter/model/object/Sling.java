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
//        direction.setX(2);
//        direction.setY(2);
    }
    public void carry(int value) {
//        location.addY(value*3);
        direction.addY(value*3);
    }

    public boolean move() {
        if(direction.getX() + direction.getY() != 0) {
//            System.out.println(location);
            location.addX(direction.getX());
            location.addY(direction.getY());
            direction.diminish();
            return true;
        }
        return false;
    }
}
