package UserCode;

import UserCode.UserInterfaces.*;

import Framework.Interfaces.IInput;
import Framework.Interfaces.IUpdatable;

import java.util.List;
import java.util.ArrayList;
/**
 * Handles Mouse Events specifically for the Observer Pattern implementation.
 * 
 * @author Marc Price
 * @version 0.1
 */
public class MouseHandler implements IInputPublisher, IUpdatable
{
    // instance variables:
    // DECLARE a list of IInputListener to store the event listener methods, call it _listeners:
    private List<IInputListener> _listeners;
    
    // DECLARE an IInput to hold a reference to our input capture implementation, call it _inputCapture:
    private IInput _inputCapture;
    
    /**
     * Constructor for objects of class MouseHandler
     */
    public MouseHandler()
    {
        // INSTANTIATE listeners list:
        _listeners = new ArrayList<IInputListener>();
    }
    
    // ---------------------------------- Implementation of IInputPublisher ---------------------------------- //
    /**
     * METHOD: initialise the handler by giving it a reference to the input capture source.
     * 
     * @param inputCapture the input capture source (normally via Env3D)
     */
    public void Initialise(IInput inputCapture)
    {
        // ASSIGN inputCapture to _inputCapture:
        _inputCapture = inputCapture;
    }
    
    /**
     * METHOD: Subscribe a new listener for input events.
     * 
     * @param l     a reference to the listener that is being subscribed
     */
    public void subscribe(IInputListener l)
    {
        // ADD a new listener to the listeners List:
        _listeners.add(l);
    }
    
    /**
     * METHOD: unsubscribe a listener.
     * 
     * @param l     a reference to the listener that is being unsubscribed
     */
    public void unsubscribe(IInputListener l)
    {
        // REMOVE a listener from the listeners List:
        _listeners.remove(l);
    }
    
    // ---------------------------------- Implementation of IUpdatable ---------------------------------- //
    
    /**
     * METHOD: update the Mouse state
     */
    public void update()
    {
        // DECLARE an int array to store any mouse input to, call it mouseVal:
        int[] mouseVal;
        
        try
        {
           if (0 == _inputCapture.getMouseClicked()) 
           {
               // STORE mouse pointer position into mouseVal:
               mouseVal = _inputCapture.getMousePointer();
               
               // RAISE event for each listener:
               for(IInputListener l : _listeners)
               {
                   l.onInput(mouseVal);
               }
           }
        }
        catch(Exception e)
        {
            // do nothing as this would occur in the real-time loop
        }
    }
}
