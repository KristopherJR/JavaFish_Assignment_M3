package UserCode;

import Framework.Interfaces.*;
import Framework.Implementations.*;

import java.util.ArrayList;
import java.util.List;
/**
 * Represents a 'SeaHorse'. This is an object that can be placed in the aquarium and swim around.
 * 
 * @author Kristopher Randle 
 * @version 14-02-2021, 0.1
 */
public class SeaHorse implements IUpdatable
{
   // DECLARE a private static final String, call it 'MODEL' and set it to an appropriate model:
   private static final String MODEL = "models/billboard/billboard.obj";
   // DECLARE a private static final String, call it 'TEXTURE' and set it to the filepath of an appropriate image:
   private static final String TEXTURE = "textures/javaFish/Seahorse.png";
   
   // DECLARE the minimum speed a JavaFish can move as specified by the client brief:
   private static final double MIN_SPEED = 0.005;
   // DECLARE the maximum speed a JavaFish can move as specified by the client brief:
   private static final double MAX_SPEED = 0.05;
   // DECLARE a private static final double, call it SCREEN_HEIGHT and set it to 7.0:
   private static final double SCREEN_HEIGHT = 7.0;
   // DECLARE a private static final double, call it SCREEN_WIDTH and set it to 8.5:
   private static final double SCREEN_WIDTH = 8.0;
   
   // DECLARE a 'DisplayObject', call it '_seaHorse':
   private DisplayObject _seaHorse;
   /**
    * Constructor for objects of class SeaHorse.
    * 
    * @param s     the scale of the SeaHorse.
    * @return      void
    */
   public SeaHorse()
   {
       //INITIALIZE '_seaHorse' as a 'DisplayObject':
       _seaHorse = new DisplayObject(MODEL, TEXTURE, Math.random() * (0.45 - 0.225) + 0.225);
       _seaHorse.orientation(0,270,0);

       this.GenerateRandomPosition();
       this.GenerateRandomSpeed();
   }
    
   public void GenerateRandomSpeed()
   {
       // GENERATE a random X velocity for the '_seaHorse':
       _seaHorse.setVelocityX(Math.random() * (MAX_SPEED - MIN_SPEED) + MIN_SPEED);
   }
    
   public void GenerateRandomPosition()
   {
       // SET the position of '_seaHorse' to a random location (x,y,z) within the bounds of the aquarium:
       _seaHorse.setPosition((Math.random() * (SCREEN_WIDTH - 1) + 1),(Math.random() * (SCREEN_HEIGHT - 1) + 1),1.0);
   }
    
   public void update()
   {
       //CHECK the DisplayObject is within the aquarium:
       _seaHorse.inBounds();

       //MOVE the DisplayObject:
       _seaHorse.update();
   }
    
   public IDisplayObject getDisplayObject()
   {
       return _seaHorse;
   }
}
