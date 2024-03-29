package UserCode.UserInterfaces;

import Framework.Interfaces.IWorld;
import Exceptions.*;

/**
 * IDisplayable: allows this object to be displayed in a virtual environment of type IWorld.
 * 
 * This interface helps preserve an objects encapulsation as it allows the object to be injected into the game world, rather than returning its private
 * DisplayObject.
 * 
 * Heavily based off of Chris Blythe's live coding session from Week 16 on BlackBoard, on the 'ISpawnable' interface.
 * 
 * @author Chris Blythe, Kristopher Randle
 * @version 22-02-2021, 0.2
 */
public interface IDisplayable
{   
    /**
     * METHOD: Inject the IDisplayable by passing its contained DisplayObject into the reference to IWorld provided.
     * - Must be done after World has been created.
     *
     * This helps preserve an objects encapulsation as it allows the object to be injected into the game world, rather than returning its private
     * DisplayObject.
     *
     *@param world      IWorld reference representing the 3D world.
     */
    void injectDisplayable(IWorld world) throws WorldDoesNotExistException;
    
    /**
     * METHOD: Removes the contained DisplayObject from the IWorld reference provided.
     * - Must be done after World has been created.
     * 
     * @param world     IWorld reference representing the 3D world.
     */
    void removeDisplayable(IWorld world) throws WorldDoesNotExistException;
}
