package UserCode.UserInterfaces;

/**
 * ILocation interface provides methods that allows the manipulation of an objects location. Can be used for an object in a 3D environment that utilizes the
 * X,Y,Z axis.
 * 
 * @author Kristopher Randle 
 * @version 14-02-2021, 0.1
 */
public interface ILocation
{ 
    /**
     * Sets the X co-ordinate of this object.
     * 
     * @param x     The new location for this object on its X axis.
     * @return void
     */
    void setX(double x);
    
    /**
     * Sets the Y co-ordinate of this object.
     * 
     * @param y     The new location for this object on its Y axis.
     * @return void
     */
    void setY(double y);
    
    /**
     * Sets the Z co-ordinate of this object.
     * 
     * @param z     The new location for this object on its Z axis.
     * @return void
     */
    void setZ(double z);
    
    /**
     * Generates a random position for this object.
     * 
     * @return void
     */
    void GenerateRandomPosition();
    
    /**
     * Gets the X co-ordinate of this object.
     * 
     * @return      The X co-ordinate of this object as a double.
     */
    double getX();
    
    /**
     * Gets the Y co-ordinate of this object.
     * 
     * @return      The Y co-ordinate of this object as a double.
     */
    double getY();
    
    /**
     * Gets the Z co-ordinate of this object.
     * 
     * @return      The Z co-ordinate of this object as a double.
     */
    double getZ();
}
