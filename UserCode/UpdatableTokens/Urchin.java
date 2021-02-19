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
 * @version 19-02-2021, 0.4
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
    * @param s     the scale of the Urchin.
    * @return      void
    */
   public Urchin()
   {
       // INITIALIZE 'urchin' as a 'DisplayObject':
       _urchin = new DisplayObject(MODEL, TEXTURE, Math.random() * (0.45 - 0.225) + 0.225);
       // SET the '_seaHorse' objects orientation so it's the right way up:
       _urchin.orientation(0,270,0);
       
       // SET a random position for the '_urchin' by calling its GenerateRandomPosition() method, cast to its ILocation interface:
       ((ILocation)_urchin).setY(1.1);
       
       // GENERATE a random speed for this object between 0.005 - 0.05:
       this.GenerateRandomSpeed();
   }
    
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
   
   // ------------------------------ IMPLEMENTATION OF IUpdatable ------------------------------ //
   public void update()
   {
       //CHECK the DisplayObject is within the aquarium:
       ((DisplayObject)_urchin).inBounds();

       // CALL update() on '_urchin', cast to its IUpdatable interface:
       ((IUpdatable)_urchin).update();
   }
}
