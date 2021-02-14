package UserCode;

import Framework.Interfaces.*;
import Framework.Implementations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a 'Piranha'. This is an object that can be placed in the aquarium and swim around.
 * 
 * @author Kristopher Randle 
 * @version 14-02-2021, 0.3
 */
public class Piranha implements IUpdatable
{
   // DECLARE a private static final String, call it 'MODEL' and set it to an appropriate model:
   private static final String MODEL = "models/billboard/billboard.obj";
   // DECLARE a private String, call it '_texture':
   private String _texture;
   
   // DECLARE the minimum speed a fish can move as specified by the client brief:
   private static final double MIN_SPEED = 0.005;
   // DECLARE the maximum speed a fish can move as specified by the client brief:
   private static final double MAX_SPEED = 0.05;
   // DECLARE a private static final double, call it SCREEN_HEIGHT and set it to 7.0:
   private static final double SCREEN_HEIGHT = 7.0;
   // DECLARE a private static final double, call it SCREEN_WIDTH and set it to 8.5:
   private static final double SCREEN_WIDTH = 8.0;
   
   // DECLARE an instance of 'Random', call it '_random':
   private Random _random;
   // DECLARE a 'DisplayObject', call it '_piranha':
   private DisplayObject _piranha;
   // DECLARE a reference to the instance of 'List<SoundEffect>', call it '_soundEffects'. Used to store all objects of type 'SoundEffect':
   private List<SoundEffect> _soundEffects;
   /**
    * Constructor for objects of class Piranha
    * 
    * @param s     the scale of the Piranha.
    * @return      void
    */
   public Piranha()
   {
       // INITIALIZE the random number generator:
       _random = new Random();
       // INITIALIZE the '_texture' String, give it a 50/50 chance of being a Green or Red Piranha:
       if(_random.nextInt(2) == 0)
       {
           _texture = "textures/javaFish/PiranhaRed.png";
       }
       else
       {
           _texture = "textures/javaFish/PiranhaGreen.png";
       }
       // INITIALIZE '_piranha' as a 'DisplayObject':
       _piranha = new DisplayObject(MODEL, _texture, Math.random() * (0.45 - 0.225) + 0.225);
       _piranha.orientation(0,270,0);
       
       this.CreateSoundEffects(); 
       this.GenerateRandomPosition();
       this.GenerateRandomSpeed();
   }
    
   /**
    * METHOD: Initalises and populates the List '_soundEffects' with an appropriate selection of .wav files.
    *         Any 'Piranha' can play any one of these 'SoundEffect's at a time.
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
    * METHOD: When called, gives the 'Piranha' a 1 in 900 chance of playing a random bubble sound:
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
       // GENERATE a random X velocity for the '_piranha':
       _piranha.setVelocityX(Math.random() * (MAX_SPEED - MIN_SPEED) + MIN_SPEED);
   }
    
   public void GenerateRandomPosition()
   {
       // SET the position of '_piranha' to a random location (x,y,z) within the bounds of the aquarium:
       _piranha.setPosition((Math.random() * (SCREEN_WIDTH - 1) + 1),(Math.random() * (SCREEN_HEIGHT - 1) + 1),1.0);
   }
    
   public void update()
   {
       //CHECK the DisplayObject is within the aquarium:
       _piranha.inBounds();
       
       this.MakeSound();
       //MOVE the DisplayObject:
       _piranha.update();
   }
    
   public IDisplayObject getDisplayObject()
   {
       return _piranha;
   }
}
