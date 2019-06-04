package at.spengergasse.hbgm.module_interfaces;

import java.io.File;

/**
 * a file loader is used for loading all the DICOM files
 * contained in the folder
 */
public interface IFileLoader extends IModuleBase {
    /**
     * load DICOM files of this folder,
     * assemble object model (Patients, Studies, ...)
     * an pass patients to patient repository
     * @param folder containing dicom files
     * @throws Exception if read errors occur
     */
    public void loadFolder(File folder) throws Exception;
}
