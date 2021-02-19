package UserCode.UserInterfaces;


/**
 * IInputListener: all listeners for input must implement this interface.
 * 
 * @author Marc Price
 * @version 0.1
 */
public interface IInputListener
{
    /**
     * Method to handle an input event
     * 
     * @param data an array of integers containing the input data
     */
    void onInput(int ...data);
}
