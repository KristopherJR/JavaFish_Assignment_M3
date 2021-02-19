package UserCode.UserInterfaces;

import Framework.Interfaces.*;
/**
 * IUpdatableFactory interface - all implementations can create instances of IUpdatable Tokens to be placed in the aquarium
 * 
 * From Chris Blythe's live coding session for week 16.
 * 
 * @author Chris Blythe 
 * @version 19-02-2021, 0.2
 */
public interface IUpdatableFactory
{
    /**
     * Create a new IUpdatable Token instance and return it.
     * 
     * @param rqdClass      the implementation of type IUpdatable to be instantiated
     * @return              the new IUpdatable instance
     */
    <T extends IUpdatable> IUpdatable create(Class<T> rqdClass) throws Exception;
}
