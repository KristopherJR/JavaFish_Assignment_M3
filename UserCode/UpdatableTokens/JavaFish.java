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
 * @version 19-02-2021, 0.4
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
       _javaFish = new DisplayObject(MODEL, TEXTURE, Math.random() * (0.45 - 0.225) + 0.225); // SETS scale within client-brief range.
       // SET the '_javaFish' objects orientation so it's the right way up:
       _javaFish.orientation(0,270,0);
       // SET a random position for the '_javaFish' by calling its GenerateRandomPosition() method, cast to its ILocation interface:
       ((ILocation)_javaFish).GenerateRandomPosition();
       // GENERATE a random speed for this object between 0.005 - 0.05:
       this.GenerateRandomSpeed();
       // _updatableFactory:
        _updatableFactory = new UpdatableFactory();
        
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

   public double getDisplayX()
   {
       return ((ILocation)_javaFish).getX();
   }
   
   public double getDisplayY()
   {
       return ((ILocation)_javaFish).getY();
   }
   
   public void GenerateRandomSpeed()
   {
       // GENERATE a random X velocity for the JavaFish:
       ((DisplayObject)_javaFish).setVelocityX(Math.random() * (MAX_SPEED - MIN_SPEED) + MIN_SPEED);
   }
   
   public List<IUpdatable> getBubblePool()
   {
       return _bubblePool;
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