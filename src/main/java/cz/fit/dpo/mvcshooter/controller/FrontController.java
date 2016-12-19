package cz.fit.dpo.mvcshooter.controller;

import cz.fit.dpo.mvcshooter.model.Model;
import cz.fit.dpo.mvcshooter.view.View;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by smolijar on 12/19/16.
 */
public class FrontController {

    Model model;
    View view;

    public FrontController(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void runGame() {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(33);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                model.tick();
                try {
                    Robot r = new Robot();
                    r.keyRelease(KeyEvent.VK_ENTER);
                } catch (AWTException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public void handleKeyboard(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                model.getSling().carry(-1);
                break;
            case KeyEvent.VK_DOWN:
                model.getSling().carry(1);
                break;
            case KeyEvent.VK_LEFT:
                model.getSling().turn(-1);
                break;
            case KeyEvent.VK_RIGHT:
                model.getSling().turn(1);
                break;
            case KeyEvent.VK_SPACE:
                model.fire();
                break;
        }
    }
}
