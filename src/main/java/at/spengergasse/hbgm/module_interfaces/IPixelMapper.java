package at.spengergasse.hbgm.module_interfaces;

import at.spengergasse.hbgm.entities.Instance;

import java.awt.image.BufferedImage;

/**
 * a pixel mapper calculates a Buffered image from the pixel data
 * contained in the DICOM file referenced by the given Instance object
 */
public interface IPixelMapper extends  IModuleBase {
    /**
     * maps (calculates) the image from the pixel data of instance
     * @param instance reference to instance from which to calculate BufferedImage
     * @return new BufferedImage
     * @throws Exception if pixel data cannot be read from DICOM foöe
     */
    public BufferedImage map(Instance instance) throws Exception;
}
