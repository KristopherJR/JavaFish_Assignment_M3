package UserCode.UpdatableTokens;

import Framework.Interfaces.*;
import Framework.Implementations.*;

import UserCode.UpdatableFactory;
import UserCode.UserInterfaces.*;
import UserCode.SoundEffect;
import Exceptions.*;

import java.util.ArrayList;
import java.util.List;
/**
 * Represents a 'JavaFish'. This is an object that can be placed in the aquarium and swim around.
 * 
 * @author Kristopher Randle 
 * @version 23-02-2021, 0.6
 */
public class JavaFish implements IUpdatable, IDisplayable, IConsumer
{
   // DECLARE a private static final String, call it 'MODEL' and set it to an appropriate model:
   private static final String MODEL = "models/billboard/billboard.obj";
   // DECLARE a private static final String, call it 'TEXTURE' and set it to the filepath of an appropriate image:
   private static final String TEXTURE = "textures/javaFish/JavaFish.png";
   
   // DECLARE the minimum speed a JavaFish can move as specified by the client brief:
   private static final double MIN_SPEED = 0.005;
   // DECLARE the maximum speed a JavaFish can move as specified by the client brief:
   private static final double MAX_SPEED = 0.05;
   
   // DECLARE an 'IDisplayObject', call it '_javaFish':
   private IDisplayObject _javaFish;
   
   // DECLARE an IUpdatableFactory, call it '_updatableFactory':
    private IUpdatableFactory _updatableFactory;
    
   // DECLARE a List array, call it '_bubblePool'. Make it contain instances of the 'IUpdatable' interface:
   private List<IUpdatable> _bubblePool;
   /**
     * Constructor for objects of class JavaFish
     * 
     * @return      void
     */
   public JavaFish()
   {
       //INITIALIZE '_javaFish' as a 'DisplayObject':
       _javaFish = new DisplayObject(MODEL, TEXTURE, Math.random() * (0.15 - 0.10) + 0.10, true); // SETS scale within client-brief range.
       // SET the '_javaFish' objects orientation so it's the right way up:
       _javaFish.orientation(0,270,0);
       // SET a random position for the '_javaFish' by calling its GenerateRandomPosition() method, cast to its ILocation interface:
       ((ILocation)_javaFish).GenerateRandomPosition();
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
           // SET both Bubble objects scale to 25% of '_javaFish' scale:
           ((Bubble)b1).setScale(((DisplayObject)_javaFish).getScale()*0.25);
           ((Bubble)b2).setScale(((DisplayObject)_javaFish).getScale()*0.25);
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
    * METHOD: used to validate that the speed of this JavaFish has been assigned is within the correct range, if it isn't an OutOfBoundsException is thrown.
    * 
    * @throws OutOfBoundsException
    * 
    * @return      void 
    */
    
   public void validateSpeed() throws OutOfBoundsException
   {
       // THROW an OutOfBoundsException if the VelocityX of the DisplayObject is out of the permitted range:
       if((((DisplayObject)_javaFish).getVelocityX()) < 0.005 || (((DisplayObject)_javaFish).getVelocityX()) > 0.05)
       {
           throw new OutOfBoundsException("JavaFish are being assigned a VelocityX value outside of their specified range (0.005 - 0.05)");
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
       return ((ILocation)_javaFish).getX();
   }
   
   /**
    * METHOD: returns the self-contained DisplayObject's Y co-ordinate.
    * 
    * @return   The self-contained DisplayObject's Y co-ordinate.
    */
   public double getDisplayY()
   {
       // RETURN the DisplayObject's Y co-ordinate:
       return ((ILocation)_javaFish).getY();
   }
   
   /**
    * METHOD: get the VelocityX of '_javaFish' and return it.
    * 
    * @return       The VelocityX of the '_javaFish' as a Double.
    */
   public Double getVelocityX()
   {
       // GET the DisplayObject's VelocityX and return it:
       return ((DisplayObject)_javaFish).getVelocityX();
   }
   
   /**
    * METHOD: returns a reference to this JavaFish's Bubble Pool.
    * 
    * @return   A list of IUpdatable containing the Bubble Pool.
    */
   public List<IUpdatable> getBubblePool()
   {
       // RETURN the '_bubblePool':
       return _bubblePool;
   }
   
   // ------------------------------ MUTATORS ------------------------------ //
   /**
    * METHOD: Generates a random VelocityX and VelocityY for the DisplayObject contained in JavaFish. The Speed is set between MIN_SPEED and MAX_SPEED.
    * 
    * @return void
    */
   public void GenerateRandomSpeed()
   {
       // GENERATE a random X velocity for the '_javaFish':
       ((DisplayObject)_javaFish).setVelocityX(Math.random() * (MAX_SPEED - MIN_SPEED) + MIN_SPEED);
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
       // INJECT '_javaFish' into the virtual world:
       world.addDisplayObject(_javaFish);
   }
   
   /**
     * METHOD: Removes the contained DisplayObject from the IWorld reference provided.
     * - Must be done after World has been created.
     * 
     * @param world     IWorld reference representing the 3D world.
     */
   public void removeDisplayable(IWorld world) throws WorldDoesNotExistException
   {
       // REMOVE '_javaFish' from the virtual world:
       world.removeDisplayObject(_javaFish);
   }
   
   // ------------------------------ IMPLEMENTATION OF IUpdatable ------------------------------ //
   /**
     * METHOD: called on each pass of the Simulation. Check the object is in the boundaries of the aquarium and move it.
     */
   public void update()
   {
       //CHECK the DisplayObject is within the aquarium:
       ((DisplayObject)_javaFish).inBounds();
       
       // CALL update() on '_javaFish', cast to its IUpdatable interface:
       ((IUpdatable)_javaFish).update();
   }
   
   // -------------------------- IMPLEMENTATION OF IConsumer --------------------------- //
   public void consume(IDisplayObject victim)
   {
       
   }
}