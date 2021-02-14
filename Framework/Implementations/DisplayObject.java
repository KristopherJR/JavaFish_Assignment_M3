package Framework.Implementations;

import Framework.Interfaces.*;


/**
 * The DisplayObject contains all the data for displaying something by a Core instance.
 * 
 * @author (Marc Price) 
 * @version 0.6
 */
public class DisplayObject implements IDisplayObject, IUpdatable
{
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
    
    // DECLARE a public static final double, call it SCREEN_HEIGHT and set it to 7.0:
    public static final double SCREEN_HEIGHT = 7.0;
    // DECLARE a public static final double, call it SCREEN_WIDTH and set it to 8.5:
    public static final double SCREEN_WIDTH = 8.0;
    
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
        else if(this.y <= 0)
        {
            this.velocityY = -velocityY; //swim the opposite way.
            this.y = 0.1; //reset it to the edge of the floor.
        }
    }
    
    // ------------------ IMPLEMENTATION OF IDisplayObject --------------------//
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
    
    public IDisplayObject getDisplayObject()
    {
        return this;
    }
    // -------------------------- ACCESSORS ----------------------
    public double getScale()
    {
        return scale;
    }
    
    public double getX()
    {
        return x;
    }
    
    public double getY()
    {
        return y;
    }
    
    public double getZ()
    {
        return z;
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
    
    public void setPosition(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public void setX(double x)
    {
        this.x = x;
    }
    
    public void setY(double y)
    {
        this.y = y;
    }
    
    public void setZ(double z)
    {
        this.z = z;
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
