package Framework.Interfaces;


/**
 * ITokenFactory interface - all implementations can create instances of IUpdatable Tokens to be placed in the aquarium
 * 
 * Based on Chris Blythe's live coding session for week 16.
 * 
 * @author Kristopher Randle, Chris Blythe 
 * @version 0.1, 12-02-2021
 */
public interface ITokenFactory
{
    /**
     * Create a new IUpdatable Token instance and return it.
     * 
     * @param rqdClass      the implementation of type IUpdatable to be instantiated
     * @return              the new IUpdatable instance
     */
    <T extends IUpdatable> IUpdatable create(Class<T> rqdClass) throws Exception;
}
