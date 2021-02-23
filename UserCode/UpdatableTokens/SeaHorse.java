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
 * @version 23-02-2021, 0.5
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
       _seaHorse = new DisplayObject(MODEL, TEXTURE, Math.random() * (0.45 - 0.225) + 0.225, false); // SETS scale within client-brief range.
       // SET the '_seaHorse' objects orientation so it's the right way up:
       _seaHorse.orientation(0,270,0);
       
       // SET a random position for the '_seaHorse' by calling its GenerateRandomPosition() method, cast to its ILocation interface:
       ((ILocation)_seaHorse).GenerateRandomPosition();
       
       // GENERATE a random speed for this object between 0.005 - 0.05:
       this.GenerateRandomSpeed();
   }
   
   /**
    * METHOD: used to validate that the speed of this SeaHorse has been assigned is within the correct range, if it isn't an OutOfBoundsException is thrown.
    * 
    * @throws OutOfBoundsException
    * 
    * @return      void 
    */
    
   public void validateSpeed() throws OutOfBoundsException
   {
       // THROW an OutOfBoundsException if the VelocityX of the DisplayObject is out of the permitted range:
       if((((DisplayObject)_seaHorse).getVelocityX()) < 0.005 || (((DisplayObject)_seaHorse).getVelocityX()) > 0.05)
       {
           throw new OutOfBoundsException("SeaHorses are being assigned a VelocityX value outside of their specified range (0.005 - 0.05)");
       }
       // THROW an OutOfBoundsException if the VelocityY of the DisplayObject is out of the permitted range:
       if((((DisplayObject)_seaHorse).getVelocityY()) < 0.005 || (((DisplayObject)_seaHorse).getVelocityY()) > 0.05)
       {
           throw new OutOfBoundsException("SeaHorses are being assigned a VelocityY value outside of their specified range (0.005 - 0.05)");
       }
   }
   
   // ------------------------------ ACCESSORS ------------------------------ //
   /**
    * METHOD: get the VelocityX of '_seaHorse' and return it.
    * 
    * @return       The VelocityX of the '_seaHorse' as a Double.
    */
   public Double getVelocityX()
   {
       // GET the DisplayObject's VelocityX and return it:
       return ((DisplayObject)_seaHorse).getVelocityX();
   }
   
   /**
    * METHOD: get the VelocityY of '_seaHorse' and return it.
    * 
    * @return       The VelocityY of the '_seaHorse' as a Double.
    */
   public Double getVelocityY()
   {
       // GET the DisplayObject's VelocityY and return it:
       return ((DisplayObject)_seaHorse).getVelocityY();
   }
   
   // ------------------------------ MUTATORS ------------------------------ //
   /**
    * METHOD: Generates a random VelocityX and VelocityY for the DisplayObject contained in SeaHorse. The Speed is set between MIN_SPEED and MAX_SPEED.
    * 
    * @return void
    */
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
   
   /**
     * METHOD: Removes the contained DisplayObject from the IWorld reference provided.
     * - Must be done after World has been created.
     * 
     * @param world     IWorld reference representing the 3D world.
     */
   public void removeDisplayable(IWorld world) throws WorldDoesNotExistException
   {
       // REMOVE '_seaHorse' from the virtual world:
       world.removeDisplayObject(_seaHorse);
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
