package UserCode.UpdatableTokens;

import Framework.Interfaces.*;
import Framework.Implementations.*;

import UserCode.UpdatableFactory;
import UserCode.UserInterfaces.*;
import UserCode.SoundEffect;
import Exceptions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * Represents a 'Piranha'. This is an object that can be placed in the aquarium and swim around.
 * 
 * @author Kristopher Randle 
 * @version 19-02-2021, 0.4
 */
public class Piranha implements IUpdatable, IDisplayable
{
   // DECLARE a private static final String, call it 'MODEL' and set it to an appropriate model:
   private static final String MODEL = "models/billboard/billboard.obj";
   // DECLARE a private String, call it '_texture':
   private String _texture;
   
   // DECLARE the minimum speed a fish can move as specified by the client brief:
   private static final double MIN_SPEED = 0.005;
   // DECLARE the maximum speed a fish can move as specified by the client brief:
   private static final double MAX_SPEED = 0.05;
   
   // DECLARE an instance of 'Random', call it '_random':
   private Random _random;
   // DECLARE an 'IDisplayObject', call it '_piranha':
   private IDisplayObject _piranha;
   
   // DECLARE a List array, call it '_bubblePool'. Make it contain instances of the 'IUpdatable' interface:
   private List<IUpdatable> _bubblePool;
   /**
    * Constructor for objects of class Piranha
    * 
    * @return      void
    */
   public Piranha()
   {
       // INITIALIZE the random number generator:
       _random = new Random();
       // INITIALIZE the '_texture' String, give it a 50/50 chance of being a Green or Red Piranha:
       if(_random.nextInt(2) == 0)
       {
           // SET to red Piranha texture:
           _texture = "textures/javaFish/PiranhaRed.png";
       }
       else
       {
           // SET to green Piranha texture:
           _texture = "textures/javaFish/PiranhaGreen.png";
       }
       // INITIALIZE '_piranha' as a 'DisplayObject':
       _piranha = new DisplayObject(MODEL, _texture, Math.random() * (0.45 - 0.225) + 0.225); // SETS scale within client-brief range.
       // SET the '_seaHorse' objects orientation so it's the right way up:
       _piranha.orientation(0,270,0);
       // SET a random position for the '_piranha' by calling its GenerateRandomPosition() method, cast to its ILocation interface:
       ((ILocation)_piranha).GenerateRandomPosition();
       // GENERATE a random speed for this object between 0.005 - 0.05:
       this.GenerateRandomSpeed();
   }
   
   /**
    * METHOD: INITIALIZE and POPULATE the '_bubblePool'. New Bubble objects should be created using the UpdatableFactory reference passed in.
    * 
    * @param A reference to the UpdatableFactory used to create the new Bubble objects.
    * 
    * @return void
    */
   public void populateBubblePool(IUpdatableFactory factory)
   {
       // INITIALIZE the '_bubblePool' as an ArrayList:
       _bubblePool = new ArrayList<IUpdatable>();
       try
       {
           // CREATE 2 Bubble objects using the factory, store them as IUpdatable:
           IUpdatable b1 = factory.create(Bubble.class);
           IUpdatable b2 = factory.create(Bubble.class);           
           // ADD the 2 Bubbles to the '_bubblePool':
           _bubblePool.add(b1);
           _bubblePool.add(b2);
       }
       catch(Exception e)
       {
           // PRINT the error message:
           System.out.println(e.getMessage());
       }
   }
    
   public void GenerateRandomSpeed()
   {
       // GENERATE a random X velocity for the '_piranha':
       ((DisplayObject)_piranha).setVelocityX(Math.random() * (MAX_SPEED - MIN_SPEED) + MIN_SPEED);
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
       // INJECT '_piranha' into the virtual world:
       world.addDisplayObject(_piranha);
   }
   
   /**
     * METHOD: Removes the contained DisplayObject from the IWorld reference provided.
     * - Must be done after World has been created.
     * 
     * @param world     IWorld reference representing the 3D world.
     */
   public void removeDisplayable(IWorld world) throws WorldDoesNotExistException
   {
       // REMOVE '_piranha' from the virtual world:
       world.removeDisplayObject(_piranha);
   }
   
   // ------------------------------ IMPLEMENTATION OF IUpdatable ------------------------------ //
   /**
     * METHOD: called on each pass of the Simulation. Check the object is in the boundaries of the aquarium and move it.
     */
   public void update()
   {
       //CHECK the DisplayObject is within the aquarium:
       ((DisplayObject)_piranha).inBounds();
 
       // CALL update() on '_piranha', cast to its IUpdatable interface:
       ((IUpdatable)_piranha).update();
   }
}