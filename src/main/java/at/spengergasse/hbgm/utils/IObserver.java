package at.spengergasse.hbgm.utils;

import at.spengergasse.hbgm.utils.IBaseComponent;

/**
 * An observer may be registered with any component in order to
 * obtain notifications about changes in the component
 */

public interface IObserver {
    /**
     * This method is called upon changes in a component with which the observer is registered
     *
     * @param c reference to component which was changed
     */
    public void changed(IBaseComponent c);
}
