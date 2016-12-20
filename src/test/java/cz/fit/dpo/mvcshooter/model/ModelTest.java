package cz.fit.dpo.mvcshooter.model;

import cz.fit.dpo.mvcshooter.model.memento.Memento;
import org.junit.Test;

/**
 * Created by smolijar on 12/20/16.
 */
public class ModelTest {
    private final int TICKS_EQUILIBRIUM = 1000;

    @Test
    public void testObserver() throws Exception {
        Model model = new Model();
        MockView view = new MockView();
        // remove enemies (moving idly)
        model.resetObjects();
        model.registerObserver(view);
        model.fire(1);
        for (int i = 0; i < TICKS_EQUILIBRIUM; ++i) {
            model.tick();
        }
        assert view.getNotified() < TICKS_EQUILIBRIUM;
    }

    @Test
    public void testMementos() throws Exception {
        Model model = new Model();
        model.resetObjects();
        Memento m1 = model.createMemento();
        for (int i = 0; i < 10; ++i) {
            model.fire(1);
        }
        Memento m2 = model.createMemento();
        for (int i = 0; i < 10; ++i) {
            model.addEnemy();
        }
        Memento m3 = model.createMemento();

        model.resetObjects();

        model.setMemento(m1);
        assert model.getObjects().size() == 1; // sling
        model.setMemento(m2);
        assert model.getObjects().size() == 11; // sling + 10 shots
        model.setMemento(m3);
        assert model.getObjects().size() == 21; // sling + 10 shots + 10 enemies
    }

    @Test
    public void testCooking() throws Exception {
        Model model = new Model();
        MockView view = new MockView();
        // remove enemies (moving idly)
        model.resetObjects();
        model.registerObserver(view);
        model.startCooking();
        for (int i = 0; i < 10; ++i) {
            model.tick();
        }
        model.cookingRelease();
        for (int i = 0; i < TICKS_EQUILIBRIUM; ++i) {
            model.tick();
        }
        int power10projectileOnScreen = view.getNotified();
        view.setNotified(0);

        model.startCooking();
        for (int i = 0; i < 200; ++i) {
            model.tick();
        }
        model.cookingRelease();
        for (int i = 0; i < TICKS_EQUILIBRIUM; ++i) {
            model.tick();
        }
        int power200projectileOnScreen = view.getNotified();
        view.setNotified(0);
        // more power, faster, less ticks on screen
        assert power10projectileOnScreen > power200projectileOnScreen;
    }
}