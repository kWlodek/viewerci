package hbgm.module_interfaces;

import javax.swing.*;

/**
 * base interface for all the module_interfaces which have a user interface.
 */
public interface IModuleUI {
    /**
     * return a reference to the UI componente of this module.
     * this method will be called in order to add the component
     * to the main window
     * @return reference to UI component of this module
     */
    public JComponent uiComponent();
}
