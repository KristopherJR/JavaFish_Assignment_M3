package UserCode.UpdatableTokens;

import Framework.Interfaces.*;
import Framework.Implementations.*;

import UserCode.UserInterfaces.*;
import Exceptions.*;

import java.util.ArrayList;
import java.util.List;
/**
 * Represents an 'Urchin'. This is an object that can be placed in the aquarium and swim around.
 * 
 * @author Kristopher Randle 
 * @version 23-02-2021, 0.6
 */
public class Urchin implements IUpdatable, IDisplayable
{
   // DECLARE a private static final String, call it 'MODEL' and set it to an appropriate model:
   private static final String MODEL = "models/billboard/billboard.obj";
   // DECLARE a private static final String, call it 'TEXTURE' and set it to the filepath of an appropriate image:
   private static final String TEXTURE = "textures/javaFish/Urchin.png"; 
    
   // DECLARE the minimum speed a fish can move as specified by the client brief:
   private static final double MIN_SPEED = 0.005;
   // DECLARE the maximum speed a fish can move as specified by the client brief:
   private static final double MAX_SPEED = 0.05;
   
   // DECLARE an 'IDisplayObject', call it '_urchin':
   private IDisplayObject _urchin;
   /**
    * Constructor for objects of class Urchin.
    * 
    * @return      void
    */
   public Urchin()
   {
       // INITIALIZE 'urchin' as a 'DisplayObject':
       _urchin = new DisplayObject(MODEL, TEXTURE, Math.random() * (0.45 - 0.225) + 0.225, false); // SETS scale within client-brief range.
       // SET the '_seaHorse' objects orientation so it's the right way up:
       _urchin.orientation(0,270,0);
       
       // SET a random position for the '_urchin' by calling its GenerateRandomPosition() method, cast to its ILocation interface:
       ((ILocation)_urchin).setY(1.1);
       
       // GENERATE a random speed for this object between 0.005 - 0.05:
       this.GenerateRandomSpeed();
   }
   
   /**
    * METHOD: used to validate that the speed of this Urchin has been assigned is within the correct range, if it isn't an OutOfBoundsException is thrown.
    * 
    * @throws OutOfBoundsException
    * 
    * @return void 
    */
    
   public void validateSpeed() throws OutOfBoundsException
   {
       // THROW an OutOfBoundsException if the VelocityX of the DisplayObject is out of the permitted range:
       if((((DisplayObject)_urchin).getVelocityX()) < 0.005 || (((DisplayObject)_urchin).getVelocityX()) > 0.05)
       {
           throw new OutOfBoundsException("Urchins are being assigned a VelocityX value outside of their specified range (0.005 - 0.05)");
       }
   }
   
   // ------------------------------ ACCESSORS ------------------------------ //
   /**
    * METHOD: get the VelocityX of '_urchin' and return it.
    * 
    * @return       The VelocityX of the '_urchin' as a Double.
    */
   public Double getVelocityX()
   {
       // GET the DisplayObject's VelocityX and return it:
       return ((DisplayObject)_urchin).getVelocityX();
   }
   
   // ------------------------------ MUTATORS ------------------------------ //
   /**
    * METHOD: Generates a random VelocityX and VelocityY for the DisplayObject contained in Urchin. The Speed is set between MIN_SPEED and MAX_SPEED.
    * 
    * @return void
    */
   public void GenerateRandomSpeed()
   {
       // GENERATE a random X velocity for the '_urchin':
       ((DisplayObject)_urchin).setVelocityX(Math.random() * (MAX_SPEED - MIN_SPEED) + MIN_SPEED);
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
       // INJECT '_urchin' into the virtual world:
       world.addDisplayObject(_urchin);
   }
   
   /**
     * METHOD: Removes the contained DisplayObject from the IWorld reference provided.
     * - Must be done after World has been created.
     * 
     * @param world     IWorld reference representing the 3D world.
     */
   public void removeDisplayable(IWorld world) throws WorldDoesNotExistException
   {
       // REMOVE '_urchin' from the virtual world:
       world.removeDisplayObject(_urchin);
   }
   
   // ------------------------------ IMPLEMENTATION OF IUpdatable ------------------------------ //
   public void update()
   {
       //CHECK the DisplayObject is within the aquarium:
       ((DisplayObject)_urchin).inBounds();

       // CALL update() on '_urchin', cast to its IUpdatable interface:
       ((IUpdatable)_urchin).update();
   }
}
