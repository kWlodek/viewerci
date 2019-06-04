package we_are_number_one;

import at.spengergasse.hbgm.ui.AbstractInfoPanel;
import at.spengergasse.hbgm.ui.Viewer;
import at.spengergasse.hbgm.utils.IBaseComponent;
import at.spengergasse.hbgm.utils.IObserver;

import javax.sound.sampled.Line;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.xml.soap.Text;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Nico on 17.11.2017.
 */
public class InfoPanelImplement extends AbstractInfoPanel implements IObserver
{
    private ArrayList<IObserver> observers = new ArrayList<IObserver>();
    private Viewer v;
    private String text = "no patient selected!";



    public InfoPanelImplement(){

        setBorder(new TitledBorder("Info Panel"));

        Font font = new Font("Verdana", Font.BOLD, 12);
        this.setFont(font);


        this.setWrapStyleWord(true);
        this.setLineWrap(true);
        this.setEditable(false);
        this.setText(text);



    }

   /* @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        FontMetrics fm = g2.getFontMetrics();
        g2.drawLine(0,1, getWidth(), 1);
        g2.dispose();
    }*/

    @Override
    public void registerObserver(IObserver observer)
    {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer)
    {
        observers.remove(observer);
    }

    @Override
    public void configure(Viewer viewer)
    {
        this.v = viewer;
        v.getPatientBrowser().registerObserver(this);
    }

    @Override
    public void changed(IBaseComponent c)
    {

        try
        {
            if (v.getPatientBrowser().selectedPatient() != null){
                text = v.getPatientBrowser().selectedPatient().toString();
                this.setText(text);
            }
            else
            {
                text = "no patient selected!";
                this.setText(text);
            }
            if (v.getPatientBrowser().selectedStudy() != null){
                text += v.getPatientBrowser().selectedStudy().toString();
                this.setText(text);
            }
            else
            {
                text += "\n\nno study selected!";
                this.setText(text);
            }
            if (v.getPatientBrowser().selectedSeries() != null){
                text += v.getPatientBrowser().selectedSeries().toString();
                text += "\nNumber of images: " + v.getPatientBrowser().selectedSeries().getImageList().size();
                this.setText(text);
            }
            else
            {
                text += "\n\nno series selected!";
                this.setText(text);
            }
            if (v.getPatientBrowser().selectedImage() != null){
                text += v.getPatientBrowser().selectedImage().toString();
                this.setText(text);
            }
            else
            {
                text += "\n\nno image selected!";
                this.setText(text);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
