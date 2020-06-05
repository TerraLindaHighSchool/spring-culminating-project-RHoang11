import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Cure here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cure extends Actor
{
    /**
     * Act - do whatever the Cure wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }
    
    public Cure()
    {
        GreenfootImage myCure = getImage();
        int cureHeight = myCure.getHeight()/10;
        int cureWidth = myCure.getWidth()/10;
        myCure.scale(cureWidth, cureHeight);
    }
    
}
