import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the cure, it will slightly lower the infection percentage if collected.
 * 
 * @author Ryan Hoang
 * @version 1.0
 */
public class Cure extends Actor
{
    /**
     * Act - do whatever the Cure wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
    }
    
    public Cure()
    {
        GreenfootImage myCure = getImage();
        int cureHeight = myCure.getHeight()/10;
        int cureWidth = myCure.getWidth()/10;
        myCure.scale(cureWidth, cureHeight);
    }
    
}
