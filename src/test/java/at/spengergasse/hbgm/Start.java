package at.spengergasse.hbgm;


import we_are_number_one.*;
import at.spengergasse.hbgm.ui.Viewer;

import javax.swing.*;

public class Start {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Viewer viewer = new Viewer();
        RealPixelMapper pm = new RealPixelMapper();
        LookupTableImplement lt = new LookupTableImplement();
        //ControlPanelLucas cp = new ControlPanelLucas();
        ControlPanelImplement cpi = new ControlPanelImplement();
        RealImagePanel imgp = new RealImagePanel();
        InfoPanelImplement ip = new InfoPanelImplement();
        RealPatientRepository pr = new RealPatientRepository();
        PatientBrowserImpl pb = new PatientBrowserImpl();

        //cp.registerObserver(pm);
        //pr.registerObserver(pb);

        viewer.setPatientRepository(pr);
        viewer.setPixelMapper(pm);
        viewer.setLookupTable(lt);
        viewer.setControlPanel(cpi);
        viewer.setImagePanel(imgp);
        viewer.setInfoPanel(ip);
        viewer.setPatientBrowser(pb);

        viewer.configure();


        viewer.setSize(1000,800);
        viewer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewer.setVisible(true);

    }
}
