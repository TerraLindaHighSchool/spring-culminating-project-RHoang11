import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    private int time;
    private int count;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    

        super(600, 400, 1); 
        prepare();
    }
    
    public void act()
    {
        count++;
        if (count % 600 == 0)
        {
            addObject(new Infected(), Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400));
        }
    }
    
    /**
     * The method that shows the score, hunger, time, and lives for the player
     */
    private void displayInScene()
    {
        showText("Time: " + time, 530, 20);
        showText("Score: " + count, 340, 20);
        showText("Lives: " + Stats.getLives(), 40, 15);
    }

    private void setTime()
    {
        if(count++ % 60 == 0)
        {
            if(time > 0)
            {
                time++;
                Stats.setScore(-1);
            }
        }
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Human human = new Human();
        addObject(human,401,260);
        human.setLocation(344,227);
        human.setLocation(293,174);
        Infected infected = new Infected();
        addObject(infected,72,95);
    }
}
