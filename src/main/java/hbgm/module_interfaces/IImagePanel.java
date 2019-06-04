package hbgm.module_interfaces;

import at.spengergasse.hbgm.module_interfaces.IModuleBase;
import at.spengergasse.hbgm.module_interfaces.IModuleUI;

/**
 * an image panel shows the image which is currently selected
 * in the patient browser.
 * the image panel must actively retrieve the selected image
 * and show it
 */
public interface IImagePanel extends IModuleBase, IModuleUI {
}
