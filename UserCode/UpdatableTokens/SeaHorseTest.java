package UserCode.UpdatableTokens;

import Exceptions.*;
import Framework.Interfaces.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class SeaHorseTest.
 *
 * This Test class is used to test that SeaHorse's function and instantiate correctly.
 * 
 * Based on Dr Marc Price's LionTest from his video lesson on Blackboard.
 * 
 * @author  Kristopher Randle & Marc Price
 * @version 23-02-2021, 0.2
 * 
 * Test Conditions:
 * SeaHorse(): CHECK that the speed has been automatically set between 0.005 - 0.05.
 */
public class SeaHorseTest
{
    /**
     * Default constructor for test class SeaHorseTest
     */
    public SeaHorseTest()
    {
    }
    
    // ------------------------------------ VelocityTests ------------------------------------ //
    /**
     * TEST CONDITION: CHECK that the VelocityX and VelocityY have been automatically set between 0.005 - 0.05.
     * 
     * Tests:
     * velocityXTest: Initialise a SeaHorse, then check that they aren't being initalized with a velocityX outside the range. If they are, test should FAIL.
     * velocityYTest: Initialise a SeaHorse, then check that they aren't being initalized with a velocityY outside the range. If they are, test should FAIL.
     */

    @Test
    public void velocityXTest()
    {
        //CREATE a new SeaHorse, call it sh1:
        SeaHorse sh1 = new SeaHorse();
        try
        {
            //RUN validateSpeed(), which will throw an exception if the speed isn't in the correct range:
            sh1.validateSpeed();
        }
        catch(OutOfBoundsException e)
        {
            //FAIL if an OutOfBoundsException is thrown:
            fail(e.getMessage() + " The SeaHorse's assigned VelocityX was: " + sh1.getVelocityX());
        } 
    }
    
    @Test
    public void velocityYTest()
    {
        //CREATE a new SeaHorse, call it sh1:
        SeaHorse sh1 = new SeaHorse();
        try
        {
            //RUN validateSpeed(), which will throw an exception if the speed isn't in the correct range:
            sh1.validateSpeed();
        }
        catch(OutOfBoundsException e)
        {
            //FAIL if an OutOfBoundsException is thrown:
            fail(e.getMessage() + " The SeaHorse's assigned VelocityY was: " + sh1.getVelocityY());
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