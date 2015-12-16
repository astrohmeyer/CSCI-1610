import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.util.List;

/**
 * A 'Body' is any kind of object in space that has a mass. It could be a star, or a planet, 
 * or anything else that floats around in space.
 * 
 * @author Michael Kšlling 
 *   (Including improvements by J. Buhl.)
 * @version 1.2
 */
public class Body extends SmoothMover
{
    private static final double GRAVITY = 5.2; //7.8 original
    private static final Color defaultColor = new Color(255, 216, 0);
    
    private int size;
    private boolean contact = false;
    private double mass;
    
    /**
     * Construct a Body with default size, mass, velocity and color.
     */
    public Body()
    {
        this (20, 300, new Vector(0, 0.0), defaultColor);
    }
    
    /**
     * Construct a Body with a specified size, mass, velocity and color.
     */
    public Body(int size, double mass, Vector velocity, Color color)
    {
        this.mass = mass;
        this.size = size;
        addToVelocity(velocity);
        GreenfootImage image = new GreenfootImage (size, size);
        image.setColor (color);
        image.fillOval (0, 0, size-1, size-1);
        setImage (image);
    }
    
    /**
     * Act. That is: apply  the gravitation forces from
     * all other bodies around, and then move.
     */
    public void act() 
    {
        applyForces();
        move();
        
        if (isAtEdge() == true)
        {
            changeColor();
        }
        checkCollision();
        bounceAtEdge();
    }
    
    /**
     * Check whether we have hit the edge of the universe. If so, bounce off it.
     */
    private void bounceAtEdge()
    {
        if (getX() == 0 || getX() == getWorld().getWidth()-1) {
            setLocation((double)getX(), (double)getY());
            invertHorizontalVelocity();
            accelerate(0.9);
        }
        else if (getY() == 0 || getY() == getWorld().getHeight()-1) {
            setLocation((double)getX(), (double)getY());
            invertVerticalVelocity();
            accelerate(0.9);
        }
    }
    
    /**
     * Apply the forces of gravity from all other celestial bodies in this universe.
     */
    private void applyForces()
    {
        List<Body> bodies = getWorld().getObjects(Body.class);
        
        for (Body body : bodies) 
        {
            if (body != this) 
            {
                applyGravity(body);
            }
        }
        
        // ensure that we don't get too fast: If the current speed is very fast, decelerate a bit.
        if (getSpeed() > 5) 
        {
            accelerate (0.9);  // acceleration with factor < 1 is actually slowing down.
        }
    }
    
    /**
     * Apply the force of gravity of a given body to this one.
     * (Force is applied for one time unit; dt==1.)
     */
    private void applyGravity(Body other)
    {
        double dx = other.getExactX() - this.getExactX();
        double dy = other.getExactY() - this.getExactY();
        Vector dv = new Vector (dx, dy); //sets direction correctly; length still invalid
        double distance = Math.sqrt (dx*dx + dy*dy);
        double force = GRAVITY * this.mass * other.mass / (distance * distance);
        double acceleration = force / this.mass;
        dv.setLength (acceleration);
        addToVelocity (dv);
    }
    
    /**
     * Return the mass of this body.
     */
    public double getMass()
    {
        return mass;
    }
    
    /**
     * Change color at edge
     */
    private void changeColor()
    {
        int r =  Greenfoot.getRandomNumber(255);
        int g =  Greenfoot.getRandomNumber(255);
        int b =  Greenfoot.getRandomNumber(255);
        
        GreenfootImage newImage = getImage();
        newImage.setColor(new Color(r, g, b));
        newImage.fillOval (0, 0, size-1, size-1);
        
        setImage (newImage);
    }
    
    /**
     * changes color on collision
     */
    private void checkCollision()
    {
        if (isTouching(Obstacle.class) && contact == false)
        {
            changeColor();
            contact = true;
        }
        if (!isTouching(Obstacle.class) && contact == true)
        {
            contact = false;
        }
        
    }
}
