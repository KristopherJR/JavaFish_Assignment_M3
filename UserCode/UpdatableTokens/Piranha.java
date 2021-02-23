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
 * @version 23-02-2021, 0.6
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
   
   // DECLARE an IUpdatableFactory, call it '_updatableFactory':
    private IUpdatableFactory _updatableFactory;
   
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
       _piranha = new DisplayObject(MODEL, _texture, Math.random() * (0.45 - 0.225) + 0.225, false); // SETS scale within client-brief range.
       // SET the '_seaHorse' objects orientation so it's the right way up:
       _piranha.orientation(0,270,0);
       // SET a random position for the '_piranha' by calling its GenerateRandomPosition() method, cast to its ILocation interface:
       ((ILocation)_piranha).GenerateRandomPosition();
       // GENERATE a random speed for this object between 0.005 - 0.05:
       this.GenerateRandomSpeed();
       // _updatableFactory:
        _updatableFactory = new UpdatableFactory();
       // POPULATE the '_bubblePool':
       this.populateBubblePool();
   }
   
   /**
    * METHOD: INITIALIZE and POPULATE the '_bubblePool'. New Bubble objects should be created using the UpdatableFactory.
    * 
    * @return void
    */
   public void populateBubblePool()
   {
       // INITIALIZE the '_bubblePool' as an ArrayList:
       _bubblePool = new ArrayList<IUpdatable>();
       try
       {
           // CREATE 2 Bubble objects using the factory, store them as IUpdatable:
           IUpdatable b1 = _updatableFactory.create(Bubble.class);
           IUpdatable b2 = _updatableFactory.create(Bubble.class);
           // SET both Bubble objects scale to 25% of '_piranha' scale:
           ((Bubble)b1).setScale(((DisplayObject)_piranha).getScale()*0.25);
           ((Bubble)b2).setScale(((DisplayObject)_piranha).getScale()*0.25);
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
   
   /**
    * METHOD: used to validate that the speed of this Piranha has been assigned is within the correct range, if it isn't an OutOfBoundsException is thrown.
    * 
    * @throws OutOfBoundsException
    * 
    * @return      void 
    */
    
   public void validateSpeed() throws OutOfBoundsException
   {
       // THROW an OutOfBoundsException if the VelocityX of the DisplayObject is out of the permitted range:
       if((((DisplayObject)_piranha).getVelocityX()) < 0.005 || (((DisplayObject)_piranha).getVelocityX()) > 0.05)
       {
           throw new OutOfBoundsException("Piranhas are being assigned a VelocityX value outside of their specified range (0.005 - 0.05)");
       }
   }
   
   // ------------------------------ ACCESSORS ------------------------------ //
   /**
    * METHOD: returns the self-contained DisplayObject's X co-ordinate.
    * 
    * @return   The self-contained DisplayObject's X co-ordinate.
    */
   public double getDisplayX()
   {
       // RETURN the DisplayObject's X co-ordinate:
       return ((ILocation)_piranha).getX();
   }
   
   /**
    * METHOD: returns the self-contained DisplayObject's Y co-ordinate.
    * 
    * @return   The self-contained DisplayObject's Y co-ordinate.
    */
   public double getDisplayY()
   {
       // RETURN the DisplayObject's Y co-ordinate:
       return ((ILocation)_piranha).getY();
   }
    
   /**
    * METHOD: returns a reference to this Piranha's Bubble Pool.
    * 
    * @return   A list of IUpdatable containing the Bubble Pool.
    */
   public List<IUpdatable> getBubblePool()
   {
       // RETURN the '_bubblePool':
       return _bubblePool;
   }
   
   /**
    * METHOD: get the VelocityX of '_piranha' and return it.
    * 
    * @return       The VelocityX of the '_piranha' as a Double.
    */
   public Double getVelocityX()
   {
       // GET the DisplayObject's VelocityX and return it:
       return ((DisplayObject)_piranha).getVelocityX();
   }
   
   // ------------------------------ MUTATORS ------------------------------ //
   /**
    * METHOD: Generates a random VelocityX and VelocityY for the DisplayObject contained in Piranha. The Speed is set between MIN_SPEED and MAX_SPEED.
    * 
    * @return void
    */
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