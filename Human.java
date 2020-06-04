import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Human here.
 * 
 * @author Ryan Hoang 
 * @version 0.0
 */
public class Human extends Actor
{
    private final int MAX_PH_AVAILABLE = 15;
    private int phAvailable;
    private boolean walk;
    
    public Human()
    {
        phAvailable = MAX_PH_AVAILABLE;
    }
    
    /**
     * Act - do whatever the Human wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        isMakingSound();
        checkKeyPress();
    }    
    
    
    private void handlePheromoneDrop()
    {
        if(phAvailable == MAX_PH_AVAILABLE)
        {
            Pheromone pheromone = new Pheromone();
            getWorld().addObject(pheromone,getX(),getY());
            phAvailable = 0;
        }
        else
        {
            phAvailable++;
        }
    }
    
    /**
     * Check whether a keyboard key has been pressed and react if it has.
     */
    private void checkKeyPress()
    {
        if (Greenfoot.isKeyDown("up") && !walk) 
        {
            setLocation(getX(), getY()-4);
        }
        if (Greenfoot.isKeyDown("down") && !walk) 
        {
            setLocation(getX(), getY()+4);
        }
        if (Greenfoot.isKeyDown("right") && !walk) 
        {
            setLocation(getX()+4, getY());
        }
        if (Greenfoot.isKeyDown("left") && !walk) 
        {
            setLocation(getX()-4, getY());
        }
        if (Greenfoot.isKeyDown("up") && walk) 
        {
            setLocation(getX(), getY()-2);
        }
        if (Greenfoot.isKeyDown("down") && walk) 
        {
            setLocation(getX(), getY()+2);
        }
        if (Greenfoot.isKeyDown("right") && walk) 
        {
            setLocation(getX()+2, getY());
        }
        if (Greenfoot.isKeyDown("left") && walk) 
        {
            setLocation(getX()-2, getY());
        }
    }
    
    private void isMakingSound()
    {
        if(Greenfoot.isKeyDown("shift"))
        {
            walk = true;
            
        }
        else
        {
            walk = false;
            handlePheromoneDrop();
        }
    }
}
