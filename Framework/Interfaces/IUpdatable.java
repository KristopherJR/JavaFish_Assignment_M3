package Framework.Interfaces;


/**
 * Any object that needs to be updated in an update loop should implement IUpdatable.
 * 
 * @author Marc Price & Kristopher Randle
 * @version 0.6
 */
public interface IUpdatable
{
    /**
     * METHOD: change to IUpdatable for next frame
     */
    void update();
    
    IDisplayObject getDisplayObject();
}
