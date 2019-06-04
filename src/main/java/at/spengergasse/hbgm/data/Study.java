package at.spengergasse.hbgm.data;

import org.dcm4che2.data.DicomObject;
import org.dcm4che2.data.Tag;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * contains data of the study and a list of series
 */
@Entity
public class Study {
    @Id
    private String studyInstanceUID;
    private Date studyDate;
    private DicomObject dcm;
    private String description;

    public Study()
    {
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="StudyID")
    private List<Series> seriesList = new ArrayList<Series>();

    public Study(DicomObject dcm) {
        // TODO get all the attribute values from the dicom object
        if (dcm != null) {
            this.dcm = dcm;
        }
        if (dcm.getString(Tag.StudyInstanceUID) != null) {
            this.studyInstanceUID = dcm.getString(Tag.StudyInstanceUID);
        } else {
            System.out.println("no StudyID!");
        }
        if (dcm.getDate(Tag.StudyDate) != null) {
            this.studyDate = dcm.getDate(Tag.StudyDate);
        } else {
            System.out.println("no StudyDate!");
        }
        if (dcm.getString(Tag.StudyDescription) != null)
        {
            this.description = dcm.getString(Tag.StudyDescription);
        }
        else{
            System.out.println("no StudyDescription!");
        }
    }

    public Series add(Series ser) {
        // TODO implement:
        /*
        In the parameter a series instance is passed to this method.
        Check if the series list already contains a series with equal
        seriesInstanceUID. If yes - return a reference to the already
        contained instance. If no - insert parameter instance and return
        a reference to it.
         */
        for (Series s : seriesList) {
            if ((s.getSeriesInstanceUID().equals(ser.getSeriesInstanceUID()))) {
                return s;
            }
        }
        seriesList.add(ser);
        return ser;
    }

    public String getStudyInstanceUID() {
        return studyInstanceUID;
    }

    public Date getStudyDate() {
        return studyDate;
    }

    public List<Series> getSeriesList() {
        return seriesList;
    }

    @Override
    public String toString() {
        String date = new SimpleDateFormat("dd.MM.yyyy").format(this.getStudyDate());
        return "\n\nDescription: " + this.getDescription() + "\nDate: " + date;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}