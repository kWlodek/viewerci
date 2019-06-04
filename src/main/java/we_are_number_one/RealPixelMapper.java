package we_are_number_one;

import at.spengergasse.hbgm.data.Image;
import at.spengergasse.hbgm.image.IPixelMapper;
import at.spengergasse.hbgm.ui.Viewer;
import at.spengergasse.hbgm.utils.IBaseComponent;
import at.spengergasse.hbgm.utils.IObserver;

import java.awt.image.BufferedImage;

/**
 * Created by Elias on 24.10.2017.
 */
public class RealPixelMapper implements IPixelMapper {

    private Viewer viewer;
    private BufferedImage buff;
    //private Image img;
    private int rgb;
    private int highest = 0;

    public void registerObserver(IObserver observer) {

    }

    public void removeObserver(IObserver observer) {

    }

    public void configure(Viewer viewer) {
        this.viewer = viewer;
        viewer.getControlPanel().registerObserver(this);
    }

    public void changed(IBaseComponent c) {
        /*try {
            mapImage(viewer.getImagePanel().getImage());
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    public BufferedImage mapImage(Image img) throws Exception {
       // this.img = viewer.getImagePanel().getImage();
        buff = new BufferedImage(img.getColumns(), img.getRows(), BufferedImage.TYPE_INT_ARGB);
        for (int r = 0; r < img.getRows(); r++)
        {
            for (int c = 0; c < img.getColumns(); c++)
            {
                int value = (int)((img.getPixelValues()[r*img.getColumns() + c]));
                //rgb =  255 << 24 | value << 16 | value << 8 | value;
                rgb = viewer.getLookupTable().lookup(value);
                if(highest < value){
                    highest = value;
                }
                viewer.getControlPanel().setHighest(highest);
                buff.setRGB(c, r, rgb);
            }
        }
        return buff;
    }
}
