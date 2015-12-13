import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * A piano that can be played with the computer keyboard.
 * 
 * @author: M. Kölling
 * @version: 0.1
 */
public class Piano extends World
{
    private String[] whiteKeys = 
        {"a","b","c","d","e","f","g","h","i","j","k","l"};
    private String[] whiteNotes = 
        {"3c","3d","3e","3f","3g","3a","3b","4c","4d","4e","4f","4g"};    
    /**
     * Make the piano.
     */
    public Piano() 
    {
        super(800, 340, 1);
        makeKeys();
    }
    
    /**
     * Adds keys.
     */
    public void makeKeys()
    {
        //addObject(new Key("g", "3a.wav"), 300, 140);
        //addObject(new Key("f", "3g.wav"), 237, 140);
        int i = 0;
        while (i < 12)
        {
            addObject(new Key(whiteKeys[i], whiteNotes[i] + ".wav"), i*63 + 54, 140);
            i++;
        }
    }
    
}