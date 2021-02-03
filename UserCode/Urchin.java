package UserCode;

import Framework.Interfaces.*;
import Framework.Implementations.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an 'Urchin'. This is an object that can be placed in the aquarium and swim around.
 * 
 * @author Kristopher Randle 
 * @version 0.2 - 29-01-2021
 */
public class Urchin implements IUpdatable
{
   // DECLARE a 'DisplayObject', call it '_urchin':
   private DisplayObject _urchin;
   // DECLARE the minimum speed a fish can move as specified by the client brief:
   private static final double MIN_SPEED = 0.005;
   // DECLARE the maximum speed a fish can move as specified by the client brief:
   private static final double MAX_SPEED = 0.05;
   // DECLARE a public static final double, call it SCREEN_HEIGHT and set it to 7.0:
   public static final double SCREEN_HEIGHT = 7.0;
   // DECLARE a public static final double, call it SCREEN_WIDTH and set it to 8.5:
   public static final double SCREEN_WIDTH = 8.0;
    
   /**
    * Constructor for objects of class Urchin.
    * 
    * @param s     the scale of the Urchin.
    * @return      void
    */
   public Urchin()
   {
       //INITIALIZE 'urchin' as a 'DisplayObject', storing it as an 'IDisplayObject':
       _urchin = new DisplayObject("models/billboard/billboard.obj", "textures/javaFish/Urchin.png", Math.random() * (0.45 - 0.225) + 0.225);
       _urchin.orientation(0,270,0);

       this.GenerateRandomPosition();
       this.GenerateRandomSpeed();
   }
    
   public void GenerateRandomSpeed()
   {
       // GENERATE a random X velocity for the '_urchin':
       _urchin.setVelocityX(Math.random() * (MAX_SPEED - MIN_SPEED) + MIN_SPEED);
   }
    
   public void GenerateRandomPosition()
   {
       // SET the position of '_urchin' to a random location (x,y,z) within the bounds of the aquarium:
       _urchin.setPosition((Math.random() * (SCREEN_WIDTH - 1) + 1),1.0,1.0);
   }
    
   public void update()
   {
       //CHECK the DisplayObject is within the aquarium:
       _urchin.inBounds();

       //MOVE the DisplayObject:
       _urchin.update();
   }
    
   public IDisplayObject getDisplayObject()
   {
       return _urchin;
   }
}
