import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Infected are people that have gotten the Coronavirus. If they touch the
 * human, they infect them and if the virus is not cleansed, the human will get
 * infected and the human will lose a life. They are attracted to sounds and
 * will go to the nearest sound if the human makes it. Otherwise they roam
 * aimlessly.
 * 
 * @author Ryan Hoang 
 * @version 1.2
 */
public class Infected extends Creature
{
    private final int MAX_PH_AVAILABLE = 16;
    private final int TIME_FOLLOWING_TRAIL = 30;
    private int phAvailable;
    private int followTrailTimeRemaining;
    private boolean followingHuman;
    
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
    }
    
    private void searchForHuman()
    {
        if(followTrailTimeRemaining == 0)
        {
            if(smellsPheromone())
            {
                walkTowardsPheromoneCenter();
                followingHuman = true;
            }
            randomWalk();
        }
        else
        {
            followTrailTimeRemaining--;
            walk();
        }
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
    
    /**
     * This method specifies the name of the author (for display on the result board).
     */
    public static String getAuthorName()
    {
        return "Ryan Hoang";  // write your name here!
    }
    
    private void status()
    {
        if(followingHuman == true)
        {
            //handlePheromoneDrop();
            walkTowardsPheromoneCenter();
            Actor ph = getOneIntersectingObject(Pheromone.class);
            if(ph == null)
            {
                followingHuman = false;
            }
        }
        else
        {
            searchForHuman();
        }
    }
}
