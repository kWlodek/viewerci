package at.spengergasse.hbgm.data;

import at.spengergasse.hbgm.utils.IBaseComponent;

import java.io.File;
import java.util.List;


/**
 * The patient repository stores all the patient / study / series / image instances.
 * Actually the repository holds only a list of patients, each patient holds a
 * list of studies, and so on.
 *
 *
 */
public interface IPatientRepository extends IBaseComponent {

    /**
     * Add all the DICOM files (.dcm) contained in the folder and its sub-folders
     * If the operation results with changes in the repository all the registered
     * observers must be notified
     *
     * @param folder folder containing DICOM files
     * @throws Exception on read errors
     */
    public void addDicomFolder(File folder) throws Exception;

    /**
     * returns a list of the patients in this repository
     * @return list of patients
     */
    public List<Patient> patientList();

}
