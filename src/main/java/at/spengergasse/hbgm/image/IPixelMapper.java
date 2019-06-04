package at.spengergasse.hbgm.image;

import at.spengergasse.hbgm.utils.IBaseComponent;
import at.spengergasse.hbgm.data.Image;

import java.awt.image.BufferedImage;

/**
 * The purpose of a pixel mapper is to retrieve the pixel data from an Image
 * and calculate a BufferedImage instance. The value of each pixel must be
 * calculated using a lookup table
 */
public interface IPixelMapper extends IBaseComponent {

    /**
     * calculate a BufferedImage from the pixel array in the image instance
     * @param img image instance which contains the pixel data
     * @return BufferedImage instance which can be displayed in RealImagePanel
     * @throws Exception in case of calculation errors or errors in pixel data provider
     */
    public BufferedImage mapImage(Image img) throws Exception;
}
