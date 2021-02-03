package Framework.Interfaces;

import Exceptions.*;

/**
 * IWorld implementations provide the virtual world functionality for the simulation.
 * Note that the top-level simulation class must implement the simulation loop.
 * 
 * @author Marc Price 
 * @version 0.6
 */
public interface IWorld
{
    /**
     * METHOD: create and initialise the 3D world.
     *
     */

    void create();
    
    /**
     * METHOD: destroy the 3D world.
     * Although there is a garbage-collector in Java, the world has to be destroyed with 'destroy'
     * in order to close-down your simulation-loop cleanly.
     *
     */

    void destroy();
    
    /**
     * METHOD: add a displayable to the world.
     *
     * @param       displayable - a displayable of type IDisplayObject
     */
    void addDisplayObject(IDisplayObject displayable) throws WorldDoesNotExistException;
    
    /**
     * METHOD: remove a displayable from the world.
     *
     * @param       displayable - the displayable of type IDisplayObject to be removed
     */
    void removeDisplayObject(IDisplayObject displayable) throws WorldDoesNotExistException;
       
}
