import greenfoot.*;

/**
 * Viruses float across the bloodstream faster than bacteria.
 * 
 * @author Alex Strohmeyer
 * @version 0.1
 */
public class Virus extends Actor
{
    /**
     * Act - The virus moves across the screen faster than bacteria and rotates counterclockwise.
     */
    public void act() 
    {
        setLocation(getX()-4, getY());
        turn(-1);
        
        if (getX() == 0) 
        {
            getWorld().removeObject(this);
        }
    }    
}
