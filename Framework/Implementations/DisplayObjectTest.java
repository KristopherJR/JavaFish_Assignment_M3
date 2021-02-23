package Framework.Implementations;

import Exceptions.*;
import Framework.Interfaces.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class DisplayObjectTest.
 * 
 * This Test class is used to test that DisplayObjectTest function and instantiate correctly.
 * 
 * Based on Dr Marc Price's LionTest from his video lesson on Blackboard. 
 *
 * @author  Kristopher Randle & Marc Price
 * @version 23-02-2021, 0.1
 * 
 * Test Conditions:
 * DisplayObject(): CHECK that a DisplayObject scale is between 0.10 - 0.15 if the object creating the DisplayObject is a JavaFish. If scale is out of range, an OutOfBoundsException should be thrown.
 * DisplayObject(): CHECK that a DisplayObject scale is between 0.225 - 0.45 if the object creating the DisplayObject is NOT a JavaFish. If scale is out of range, an OutOfBoundsException should be thrown.
 * DisplayObject(): CHECK that a DisplayObject can't be initalised with an invalid model. The Model must end in .obj or be a sphere. An InvalidImageFileException should be thrown if it doesn't.
 * DisplayObject(): CHECK that a DisplayObject can't be initalised with an invalid texture. The texture must end in .png or .jpg. An InvalidImageFileException should be thrown if it doesn't.
 */
public class DisplayObjectTest
{
    /**
     * Default constructor for test class JavaFishTest
     */
    public DisplayObjectTest()
    {
    }
    
    // ------------------------------------ JavaScaleTests ------------------------------------ //
    /**
     * TEST CONDITION: CHECK that a DisplayObject scale is between 0.10 - 0.15 if the object creating the DisplayObject is a JavaFish. If scale is out of range, an OutOfBoundsException should be thrown.
     * 
     * Tests:
     * javaScaleTest1: CHECK that an OutOfBoundsException is NOT thrown if 0.10 <= scale <= 0.15 is provided.
     * javaScaleTest2: CHECK that an OutOfBoundsException is thrown if scale < 0.10.
     * javaScaleTest3: CHECK that an OutOfBoundsException is thrown if scale > 0.15.
     */
    @Test
    public void javaScaleTest1()
    {
        try
        {
            // CREATE a new DisplayObject, cast to IDisplayObject. Call it 'd1' and give it a valid scale. Set it so it looks like a JavaFish object created it:
            IDisplayObject d1 = new DisplayObject("models/billboard/billboard.obj","textures/javaFish/JavaFish.png",0.10,true);
        }
        catch(OutOfBoundsException e)
        {
            //FAIL if an OutOfBoundsException is thrown:
            fail("OutOfBoundsException was thrown when scale was in acceptable range.");
        }
    }
    
    @Test
    public void javaScaleTest2()
    {
        //DECLARE a boolean to determine if the test passes or fails, call it pass, set it to false:
        boolean pass = false;
        try
        {
            // CREATE a new DisplayObject, cast to IDisplayObject. Call it 'd1' and give it an invalid scale which is too small. Set it so it looks like a JavaFish object created it:
            IDisplayObject d1 = new DisplayObject("models/billboard/billboard.obj","textures/javaFish/JavaFish.png",0.01,true);
        }
        catch(OutOfBoundsException e)
        {
            //SET pass to true if OutOfBoundsException is thrown:
            pass = true;
        }
        //FAIL if an OutOfBoundsException is NOT thrown:
        assertTrue("OutOfBoundsException was NOT thrown when scale was too small (below 0.10)", pass);
    }
    
    @Test
    public void javaScaleTest3()
    {
        //DECLARE a boolean to determine if the test passes or fails, call it pass, set it to false:
        boolean pass = false;
        try
        {
            // CREATE a new DisplayObject, cast to IDisplayObject. Call it 'd1' and give it an invalid scale which is too big. Set it so it looks like a JavaFish object created it:
            IDisplayObject d1 = new DisplayObject("models/billboard/billboard.obj","textures/javaFish/JavaFish.png",0.3,true);
        }
        catch(OutOfBoundsException e)
        {
            //SET pass to true if OutOfBoundsException is thrown:
            pass = true;
        }
        //FAIL if an OutOfBoundsException is NOT thrown:
        assertTrue("OutOfBoundsException was NOT thrown when scale was too big (above 0.15)", pass);
    }
    
    // ------------------------------------ NonJavaScaleTests ------------------------------------ //
    /**
     * TEST CONDITION: CHECK that a DisplayObject scale is between 0.225 - 0.45 if the object creating the DisplayObject is NOT a JavaFish. If scale is out of range, an OutOfBoundsException should be thrown.
     * 
     * Tests:
     * nonJavaScaleTest1: CHECK that an OutOfBoundsException is NOT thrown if 0.225 <= scale <= 0.45 is provided.
     * nonJavaScaleTest2: CHECK that an OutOfBoundsException is thrown if scale < 0.225.
     * nonJavaScaleTest3: CHECK that an OutOfBoundsException is thrown if scale > 0.45.
     */
    @Test
    public void nonJavaScaleTest1()
    {
        try
        {
            // CREATE a new DisplayObject, cast to IDisplayObject. Call it 'd1' and give it a valid scale. Make it look like a Fish other than a JavaFish created it:
            IDisplayObject d1 = new DisplayObject("models/billboard/billboard.obj","textures/javaFish/JavaFish.png",0.225,false);
        }
        catch(OutOfBoundsException e)
        {
            //FAIL if an OutOfBoundsException is thrown:
            fail("OutOfBoundsException was thrown when scale was in acceptable range.");
        }
    }
    
    @Test
    public void nonJavaScaleTest2()
    {
        //DECLARE a boolean to determine if the test passes or fails, call it pass, set it to false:
        boolean pass = false;
        try
        {
            // CREATE a new DisplayObject, cast to IDisplayObject. Call it 'd1' and give it an invalid scale which is too small. Make it look like a Fish other than a JavaFish created it:
            IDisplayObject d1 = new DisplayObject("models/billboard/billboard.obj","textures/javaFish/JavaFish.png",0.1,false);
        }
        catch(OutOfBoundsException e)
        {
            //SET pass to true if OutOfBoundsException is thrown:
            pass = true;
        }
        //FAIL if an OutOfBoundsException is NOT thrown:
        assertTrue("OutOfBoundsException was NOT thrown when scale was too small (below 0.225)", pass);
    }
    
    @Test
    public void nonJavaScaleTest3()
    {
        //DECLARE a boolean to determine if the test passes or fails, call it pass, set it to false:
        boolean pass = false;
        try
        {
            // CREATE a new DisplayObject, cast to IDisplayObject. Call it 'd1' and give it an invalid scale which is too big. Make it look like a Fish other than a JavaFish created it:
            IDisplayObject d1 = new DisplayObject("models/billboard/billboard.obj","textures/javaFish/JavaFish.png",0.6,false);
        }
        catch(OutOfBoundsException e)
        {
            //SET pass to true if OutOfBoundsException is thrown:
            pass = true;
        }
        //FAIL if an OutOfBoundsException is NOT thrown:
        assertTrue("OutOfBoundsException was NOT thrown when scale was too big (above 0.45)", pass);
    }
    
    // ------------------------------------ ModelTests ------------------------------------ // 
    /**
     * TEST CONDITION: CHECK that a DisplayObject can't be initalised with an invalid model. The model must end in .obj or be a sphere. An InvalidImageFileException should be thrown if it doesn't.
     * 
     * Tests:
     * modelTest1: CHECK that an InvalidImageFileException is NOT thrown if a valid model is provided for the DisplayObject.
     * modelTest2: CHECK that an InvalidImageFileException is thrown if an invalid model is provided for the DisplayObject.
     */
    @Test
    public void modelTest1()
    {
        try
        {
            // CREATE a new DisplayObject, cast to IDisplayObject. Call it 'd1' and give it a valid model.
            IDisplayObject d1 = new DisplayObject("models/billboard/billboard.obj","textures/javaFish/JavaFish.png",0.225,false);
        }
        catch(InvalidImageFileException e)
        {
            //FAIL if an InvalidImageFileException is thrown:
            fail("InvalidImageFileException was thrown when valid model was provided.");
        }
    }
    
    @Test
    public void modelTest2()
    {
        //DECLARE a boolean to determine if the test passes or fails, call it pass, set it to false:
        boolean pass = false;
        try
        {
            // CREATE a new DisplayObject, cast to IDisplayObject. Call it 'd1' and give it an invalid model.
            IDisplayObject d1 = new DisplayObject("cube.ok","textures/javaFish/JavaFish.png",0.225,false);
        }
        catch(InvalidImageFileException e)
        {
            //SET pass to true if InvalidImageFileException is thrown:
            pass = true;
        }
        //FAIL if an InvalidImageFileException is NOT thrown:
        assertTrue("InvalidImageFileException was NOT thrown when model was invalid.", pass);
    }
    
    // ------------------------------------ TextureTests ------------------------------------ //
    /**
     * TEST CONDITION: CHECK that a DisplayObject can't be initalised with an invalid texture. The texture must end in .png or .jpg. An InvalidImageFileException should be thrown if it doesn't.
     * 
     * Tests:
     * textureTest1: CHECK that an InvalidImageFileException is NOT thrown if a valid texture is provided for the DisplayObject.
     * textureTest2: CHECK that an InvalidImageFileException is thrown if an invalid texture is provided for the DisplayObject.
     */
    @Test
    public void textureTest1()
    {
        try
        {
            // CREATE a new DisplayObject, cast to IDisplayObject. Call it 'd1' and give it a valid texture.
            IDisplayObject d1 = new DisplayObject("models/billboard/billboard.obj","textures/javaFish/JavaFish.png",0.225,false);
        }
        catch(InvalidImageFileException e)
        {
            //FAIL if an InvalidImageFileException is thrown:
            fail("InvalidImageFileException was thrown when valid texture was provided.");
        }
    }
    
    @Test
    public void textureTest2()
    {
        //DECLARE a boolean to determine if the test passes or fails, call it pass, set it to false:
        boolean pass = false;
        try
        {
            // CREATE a new DisplayObject, cast to IDisplayObject. Call it 'd1' and give it an invalid texture.
            IDisplayObject d1 = new DisplayObject("models/billboard/billboard.obj","textures/javaFish/JavaFish.sosig",0.225,false);
        }
        catch(InvalidImageFileException e)
        {
            //SET pass to true if InvalidImageFileException is thrown:
            pass = true;
        }
        //FAIL if an InvalidImageFileException is NOT thrown:
        assertTrue("InvalidImageFileException was NOT thrown when texture was invalid.", pass);
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