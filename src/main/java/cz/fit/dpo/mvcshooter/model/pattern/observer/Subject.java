package cz.fit.dpo.mvcshooter.model.pattern.observer;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by smolijar on 10/25/16.
 */
public abstract class Subject {
    private Set<Observer> observers;

    public Subject() {
        observers = new HashSet<Observer>();
    }

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void unregisterObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer o : observers) {
            o.observerNotify();
        }
    }
}
