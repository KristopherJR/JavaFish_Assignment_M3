package UserCode.UpdatableTokens;

import Framework.Interfaces.*;
import Framework.Implementations.*;

import UserCode.UserInterfaces.*;
import Exceptions.*;

import java.util.ArrayList;
import java.util.List;
/**
 * Represents a 'SeaHorse'. This is an object that can be placed in the aquarium and swim around.
 * 
 * @author Kristopher Randle 
 * @version 19-02-2021, 0.3
 */
public class SeaHorse implements IUpdatable, IDisplayable
{
   // DECLARE a private static final String, call it 'MODEL' and set it to an appropriate model:
   private static final String MODEL = "models/billboard/billboard.obj";
   // DECLARE a private static final String, call it 'TEXTURE' and set it to the filepath of an appropriate image:
   private static final String TEXTURE = "textures/javaFish/Seahorse.png";
   
   // DECLARE the minimum speed a JavaFish can move as specified by the client brief:
   private static final double MIN_SPEED = 0.005;
   // DECLARE the maximum speed a JavaFish can move as specified by the client brief:
   private static final double MAX_SPEED = 0.05;
   
   // DECLARE an 'IDisplayObject', call it '_seaHorse':
   private IDisplayObject _seaHorse;
   /**
    * Constructor for objects of class SeaHorse.
    * 
    * @return      void
    */
   public SeaHorse()
   {
       // INITIALIZE '_seaHorse' as a 'DisplayObject':
       _seaHorse = new DisplayObject(MODEL, TEXTURE, Math.random() * (0.45 - 0.225) + 0.225);
       // SET the '_seaHorse' objects orientation so it's the right way up:
       _seaHorse.orientation(0,270,0);
       
       // SET a random position for the '_seaHorse' by calling its GenerateRandomPosition() method, cast to its ILocation interface:
       ((ILocation)_seaHorse).GenerateRandomPosition();
       
       // GENERATE a random speed for this object between 0.005 - 0.05:
       this.GenerateRandomSpeed();
   }
    
   public void GenerateRandomSpeed()
   {
       // GENERATE a random X velocity for the '_seaHorse':
       ((DisplayObject)_seaHorse).setVelocityX(Math.random() * (MAX_SPEED - MIN_SPEED) + MIN_SPEED);
       
       // GENERATE a random Y velocity for the '_seaHorse':
       ((DisplayObject)_seaHorse).setVelocityY(Math.random() * (MAX_SPEED - MIN_SPEED) + MIN_SPEED);
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
       // INJECT '_seaHorse' into the virtual world:
       world.addDisplayObject(_seaHorse);
   }

   // ------------------------------ IMPLEMENTATION OF IUpdatable ------------------------------ //
   /**
     * METHOD: called on each pass of the Simulation. Check the object is in the boundaries of the aquarium and move it.
     */
   public void update()
   {
       //CHECK the DisplayObject is within the aquarium:
       ((DisplayObject)_seaHorse).inBounds();

       // CALL update() on '_seaHorse', cast to its IUpdatable interface:
       ((IUpdatable)_seaHorse).update();
   }
}
