package UserCode.UpdatableTokens;

import Exceptions.*;
import Framework.Interfaces.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class JavaFishTest.
 *
 * This Test class is used to test that JavaFish function and instantiate correctly.
 * 
 * Based on Dr Marc Price's LionTest from his video lesson on Blackboard.
 * 
 * @author  Kristopher Randle & Marc Price
 * @version 23-02-2021, 0.2
 * 
 * Test Conditions:
 * JavaFish(): CHECK that the speed has been automatically set between 0.005 - 0.05.
 */
public class JavaFishTest
{
    /**
     * Default constructor for test class JavaFishTest
     */
    public JavaFishTest()
    {
    }
    
    // ------------------------------------ VelocityTests ------------------------------------ //
    /**
     * TEST CONDITION: CHECK that the VelocityX has been automatically set between 0.005 - 0.05.
     * 
     * Tests:
     * velocityXTest: Initialise a JavaFish, then check that they aren't being initalized with a velocityX outside the range. If they are, test should FAIL.
     */

    @Test
    public void velocityXTest()
    {
        //CREATE a new JavaFish, call it j1:
        JavaFish j1 = new JavaFish();
        try
        {
            //RUN validateSpeed(), which will throw an exception if the speed isn't in the correct range:
            j1.validateSpeed();
        }
        catch(OutOfBoundsException e)
        {
            //FAIL if an OutOfBoundsException is thrown:
            fail(e.getMessage() + " The JavaFish's assigned VelocityX was: " + j1.getVelocityX());
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