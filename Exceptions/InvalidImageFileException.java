package Exceptions;

/**
 * Thrown if a String containing a file path does not lead to an image file.
 * 
 * @author Kristopher Randle
 * @version 18-12-2020
 */
public class InvalidImageFileException extends RuntimeException
{
    /**
     * Constructor for objects of class InvalidImageFileException
     * 
     * @param message A description of the error that caused the exception to be thrown.
     * 
     */
    public InvalidImageFileException(String message)
    {
        super(message);
    }
}
