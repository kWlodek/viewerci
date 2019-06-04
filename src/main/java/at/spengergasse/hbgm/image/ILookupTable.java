package at.spengergasse.hbgm.image;

import at.spengergasse.hbgm.utils.IBaseComponent;

/**
 * The responsibility of a lookup table is to calculate a RGBA value from
 * an intensity value. Usually the calculation is based on a center and a
 * window value (windowing) and on alpha.
 *
 * All the set-Methods must notify registered Observers if value is changed
 */
public interface ILookupTable extends IBaseComponent{

    /**
     * calculate RGBA value from intensity value
     * @param value intensity
     * @return RGBA value
     */
    public int lookup(int value);


    /**
     * set the center value
     * @param center new center value
     * @throws Exception if value is not valid
     */
    public void setCenter(int center) throws Exception;

    /**
     * retrieve the actual center value
     * @return actual center value
     */
    public int getCenter();


    /**
     * set the width value
     * @param width new width value
     * @throws Exception if value is not valid
     */
    public void setWidth(int width) throws Exception;

    /**
     * retrieve actual width value
     * @return actual width value
     */
    public int getWidth();

    /**
     * set alpha value
     * @param alpha new alpha value
     * @throws Exception if value is not valid
     */
    public void setAlpha(int alpha) throws Exception;

    /**
     * retrieve actual alpha value
     * @return actual alpha value
     */
    public int getAlpha();
}
