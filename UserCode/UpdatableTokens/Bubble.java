package UserCode.UpdatableTokens;

import UserCode.SoundEffect;
import UserCode.UserInterfaces.*;
import Exceptions.*;

import Framework.Interfaces.*;
import Framework.Implementations.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Bubbles contains all of the code needed to represent a Bubble to be placed in the Aquarium.
 * 
 * @author Kristopher Randle 
 * @version 22-02-2021, 0.5
 */
public class Bubble implements IUpdatable, IDisplayable
{
    // DECLARE a private static final String, call it 'MODEL' and set it to an appropriate model:
    private static final String MODEL = "sphere";
    // DECLARE a private static final String, call it 'TEXTURE' and set it to the filepath of an appropriate image:
    private static final String TEXTURE = "textures/javaFish/Bubble.png";
    // DECLARE an 'IDisplayObject', call it '_bubble':
    private IDisplayObject _bubble;
    // DECLARE a reference to the instance of 'List<SoundEffect>', call it '_soundEffects'. Used to store all objects of type 'SoundEffect':
    private List<SoundEffect> _soundEffects;
    // DECLARE a private Boolean, call it '_canSpawn'. This is used to check if a bubble has been placed in the aquarium:
    private Boolean _canSpawn = true;
    // DECLARE a private Boolean, call it '_flagDeletion'. This will be set to true if a Bubble touches the roof of the aquarium. Set it to false:
    private Boolean _flagDeletion = false;
    /**
     * Constructor for objects of class Bubble
     *
     */
    public Bubble()
    {
        // INITALISE '_bubble' with appropriate parameters:
        _bubble = new DisplayObject(MODEL, TEXTURE, 0.15, false);
        // SET 'velocityY' to 0.005:
        ((DisplayObject)_bubble).setVelocityY(0.005);
        // POPULATE the _soundEffects List:
        this.createSoundEffects();
    }
    
    /**
     * METHOD: Initalises and populates the List 'soundEffects' with an appropriate selection of .wav files.
     *         Any 'Bubble' can play any one of these 'SoundEffect's at a time.
     * 
     * @return      void 
     */
    public void createSoundEffects()
    {
        //INITIALISE the List '_soundEffects' as an 'ArrayList': 
        _soundEffects = new ArrayList<SoundEffect>();
        
        //INITIALISE all sound effects as type 'SoundEffect':
        SoundEffect bubble_emit1 = new SoundEffect("sfx/bubble_emit1.wav");
        SoundEffect bubble_emit2 = new SoundEffect("sfx/bubble_emit2.wav"); 
        SoundEffect bubble_emit3 = new SoundEffect("sfx/bubble_emit3.wav"); 
        SoundEffect bubble_emit4 = new SoundEffect("sfx/bubble_emit4.wav"); 
        SoundEffect bubble_emit5 = new SoundEffect("sfx/bubble_emit5.wav"); 
        
        //ADD each 'SoundEffect' to the _soundEffects List:
        _soundEffects.add(bubble_emit1);
        _soundEffects.add(bubble_emit2);
        _soundEffects.add(bubble_emit3);
        _soundEffects.add(bubble_emit4);
        _soundEffects.add(bubble_emit5);
    }
    
    /**
     * METHOD: When called, This method will play a random sound effect everytime it is called from the '_soundEffects' List.
     *         
     * @return void
     */
    public void makeSound()
    {
        //DECLARE int i, generate a random number between 0 - _soundEffects.size() - Store it in i:
        int i = (int)(_soundEffects.size() * Math.random()); //code snippet from https://javarevisited.blogspot.com/2013/05/how-to-generate-random-numbers-in-java-between-range.html#:~:text=If%20you%20want%20to%20create,that%20number%20into%20int%20later.
        //PLAY a random sound effect from the _soundEffects List at index i:
        _soundEffects.get(i).playSoundEffect();
    }
    
    // ------------------------------ ACCESSORS ------------------------------ //
    /**
     * METHOD: get the value contained in '_canSpawn'
     * 
     * @return      The Boolean value of '_canSpawn'
     */
    public Boolean getCanSpawn()
    {
        // RETURN 'canSpawn':
        return _canSpawn;
    }
    
    /**
     * METHOD: get the value contained in '_flagDeletion'
     * 
     * @return      The Boolean value of '_flagDeletion'
     */
    public Boolean getFlagDeletion()
    {
        // RETURN '_flagDeletion':
        return _flagDeletion;
    }
    
    // ------------------------------ MUTATORS ------------------------------ //
    /**
     * METHOD: set the initial position of the Bubble in the aquarium - method from Marc Price, edited by Kristoper Randle.
     * 
     * @param  x    the rqd position along the x axis
     * @param  y    the rqd position along the y axis
     * 
     * @return      void 
     */
    public void setPosition(double x, double y)
    {
        // SET the position of this Bubble to the x,y provided, cast to its ILocation interface:
        ((ILocation)_bubble).setX(x);
        ((ILocation)_bubble).setY(y);
    }
    
    public void setScale(double scale)
    {
        // SET the scale of '_bubble' to the provided parameter:
        ((DisplayObject)_bubble).setScale(scale);
    }
    
    /**
     * METHOD: used to set the value of '_canSpawn' to true of false. '_canSpawn' is used to check if a Bubble is eligible to be placed in the 3D world.
     * 
     * @param  value     a Boolean value to set '_canSpawn' to
     * @return void
     */
    public void setCanSpawn(Boolean value)
    {
        // SET '_canSpawn' to value:
        _canSpawn = value;
    }
    
    /**
     * METHOD: used to set the value of '_flagDeletion' to true of false. '_flagDeletion' is used to signify that a Bubble needs to be removed from the 3D world.
     * 
     * @param  value     a Boolean value to set '_flagDeletion' to
     * @return void
     */
    public void setFlagDeletion(Boolean value)
    {
        // SET '_flagDeletion' to value:
        _flagDeletion = value;
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
        // INJECT '_bubble' into the virtual world:
        world.addDisplayObject(_bubble);
    }
    
    /**
     * METHOD: Removes the contained DisplayObject from the IWorld reference provided.
     * - Must be done after World has been created.
     * 
     * @param world     IWorld reference representing the 3D world.
     */
    public void removeDisplayable(IWorld world) throws WorldDoesNotExistException
    {
        // REMOVE '_bubble' from the virtual world:
        world.removeDisplayObject(_bubble);
    }
    
    // ------------------------------ IMPLEMENTATION OF IUpdatable ------------------------------ //
    /**
     * METHOD: update() the DisplayObject contained in the class.
     *
     *@return   void
     */
    public void update()
    {
        // CALL update() on '_bubble', cast to its IUpdatable interface:
        ((IUpdatable)_bubble).update();
        
        // IF the Bubble has gone past the roof:
        if(((ILocation)_bubble).getY() > 7.5)
        {
            // RESET the Bubble to just below the roof, this will stop the program polling to check if the bubble has gone past the roof on each pass of the update loop:
            ((ILocation)_bubble).setY(7.3);
            // MAKE a 'pop' sound:
            this.makeSound();
            // WRITE to the console to signifying popping (de-bugging usage):
            System.out.println("THIS BOI POPPED!");
            // FLAG it for deletion:
            _flagDeletion = true;
        }
    }
}