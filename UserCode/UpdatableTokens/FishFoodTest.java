package UserCode.UpdatableTokens;

import Exceptions.*;
import Framework.Interfaces.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class FishFoodTest.
 *
 * This Test class is used to test that FishFood function and instantiate correctly.
 * 
 * Based on Dr Marc Price's LionTest from his video lesson on Blackboard.
 * 
 * @author  Kristopher Randle & Marc Price
 * @version 23-02-2021, 0.1
 * 
 * Test Conditions:
 * setDisplayObjectPosition(): CHECK that a FishFood can't be positioned outside of the aquarium.
 */
public class FishFoodTest
{
    /**
     * Default constructor for test class FishFoodTest
     */
    public FishFoodTest()
    {
    }
    
    // ------------------------------------ PositionTests ------------------------------------ //
    /**
     * TEST CONDITION: CHECK that a FishFood can't be positioned outside of the aquarium.
     * 
     * Tests:
     * positionTest1: Initialise a FishFood, then check that you can set it's position to a valid location in the aquarium WITHOUT an exception being thrown. Test fails if OutOfBoundsException gets thrown.
     * positionTest2: Initialise a FishFood, then check that you can't set it's position outside the visible area of the aquarium. Test passes if OutOfBoundsException is thrown.
     */

    @Test
    public void positionTest1()
    {
        // CREATE a new FishFood, call it ff1:
        FishFood ff1 = new FishFood();
        try
        {
            // CALL setDisplayObjectPosition() and provide a value that would be inside of the aquariums visible area:
            ff1.setDisplayObjectPosition(5.0,6.0,1.0);
        }
        catch(OutOfBoundsException e)
        {
            //FAIL if an OutOfBoundsException is thrown:
            fail("OutOfBoundsException was thrown when a valid location was provided for the FishFood.");
        } 
    }
    
    @Test
    public void positionTest2()
    {
        //DECLARE a boolean to determine if the test passes or fails, call it pass, set it to false:
        boolean pass = false;
        // CREATE a new FishFood, call it ff1:
        FishFood ff1 = new FishFood();
        try
        {
            // CALL setDisplayObjectPosition() and provide a value that would be outside of the aquariums visible area:
            ff1.setDisplayObjectPosition(5.0,11.0,1.0);
        }
        catch(OutOfBoundsException e)
        {
            //SET pass to true if OutOfBoundsException is thrown:
            pass = true;
        }
        //FAIL if an OutOfBoundsException is NOT thrown:
        assertTrue("OutOfBoundsException was NOT thrown when location was outside of the visible aquarium area.", pass);
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