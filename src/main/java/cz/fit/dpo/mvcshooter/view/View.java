package cz.fit.dpo.mvcshooter.view;

import cz.fit.dpo.mvcshooter.model.Model;
import cz.fit.dpo.mvcshooter.model.object.pattern.observer.Observer;

import javax.swing.*;

/**
 * Created by smolijar on 12/19/16.
 */
public class View extends Observer {
    private Canvas canvas;

    public View(Model model) {
        this.canvas = new Canvas(model, 0, 0, (int)model.getBattlefield().getX(), (int)model.getBattlefield().getY());
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void showHelp() {
        String help = "Controls: \n" +
                "Arrows: move / rotate sling \n" +
                "Space: fire (hold to control power) \n" +
                "m: change mode \n" +
                "e: add enemy \n" +
                "s: change sling state \n" +
                "r: reset elements - remove all enemies and prjectiles \n" +
                "f1: help \n" +
                "f5: save game (in memory) \n" +
                "f9: load game (last saved)";
        JOptionPane.showMessageDialog(canvas, help);
    }

    @Override
    public void observerNotify() {
        this.canvas.thisIsHowYouForceGuiToRepaint();
    }
}
