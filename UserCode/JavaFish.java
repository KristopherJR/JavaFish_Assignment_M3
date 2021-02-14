package UserCode;

import Framework.Interfaces.*;
import Framework.Implementations.*;

import UserCode.UserInterfaces.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a 'JavaFish'. This is an object that can be placed in the aquarium and swim around.
 * 
 * @author Kristopher Randle 
 * @version 14-02-2021, 0.3
 */
public class JavaFish implements IUpdatable, ILocation
{
   // DECLARE a private static final String, call it 'MODEL' and set it to an appropriate model:
   private static final String MODEL = "models/billboard/billboard.obj";
   // DECLARE a private static final String, call it 'TEXTURE' and set it to the filepath of an appropriate image:
   private static final String TEXTURE = "textures/javaFish/JavaFish.png";
   
   // DECLARE the minimum speed a JavaFish can move as specified by the client brief:
   private static final double MIN_SPEED = 0.005;
   // DECLARE the maximum speed a JavaFish can move as specified by the client brief:
   private static final double MAX_SPEED = 0.05;
   // DECLARE a private static final double, call it SCREEN_HEIGHT and set it to 7.0:
   private static final double SCREEN_HEIGHT = 7.0;
   // DECLARE a private static final double, call it SCREEN_WIDTH and set it to 8.5:
   private static final double SCREEN_WIDTH = 8.0;
   
   // DECLARE a 'DisplayObject', call it '_javaFish':
   private DisplayObject _javaFish;
   // DECLARE a reference to the instance of 'List<SoundEffect>', call it '_soundEffects'. Used to store all objects of type 'SoundEffect':
   private List<SoundEffect> _soundEffects; 
   /**
     * Constructor for objects of class JavaFish
     * 
     * @param s     the scale of the JavaFish.
     * @return      void
     */
   public JavaFish()
   {
       //INITIALIZE '_javaFish' as a 'DisplayObject':
       _javaFish = new DisplayObject(MODEL, TEXTURE, Math.random() * (0.45 - 0.225) + 0.225);
       _javaFish.orientation(0,270,0);
       
       this.CreateSoundEffects(); 
       this.GenerateRandomPosition();
       this.GenerateRandomSpeed();
   }
   
   /**
     * METHOD: Initalises and populates the List '_soundEffects' with an appropriate selection of .wav files.
     *         Any 'JavaFish' can play any one of these 'SoundEffect's at a time.
     * 
     * @return      void 
     */
   public void CreateSoundEffects()
   {
       //INITIALISE the List '_soundEffects' as an 'ArrayList': 
       _soundEffects = new ArrayList<SoundEffect>();
        
       //INITIALISE all sound effects as type 'SoundEffect':
       SoundEffect bubble_emit1 = new SoundEffect("sfx/bubble_emit1.wav");
       SoundEffect bubble_emit2 = new SoundEffect("sfx/bubble_emit2.wav"); 
       SoundEffect bubble_emit3 = new SoundEffect("sfx/bubble_emit3.wav"); 
       SoundEffect bubble_emit4 = new SoundEffect("sfx/bubble_emit4.wav"); 
       SoundEffect bubble_emit5 = new SoundEffect("sfx/bubble_emit5.wav"); 
        
       //ADD each 'SoundEffect' to the '_soundEffects' List:
       _soundEffects.add(bubble_emit1);
       _soundEffects.add(bubble_emit2);
       _soundEffects.add(bubble_emit3);
       _soundEffects.add(bubble_emit4);
       _soundEffects.add(bubble_emit5);
   }

   /**
    * METHOD: When called, gives the 'JavaFish' a 1 in 900 chance of playing a random bubble sound:
    */
   public void MakeSound()
   {
       //HAVE a 1 in 900 chance of playing a random sound effect each time update() is called:
       int random = (int)(900 * Math.random()); //code snippet from https://javarevisited.blogspot.com/2013/05/how-to-generate-random-numbers-in-java-between-range.html#:~:text=If%20you%20want%20to%20create,that%20number%20into%20int%20later.
       if(random == 1)
       {
           this.playRandomSoundEffect();
       }
   }
    
   /**
    * METHOD: This method will play a random sound effect everytime it is called from the '_soundEffects' List.
    * 
    * @return void
    */
   public void playRandomSoundEffect()
   {
       //DECLARE int i, generate a random number between 0 - _soundEffects.size() - Store it in i:
       int i = (int)(_soundEffects.size() * Math.random()); //code snippet from https://javarevisited.blogspot.com/2013/05/how-to-generate-random-numbers-in-java-between-range.html#:~:text=If%20you%20want%20to%20create,that%20number%20into%20int%20later.
       //PLAY a random sound effect from the _soundEffects List at index i:
       _soundEffects.get(i).playSoundEffect();
   }
    
   public void GenerateRandomSpeed()
   {
       // GENERATE a random X velocity for the JavaFish:
       _javaFish.setVelocityX(Math.random() * (MAX_SPEED - MIN_SPEED) + MIN_SPEED);
   }
    
   
   
   // -------------------------- IMPLEMENTATION OF ILocation ---------------------------
   /**
     * Sets the X co-ordinate of this object.
     * 
     * @param x     The new location for this object on its X axis.
     * @return void
     */
   public void setX(double x)
   {
       // SET the DisplayObject's X co-ordinate to the provided parameter:
       _javaFish.setX(x);
   }
    
   /**
     * Sets the Y co-ordinate of this object.
     * 
     * @param y     The new location for this object on its Y axis.
     * @return void
     */
   public void setY(double y)
   {
       // SET the DisplayObject's Y co-ordinate to the provided parameter:
       _javaFish.setY(y);
   }
   
   /**
     * Sets the Z co-ordinate of this object.
     * 
     * @param z     The new location for this object on its Z axis.
     * @return void
     */
   public void setZ(double z)
   {
       // SET the DisplayObject's Z co-ordinate to the provided parameter:
       _javaFish.setZ(z);
   }
   
   /**
     * Generates a random position for this object.
     * 
     * @return void
     */
   public void GenerateRandomPosition()
   {
       // SET the position of '_javaFish' to a random location (x,y,z) within the bounds of the aquarium:
       _javaFish.setPosition((Math.random() * (SCREEN_WIDTH - 1) + 1),(Math.random() * (SCREEN_HEIGHT - 1) + 1),1.0);
   }
   
   /**
     * Gets the X co-ordinate of this object.
     * 
     * @return      The X co-ordinate of this object as a double.
     */
   public double getX()
   {
       // GET the DisplayObject's X co-ordinate and return it:
       return _javaFish.getX();
   }
    
   /**
     * Gets the Y co-ordinate of this object.
     * 
     * @return      The Y co-ordinate of this object as a double.
     */
   public double getY()
   {
       // GET the DisplayObject's Y co-ordinate and return it:
       return _javaFish.getY();
   }
    
   /**
     * Gets the Z co-ordinate of this object.
     * 
     * @return      The Z co-ordinate of this object as a double.
     */
   public double getZ()
   {
       // GET the DisplayObject's Z co-ordinate and return it:
       return _javaFish.getZ();
   }
   
   // -------------------------- IMPLEMENTATION OF IUpdatable --------------------------
   /**
     * METHOD: called on each pass of the Simulation.
     */
   public void update()
   {
       //CHECK the DisplayObject is within the aquarium:
       _javaFish.inBounds();
       
       this.MakeSound();
       //MOVE the DisplayObject:
       _javaFish.update();
   }
   
   /**
     * METHOD: get the DisplayObject stored in this class and return it.
     * 
     * @return The DisplayObject contained within this class.
     */
   public IDisplayObject getDisplayObject()
   {
       // RETURN the DisplayObject '_javaFish':
       return _javaFish;
   }
}