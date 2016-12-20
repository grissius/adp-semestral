package cz.fit.dpo.mvcshooter.model;

import cz.fit.dpo.mvcshooter.pattern.observer.Observer;

/**
 * Created by smolijar on 12/20/16.
 */
public class MockView extends Observer {

    private int notified = 0;

    @Override
    public void observerNotify() {
        notified++;
    }

    public int getNotified() {
        return notified;
    }

    public void setNotified(int notified) {
        this.notified = notified;
    }
}
