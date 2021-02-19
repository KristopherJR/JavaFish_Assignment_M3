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
 * @version 19-02-2021, 0.4
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
    /**
     * Constructor for objects of class Bubble
     * 
     * @param x         The initial X co-ordiate of the Bubble.
     * @param y         The initial Y co-ordinate of the Bubble.
     * @param scale     The scale (size) of the Bubble.
     */
    public Bubble(double x, double y, double scale)
    {
        //INITALISE '_bubble' with appropriate parameters:
        _bubble = new DisplayObject(MODEL, TEXTURE, scale);
        //SET the Bubbles position to the parameters provided on creation:
        _bubble.position(x,y,1.0);
        //SET 'velocityY' to 0.005:
        ((DisplayObject)_bubble).setVelocityY(0.005);
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
     * METHOD: set the initial position of the Bubble in the aquarium - method from Marc Price, edited by Kristoper Randle.
     * 
     * @param  x    the rqd position along the x axis
     * @param  y    the rqd position along the y axis
     * 
     * @return      void 
     */
    protected void setPosition(double x, double y)
    {
        // SET the position of this Bubble to the x,y provided, cast to its ILocation interface:
        ((ILocation)_bubble).setX(x);
        ((ILocation)_bubble).setY(y);
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
    }
}