import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the world, nuff said.
 * 
 * @author Ryan Hoang 
 * @version 1
 */
public class MyWorld extends World
{
    public static final int RESOLUTION = 1;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    
    public static final int SCORE_DISPLAY_TIME = 240;
    
    private int time;
    private int count;
    private GreenfootImage map;
    private int currentMap;
    
    private int[][][] mapData = {
        { {480, 100, 0}, {40, 721, 532}, {12, 400, 560}, {40, 615, 400},    // map 1
          {40, 642, 192}, {16, 128, 113}, {30, 400, 40} },
 
        { {496, 709, 0}, {10, 322, 422}, {40, 700, 241}, {40, 681, 49},     // map 2
          {10, 317, 54}, {50, 90, 174}, {40, 60, 339} },
          
        { {272, 394, 0}, {10, 39, 30}, {30, 71, 476}, {50, 398, 520},       // map 3
          {40, 655, 492} },
    };
    
    private int[] scores;
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        super(WIDTH / RESOLUTION, HEIGHT / RESOLUTION, RESOLUTION);
        
        prepare();
    }
    
    
    /**
     * Jump to the given map number (1..n).
     */
    public void jumpToMap(int map)
    {
        clearWorld();
        currentMap = map-1;
        
    }
    
    
    
    private void clearWorld()
    {
        removeObjects(getObjects(null));
    }
    
    

    private void setTime()
    {
        if(count++ % 60 == 0)
        {
            if(time > 0)
            {
                time++;
            }
        }
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Infected infected = new Infected();
        addObject(infected,72,95);
        Human human = new Human();
        addObject(human,384,292);
        
    }
    
    public void act()
    {
        count++;
        Stats.setScore(1);
        if(count % 600 == 0)
         {
            Infected infected = new Infected();
            addObject(infected, Greenfoot.getRandomNumber(getWidth()), 
                Greenfoot.getRandomNumber(getHeight()));
                
            Cure cure = new Cure();
            addObject(cure, Greenfoot.getRandomNumber(getWidth()), 
                Greenfoot.getRandomNumber(getHeight()));
        } 
    }
    
    public void gameOver() 
    {
        int x=getWidth()/2;
        int y=getHeight()/2;
        int currentScore=Stats.getScore();
        addObject(new ScoreBoard(currentScore),x,y);

    }
}
