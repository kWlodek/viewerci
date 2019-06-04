package at.spengergasse.hbgm.ui;

import at.spengergasse.hbgm.utils.IBaseComponent;

import javax.swing.*;

/**
 * The purpose of this component is to display informations about the currently
 * selected object of the patient browser. Thus the component should register
 * itself as an observer of the patient browser.
 */
public abstract class AbstractInfoPanel extends JTextArea implements IBaseComponent {
}
