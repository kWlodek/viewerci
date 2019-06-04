package hbgm.data;

import at.spengergasse.hbgm.data.Image;
import org.dcm4che2.data.DicomObject;
import org.dcm4che2.data.Tag;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * contains informations of series and a list of images
 */
@Entity
public class Series {
    @Id
    private String seriesInstanceUID;
    private Date acquisitionTime;
    private DicomObject dcm;
    private String description;
    private String device;
    private int picnumber;
    private Date date;
    private Date time;

    public Series()
    {
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="SeriesID")
    private List<Image> imageList = new ArrayList<Image>();


    public Series(DicomObject dcm) {
        // TODO get attribute values from dicom object
        this.dcm = dcm;
        if(dcm != null){
           if(dcm.getString(Tag.SeriesInstanceUID) != null){
               this.seriesInstanceUID = dcm.getString(Tag.SeriesInstanceUID);
           }
           else{
               System.out.println("No SeriesInstanceUID!");
           }
           if(dcm.getDate(Tag.AcquisitionTime) != null){
               this.acquisitionTime = dcm.getDate(Tag.AcquisitionTime);
           }
           else {
               System.out.println("No AcquisitionTime!");
           }
            if(dcm.getString(Tag.SeriesDescription) != null){
                this.description = (dcm.getString(Tag.SeriesDescription));
            }
            else{
                System.out.println("No SeriesDescription!");
            }
            if(dcm.getString(Tag.Modality) != null){
                this.device = dcm.getString(Tag.Modality);
            }
            else{
                System.out.println("No Modality!");
            }
            if(dcm.getDate(Tag.SeriesDate) != null){
                this.date = dcm.getDate(Tag.SeriesDate);
            }
            else{
                System.out.println("No Date!");
            }
            if(dcm.getDate(Tag.SeriesTime) != null){
                this.time = dcm.getDate(Tag.SeriesTime);
            }
            else{
                System.out.println("No Time!");
            }


        }
    }

    public Image add(Image img){
        // TODO implement:
        /*
        In the parameter an image instance is passed to this method.
        Check if the image list already contains an image with equal
        sopInstanceUID. If yes - return a reference to the already
        contained instance. If no - insert parameter instance and return
        a reference to it.
         */
        for(Image i : imageList) {
            if((i.getSopInstanceUID().equals(img.getSopInstanceUID()))) {
                return i;
            }
        }
        imageList.add(img);
        return img;

    }

    public String getSeriesInstanceUID() {
        return seriesInstanceUID;
    }

    public Date getAcquisitionTime() {
        return acquisitionTime;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    @Override
    public String toString(){
        String date = new SimpleDateFormat("dd.MM.yyyy").format(this.getDate());
        String time = new SimpleDateFormat("HH:mm:ss").format(this.getTime());
        return "\n\nDescription: " + this.description + "\nDevice: " + this.device + "\nDate: " + date + "\nTime: " + time;
    }

    public String getDevice()
    {
        return device;
    }

    public void setDevice(String device)
    {
        this.device = device;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getPicnumber()
    {
        return picnumber;
    }

    public void setPicnumber(int picnumber)
    {
        this.picnumber = picnumber;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public Date getTime()
    {
        return time;
    }

    public void setTime(Date time)
    {
        this.time = time;
    }
}
