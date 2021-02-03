package Framework.Implementations;


import java.util.List;
import java.util.ArrayList;
import env3d.Env;
import Exceptions.*;
import Framework.Interfaces.*;

/**
 * Core is intended to provide the core functionality for the top-level aquarium simulation class.
 * It implements the IWorld and IInput functionality.
 * Note that the top-level simulation class must implement the simulation loop.
 * 
 * @author Marc Price 
 * @version 0.4
 */
public class Core implements IWorld, IInput, IUpdatable
{
    // instance variables (fields):
    // DECLARE the reference to the 3D world:
    private Env _world;
    
    // DECLARE a boolean that is false by default and set to true when _world is not null, call it _worldIsValid:
    private boolean _worldIsValid = false;
    
    // DEFINE a private inner class that creates the Env3D 'room', call it 'Aquarium':
    private class Aquarium
    {
        private String textureTop;
        private String textureBottom; // = "textures/orangeFish/AquariumBackground.png";
        private String textureNorth = "textures/javaFish/AquariumBackground.png";
        private String textureSouth;
        private String textureEast; // = "textures/orangeFish/AquariumBackground.png";
        private String textureWest; // = "textures/orangeFish/AquariumBackground.png";
        
        private double width;
        private double height;
        private double depth;
        
        private double bgRed;
        private double bgGreen;
        private double bgBlue;
        
        /**
         * Constructor
         *
         * @param  x   width of type double
         * @param  y   height of type double
         * @param  z   depth of type double
         */
        public Aquarium(double x, double y, double z)
        {
            width = x;
            height = y;
            depth = z;
        }
    
    }
    
        
    /**
     * Constructor for objects of class Core
     */
    public Core()
    {

    }
    
    
    // ---------------------------------- Implementation Of Iworld ---------------------------------------- //
    /**
     * METHOD: create and initialise the 3D world.
     *
     */

    public void create()
    {
        // Instantiate an environment:
        _world = new Env();
        
        // Get rid of the awful mouse control:
        _world.setDefaultControl(false);
        
        // Set the window resolution:
        _world.setResolution(1280,960,24);
        
        // Instantiate Aquarium and assign it to the environment as the scene:
        _world.setRoom(new Aquarium(10, 8, 1));
        
        // Set the camera to a fixed position so that scene looks flat:
        _world.setCameraXYZ((_world.getCameraX()),3.9,9.1);
        
        // Set _worldIsValid true:
        _worldIsValid = true;
    }
    
    /**
     * METHOD: destroy the 3D world.
     * Although there is a garbage-collector in Java, the world has to be destroyed with 'destroyWorld'
     * in order to close-down your simulation-loop cleanly.
     *
     */

    public void destroy()
    {
        // Close down the environment:
        _world.exit();
        
        // Reset _worldIsValid false:
        _worldIsValid = false;
    }
    
    /**
     * METHOD: add a displayable to the world.
     *
     * @param       displayable - a displayable of type IDisplayObject
     */
    public void addDisplayObject(IDisplayObject displayable) throws WorldDoesNotExistException
    {
        // Place object in _world (if it exists yet):
        if (_worldIsValid)
        {
            _world.addObject(displayable);
        }
        
        // OTHERWISE throw WorldDoesNotExistException advising that 3D world does not yet exist:
        else
        {
            throw new WorldDoesNotExistException("Cannot add objects until the 3D world has been created");
        }    
    }

    /**
     * METHOD: remove a displayable from the world.
     *
     * @param       displayable - the displayable of type IDisplayObject to be removed
     */
    public void removeDisplayObject(IDisplayObject displayable) throws WorldDoesNotExistException
    {
        // Remove object from world (if it exists yet):
        if (_worldIsValid)
        {
            _world.removeObject(displayable);
        }
        
        // OTHERWISE throw WorldDoesNotExistException advising that 3D world does not yet exist:
        else
        {
            throw new WorldDoesNotExistException("Cannot remove objects until the 3D world has been created");
        }    
    }
    
    //----------------------------------------------------------------------------------------------------------//
    
    
    // --------------------------------------- Implementation of IUpdatable --------------------------------------- //
    /**
     * Apply all updates to the environment.
     * This must be called at the end of each pass through the simulation loop.
     *
     */

    public void update()
    {
        // Update the environment (if _world exists yet):
        if (_worldIsValid)
        {
            _world.advanceOneFrame();
        }
        
    }
    //----------------------------------------------------------------------------------------------------------//
    
    
    // --------------------------------------- Implementation of IInput --------------------------------------- //
    
    /**
     * METHOD: get keyboard input.
     *
     * @return the key value (-1 if no key has been pressed since last check).
     */
    public int getKey() throws WorldDoesNotExistException
    {
        // GET the key value from _world (if _world exists yet):
        if (_worldIsValid)
        {
            return _world.getKey();
        }
        
        // OTHERWISE throw WorldDoesNotExistException advising that 3D world does not yet exist:
        else
        {
            throw new WorldDoesNotExistException("Cannot get input until the 3D world has been created");
        }    
    }
    
    /**
     * METHOD: check if a mouse button has been clicked.
     *
     * @return the button value: 0= left button; 1= middle button; 2= right button.
     */
    public int getMouseClicked() throws WorldDoesNotExistException
    {
        // GET the key value from _world (if _world exists yet):
        if (_worldIsValid)
        {
            return _world.getMouseButtonClicked();
        }
        
        // OTHERWISE throw WorldDoesNotExistException advising that 3D world does not yet exist:
        else
        {
            throw new WorldDoesNotExistException("Cannot get input until the 3D world has been created");
        }            
    }
    
    /**
     * METHOD: check if a mouse button has been clicked.
     *
     * @return     the x,y coordinates of the mouse pointer as a 2-element array of ints.
     */
    public int[] getMousePointer() throws WorldDoesNotExistException
    {
        // GET the key value from _world (if _world exists yet):
        if (_worldIsValid)
        {
            // DECLARE an int[] array for the method return value
            // INITIALISE: to return -1,-1
            int[] rtnVal={-1,-1};
        
            rtnVal[0] = _world.getMouseX();
            rtnVal[1] = _world.getMouseY();
            
            return rtnVal;
        }
        
        // OTHERWISE throw WorldDoesNotExistException advising that 3D world does not yet exist:
        else
        {
            throw new WorldDoesNotExistException("Cannot get input until the 3D world has been created");
        }            
    }    
}
