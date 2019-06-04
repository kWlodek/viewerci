package we_are_number_one;

import at.spengergasse.hbgm.image.ILookupTable;
import at.spengergasse.hbgm.ui.Viewer;
import at.spengergasse.hbgm.utils.IBaseComponent;
import at.spengergasse.hbgm.utils.IObserver;
import javax.swing.JOptionPane;

/**
 * Created by Nico on 20.10.2017.
 */
public class LookupTableImplement implements ILookupTable
{

    private Viewer viewer;
    private int width;
    private int alpha;
    private int center;


    public LookupTableImplement ()
    {

        width = (int)Math.round(255 * 0.80);
        center = (int) Math.round(255 * 0.75);
        alpha = 100;

    }

    public void registerObserver(IObserver observer)
    {

    }

    public void removeObserver(IObserver observer)
    {

    }

    public void configure(Viewer viewer)
    {
        this.viewer = viewer;
    }

    public void changed(IBaseComponent c)
    {

    }

    public int lookup(int value)
    {
        int newValue = (value - center + width/2) * 255 / width;
        if(newValue > 255)
            newValue = 255;
        if(newValue < 0)
            newValue = 0;

        int rgb = newValue << 16 | newValue << 8 | newValue | (alpha * 255 / 100) << 24;
        return rgb;

    }

    public void setCenter(int center) throws Exception
    {
        this.center = center;
    }

    public int getCenter()
    {
        return center;
    }

    public void setWidth(int width) throws Exception
    {
        if(this.width <= 0)
        {
            this.width = 1;
            viewer.getControlPanel().setWidthValue(1);
            //JOptionPane.showMessageDialog(null, "Width darf nicht 0 sein! " + JOptionPane.INFORMATION_MESSAGE);
            throw new Exception();
            //
            //this.width = 1;
        } else
            this.width = width;
    }

    public int getWidth()
    {
        return width;
    }

    public void setAlpha(int alpha) throws Exception
    {
        this.alpha = alpha;
    }

    public int getAlpha()
    {
        return alpha;
    }

}
