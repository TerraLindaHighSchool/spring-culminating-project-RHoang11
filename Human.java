import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Human here.
 * 
 * @author Ryan Hoang 
 * @version 3.9
 */
public class Human extends Actor
{
    private final int MAX_PH_AVAILABLE = 15;
    private int phAvailable;
    private boolean walk;
    
    private int time = 120;
    private int count;
    
    GreenfootImage image1 = new GreenfootImage("PersonUp.png");
    GreenfootImage image2 = new GreenfootImage("PersonDown.png");
    GreenfootImage image3 = new GreenfootImage("PersonLeft.png");
    GreenfootImage image4 = new GreenfootImage("PersonRight.png");
    
    //checks which direction you are moving
    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;
    
    
    
    public void updateImage()
    {
        int image1Height = (int)image1.getHeight()/25;
        int image1Width = (int)image1.getWidth()/25;
        image1.scale(48, 48);
        
        
        image2.scale(48, 48);
        
        
        image3.scale(48, 48);
        
        
        image4.scale(48, 48);
    }
    
    /**
     * Act - do whatever the Human wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        //creates image of the Human
        updateImage();
        //makes sure that the "sound" is handled when the human is running
        isMakingSound();
        
        //makes sure that we can move and that you take can collide with things
        checkKeyPress();
        checkCollisions();
        
        //shows time and sets the stats on the screen
        setTime();
        displayInScene();
        endGame();
    }    
    
    public Human()
    {
        phAvailable = MAX_PH_AVAILABLE;
        setImage(image1);
    }
    
    /**
     * Allows for the infected to hunt the human
     */
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
            setImage(image1);
            up = true;
        }
        else
        {
            up = false;
        }
        
        if (Greenfoot.isKeyDown("down") && !walk) 
        {
            setLocation(getX(), getY()+4);
            setImage(image2);
            down = true;
        }
        else
        {
            down = false;
        }
        
        if (Greenfoot.isKeyDown("right") && !walk) 
        {
            setLocation(getX()+4, getY());
            setImage(image4);
            right = true;
        }
        else
        {
            right = false;
        }
        
        if (Greenfoot.isKeyDown("left") && !walk) 
        {
            setLocation(getX()-4, getY());
            setImage(image3);
            left = true;
        }
        else
        {
            left = false;
        }
        
        if (Greenfoot.isKeyDown("up") && walk) 
        {
            setLocation(getX(), getY()-2);
            setImage(image1);
            left = true;
        }
        else
        {
            left = false;
        }
        
        if (Greenfoot.isKeyDown("down") && walk) 
        {
            setLocation(getX(), getY()+2);
            setImage(image2);
            left = true;
        }
        else
        {
            left = false;
        }
        
        if (Greenfoot.isKeyDown("right") && walk) 
        {
            setLocation(getX()+2, getY());
            setImage(image4);
            left = true;
        }
        else
        {
            left = false;
        }
        
        if (Greenfoot.isKeyDown("left") && walk) 
        {
            setLocation(getX()-2, getY());
            setImage(image3);
            left = true;
        }
        else
        {
            left = false;
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
            handlePheromoneDrop();
            walk = false;
        }
    }
    
    public void checkCollisions()
    {
        if(isTouching(Infected.class))
        {
             Stats.setInfection(25);
             setLocation(Greenfoot.getRandomNumber(800), Greenfoot.getRandomNumber(600));
        }
        
        Cure cure = (Cure) getOneIntersectingObject(Cure.class);
        if(cure != null)
        {
            Stats.setInfection(-15);
            removeTouching(Cure.class);
        }
        
        if(isTouching(Wall.class) && (up) && walk)
        {
            setLocation(getX(), getY()+4);
        }
        
        if(isTouching(Wall.class) && (down) && walk)
        {
            setLocation(getX(), getY()-4);
        }
        
        if(isTouching(Wall.class) && (left) && walk)
        {
            setLocation(getX()+4, getY());
        }
        
        if(isTouching(Wall.class) && (right) && walk)
        {
            setLocation(getX()-4, getY());
        }
        
        if(isTouching(Wall.class) && (up) && walk)
        {
            setLocation(getX(), getY()+4);
        }
        
        if(isTouching(Wall.class) && (down) && walk)
        {
            setLocation(getX(), getY()-4);
        }
        
        if(isTouching(Wall.class) && (left) && walk)
        {
            setLocation(getX()+4, getY());
        }
        
        if(isTouching(Wall.class) && (right) && walk)
        {
            setLocation(getX()-4, getY());
        }
    }
    
    /**
     * The method that shows the score, hunger, time, and lives for the player
     */
    private void displayInScene()
    {
        getWorld( ).showText("Infection: " + Stats.getInfection(), 90, 35);
        getWorld( ).showText("Time: " + time, 710, 20);
        getWorld( ).showText("Score: " + count, 340, 20);
        getWorld( ).showText("Lives: " + Stats.getLives(), 40, 15);
    }
    
    /**
     * This is how the amount of time is shown in the method.
     * 
     */
    private void setTime()
    {
        if(count++ % 50 == 0)
        {
            if(time > 0)
            {
                time--;
            }
        }
    }
    
    /**
     * The method that describes the end of the game.
     */
    private void endGame()
    {
        if (Stats.getInfection() >= 100)
        {
            Stats.setLives(-1);
            Stats.revive();
            Greenfoot.playSound("lostLive.wav");
        }
        if (Stats.getLives() <= 0)
        {
            getWorld().showText("YOU LOSE", 390, 180);
            Greenfoot.stop();
            Greenfoot.playSound("game-over.wav");
        }
    }
}
