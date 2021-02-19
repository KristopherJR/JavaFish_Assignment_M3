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
 * @author Kristopher Randle & Marc Price
 * @version 19-02-2021, 1.0
 */
public class Simulation implements IInputListener
{
    // instance variables:
    // DECLARE a reference to the IWorld, call it '_world':
    private IWorld _world;
    // DECLARE a reference to the IInput, call it '_input':
    private IInput _input;
    // DECLARE a reference to an IInputPublisher, call it '_inputPublisher':
    private IInputPublisher _inputPublisher;
    // DECLARE an int array, call it '_mPLocation'. Initialise it to {-1,-1}:
    private int[] _mPLocation = {-1,-1};
    // DECLARE a boolean that signals when the simulation loop should be exited:
    private boolean endSim = false;
    // DECLARE a boolean that signals when a new 'FishFood' needs to be created, call it spawnFood(), set it to false:
    private boolean _spawnFood = false;
    // DECLARE a List to store object of type 'IUpdatable', call it '_entities':
    private List<IUpdatable> _entities;
    // DECLARE an ITokenFactory, call it '_updatableFactory':
    private IUpdatableFactory _updatableFactory;
    // DECLARE an int called _jNumber, used for specifying the number of JavaFish to be added to the scene:
    private int _jNumber;
    // DECLARE an int called _pNumber, used for specifying the number of Piranha to be added to the scene:
    private int _pNumber;
    // DECLARE an int called _uNumber, used for specifying the number of Urchins to be added to the scene:
    private int _uNumber;
    // DECLARE an int called _sNumber, used for specifying the number of SeaHorse to be added to the scene:
    private int _sNumber;
    // DECLARE an int called _foodCount, used for limiting the amount of FishFood in the aquarium at any given time to 5:
    private int _foodCount;
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
        // _entities:
        _entities = new ArrayList<IUpdatable>();
        // _updatableFactory:
        _updatableFactory = new UpdatableFactory();
        try
        {
            // INITIALISE '_world' by returning a 'Core' class object from the '_updatableFactory'. Cast it to 'IWorld':
            _world = ((IWorld) _updatableFactory.create(Core.class));
            // _input:
            _input = (IInput) _world;
            
            // INITIALISE '_inputPublisher', create it using the '_updatableFactory' to return a 'MouseHandler' object. Cast it to 'IInputPublisher':
            _inputPublisher = ((IInputPublisher) _updatableFactory.create(MouseHandler.class));
            _inputPublisher.Initialise(_input);
        }
        catch(Exception e)
        {
            // PRINT the error message:
            System.out.println(e.getMessage());
        }
        // Subscribe this instance of 'Simulation' to events published by the '_inputPublisher':
        _inputPublisher.subscribe(this);
        // INITIALISE _jNumber, this specifies that 10 'JavaFish' objects will be added to the aquarium:
        _jNumber = 10; 
        // INITIALISE _pNumber, this specifies that 3 'Piranha' objects will be added to the aquarium:
        _pNumber = 3;
        // INITIALISE _uNumber, this specifies that 3 'Urchin' objects will be added to the aquarium:
        _uNumber = 3; 
        // INITIALISE _sNumber, this specifies that 3 'SeaHorse' objects will be added to the aquarium:
        _sNumber = 3;
        // INITIALISE _foodCount:
        _foodCount = 1; 
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
            //CREATE a specific number of JavaFish in a for loop using jNumber. Add them to the _entities List:
            for(int i = 0; i < _jNumber; i++)
            {
                // RETURN the new instance of 'IUpdatable' by calling the 'create()' method in the '_updatableFactory' and store it in '_entities':
                _entities.add(_updatableFactory.create(UserCode.UpdatableTokens.JavaFish.class));
            }
            //CREATE a specific number of Piranha in a for loop using sNumber. Add them to the _entities List.
            for(int i = 0; i < _pNumber; i++)
            {
                // RETURN the new instance of 'IUpdatable' by calling the 'create()' method in the '_updatableFactory' and store it in '_entities':
                _entities.add(_updatableFactory.create(UserCode.UpdatableTokens.Piranha.class));
            }
            //CREATE a specific number of Urchins in a for loop using uNumber. Add them to the _entities List.
            for(int i = 0; i < _uNumber; i++)
            {
                // RETURN the new instance of 'IUpdatable' by calling the 'create()' method in the '_updatableFactory' and store it in '_entities':
                _entities.add(_updatableFactory.create(UserCode.UpdatableTokens.Urchin.class));
            }
            //CREATE a specific number of SeaHorses in a for loop using sNumber. Add them to the _entities List.
            for(int i = 0; i < _sNumber; i++)
            {
                // RETURN the new instance of 'IUpdatable' by calling the 'create()' method in the '_updatableFactory' and store it in '_entities':
                _entities.add(_updatableFactory.create(UserCode.UpdatableTokens.SeaHorse.class));
            }
        }
        catch(Exception e)
        {
            // PRINT the error message:
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * METHOD: This method will iterate through the '_entities' List and inject all of the objects in the List to the 3D world provided as a parameter.
     * 
     * An exception is thrown is a user tries to execute this code before the world environment has been created.
     * 
     * @author Kristopher Randle
     * @return void
     */
    public void displayEntities()
    {
        for(int i = 0; i < _entities.size(); i++)
        {
            try
            {
                // CALL 'injectDisplayable()' on all objects in the '_entities' List. Cast them to their interface 'IDisplayable' and pass in a reference to the virtual world:
                ((IDisplayable)(_entities.get(i))).injectDisplayable(_world);
            }
            catch(Exception e)
            {
                // PRINT the error message:
                System.out.println(e.getMessage());
            }
        }
    }
    
    /**
     * METHOD: Spawns a new 'FishFood' object at the mouse cursors location when the left mouse button is clicked.
     * 
     * @author Kristopher Randle
     * @return void
     */
    public void createFood() throws WorldDoesNotExistException
    {
        // CREATE a new 'FishFood' if an event is raised 
        if(_spawnFood)
        {
            // AND there are less than 5 FishFood objects in the aquarium:
            if(_foodCount <= 5)
            {
                // RESET '_spawnFood' to false:
                _spawnFood = false;
                // CALCULATE the location for the 'FishFood' based on the mouse location, store it in a Double array called '_foodLoc':
                Double[] _foodLoc = {_mPLocation[0] * 0.0077, _mPLocation[1] * 0.0080, 1.1};
                try
                {
                    // CREATE a new 'FishFood' using the '_updatableFactory', call it '_newFood':
                    IUpdatable _newFood = _updatableFactory.create(FishFood.class);
                    // SET the location of '_newFood' using '_foodLoc', cast to 'IDisplayObject' to use the 'position()' method:
                    ((FishFood)_newFood).setDisplayObjectPosition(_foodLoc[0], _foodLoc[1], _foodLoc[2]);
                    // ADD the '_newFood' to the '_entities' List:
                    _entities.add(_newFood);
                    // DISPLAY the '_newFood' in the '_world' by injecting the DisplayObject:
                    ((IDisplayable)_newFood).injectDisplayable(_world);
                    // INCREMENT the foodCount:
                    _foodCount++;
                }
                catch(Exception e)
                {
                    // PRINT the error message:
                    System.out.println(e.getMessage());
                }
            }
            else
            {
                // RESET '_spawnFood' to false:
                _spawnFood = false;
                // PRINT an error to the user stating they can't make more food:
                System.out.println("Can't spawn more than 5 FishFood at a time!!!");
            }
        }
    }
    
    // ------------------------------ IMPLEMENTATION OF IInputListener ------------------------------ //
    /**
     * Method to handle an input event.
     * 
     * Interface author: Marc Price.
     * Method from: Chris Blythe, week 17 live coding video on BlackBoard.
     * 
     * @param data an array of integers containing the input data.
     */
    public void onInput(int ...data)
    {
        // SIGNAL that a new 'FishFood' needs to be created using the boolean '_spawnFood':
        _spawnFood = true;
        // STORE the mouse data provided to '_mPLocation':
        _mPLocation = data;
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
            this.displayEntities();
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
                // UPDATE Objects in '_entities' List:
                for(int i = 0; i < _entities.size(); i++)
                {
                    _entities.get(i).update();
                }
                // UPDATE the '_inputPublisher':
                ((IUpdatable)_inputPublisher).update();
                // UPDATE the '_world':
                ((IUpdatable)_world).update();
                
                // Attempt to create a new FishFood:
                this.createFood();
            }
            // EXIT: cleanly by closing-down the environment:
            _world.destroy();
        }
        catch (WorldDoesNotExistException e)
        {
            // PRINT the error message:
            System.out.println(e.getMessage());
        }
    }
}
