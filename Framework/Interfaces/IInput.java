package Framework.Interfaces;


import Exceptions.*;

/**
 * IInput implementations access input from Env3D.
 * Note that you may need to extend this if there are specific functions you need.
 * 
 * @author Marc Price 
 * @version 0.6
 */
public interface IInput
{
    /**
     * METHOD: get keyboard input.
     *
     * @return the key value (-1 if no key has been pressed since last check).
     */
    int getKey() throws WorldDoesNotExistException;
    
    /**
     * METHOD: check if a mouse button has been clicked.
     *
     * @return the button value: 0= left button; 1= middle button; 2= right button.
     */
    int getMouseClicked() throws WorldDoesNotExistException;
    
    /**
     * METHOD: check if a mouse button has been clicked.
     *
     * @return the pointer value as a 2-element array of ints: return[0]=X, return[1]=Y.
     */
    int[] getMousePointer() throws WorldDoesNotExistException;
}
