package UserCode.UpdatableTokens;

import Exceptions.*;
import Framework.Interfaces.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class PiranhaTest.
 *
 * This Test class is used to test that Piranha's function and instantiate correctly.
 * 
 * Based on Dr Marc Price's LionTest from his video lesson on Blackboard.
 * 
 * @author  Kristopher Randle & Marc Price
 * @version 23-02-2021, 0.2
 * 
 * Test Conditions:
 * Piranha(): CHECK that the speed has been automatically set between 0.005 - 0.05.
 */
public class PiranhaTest
{
    /**
     * Default constructor for test class PiranhaTest
     */
    public PiranhaTest()
    {
    }
    
    // ------------------------------------ VelocityTests ------------------------------------ //
    /**
     * TEST CONDITION: CHECK that the VelocityX has been automatically set between 0.005 - 0.05.
     * 
     * Tests:
     * velocityXTest: Initialise a Piranha, then check that they aren't being initalized with a velocityX outside the range. If they are, test should FAIL.
     */

    @Test
    public void velocityXTest()
    {
        //CREATE a new Piranha, call it p1:
        Piranha p1 = new Piranha();
        try
        {
            //RUN validateSpeed(), which will throw an exception if the speed isn't in the correct range:
            p1.validateSpeed();
        }
        catch(OutOfBoundsException e)
        {
            //FAIL if an OutOfBoundsException is thrown:
            fail(e.getMessage() + " The Piranha's assigned VelocityX was: " + p1.getVelocityX());
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