package UserCode.UpdatableTokens;

import Exceptions.*;
import Framework.Interfaces.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class BubbleTest.
 *
 * This Test class is used to test that Bubbles function and instantiate correctly.
 * 
 * Based on Dr Marc Price's LionTest from his video lesson on Blackboard.
 * 
 * @author  Kristopher Randle & Marc Price
 * @version 23-02-2021, 0.2
 * 
 * Test Conditions:
 * setPosition(): CHECK that a Bubble can't be positioned outside of the aquarium.
 * setScale(): CHECK that a Bubble can't have its scale set outside of range 0.025 - 0.1125.
 */
public class BubbleTest
{
    /**
     * Default constructor for test class BubbleTest
     */
    public BubbleTest()
    {
    }
    
    // ------------------------------------ PositionTests ------------------------------------ //
    /**
     * TEST CONDITION: CHECK that a Bubble can't be positioned outside of the aquarium.
     * 
     * Tests:
     * positionTest1: Initialise a Bubble, then check that you can set it's position to a valid location in the aquarium WITHOUT an exception being thrown. Test fails if OutOfBoundsException gets thrown.
     * positionTest2: Initialise a Bubble, then check that you can't set it's position outside the visible area of the aquarium. Test passes if OutOfBoundsException is thrown.
     */

    @Test
    public void positionTest1()
    {
        // CREATE a new Bubble, call it b1:
        Bubble b1 = new Bubble();
        try
        {
            // CALL setPosition() and provide a value that would be inside of the aquariums visible area:
            b1.setPosition(5.0,6.0);
        }
        catch(OutOfBoundsException e)
        {
            //FAIL if an OutOfBoundsException is thrown:
            fail("OutOfBoundsException was thrown when a valid location was provided for the Bubble.");
        } 
    }
    
    @Test
    public void positionTest2()
    {
        //DECLARE a boolean to determine if the test passes or fails, call it pass, set it to false:
        boolean pass = false;
        // CREATE a new Bubble, call it b1:
        Bubble b1 = new Bubble();
        try
        {
            // CALL setPosition() and provide a value that would be outside of the aquariums visible area:
            b1.setPosition(5.0,11.0);
        }
        catch(OutOfBoundsException e)
        {
            //SET pass to true if OutOfBoundsException is thrown:
            pass = true;
        }
        //FAIL if an OutOfBoundsException is NOT thrown:
        assertTrue("OutOfBoundsException was NOT thrown when location was outside of the visible aquarium area.", pass);
    }

    // ------------------------------------ ScaleTests ------------------------------------ //
    /**
     * TEST CONDITION: CHECK that a Bubble can't have its scale set outside of range 0.025 - 0.1125.
     * 
     * Tests:
     * scaleTest1: Initialise a Bubble, then check that you can set it's scale to a valid size WITHOUT an exception being thrown. Test fails if OutOfBoundsException gets thrown.
     * scaleTest2: Initialise a Bubble, then check that you can't set it's scale outside of the allowed range. Test passes if OutOfBoundsException is thrown.
     */
    @Test
    public void scaleTest1()
    {
        // CREATE a new Bubble, call it b1:
        Bubble b1 = new Bubble();
        try
        {
            // CALL setScale() and provide it a valid scale:
            b1.setScale(0.025);
        }
        catch(OutOfBoundsException e)
        {
            //FAIL if an OutOfBoundsException is thrown:
            fail("OutOfBoundsException was thrown when a valid scale was provided for the Bubble.");
        } 
    }
    
    @Test
    public void scaleTest2()
    {
        //DECLARE a boolean to determine if the test passes or fails, call it pass, set it to false:
        boolean pass = false;
        // CREATE a new Bubble, call it b1:
        Bubble b1 = new Bubble();
        try
        {
            // CALL setScale() and provide it an invalid scale:
            b1.setScale(0.001);
        }
        catch(OutOfBoundsException e)
        {
            //SET pass to true if OutOfBoundsException is thrown:
            pass = true;
        }
        //FAIL if an OutOfBoundsException is NOT thrown:
        assertTrue("OutOfBoundsException was NOT thrown when scale was outside of the allowed range.", pass);
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