package com.crud.traveller.patterns.observer;

public interface Observable {
    void registerObserver (Observer observer);
    void notifyObservers ();
    void removeObserver (Observer observer);
}
