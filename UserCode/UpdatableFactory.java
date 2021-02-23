package UserCode;

import Framework.Interfaces.*;
import UserCode.UserInterfaces.*;
/**
 * UpdatableFactory - all implementations can create instances of IUpdatable Tokens to be placed in the aquarium
 * 
 * From Chris Blythe's live coding session for week 16.
 * 
 * @author Chris Blythe 
 * @version 19-02-2021, 0.2
 */
public class UpdatableFactory implements IUpdatableFactory
{    
    /**
     * Constructor for objects of class UpdatableFactory
     */
    public UpdatableFactory()
    {
        
    }
    
    /**
     * Create a new IUpdatable instance and return it.
     * 
     * @param rqdClass      the implementation of type IUpdatable to be instantiated
     * @return              the new IUpdatable instance
     */
    public <T extends IUpdatable> IUpdatable create(Class<T> rqdClass) throws Exception
    {
        IUpdatable newObject = null;
        try
        {
            // INSTANTIATE new IUpdatable, call it 'newObject':
            newObject = rqdClass.newInstance();
        }
        catch(Exception e)
        {
            // PRINT the error message:
            System.out.println(e.getMessage());
        }
        // RETURN new IUpdatable:
        return newObject;
    }
}
