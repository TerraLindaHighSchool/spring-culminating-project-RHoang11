import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the title screen. Pressing space will start the game.
 * 
 * @author Ryan Hoang
 * @version 1.0
 */
public class TitleScreen extends World
{

    /**
     * Constructor for objects of class TitleScreen.
     * 
     */
    public TitleScreen()
    {    
        super(800, 600, 1); 
        prepare();
    }
    
    private void prepare()
    {
        TitleLetters titleLetters = new TitleLetters();
        addObject(titleLetters,400,300);
    }
    
    public void act()
    {
        if (Greenfoot.isKeyDown("space"))
        Greenfoot.setWorld(new MyWorld());
    }
}
