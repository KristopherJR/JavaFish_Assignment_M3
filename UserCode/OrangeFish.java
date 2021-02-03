package UserCode;

import Framework.Interfaces.*;
import Framework.Implementations.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an 'OrangeFish'. This is an object that can be placed in the aquarium and swim around.
 * 
 * @author Kristopher Randle 
 * @version 0.2 - 29-01-2021
 */
public class OrangeFish implements IUpdatable
{
   // DECLARE a 'DisplayObject', call it '_orangeFish':
   private DisplayObject _orangeFish;
   // DECLARE the minimum speed a fish can move as specified by the client brief:
   private static final double MIN_SPEED = 0.005;
   // DECLARE the maximum speed a fish can move as specified by the client brief:
   private static final double MAX_SPEED = 0.05;
   // DECLARE a public static final double, call it SCREEN_HEIGHT and set it to 7.0:
   public static final double SCREEN_HEIGHT = 7.0;
   // DECLARE a public static final double, call it SCREEN_WIDTH and set it to 8.5:
   public static final double SCREEN_WIDTH = 8.0;
   // DECLARE a reference to the instance of 'List<SoundEffect>', call it '_soundEffects'. Used to store all objects of type 'SoundEffect':
   private List<SoundEffect> _soundEffects;
   /**
    * Constructor for objects of class orangeFish
    * 
    * @param s     the scale of the OrangeFish.
    * @return      void
    */
   public OrangeFish()
   {
       //INITIALIZE 'orangeFish' as a 'DisplayObject', storing it as an 'IDisplayObject':
       _orangeFish = new DisplayObject("models/billboard/billboard.obj", "textures/javaFish/Orange_Fish.png", Math.random() * (0.45 - 0.225) + 0.225);
       _orangeFish.orientation(0,270,0);
       
       this.CreateSoundEffects(); 
       this.GenerateRandomPosition();
       this.GenerateRandomSpeed();
   }
    
   /**
    * METHOD: Initalises and populates the List '_soundEffects' with an appropriate selection of .wav files.
    *         Any 'OrangeFish' can play any one of these 'SoundEffect's at a time.
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
    * METHOD: When called, gives the 'OrangeFish' a 1 in 900 chance of playing a random bubble sound:
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
       _orangeFish.setVelocityX(Math.random() * (MAX_SPEED - MIN_SPEED) + MIN_SPEED);
   }
    
   public void GenerateRandomPosition()
   {
       // SET the position of 'javaFish' to a random location (x,y,z) within the bounds of the aquarium:
       _orangeFish.setPosition((Math.random() * (SCREEN_WIDTH - 1) + 1),(Math.random() * (SCREEN_HEIGHT - 1) + 1),1.0);
   }
    
   public void update()
   {
       //CHECK the DisplayObject is within the aquarium:
       _orangeFish.inBounds();
       
       this.MakeSound();
       //MOVE the DisplayObject:
       _orangeFish.update();
   }
    
   public IDisplayObject getDisplayObject()
   {
       return _orangeFish;
   }
}
