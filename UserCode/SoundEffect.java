package UserCode;

import java.io.*;
import javax.sound.sampled.*;
/**
 * SoundEffect class handles all operations required to play an audible sound within a program. 
 * This class was based on this video tutorial: https://www.youtube.com/watch?v=qPVkRtuf9CQ
 * 
 * Sound effect samples obtained from https://www.zapsplat.com and used in accordance to their standard licensing.
 * 
 * @author Kristopher Randle & RyiSnow
 * @version 29-01-2021, 0.1 
 */
public class SoundEffect
{
    // DECLARE Instance Variables:
    String filePath;
    Clip soundClip;
    AudioInputStream soundEffect;
    File file;
    /**
     * Constructor for objects of class SoundEffect
     * 
     * @param f     the file path to the sound effect that should be played. EG. "sfx/bubble_emit1.wav"
     * 
     */
    public SoundEffect(String f)
    {
        // STORE the passed String 'f' as 'filePath':
        filePath = f;
        
        // SET-UP the sound effect in a try-catch block to catch potential exceptions:
        try
        {
            file = new File(filePath);
            soundEffect = AudioSystem.getAudioInputStream(file);
            soundClip = AudioSystem.getClip();
            soundClip.open(soundEffect);
        }
        catch(Exception e)
        {
            // IF Exception thrown, print the error message to the console:
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * METHOD: plays the instances stored sound effect when called.
     *
     *@return   void
     */  
    
    public void playSoundEffect()
    {
        soundClip.setFramePosition(0);
        soundClip.start();
    }
}