package at.spengergasse.hbgm.ui;

import at.spengergasse.hbgm.utils.IBaseComponent;
import at.spengergasse.hbgm.data.Image;
import at.spengergasse.hbgm.data.Patient;
import at.spengergasse.hbgm.data.Series;
import at.spengergasse.hbgm.data.Study;

import javax.swing.*;

/**
 * The patient browser allows the user to browse through all the objects
 * loaded in the patient repository. Thus it should register as an observer
 * of the patient repository in order to display changes properly.
 * On the other hand the patient browser must notify all the registered
 * observers if the selection is changed.
 */
public abstract class AbstractPatientBrowser extends JTree implements IBaseComponent {

    /**
     * return a reference to the currently selected patient
     * @return currently selected patient
     */
    public abstract Patient selectedPatient();

    /**
     * return a reference to the currently selected study
     * @return currently selected study
     */
    public abstract Study selectedStudy();

    /**
     * return a reference to the currently selected series
     * @return currently selected series
     */
    public abstract Series selectedSeries();

    /**
     * return a reference to the currently selected image
     * @return currently selected image
     */
    public abstract Image selectedImage();

    public abstract Image getImage();
    public abstract Image setImage();
}
