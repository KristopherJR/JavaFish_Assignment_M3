package UserCode;

import Framework.Interfaces.*;

/**
 * ITokenFactory interface - all implementations can create instances of IUpdatable Tokens to be placed in the aquarium
 * 
 * Based on Chris Blythe's live coding session for week 16.
 * 
 * @author Kristopher Randle, Chris Blythe 
 * @version 0.1, 12-02-2021
 */
public class TokenFactory implements ITokenFactory
{
    // instance variables
    
    /**
     * Constructor for objects of class TokenFactory
     */
    public TokenFactory()
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
        // INSTANTIATE new IUpdatable, call it 'newObject':
        IUpdatable newObject = rqdClass.newInstance();
        
        // RETURN new IUpdatable:
        return newObject;
    }
}
