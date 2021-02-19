package UserCode.UpdatableTokens;

import Framework.Interfaces.*;
import Framework.Implementations.*;

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
   // DECLARE a reference to the instance of 'List<SoundEffect>', call it '_soundEffects'. Used to store all objects of type 'SoundEffect':
   private List<SoundEffect> _soundEffects; 
   /**
     * Constructor for objects of class JavaFish
     * 
     * @return      void
     */
   public JavaFish()
   {
       //INITIALIZE '_javaFish' as a 'DisplayObject':
       _javaFish = new DisplayObject(MODEL, TEXTURE, Math.random() * (0.45 - 0.225) + 0.225);
       // SET the '_javaFish' objects orientation so it's the right way up:
       _javaFish.orientation(0,270,0);
       
       // SET a random position for the '_javaFish' by calling its GenerateRandomPosition() method, cast to its ILocation interface:
       ((ILocation)_javaFish).GenerateRandomPosition();
       
       // INITALIZE the _soundEffects List:
       this.CreateSoundEffects(); ;
       // GENERATE a random speed for this object between 0.005 - 0.05:
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
       ((DisplayObject)_javaFish).setVelocityX(Math.random() * (MAX_SPEED - MIN_SPEED) + MIN_SPEED);
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
   
   // -------------------------- IMPLEMENTATION OF IUpdatable --------------------------- //
   /**
     * METHOD: called on each pass of the Simulation. Check the object is in the boundaries of the aquarium and move it.
     */
   public void update()
   {
       //CHECK the DisplayObject is within the aquarium:
       ((DisplayObject)_javaFish).inBounds();
       
       this.MakeSound();
       // CALL update() on '_javaFish', cast to its IUpdatable interface:
       ((IUpdatable)_javaFish).update();
   }
   
   // -------------------------- IMPLEMENTATION OF IConsumer --------------------------- //
   public void consume(IDisplayObject victim)
   {
       
   }
}