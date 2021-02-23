package UserCode.UpdatableTokens;

import Exceptions.*;
import Framework.Interfaces.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class UrchinTest.
 *
 * This Test class is used to test that Urchin's function and instantiate correctly.
 * 
 * Based on Dr Marc Price's LionTest from his video lesson on Blackboard.
 * 
 * @author  Kristopher Randle & Marc Price
 * @version 23-02-2021, 0.2
 * 
 * Test Conditions:
 * Urchin(): CHECK that the speed has been automatically set between 0.005 - 0.05.
 */
public class UrchinTest
{
    /**
     * Default constructor for test class UrchinTest
     */
    public UrchinTest()
    {
    }
    
    // ------------------------------------ VelocityTests ------------------------------------ //
    /**
     * TEST CONDITION: CHECK that the VelocityX has been automatically set between 0.005 - 0.05.
     * 
     * Tests:
     * velocityXTest: Initialise a Urchin, then check that they aren't being initalized with a velocityX outside the range. If they are, test should FAIL.
     */

    @Test
    public void velocityXTest()
    {
        //CREATE a new Urchin, call it u1:
        Urchin u1 = new Urchin();
        try
        {
            //RUN validateSpeed(), which will throw an exception if the speed isn't in the correct range:
            u1.validateSpeed();
        }
        catch(OutOfBoundsException e)
        {
            //FAIL if an OutOfBoundsException is thrown:
            fail(e.getMessage() + " The Urchin's assigned VelocityX was: " + u1.getVelocityX());
        } 
    }
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
}