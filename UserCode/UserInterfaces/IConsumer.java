package UserCode.UserInterfaces;

import Framework.Interfaces.*;
/**
 * IConsumer Interface - Provides functionality to allow implementations to 'consume' other tokens of type IDisplayObject:
 * 
 * @author Kristopher Randle
 * @version 15-02-2021, 0.1
 */
public interface IConsumer
{
    /**
     * 'Consumes' an IDisplayObject, removing it from the simulation and increasing the consumers size.
     * 
     * @param victim        the unfortunate IDisplayObject to be eaten.
     * @return void
     */
    void consume(IDisplayObject victim);
}
