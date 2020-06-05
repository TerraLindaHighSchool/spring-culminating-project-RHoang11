import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
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
        showMap(currentMap);
    }
    
    /**
     * Set up the start scene.
     */
    private void showMap(int mapNo)
    {
        map = new GreenfootImage("map" + mapNo + ".jpg");
        setBackground(map);
        Counter mapTitle = new Counter(Infected.getAuthorName() + " - Map ", mapNo+1);
        addObject(mapTitle, 200, 20);
        int[][] thisMap = mapData[mapNo];
        for (int i = 1; i < thisMap.length; i++) {
            int[] data = thisMap[i];
            
        }
        int[] shipData = thisMap[0];
        
        
    }
    
    /**
     * Game is over. Stop running, display score.
     */
    public void mapFinished(int time)
    {
        displayScore(time);
        Greenfoot.delay(SCORE_DISPLAY_TIME);
        clearWorld();
        currentMap++;
        if (currentMap < mapData.length) {
            showMap(currentMap);
        }
        else {
            displayFinalScore();
            Greenfoot.stop();
        }
    }
    
    private void displayFinalScore()
    {
        clearWorld();
        ScoreBoard board = new ScoreBoard(Infected.getAuthorName(), "Final score", "Total: ", scores);
        addObject(board, getWidth() / 2, getHeight() / 2);
    }
    
    private void clearWorld()
    {
        removeObjects(getObjects(null));
    }
    
    private void displayScore(int time)
    {
        int points = time;
        scores[currentMap] = points;
        ScoreBoard board = new ScoreBoard(Infected.getAuthorName(), "Map " + (currentMap+1), "Score: ", currentMap, scores);
        addObject(board, getWidth() / 2, getHeight() / 2);
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
}
