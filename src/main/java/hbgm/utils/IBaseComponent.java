package hbgm.utils;

import hbgm.ui.Viewer;

/**
 * interface which should be implemented by all the components of the viewer
 */

public interface IBaseComponent extends hbgm.utils.IObserver {

    /**
     * register an observer which needs to be notified about changes in the component
     * @param observer
     */
    public void registerObserver(hbgm.utils.IObserver observer);

    /**
     * remove an observer - will not be notified about changes any more
     * @param observer
     */
    public void removeObserver(IObserver observer);

    /**
     * configure the component
     * @param viewer reference to the viewer component
     */
    public void configure(Viewer viewer);
}
