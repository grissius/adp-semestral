package cz.fit.dpo.mvcshooter.controller;

import cz.fit.dpo.mvcshooter.model.Model;
import cz.fit.dpo.mvcshooter.view.Canvas;

import java.awt.event.KeyEvent;

/**
 * Created by smolijar on 12/19/16.
 */
public class FrontController {

    Model model;
    Canvas view;

    public FrontController(Model model, Canvas view) {
        this.model = model;
        this.view = view;
    }

    public void runGame() {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                model.tick();
                view.thisIsHowYouForceGuiToRepaint();
            }
        });
        thread.start();
        System.out.println('-');
    }

    public void handleKeyboard(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                System.out.println("Move up");
                model.getSling().move(-1);
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("Move down");
                model.getSling().move(1);
                break;
        }
    }
}
