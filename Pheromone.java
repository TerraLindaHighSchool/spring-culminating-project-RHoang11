import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Pheromones are chemical produced by animals to communicate to other
 * animals mostly through smell.  Humans produce a unique pheromone to 
 * mark a trail that the Infected will use to track humans.
 * 
 * @author Ryan Hoang 
 * @version 1.0
 */
public class Pheromone extends Actor
{
    private GreenfootImage image;
    private final static int MAX_INTENSITY = 180;
    private int intensity;
   
    
    public Pheromone()
    {
        intensity = 180;
        updateImage();
    }
    /**
     * Act - do whatever the Pheromone wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        intensity--;
        if(intensity <= 0)
        {
            getWorld().removeObject(this);
        }
        else
        {
             if ((intensity % 6) == 0)  // prevents updating too often
             { 
                 updateImage();
             }
        }
    }    
    
    public void updateImage()
    {
        int size = intensity / 3 + 5;
        //make base image
        image = new GreenfootImage(size +1, size + 1);
        //make the oval
        image.setColor(new Color(255, 255, 255, intensity / 3));
        image.fillOval(0,0, size, size);
        //make the rectangle
        image.setColor(Color.DARK_GRAY);
        image.fillRect(size/2, size/2, 2, 2);
        setImage(image);
    }
}
