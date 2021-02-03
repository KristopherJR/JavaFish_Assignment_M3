package Exceptions;


/**
 * The WorldDoesNotExistException class:
 * an instance is thrown when someone tries to do something to a non-existent Env3D world.
 *
 * @author Marc Price
 * @version 0.4
 */
public class WorldDoesNotExistException extends Exception
{
    // instance variables - none

    /**
     * Constructor for objects of class WorldDoesNotExistException
     * 
     * @param message a String for the 'thrower' to provide extra information about the exception
     */
    public WorldDoesNotExistException(String message)
    {
        // PASS message to superclass:
        super(message);
    }

}
