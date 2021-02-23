package Exceptions;

/**
 * Thrown if a parameter is not within it's specified range.
 * 
 * Based on Dr Marc Price's ArgumentOutOfBoundsException from his video lesson on Blackboard.
 * 
 * @author Kristopher Randle & Marc Price
 * @version 14-12-2020
 */
public class OutOfBoundsException extends RuntimeException
{
    /**
     * Constructor for objects of class OutOfBoundsException
     * 
     * @param message A description of the error that caused the exception to be thrown.
     * 
     */
    public OutOfBoundsException(String message)
    {
        super(message);
    }
}
