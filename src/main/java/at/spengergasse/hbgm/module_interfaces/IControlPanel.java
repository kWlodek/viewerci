package at.spengergasse.hbgm.module_interfaces;

/**
 * a control panel must provide the possibility to
 * adjust center, width and alpha value.
 * getters are provided to retrieve these values
 */
public interface IControlPanel extends IModuleBase, IModuleUI {
    public int getCenter();
    public int getWidth();
    public int getAlpha();
}
