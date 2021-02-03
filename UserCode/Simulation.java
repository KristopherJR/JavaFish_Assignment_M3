package UserCode;

///////////////////////////////////////////////////////////////////////////////////////////////////
// Notes:
// * Add code to this as necessary to produce your simulation.
// * Use comments to clearly highlight your code that has been added.
// * Acknowledge/cite appropriately any added code that is not your own.
///////////////////////////////////////////////////////////////////////////////////////////////////
import env3d.Env;
import Framework.Interfaces.*;
import Framework.Implementations.*;
import Exceptions.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Simulation is the top-level class for the Aquarium simulation.
 * 
 * @author Kristopher Randle and Marc Price
 * @version 0.7 - 29-01-2021
 */
public class Simulation
{
    // instance variables:
    // DECLARE a reference to the IWorld, call it '_world':
    private IWorld _world;
    
    // DECLARE a reference to the IInput, call it '_input':
    private IInput _input;
        
    // DECLARE a boolean that signals when the simulation loop should be exited:
    private boolean endSim = false;
    
    // DECLARE a List to store object of type 'IUpdatable', call it '_pets':
    private List<IUpdatable> _pets;
    
    // DECLARE an int called jNumber, used for specifying the number of JavaFish to be added to the scene:
    private int jNumber;

    // DECLARE an int called sNumber, used for specifying the number of OrangeFish to be added to the scene:
    private int oNumber;
    
    // DECLARE an int called uNumber, used for specifying the number of Urchins to be added to the scene:
    private int uNumber;

    public static void main(String args[])
    {
        Simulation sim = new Simulation();
        sim.populate();
        sim.run();
    }
    
    /**
     * Constructor for objects of class Simulation
     */
    public Simulation()
    {
        // INITIALISE instance variables:
        // _world:
        _world = new Core();
        
        // _input:
        _input = (IInput) _world;
        
        // _pets:
        _pets = new ArrayList<IUpdatable>();
        
        jNumber = 3; //INITIALISE jNumber, this specifies that 3 'JavaFish' objects will be added to the aquarium.
        oNumber = 3; //INITIALISE oNumber, this specifies that 3 'OrangeFish' objects will be added to the aquarium.
        uNumber = 3; //INITIALISE uNumber, this specifies that 3 'Urchin' objects will be added to the aquarium.
    }
    
    /**
     * METHOD: This method will populate the '_pets' array so that they can be added to the '_world' later.
     * 
     * @Author Kristopher Randle
     */
    public void populate()
    {
        //CREATE a specific number of JavaFish in a for loop using jNumber. Add them to the _javaFish List, Then add the bubbles to the _displayObjects List:
        for(int i = 0; i < jNumber; i++)
        {
            _pets.add(new JavaFish());
        }
        //CREATE a specific number of SeaHorses in a for loop using sNumber. Add them to the _displayObjects List.
        for(int i = 0; i < oNumber; i++)
        {
            _pets.add(new OrangeFish());
        }
        //CREATE a specific number of SeaHorses in a for loop using uNumber. Add them to the _displayObjects List.
        for(int i = 0; i < uNumber; i++)
        {
            _pets.add(new Urchin());
        }
    }
    
    public void CreateBubble() throws WorldDoesNotExistException
    {
        //GET the mouse button that was clicked:
        int _mouseButtonClicked = ((IInput)_world).getMouseClicked();
        //GET the location of the mouse pointer:
        int[] _mPLocation = ((IInput)_world).getMousePointer();
        //CHECK if the mouse button clicked was the left mouse button:
        if(_mouseButtonClicked == 0)
        {
            //IF left button clicked, create a new 'Bubble' at the mouse pointer location:
            Bubble b = new Bubble(_mPLocation[0],_mPLocation[1],0.15);
            //ADD the Bubble to the '_pets' List:
            _pets.add(b);
            //ADD the DisplayObject stored in the Bubble object to the world:
            _world.addDisplayObject(b.getDisplayObject());
        }
    }

    /**
     * METHOD: Run the simulation loop.  User presses escape to exit.
     *
     */
    public void run()
    {
        // Create the 3D world:
        _world.create();
        // User try - catch to ensure 3D world was successfully created:
        try
        {
            // ADD Objects to 3D world?:
            for(int i = 0; i < _pets.size(); i++)
            {
                _world.addDisplayObject(((IUpdatable)_pets.get(i)).getDisplayObject());
            }
            
            // Start simulation loop:
            while (!endSim)
            {
                // UPDATE STAGE:
                // IF: user has requested simulation loop exit (ie escape pressed):
                if (_input.getKey() == 1)
                {
                    // SET: render loop exit condition
                    endSim = true;
                }
                        
                // UPDATE Objects in 3D world:
                for(int i = 0; i < _pets.size(); i++)
                {
                    _pets.get(i).update();
                }
                
                CreateBubble();
            
                // UPDATE 3D World:
                // Apply all updates to the environment.
                // This must be called at the end of each pass through the simulation loop.
                // Cast to IUpdatable interface
                ((IUpdatable)_world).update();
            }
        
            // EXIT: cleanly by closing-down the environment:
            _world.destroy();
        }
        catch (WorldDoesNotExistException e)
        {
            System.out.println(e.getMessage());
        }

    }

}
