package Framework.Implementations;

import UserCode.UserInterfaces.*;
import Exceptions.*;

import Framework.Interfaces.*;
/**
 * The DisplayObject contains all the data for displaying something by a Core instance.
 * 
 * @author Marc Price & Kristopher Randle 
 * @version 23-02-2021, 0.8
 */
public class DisplayObject implements IDisplayObject, IUpdatable, ILocation
{
    // DECLARE a public static final double, call it SCREEN_HEIGHT and set it to 7.0:
    public static final double SCREEN_HEIGHT = 7.0;
    // DECLARE a public static final double, call it SCREEN_WIDTH and set it to 8.0:
    public static final double SCREEN_WIDTH = 8.0;
    
    // Env3d-defined object-specific fields:
    // Reference to the 3D model, called 'model':
    protected String model;
    // Reference to texture-map, called 'texture':
    protected String texture;
  
    // Scale factor applied to model:
    private double scale=1.0;
    // Position in 3D world (x,y,z coordinates):
    private double x=0, y=0, z=1.0;
    // Orientation (about x,y,z):
    private double rotateX=0, rotateY=0, rotateZ=0;
    // Set transparency to true:
    private boolean transparent=true;
   
    //DECLARE a reference to a double, 'velocityX' and 'velocityY':
    private double velocityX;
    private double velocityY;
    /**
     * Constructor for objects of class DisplayObject
     * 
     * @param  model    the path to the file for the model
     * @param  tex      the path to the file for the texture
     * @param  scale    the amount to scale the model by to produce the rqd size object
     * @param  JavaFish a Boolean value used to determine if the creating class is a JavaFish or not
     * 
     * @throws OutOfBoundsException
     */
    public DisplayObject(String model, String tex, double scale, Boolean JavaFish) throws OutOfBoundsException
    {
        // IF the current class setting up the DisplayObject is a JavaFish:
        if(JavaFish == true)
        {
            // CHECK the scale is between the specified range:
            if((scale < 0.10)||(scale > 0.15))
            {
                // IF it's not, throw an OutOfBoundsException:
                throw new OutOfBoundsException("A JavaFish DisplayObject scale must be between 0.10 - 0.15");
            }
            else
            {
                // IF it is, set the DisplayObjects scale to the provided scale parameter:
                this.scale = scale;
            }
        }
        // IF the current class setting up the DisplayObject is NOT a JavaFish:
        else if(JavaFish == false)
        {
            // CHECK the scale is between the specified range:
            if((scale < 0.15)||(scale > 0.45))
            {
                // IF it's not, throw an OutOfBoundsException:
                throw new OutOfBoundsException("A DisplayObject scale must be between 0.225 - 0.45 (1/20th - 1/10th window size)");
            }
            else
            {
                // IF it is, set the DisplayObjects scale to the provided scale parameter:
                this.scale = scale;
            }
        }
        //CALL this DisplayObjects validateModel method, to check that the model provided is a valid model file:
        if(this.validateModel(model) == true)
        {
            // VALIDATE provided String 'model' is a .obj file or 'sphere' before initalising the object with that model.
            this.model = model;
        }
        //CALL this DisplayObjects validateTexture method, to check that the texture provided is a valid image file:
        if(this.validateTexture(tex) == true)
        {
            // VALIDATE provided String 'tex' is a .png or .jpg file before initalising the object with that texture.
            this.texture = tex;
        }
    }
    
    /**
     * METHOD: This method is the aquariums main boundary detection system. DisplayObject uses this to know if objects hit a wall, if they do they will swim in the opposite direction from which they came.
     * 
     * @return  void
     */  
    public void inBounds()
    {
        //CHECK that the Pet has not gone past the right wall, if it has, send it back the other way on the x axis:
        if((this.x >= (SCREEN_WIDTH + 1)))
        {
            this.velocityX = -velocityX; //swim the opposite way.
            this.x = SCREEN_WIDTH + 0.99; //reset it to the edge of the right wall.
            this.rotateY = 90; //face the opposite way. 
        }
        //CHECK that the Pet has not gone past the roof, if it has, send it back the other way on the y axis:
        else if(this.y >= SCREEN_HEIGHT)
        {
            this.velocityY = -velocityY; //swim the opposite way.
            this.y = SCREEN_HEIGHT - 0.1; //reset it to the edge of the roof.
        }
        //CHECK that the Pet has not gone past the left wall, if it has, send it back the other way on the x axis:
        if(this.x <= 1.0)
        {
            this.velocityX = -velocityX; //swim the opposite way.
            this.x = 1.1; //reset it to the edge of the left wall.
            this.rotateY = -90; //face the opposite way.
        }
        //CHECK that the Pet has not gone past the floor, if it has, send it back the other way on the y axis:
        else if(this.y <= 1)
        {
            this.velocityY = -velocityY; //swim the opposite way.
            this.y = 1.1; //reset it to the edge of the floor.
        }
    }
    
    /**
     * METHOD: Used to check that the String model that was provided is an accepted image type.
     * 
     * @param m     The model filepath that was provided on creation of the DisplayObject.
     * 
     * @throws InvalidImageFileException
     *
     * @return      A true boolean value if the instantiated model is valid.
     */
    public boolean validateModel(String m) throws InvalidImageFileException
    {
        //CHECK the model String is an accepted file type:
        if(m.endsWith(".obj") || m.equals("sphere"))
        {
            return true;
        }
        //THROW an InvalidImageFileException if it isn't a valid type:
        else
        {
            throw new InvalidImageFileException("The given Filepath does not present a model ending with .obj.");
        }
    }
    
    /**
     * METHOD: Used to check that the String texture that was provided is an accepted image type.
     * 
     * @param t     The texture filepath that was provided on creation of the DisplayObject.
     * 
     * @throws InvalidImageFileException
     * 
     * @return      A true boolean value if the instantiated texture is valid.
     */
    public boolean validateTexture(String t) throws InvalidImageFileException
    {
        //CHECK the texture String is an accepted file type:
        if(t.endsWith(".png"))
        {
            return true;
        }
        else if(t.endsWith(".jpg"))
        {
            return true;
        }
        //THROW an InvalidImageFileException if it isn't a valid type:
        else
        {     
            throw new InvalidImageFileException("The given Filepath does not present a texture ending with .png or .jpg.");
        }
    }
    
    // ------------------------------ IMPLEMENTATION OF IDisplayObject ------------------------------ //
        /**
     * METHOD: set the IDisplayObject position along x,y,z axes
     * @param x double giving the new position along x axis.
     * @param y double giving the new position along y axis.
     * @param z double giving the new position along z axis.
     */
    public void position(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    /**
     * METHOD: rotate the IDisplayObject about x,y,z axes
     * @param xOrientation double giving the new orientation about x axis.
     * @param yOrientation double giving the new orientation about y axis.
     * @param zOrientation double giving the new orientation about z axis.
     */
    public void orientation(double xOrientation, double yOrientation, double zOrientation)
    {
        this.rotateX = xOrientation;
        this.rotateY = yOrientation;
        this.rotateZ = zOrientation;
    }
    
    // -------------------------- IMPLEMENTATION OF IUpdatable ----------------------
    /**
     * METHOD: change to DisplayObject for next frame, called by Core class on each update
     * 
     */
    public void update()
    {
        this.x += velocityX;
        this.y += velocityY;
    }
    
    // ------------------------------ IMPLEMENTATION OF ILocation ------------------------------ //
    /**
     * Sets the X co-ordinate of this object.
     * 
     * @param x     The new location for this object on its X axis.
     * @return void
     */
    public void setX(double x)
    {
        // SET the DisplayObject's X co-ordinate to the provided parameter:
        this.x = x;
    }
    
    /**
     * Sets the Y co-ordinate of this object.
     * 
     * @param y     The new location for this object on its Y axis.
     * @return void
     */
    public void setY(double y)
    {
        // SET the DisplayObject's Y co-ordinate to the provided parameter:
        this.y = y;
    }
   
    /**
     * Sets the Z co-ordinate of this object.
     * 
     * @param z     The new location for this object on its Z axis.
     * @return void
     */
    public void setZ(double z)
    {
        // SET the DisplayObject's Z co-ordinate to the provided parameter:
        this.z = z;
    }
   
    /**
     * Generates a random position for this object within the bounds of the aquarium.
     * 
     * @return void
     */
    public void GenerateRandomPosition()
    {
        // SET the position of the DisplayObject to a random location (x,y,z) within the bounds of the aquarium:
        this.position((Math.random() * (SCREEN_WIDTH - 1) + 1),(Math.random() * (SCREEN_HEIGHT - 1) + 1),1.0);
    }
   
    /**
     * Gets the X co-ordinate of this object.
     * 
     * @return      The X co-ordinate of this object as a double.
     */
    public double getX()
    {
        // GET the DisplayObject's X co-ordinate and return it:
        return x;
    }
    
    /**
     * Gets the Y co-ordinate of this object.
     * 
     * @return      The Y co-ordinate of this object as a double.
     */
    public double getY()
    {
        // GET the DisplayObject's Y co-ordinate and return it:
        return y;
    }
    
    /**
     * Gets the Z co-ordinate of this object.
     * 
     * @return      The Z co-ordinate of this object as a double.
     */
    public double getZ()
    {
        // GET the DisplayObject's Z co-ordinate and return it:
        return z;
    }
    
    // -------------------------- ACCESSORS ----------------------
    public double getScale()
    {
        return scale;
    }
    
    public double getVelocityX()
    {
        return velocityX;
    }
    
    public double getVelocityY()
    {
        return velocityY;
    }
    
    // -------------------------- MUTATORS ----------------------
    public void setScale(double scale)
    {
        this.scale = scale;
    }
    
    public void setVelocityX(double vx)
    {
        this.velocityX = vx;
    }
    
    public void setVelocityY(double vy)
    {
        this.velocityY = vy;
    }
}
