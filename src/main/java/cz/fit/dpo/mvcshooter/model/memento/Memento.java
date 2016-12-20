package cz.fit.dpo.mvcshooter.model.memento;

/**
 * Created by smolijar on 12/20/16.
 */
public class Memento {
    private State state;

    public Memento(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
