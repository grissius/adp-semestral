package cz.fit.dpo.mvcshooter.model.object.sling;

import cz.fit.dpo.mvcshooter.model.factory.AbstractObjectFactory;
import cz.fit.dpo.mvcshooter.model.geometry.Vector;
import cz.fit.dpo.mvcshooter.model.object.GameObject;
import cz.fit.dpo.mvcshooter.model.object.pattern.visitor.Visitor;
import cz.fit.dpo.mvcshooter.model.object.projectile.Projectile;
import cz.fit.dpo.mvcshooter.model.object.sling.state.DoubleShootingState;
import cz.fit.dpo.mvcshooter.model.object.sling.state.SingleShootingState;
import cz.fit.dpo.mvcshooter.model.object.sling.state.SlingState;

import java.util.List;

/**
 * Created by smolijar on 10/25/16.
 */
public class Sling extends GameObject {

    private AbstractObjectFactory objectFactory;
    private SlingState state;
    private ActiveState activeState;


    private static enum ActiveState {
        SINGLE,
        DOUBLE
    }

    public Sling(Vector battlefield) {
        super();
        size.setX(25);
        size.setY(69);
        location.setX(25);
        location.setY(battlefield.getY() / 2);
        setSingleState();
    }

    public Sling() {
    }

    public Sling clone() {
        Sling sling = new Sling();
        sling.setAs(this);
        if (activeState == ActiveState.SINGLE) {
            sling.setSingleState();
        } else {
            sling.setDoubleState();
        }
        return sling;
    }

    private void setSingleState() {
        this.activeState = ActiveState.SINGLE;
        this.state = new SingleShootingState();
    }

    private void setDoubleState() {
        this.activeState = ActiveState.DOUBLE;
        this.state = new DoubleShootingState();
    }

    public void swapStates() {
        if (activeState == ActiveState.DOUBLE) {
            setSingleState();
        } else {
            setDoubleState();
        }
    }

    public void setFactory(AbstractObjectFactory objectFactory) {
        this.objectFactory = objectFactory;
    }

    public AbstractObjectFactory getObjectFactory() {
        return objectFactory;
    }

    public List<Projectile> fire(int power) {
        return state.fire(this, power);
    }

    @Override
    public synchronized boolean move(Vector battlefield, float gravity) {
        direction.diminish(1.2);
        return super.move(battlefield, gravity);
    }

    public synchronized void carry(int value) {
        direction.addY(3 * value);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitSling(this);
    }

    public String getUserMsg() {
        String msg = "";
        msg += "Sling state: ";
        if (activeState == ActiveState.SINGLE) {
            msg += "single";
        } else if (activeState == ActiveState.DOUBLE) {
            msg += "double";
        }
        msg += ", angle: " + getDisplayAngle();
        return msg;
    }

}
