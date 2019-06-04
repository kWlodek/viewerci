package at.spengergasse.hbgm.ui;

import at.spengergasse.hbgm.data.Image;
import at.spengergasse.hbgm.utils.IBaseComponent;

import javax.swing.*;

/**
 * The image panel component displays the currently selected image.
 * It retrieves the currently selected image from the patient browser.
 * Then it passes the image to the pixel mapper in order to obtain
 * a BufferedImage instance.
 * The BufferedImage instance can be displayed using the Graphics context
 * in the paintComponent()-Method
 *
 * The image panel must listen to changes of:
 * the patient browser - if selection changes
 * the lookup table - if contrast values are changed
 */
public abstract class AbstractImagePanel extends JPanel implements IBaseComponent {

    Image image;
    public Image getImage(){
        return this.image;
    }
}
