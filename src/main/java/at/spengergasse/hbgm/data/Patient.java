package at.spengergasse.hbgm.data;

import org.dcm4che2.data.DicomObject;
import org.dcm4che2.data.Tag;


import javax.persistence.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * contains patient data and the patient's studies list
 */
@Entity
@NamedQuery(name="Patient.findAll", query = "SELECT p FROM Patient p")
public class Patient {

    @Id
    private String patientID;
    private String name;
    private Date birthDate;
    private String sex;

    public Patient()
    {
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="PatientID")
    private List<Study> studyList = new ArrayList<Study>();


    public Patient(DicomObject dcm) {
        // TODO obtain attribute values from dicom object
        if(dcm != null) {
            if(dcm.getDate(Tag.PatientBirthDate) != null) {
                this.birthDate = dcm.getDate(Tag.PatientBirthDate);
            }
            else{
                System.out.println("no birthdate");
            }
            if(dcm.getString(Tag.PatientID) != null){
                this.patientID = dcm.getString(Tag.PatientID);
            }
            else{
                System.out.println("no PatientID");
            }
            if(dcm.getString(Tag.PatientName) != null){
                this.name = dcm.getString(Tag.PatientName);
            }
            else{
                System.out.println("no Patientname");
            }
            if(dcm.getString(Tag.PatientSex) != null){
                this.sex = (dcm.getString(Tag.PatientSex));
            }
            else{
                System.out.println("no PatientSex");
            }

        }
        else{
            System.out.println("DCM is null!");
        }
    }

    public Study add(Study st){
        // TODO implement:
        /*
        In the parameter a study instance is passed to this method.
        Check if the studies list already contains a study with equal
        studyInstanceUID. If yes - return a reference to the already
        contained instance. If no - insert parameter instance and return
        a reference to it.
         */
        for(Study s : studyList) {
            if((s.getStudyInstanceUID().equals(st.getStudyInstanceUID()))) {
                return s;
            }
        }
        studyList.add(st);
        return st;
    }

    public String getPatientID() {
        return patientID;
    }

    public String getName() {
        return name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getSex() { return sex; }

    public List<Study> getStudyList() {
        return studyList;
    }

    @Override
    public String toString(){
        if(this.getBirthDate() != null){
            String date = new SimpleDateFormat("dd.MM.yyyy").format(this.getBirthDate());
            return "Name: " + this.getName()  + "\nGender: " + this.getSex() + "\nDate of birth: " + date;
        }

      return "Name: " + this.getName()  + "\nGender: " + this.getSex();
    }
}
