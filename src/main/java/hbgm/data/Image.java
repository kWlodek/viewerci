package hbgm.data;

import org.dcm4che2.data.DicomObject;
import org.dcm4che2.data.Tag;
import org.dcm4che2.io.DicomInputStream;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.File;
import java.io.IOException;

/**
 * data of an image instance
 */

@Entity
public class Image {
    @Id
    private String sopInstanceUID;
    private File dicomFile;
    private int rows;
    private int columns;
    private int minValue; //wurde auf int geändert
    private int maxValue; //dito
    private DicomObject dcm;
    private int imageid;
    private int instnr;

    public Image()
    {
    }

    public Image(DicomObject dicomFile) {
        this.dcm = dicomFile;
        // TODO all the attribute values must be determined from the dicom file
        try {
            //DicomInputStream di = new DicomInputStream(dicomFile);
            // dcm = di.readDicomObject();

            this.instnr = dcm.getInt((Tag.InstanceNumber));
            this.rows = dcm.getInt(Tag.Rows);
            this.columns = dcm.getInt(Tag.Columns);
            this.sopInstanceUID = dcm.getString(Tag.SOPInstanceUID);
            this.minValue = dcm.getInt(Tag.SmallestImagePixelValue);
            this.maxValue = dcm.getInt(Tag.LargestImagePixelValue);

            System.out.println(dcm.getInt(Tag.ImageID));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(dcm != null){
            if(dcm.getString(Tag.ImageID) != null){
                this.imageid = dcm.getInt(Tag.ImageID);


            }
            else{
                System.out.println("No ImageID!");
            }
            }
    }

    public String getSopInstanceUID() {
        return sopInstanceUID;
    }

    public File getDicomFile() {
        return dicomFile;
    }

    public short[] getPixelValues() {
        // TODO: implement
        /*
        for saving RAM space pixel values are read from dicom file only if needed
         */
        if (dcm != null) {
            return dcm.getShorts(Tag.PixelData);
        }
        return null;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getMinValue() {
        return minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public int getInstnr() {
        return instnr;
    }

    @Override
    public String toString() {
        int resolotion = this.getColumns() * this.getRows();
        return "\n\nID: " + this.instnr + "\nAuflösung: " + resolotion;
    }

    public int getImageid()
    {
        return imageid;
    }

    public void setImageid(int imageid)
    {
        this.imageid = imageid;
    }


}
