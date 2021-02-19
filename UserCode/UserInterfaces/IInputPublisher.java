package UserCode.UserInterfaces;

import Framework.Interfaces.IInput;
/**
 * IInputPublisher: publishes input events to all listeners.
 * 
 * @author  Marc Price
 * @version 0.1
 */
public interface IInputPublisher
{
    /**
     * METHOD: initialise the handler by giving it a reference to the input capture source.
     * 
     * @param inputCapture the input capture source (normally via Env3D)
     */
    void Initialise(IInput inputCapture);
    
    /**
     * METHOD: Subscribe a new listener for input events.
     * 
     * @param l     a reference to the listener that is being subscribed
     */
    void subscribe(IInputListener l);
    
    /**
     * METHOD: unsubscribe a listener.
     * 
     * @param l     a reference to the listener that is being unsubscribed
     */
    void unsubscribe(IInputListener l);
}