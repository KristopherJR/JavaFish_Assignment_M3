package UserCode;

import Framework.Implementations.DisplayObject;
import Framework.Interfaces.*;
/**
 * This class represents Fish Food. Instances of this class can be placed into the aquarium by left-clicking the mouse. The food should fall to the bottom of
 * the aquarium and rest there. If the food collides with a fish token at any point, it should be 'eaten', removing it from the aquarium.
 * 
 * @author Kristopher Randle 
 * @version 14-02-2021, 0.1
 */
public class FishFood implements IUpdatable
{
    // DECLARE a private static final String, call it 'MODEL' and set it to an appropriate model:
    private static final String MODEL = "models/billboard/billboard.obj";
    // DECLARE a private static final String, call it 'TEXTURE' and set it to the filepath of an appropriate image:
    private static final String TEXTURE = "textures/javaFish/FishFood.png";
    
    // DECLARE a 'DisplayObject, call it '_fishFood':
    private DisplayObject _fishFood;
    /**
     * Constructor for objects of class FishFood
     */
    public FishFood()
    {
        //INITALISE '_fishFood' with appropriate parameters:
        _fishFood = new DisplayObject(MODEL, TEXTURE, 0.15);
        
    }
    
    // -------------------------- IMPLEMENTATION OF IUpdatable ----------------------
    /**
     * METHOD: called on each pass of the Simulation, calls the update method in DisplayObject.
     */
    public void update()
    {
        // CALL the update() method in '_fishFood':
        _fishFood.update();
    }
    
    /**
     * METHOD: get the DisplayObject stored in this class and return it.
     * 
     * @return The DisplayObject contained within this class.
     */
    public IDisplayObject getDisplayObject()
    {
        // RETURN the DisplayObject '_fishFood':
        return _fishFood;
    }
}
