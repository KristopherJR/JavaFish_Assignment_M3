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
import UserCode.UserInterfaces.*;
import UserCode.UpdatableTokens.*;
import Exceptions.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Simulation is the top-level class for the Aquarium simulation.
 * 
 * @author Kristopher Randle and Marc Price
 * @version 19-02-2021, 0.9
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
    // DECLARE a List to store object of type 'IUpdatable', call it '_entities':
    private List<IUpdatable> _entities;
    
    // DECLARE an ITokenFactory, call it '_updatableFactory':
    private IUpdatableFactory _updatableFactory;
    
    // DECLARE an int called jNumber, used for specifying the number of JavaFish to be added to the scene:
    private int jNumber;
    // DECLARE an int called pNumber, used for specifying the number of Piranha to be added to the scene:
    private int pNumber;
    // DECLARE an int called uNumber, used for specifying the number of Urchins to be added to the scene:
    private int uNumber;
    // DECLARE an int called sNumber, used for specifying the number of SeaHorse to be added to the scene:
    private int sNumber;
    // DECLARE an int called foodCount, used for limiting the amount of FishFood in the aquarium at any given time to 5:
    private int foodCount;
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
        
        // _entities:
        _entities = new ArrayList<IUpdatable>();
        
        // _updatableFactory:
        _updatableFactory = new UpdatableFactory();
        
        jNumber = 10; // INITIALISE jNumber, this specifies that 10 'JavaFish' objects will be added to the aquarium.
        pNumber = 3; // INITIALISE pNumber, this specifies that 3 'Piranha' objects will be added to the aquarium.
        uNumber = 3; // INITIALISE uNumber, this specifies that 3 'Urchin' objects will be added to the aquarium.
        sNumber = 3; // INITIALISE uNumber, this specifies that 3 'SeaHorse' objects will be added to the aquarium.
        foodCount = 1; // INITIALISE foodCount.
    }
    
    /**
     * METHOD: This method will populate the '_entities' list so that they can be added to the '_world' later.
     * 
     * @Author Kristopher Randle
     */
    public void populate()
    {
        try
        {
            //CREATE a specific number of JavaFish in a for loop using jNumber. Add them to the _javaFish List, Then add the bubbles to the _entities List:
            for(int i = 0; i < jNumber; i++)
            {
                // RETURN the new instance of IUpdatable by calling the 'create()' method in the '_updatableFactory':
                _entities.add(_updatableFactory.create(UserCode.UpdatableTokens.JavaFish.class));
            }
            //CREATE a specific number of Piranha in a for loop using sNumber. Add them to the _entities List.
            for(int i = 0; i < pNumber; i++)
            {
                // RETURN the new instance of IUpdatable by calling the 'create()' method in the '_updatableFactory':
                _entities.add(_updatableFactory.create(UserCode.UpdatableTokens.Piranha.class));
            }
            //CREATE a specific number of Urchins in a for loop using uNumber. Add them to the _entities List.
            for(int i = 0; i < uNumber; i++)
            {
                // RETURN the new instance of IUpdatable by calling the 'create()' method in the '_updatableFactory':
                _entities.add(_updatableFactory.create(UserCode.UpdatableTokens.Urchin.class));
            }
            //CREATE a specific number of SeaHorses in a for loop using sNumber. Add them to the _entities List.
            for(int i = 0; i < sNumber; i++)
            {
                // RETURN the new instance of IUpdatable by calling the 'create()' method in the '_updatableFactory':
                _entities.add(_updatableFactory.create(UserCode.UpdatableTokens.SeaHorse.class));
            }
        }
        catch(Exception e)
        {
            //LOL
        }
    }
    
    /**
     * METHOD: Spawns a new 'FishFood' object at the mouse cursors location when the left mouse button is clicked.
     * 
     * @author Kristopher Randle
     * @return void
     */
    public void CreateFood() throws WorldDoesNotExistException
    {
        // GET the mouse button that was clicked:
        int _mouseButtonClicked = ((IInput)_world).getMouseClicked();
        // CHECK if the mouse button clicked was the left mouse button:
        if(_mouseButtonClicked == 0)
        {
            // IF there are less than 5 FishFood objects in the aquarium:
            if(foodCount <= 5)
            {
                // GET the location of the mouse pointer:
                int[] _mPLocation = ((IInput)_world).getMousePointer();
                // CREATE a double[] to store the _mPLocation in a format that matches the aquariums co-ordinate system:
                double[]_mPLD = new double[2];
                // ALTER the x by factor 0.0077 and y by factor 0.0080 due to discrepancies in the co-ordinates system:
                _mPLD[0] = _mPLocation[0] * 0.0077;
                _mPLD[1] = _mPLocation[1] * 0.0080;
                // CREATE a new FishFood() instance using the factory, store it as an IUpdatable:
                try
                {
                    IUpdatable food = (_updatableFactory.create(UserCode.UpdatableTokens.FishFood.class));
                    // CAST  'food' to 'FishFood' and call its 'setDisplayObjectPosition()' method. Pass in the mouse X and Y:
                    ((FishFood)food).setDisplayObjectPosition(_mPLD[0], _mPLD[1], 1.1);
                    
                    // ADD the FishFood to the '_entities' List:
                    _entities.add(food);
                    // CALL 'injectDisplayable()' on 'food' and pass in a reference to the virtual world:
                    ((IDisplayable)food).injectDisplayable(_world);
                
                    // INCREMENT the foodCount:
                    foodCount++;
                }
                catch(Exception e)
                {
                    // LOL
                    System.out.println(e.getMessage());
                }
            }
            else
            {
                // PRINT an error to the user stating they can't make more food:
                System.out.println("Can't spawn more than 5 FishFood at a time!!!");
            }
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
            for(int i = 0; i < _entities.size(); i++)
            {
                // CALL 'injectDisplayable()' on all objects in the '_entities' List. Cast them to their interface 'IDisplayable' and 
                // pass in a reference to the virtual world:
                ((IDisplayable)(_entities.get(i))).injectDisplayable(_world);
                
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
                for(int i = 0; i < _entities.size(); i++)
                {
                    _entities.get(i).update();
                }
                
                CreateFood();
            
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
