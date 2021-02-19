package Framework.Implementations;

import UserCode.UserInterfaces.*;

import Framework.Interfaces.*;
/**
 * The DisplayObject contains all the data for displaying something by a Core instance.
 * 
 * @author Marc Price & Kristopher Randle 
 * @version 0.7
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
     */
    public DisplayObject(String model, String tex, double scale)
    {
        //SET the 'model', 'texture' and 'scale' to the provided parameters:
        this.model = model;
        this.texture = tex;
        this.scale = scale;
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
