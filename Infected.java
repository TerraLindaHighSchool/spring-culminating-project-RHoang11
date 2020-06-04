import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Infected are people that have gotten the Coronavirus. If they touch the
 * human, they infect them and if the virus is not cleansed, the human will get
 * Coronavirus and the game ends.
 * 
 * @author Ryan Hoang 
 * @version 0
 */
public class Infected extends Creature
{
    private final int MAX_PH_AVAILABLE = 16;
    private final int TIME_FOLLOWING_TRAIL = 30;
    private int phAvailable;
    private int followTrailTimeRemaining;
    
    public Infected()
    {
        phAvailable = MAX_PH_AVAILABLE;
        followTrailTimeRemaining = 0;
    }
    
    /**
     * Act - do whatever the Infected wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        searchForHuman();
        smellsPheromone();
    }    
    
    private void searchForHuman()
    {
        if(followTrailTimeRemaining == 0)
        {
            if(smellsPheromone())
            {
                walkTowardsPheromoneCenter();
            }
            randomWalk();
        }
        else
        {
            followTrailTimeRemaining--;
        }
        randomWalk();
    }
    
    private boolean smellsPheromone()
    {
        if(isTouching(Pheromone.class))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    private void walkTowardsPheromoneCenter()
    {
        Actor ph = getOneIntersectingObject(Pheromone.class);
        if(ph != null)
        {
            headTowards(ph);
        }
        if(getX() == ph.getX() && getY() == ph.getY())
        {
            followTrailTimeRemaining = TIME_FOLLOWING_TRAIL;
        }
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
}
