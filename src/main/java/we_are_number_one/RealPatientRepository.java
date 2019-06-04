package we_are_number_one;

import at.spengergasse.hbgm.data.*;
import at.spengergasse.hbgm.ui.Viewer;
import at.spengergasse.hbgm.utils.IBaseComponent;
import at.spengergasse.hbgm.utils.IObserver;
import org.dcm4che2.data.BasicDicomObject;
import org.dcm4che2.data.DicomObject;
import org.dcm4che2.data.DicomObjects;
import org.dcm4che2.io.DicomInputStream;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elias on 20.10.2017.
 */
public class RealPatientRepository implements IPatientRepository {

    private EntityManager em;
    private ArrayList<IObserver> observers = new ArrayList<IObserver>();
    private List<File> filelist = new ArrayList<File>();
    private ArrayList<Patient> patientlist = new ArrayList<Patient>();
    private Viewer viewer;
    private int cnt = 0;
    private Patient b;

    public void registerObserver(IObserver observer) {
        this.observers.add(observer);
    }

    public void removeObserver(IObserver observer) {
        this.observers.remove(observer);
    }

    public void configure(Viewer viewer) {
        this.viewer = viewer;
    }

    public void changed(IBaseComponent c) {

    }

    public RealPatientRepository()
    {
        em = Persistence.createEntityManagerFactory("viewer").createEntityManager();
        for (IObserver obs : observers) {
            obs.changed(this);
        }
    }

    public void addDicomFolder(File folder) throws Exception {
        //.dcm
        //System.out.println("I'm in!");

        if (folder != null) {


            File[] allfiles = folder.listFiles();
            for (int i = 0; i < allfiles.length; i++) {
                String x = allfiles[i].getName().toLowerCase();
                //System.out.println(x);
                if (x.endsWith(".dcm")) {
                    //System.out.println("DICOM found!");
                    filelist.add(allfiles[i]);
                } else {
                    //System.out.println("Not a DICOM file!");
                    //System.out.println(allfiles[i].getF());
                }
            }

            for (File f : filelist) {
                DicomInputStream di = new DicomInputStream(f);
                DicomObject dc = di.readDicomObject();
                //Patient pat = new Patient(dc);
                Patient p = add(new Patient(dc));

                Study s = p.add(new Study(dc));
                Series se = s.add(new Series(dc));
                Image img = se.add(new Image(dc));







            }

            em.getTransaction().begin();
            em.getTransaction().commit();


            for (IObserver obs : observers) {
                obs.changed(this);
            }
        }
    }
        private Patient add(Patient p){
            /*for(Patient pat: patientlist) {
                if((pat.getPatientID().equals(p.getPatientID()))){
                    return pat;
                }
            }*/

            Patient da = em.find(Patient.class, p.getPatientID());
            if (da != null)
            {
                return da;
            }
            else
            {
                em.persist(p);
            }
            //patientlist.add(p);

            return p;


    }

        public List<Patient> patientList () {

            TypedQuery<Patient> query = em.createNamedQuery("Patient.findAll", Patient.class);
            List<Patient> liste = query.getResultList();

            return liste;
        }
}