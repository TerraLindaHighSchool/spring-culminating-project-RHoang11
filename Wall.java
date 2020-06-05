import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Wall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Wall extends Actor
{
    private GreenfootImage image;
    
    /**
     * Act - do whatever the Wall wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        updateImage();
    }    
    
    
    public void updateImage()
    {
        int size = 50;
        //make base image
        image = new GreenfootImage(size +1, size + 1);
        //make the rectangle
        image.setColor(Color.DARK_GRAY);
        image.fillRect(size/2, size/2, size, size);
        setImage(image);
    }
}
