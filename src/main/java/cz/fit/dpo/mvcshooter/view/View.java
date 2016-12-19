package cz.fit.dpo.mvcshooter.view;

import cz.fit.dpo.mvcshooter.model.Model;
import cz.fit.dpo.mvcshooter.pattern.observer.Observer;

/**
 * Created by smolijar on 12/19/16.
 */
public class View extends Observer {
    private Canvas canvas;

    public View(Model model) {
        this.canvas = new Canvas(model, 0, 0, model.getWidth(), model.getHeight());
    }

    public Canvas getCanvas() {
        return canvas;
    }

    @Override
    public void observerNotify() {
        this.canvas.thisIsHowYouForceGuiToRepaint();
    }
}
