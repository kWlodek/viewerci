package at.spengergasse.hbgm.module_interfaces;

/**
 * a lookup table calculates the alpha-rgb value from an intensity
 * value. The lookup table must actively acquire other used values
 *  like center, width, alpha
 */
public interface ILookupTable extends IModuleBase {
    /**
     * calculate alpha-rgb from intensity value
     * @param value intensity
     * @return alpha-rgb value
     */
    public int argb(int value);


}
