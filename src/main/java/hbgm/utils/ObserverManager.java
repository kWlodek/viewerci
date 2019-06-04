package hbgm.utils;

import at.spengergasse.hbgm.utils.IBaseComponent;
import at.spengergasse.hbgm.utils.IObserver;

import java.util.HashSet;
import java.util.Set;

public class ObserverManager {
    private Set<IObserver> observers = new HashSet<IObserver>();

    public void registerObserver(IObserver observer)
    {
        observers.add(observer);
    }

    public void removeObserver(IObserver observer)
    {
        observers.remove(observer);
    }

    public void notifyObservers(IBaseComponent component)
    {
        for (IObserver observer : observers)
            observer.changed(component);
    }
}
