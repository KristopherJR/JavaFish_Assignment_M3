package Framework.Interfaces;


/**
 * The IDisplayObject instance contains all the data for displaying something by an IWorld instance.
 * 
 * @author (Marc Price) 
 * @version (19.12.2018)
 */
public interface IDisplayObject
{
    /**
     * METHOD: set the IDisplayObject position along x,y,z axes
     * @param x double giving the new position along x axis.
     * @param y double giving the new position along y axis.
     * @param z double giving the new position along z axis.
     */
    void position(double x, double y, double z);
    
    /**
     * METHOD: rotate the IDisplayObject about x,y,z axes
     * @param xOrientation double giving the new orientation about x axis.
     * @param yOrientation double giving the new orientation about y axis.
     * @param zOrientation double giving the new orientation about z axis.
     */
    void orientation(double xOrientation, double yOrientation, double zOrientation);

}
