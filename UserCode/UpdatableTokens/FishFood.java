package UserCode.UpdatableTokens;

import Framework.Interfaces.*;
import Framework.Implementations.*;

import UserCode.UserInterfaces.*;
import Exceptions.*;
/**
 * This class represents Fish Food. Instances of this class can be placed into the aquarium by left-clicking the mouse. The food should fall to the bottom of
 * the aquarium and rest there. If the food collides with a fish token at any point, it should be 'eaten', removing it from the aquarium.
 * 
 * @author Kristopher Randle 
 * @version 19-02-2021, 0.3
 */
public class FishFood implements IUpdatable, IDisplayable
{
    // DECLARE a private static final String, call it 'MODEL' and set it to an appropriate model:
    private static final String MODEL = "sphere";
    // DECLARE a private static final String, call it 'TEXTURE' and set it to the filepath of an appropriate image:
    private static final String TEXTURE = "textures/javaFish/FishFood.png";
    
    // DECLARE an 'IDisplayObject, call it '_fishFood':
    private IDisplayObject _fishFood;
    /**
     * Constructor for objects of class FishFood
     */
    public FishFood()
    {
        // INITALISE '_fishFood' with appropriate parameters:
        _fishFood = new DisplayObject(MODEL, TEXTURE, 0.15);
        // SET the orientation of the '_fishFood' so that it's the right way up:
        _fishFood.orientation(0,270,0);
        
        // SET the VelocityY of '_fishFood' to -0.005 so that it slowly falls through the water:
        ((DisplayObject)_fishFood).setVelocityY(-0.005);
    }
    
    /**
     * METHOD: Set the DisplayObjects position contained in this class by calling it's ILocation methods.
     * Pass in the provided x,y,z values.
     */
    public void setDisplayObjectPosition(double x, double y, double z)
    {
        // CAST _fishFood to its ILocation interface and pass in the x,y,z values provided:
        ((ILocation)_fishFood).setX(x);
        ((ILocation)_fishFood).setY(y);
        ((ILocation)_fishFood).setZ(z);
    }
    
    
    // ------------------------------ IMPLEMENTATION OF IDisplayable ------------------------------ //
    /**
     * METHOD: Inject the IDisplayable by passing its contained DisplayObject into the reference to IWorld provided.
     * - Must be done after World has been created.
     * 
     * This helps preserve an objects encapulsation as it allows the object to be injected into the game world, rather than returning its private
     * DisplayObject.
     *
     *@param world IWorld reference representing the 3D world.
     */
    public void injectDisplayable(IWorld world) throws WorldDoesNotExistException
    {
       // INJECT '_fishFood' into the virtual world:
       world.addDisplayObject(_fishFood);
    }
    
    /**
     * METHOD: Removes the contained DisplayObject from the IWorld reference provided.
     * - Must be done after World has been created.
     * 
     * @param world     IWorld reference representing the 3D world.
     */
    public void removeDisplayable(IWorld world) throws WorldDoesNotExistException
    {
        // REMOVE '_fishFood' from the virtual world:
        world.removeDisplayObject(_fishFood);
    }
    
    // ------------------------------ IMPLEMENTATION OF IUpdatable ------------------------------ //
    /**
     * METHOD: called on each pass of the Simulation, calls the update method in DisplayObject. Stops FishFood from going past the aquarium floor and makes it rest
     * at the bottom of the aquarium
     * 
     * @return void
     */
    public void update()
    {
        // CALL update() on '_fishFood', cast to its IUpdatable interface:
        ((IUpdatable)_fishFood).update();
        
        // IF the FishFood has gone past the floor:
        if(((ILocation)_fishFood).getY() < 0.99)
        {
            // STOP the food from moving by setting its Y velocity to 0.0:
            ((DisplayObject)_fishFood).setVelocityY(0.0);
            // RESET the food to just above the floor, this will stop the program polling to check if the food has gone past the floor on each pass of the update loop:
            ((ILocation)_fishFood).setY(1.0);
        }
    }
}
