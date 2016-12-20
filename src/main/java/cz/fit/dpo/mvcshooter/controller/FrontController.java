package cz.fit.dpo.mvcshooter.controller;

import cz.fit.dpo.mvcshooter.model.Model;
import cz.fit.dpo.mvcshooter.view.View;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by smolijar on 12/19/16.
 */
public class FrontController {

    private Model model;
    private View view;

    private boolean GOING_UP = false;
    private boolean GOING_DOWN = false;
    private boolean TURNING_LEFT = false;
    private boolean TURNING_RIGHT = false;
    private boolean HOLDING_SPACE = false;
    private long fireStart = 0;

    public FrontController(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    private void handleInput() {
        if(GOING_UP) {
            model.getSling().carry(-1);
        }
        if(GOING_DOWN) {
            model.getSling().carry(1);
        }
        if(TURNING_LEFT) {
            model.getSling().turn(-1);
        }
        if(TURNING_RIGHT) {
            model.getSling().turn(1);
        }
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
                handleInput();
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

    public void handleKeyboardPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                GOING_UP = true;
                break;
            case KeyEvent.VK_DOWN:
                GOING_DOWN = true;
                break;
            case KeyEvent.VK_LEFT:
                TURNING_LEFT = true;
                break;
            case KeyEvent.VK_RIGHT:
                TURNING_RIGHT = true;
                break;
            case KeyEvent.VK_SPACE:
                if(!HOLDING_SPACE) {
                    HOLDING_SPACE = true;
                    this.model.startCooking();
                }
                break;
            case KeyEvent.VK_M:
                model.swapMode();
                break;
            case KeyEvent.VK_R:
                model.resetObjects();
                break;
            case KeyEvent.VK_E:
                model.addEnemy();
                break;
            case KeyEvent.VK_S:
                model.swapSling();
                break;
            case KeyEvent.VK_F1:
                view.showHelp();
                break;
        }
    }

    public void handleKeyboardReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                GOING_UP = false;
                break;
            case KeyEvent.VK_DOWN:
                GOING_DOWN = false;
                break;
            case KeyEvent.VK_LEFT:
                TURNING_LEFT = false;
                break;
            case KeyEvent.VK_RIGHT:
                TURNING_RIGHT = false;
                break;
            case KeyEvent.VK_SPACE:
                model.cookingRelease();
                HOLDING_SPACE = false;
                break;
        }
    }
}
