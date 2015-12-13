import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is a white blood cell. This kind of cell has the job to catch bacteria and
 * remove them from the blood.
 * 
 * @author Michael Kölling
 * @version 0.2
 */
public class WhiteCell extends Actor
{
    int score;
    
    /**
     * Act: move up and down when cursor keys are pressed.
     */
    public void act() 
    {
        checkKeyPress();
        checkCollision();
    }
    
    /**
     * Check whether a keyboard key has been pressed and react if it has.
     */
    private void checkKeyPress()
    {
        if (Greenfoot.isKeyDown("up")) 
        {
            setLocation(getX(), getY()-4);
        }
        
        if (Greenfoot.isKeyDown("down")) 
        {
            setLocation(getX(), getY()+4);
        }
        
        if (Greenfoot.isKeyDown("right")) 
        {
            setLocation(getX()+4, getY());
        }
        
        if (Greenfoot.isKeyDown("left")) 
        {
            setLocation(getX()-4, getY());
        }
    }
    
    /**
     * Check whether we are touching a bacterium or virus. Remove bacteria.
     * Game over if we hit a virus.
     */
    private void checkCollision()
    {
        if (isTouching(Bacteria.class)) 
        {
            Greenfoot.playSound("slurp.wav");
            removeTouching(Bacteria.class);
            score += 20;
            getWorld().showText("Score: " + score, 80, 25);
        }

        if (isTouching(Virus.class)) 
        {
            Greenfoot.playSound("game-over.wav");
            Greenfoot.stop();
        }
    }
}
