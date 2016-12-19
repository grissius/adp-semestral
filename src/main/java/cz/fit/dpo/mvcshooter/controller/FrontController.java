package cz.fit.dpo.mvcshooter.controller;

import cz.fit.dpo.mvcshooter.model.Model;
import cz.fit.dpo.mvcshooter.view.View;

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
                Thread inner = new Thread(() -> {
                    model.tick();
                });
                inner.start();
            }
        });
        thread.start();
    }

    public void handleKeyboard(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                System.out.println("Move up");
                model.getSling().carry(-1);
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("Move down");
                model.getSling().carry(1);
                break;
        }
    }
}
