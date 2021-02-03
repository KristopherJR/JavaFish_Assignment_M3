package UserCode;

import Framework.Interfaces.*;
import Framework.Implementations.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Bubbles contains all of the code needed to represent a Bubble to be placed in the Aquarium.
 * 
 * @author Kristopher Randle 
 * @version 29-01-2021
 */
public class Bubble implements IUpdatable
{
    //Instance Variables:
    // DECLARE a public static final double, call it SCREEN_HEIGHT and set it to 7.0:
    public static final double SCREEN_HEIGHT = 7.0;
    // DECLARE a private double, call it floatSpeed:
    private double floatSpeed;
    // DECLARE a 'DisplayObject', call it 'bubble':
    private DisplayObject bubble;
    // DECLARE a reference to the instance of 'List<SoundEffect>', call it 'soundEffects'. Used to store all objects of type 'SoundEffect':
    private List<SoundEffect> soundEffects;
    /**
     * Constructor for objects of class Bubble
     * 
     * @param x         The initial X co-ordiate of the Bubble.
     * @param y         The initial Y co-ordinate of the Bubble.
     * @param scale     The scale (size) of the Bubble.
     */
    public Bubble(double x, double y, double scale)
    {
        bubble = new DisplayObject("sphere", "textures/javaFish/Bubble.png", scale);
        
      

        floatSpeed = 0.005;
        
        bubble.position(3.0,3.0,0.1);
        bubble.orientation(0,270,0);
        
        //SET 'velocityY' to 'floatSpeed' as default:
        bubble.setVelocityY(floatSpeed);
    }
    
    /**
     * METHOD: Initalises and populates the List 'soundEffects' with an appropriate selection of .wav files.
     *         Any 'Bubble' can play any one of these 'SoundEffect's at a time.
     * 
     * @return      void 
     */
    public void createSoundEffects()
    {
        //INITIALISE the List 'soundEffects' as an 'ArrayList': 
        soundEffects = new ArrayList<SoundEffect>();
        
        //INITIALISE all sound effects as type 'SoundEffect':
        SoundEffect bubble_emit1 = new SoundEffect("sfx/bubble_emit1.wav");
        SoundEffect bubble_emit2 = new SoundEffect("sfx/bubble_emit2.wav"); 
        SoundEffect bubble_emit3 = new SoundEffect("sfx/bubble_emit3.wav"); 
        SoundEffect bubble_emit4 = new SoundEffect("sfx/bubble_emit4.wav"); 
        SoundEffect bubble_emit5 = new SoundEffect("sfx/bubble_emit5.wav"); 
        
        //ADD each 'SoundEffect' to the _bubbleSoundEffects List:
        soundEffects.add(bubble_emit1);
        soundEffects.add(bubble_emit2);
        soundEffects.add(bubble_emit3);
        soundEffects.add(bubble_emit4);
        soundEffects.add(bubble_emit5);
    }
    
    /**
     * METHOD: set the initial position of the Bubble in the aquarium - method from Marc Price, edited by Kristoper Randle.
     * 
     * 
     * @param  x    the rqd position along the x axis
     * @param  y    the rqd position along the y axis
     * 
     * @return      void 
     */
    protected void setPosition(double x, double y)
    {
        // SET the position of this Bubble to the x,y provided:
        bubble.setX(x);
        bubble.setY(y);
    }
    
    /**
     * METHOD: to set the next frame of the simulation, called on each pass of the simulation.
     *
     *@return   void
     */
    public void update()
    {
        bubble.update();
    }
    
    public IDisplayObject getDisplayObject()
    {
        return bubble;
    }
}