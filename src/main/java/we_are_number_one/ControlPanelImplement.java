package we_are_number_one;

import at.spengergasse.hbgm.ui.AbstractControlPanel;
import at.spengergasse.hbgm.ui.Viewer;
import at.spengergasse.hbgm.utils.IBaseComponent;
import at.spengergasse.hbgm.utils.IObserver;
import at.spengergasse.hbgm.utils.ObserverManager;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.SliderUI;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by Nico on 24.10.2017.
 */
public class ControlPanelImplement extends AbstractControlPanel implements IObserver
{
    private ArrayList<IObserver> observers = new ArrayList<IObserver>();
    private Viewer viewer;
    private int highest = 0;

    private JSlider slWidth = new JSlider(0, 255, 1);
    private JSlider slCenter = new JSlider(0, 255, 1);
    private JSlider slAlpha = new JSlider(0, 100, 1);


    public ControlPanelImplement()
    {
        setBorder(new TitledBorder("Brightness & Contrast"));
        this.setLayout(new GridLayout(0, 1));
        this.add(slWidth);
        this.add(slCenter);
        this.add(slAlpha);

        slWidth.setBorder(new TitledBorder("Width"));
        slCenter.setBorder(new TitledBorder("Center"));
        slAlpha.setBorder(new TitledBorder("Opacity"));

        /*
        Hashtable labels = new Hashtable();
        labels.put(0, new JLabel("0"));
        labels.put(255/2, new JLabel("127.5"));
        labels.put(255, new JLabel("255"));
        */


        Hashtable alphalables = new Hashtable();
        alphalables.put(0, new JLabel("0"));
        alphalables.put(50, new JLabel("50"));
        alphalables.put(100, new JLabel("100"));

        //this.setBorder(BorderFactory.createEmptyBorder(50,10,50,10));


        slAlpha.setLabelTable(alphalables);
        slAlpha.setMajorTickSpacing(50);
        slAlpha.setMinorTickSpacing(10);
        slAlpha.setPaintTicks(true);
        slAlpha.setPaintLabels(true);

        /*
        slCenter.setValue((int)Math.round(255 * 0.80));
        slAlpha.setValue((int) Math.round(255 * 0.75));
        slWidth.setValue(slWidth.getMaximum());
        */

        slCenter.addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent e)
            {
                try
                {
                    viewer.getLookupTable().setCenter(slCenter.getValue());
                    System.out.println("center: " + slCenter.getValue());
                    //viewer.getPixelMapper().mapImage(viewer.getImagePanel().getImage());
                    //viewer.getImagePanel().repaint();
                    for (IObserver obs : observers)
                    {
                        obs.changed(ControlPanelImplement.this);
                    }
                } catch (Exception e1)
                {
                    e1.printStackTrace();
                }
            }
        });

        slWidth.addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent e)
            {
                try
                {
                    viewer.getLookupTable().setWidth(slWidth.getValue());
                    System.out.println("width: " + slWidth.getValue());
                    //viewer.getPixelMapper().mapImage(viewer.getImagePanel().getImage());
                    //viewer.getImagePanel().repaint();
                    for (IObserver obs : observers)
                    {
                        obs.changed(ControlPanelImplement.this);
                    }
                } catch (Exception e1)
                {
                    e1.printStackTrace();
                }
            }
        });

        slAlpha.addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent e)
            {
                try
                {
                    viewer.getLookupTable().setAlpha(slAlpha.getValue());
                    System.out.println("opacity: " + slAlpha.getValue());
                    //viewer.getPixelMapper().mapImage(viewer.getImagePanel().getImage());
                    //viewer.getImagePanel().repaint();
                    for (IObserver obs : observers)
                    {
                        obs.changed(ControlPanelImplement.this);
                    }
                } catch (Exception e1)
                {
                    e1.printStackTrace();
                }
            }
        });
    }



        /*
        slWidth.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                changeCenter();
                changeWith();
                changeAlpha();

            }


        });
        slCenter.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                changeCenter();
            }
        });

        slAlpha.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                changeAlpha();
            }
        });
    }
    private void changeWith()
    {
        try {
            viewer.getLookupTable().setWidth(slWidth.getValue());
            //System.out.println(viewer.getLookupTable().lookup(slWidth.getValue()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }

    private void changeAlpha()
    {
        try {
            viewer.getLookupTable().setAlpha(slAlpha.getValue());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }

    private void changeCenter()
    {
        try {
            viewer.getLookupTable().setCenter(slCenter.getValue());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }


    */
    @Override
     public void setWidthValue(int value) {
        this.slWidth.setValue(value);
    }
    public void registerObserver(IObserver observer)
    {
        this.observers.add(observer);
    }

    public void removeObserver(IObserver observer)
    {
        this.observers.remove(observer);
    }

    public void configure(Viewer viewer)
    {
        this.viewer = viewer;
        viewer.getPatientBrowser().registerObserver(this);
        viewer.getLookupTable().registerObserver(this);
    }

    public void changed(IBaseComponent c)
    {
        /*
        if(viewer.getPatientBrowser().selectedImage() != null) {


            short sh = 0;
            for (short s : viewer.getPatientBrowser().selectedImage().getPixelValues()) {
                if (s > sh) {
                    sh = s;
                }
            }
            slWidth.setMaximum(sh);
            slCenter.setMaximum(sh);
            slCenter.setValue(slCenter.getMaximum() / 2);
            slAlpha.setValue(slAlpha.getMaximum() / 2);
            //System.out.println(slCenter.getMaximum());
            //System.out.println(slWidth.getMaximum());
        }
        */
    }

    public void setHighest(int highest){
        this.highest = highest;
    }
}
