package atl.g52818.othello.util;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the observable events in othello game
 */
public class Observable {

    private List<Observer> observers;

    /**
     * Constructor of Obervable. Creates a new ArrayList.
     */
    public Observable() {
        observers = new ArrayList<>();
    }

    /**
     * Adds the given observer in parameter to the list of obervers
     *
     * @param obs the observer to be added to the list of observers
     */
    public void addObserver(Observer obs) {
        observers.add(obs);
    }

    /**
     * Notifies all observers in the list of observers
     */
    public void notifyObservers() {
        for (Observer obs : observers) {
            obs.update();
        }
    }

}

