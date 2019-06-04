package hbgm.module_interfaces;

/**
 * An observable object may be observed by an arbitrary number
 * of observer objects. If there occurs a relevant change,
 * the observable must notify all the registered observers.
 */
public interface IObservable {
    /**
     * register an observer object to listen for changes in this
     * observable object. All the registered observers must be
     * maintained in a collection and must be notified upon changes
     * @param o reference to the observer to be registered
     */
    public void registerObserver(hbgm.module_interfaces.IObserver o);

    /**
     * remove an observer from the collection of observers to be notified
     * @param o reference to observer not to be notified any more
     */
    public void removeObserver(IObserver o);
}
