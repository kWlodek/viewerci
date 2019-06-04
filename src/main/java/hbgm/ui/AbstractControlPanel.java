package hbgm.ui;

import hbgm.utils.IBaseComponent;

import javax.swing.*;

/**
 * The control panel is a component which allows the user to change image contrast.
 * It should contain sliders which are used to change window width and center
 * or the alpha value.
 * On changes the component must set the corresponding values in the lookup table.
 * Additionall all the registered observers must be notified.
 */


public abstract class AbstractControlPanel extends JPanel implements IBaseComponent {
    public abstract void setHighest(int highest);

    public abstract void setWidthValue(int i);
}
