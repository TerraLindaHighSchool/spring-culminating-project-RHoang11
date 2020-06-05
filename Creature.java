import greenfoot.*; 

/**
 * A creature in a simulation. The creature has a known home. It can move and head towards or away from home.
 * 
 * Movement of the creature is arranged by storing a deltaX/deltaY pair: the offsets in the x/y direction
 * that the creature will move in the next step. The value for these is capped by the SPEED constant: 
 * the delta values will always be in the range [-SPEED..SPEED].
 * 
 * @author Ryan Hoang
 * @version 1.3
 */
public class Creature  extends Actor
{
    /** The maximum movement speed of the ant. */
    private static final int SPEED = 3;

    /** Current movement. Defined as the offset in x and y direction moved in each step. */
    private int deltaX;
    private int deltaY;

    private boolean moved = false;
    private boolean atWater = false;
    
    private static final double WALKING_SPEED = 5.0;

    /**
     * Crtae a new creature with neutral movement (movement speed is zero).
     */
    public Creature()
    {
        deltaX = 0;
        deltaY = 0;
        setRotation(Greenfoot.getRandomNumber(360));
    }
    /**
    
    public void setHomeHill(AntHill homeHill)
    {
        home = homeHill;
    }
    
    
    public AntHill getHomeHill()
    {
        return home;
    }
    */
    /**
     * Walk around randomly (random direction and speed).
     */
    public void randomWalk()
    {
        if (randomChance(50)) {
            deltaX = adjustSpeed(deltaX);
            deltaY = adjustSpeed(deltaY);
        }
        walk();
    }

    /**
     * Try to walk home. Sometimes creatures get distracted or encounter small obstacles, so
     * they occasionally head in a different direction for a moment.
     
    public void walkTowardsHome()
    {
        if(home == null) {
            //if we do not have a home, we can not go there.
            return;
        }
        if (randomChance(2)) {
            randomWalk();  // cannot always walk straight. 2% chance to turn off course.
        }
        else {
            headRoughlyTowards(home);
            walk();
        }
    }
    
    /**
     * Try to walk away from home. (Goes occasionally off course a little.)
     
    public void walkAwayFromHome()
    {
        if(home == null) {
            //if we do not have a home, we can not head away from it.
            return;
        }
        if (randomChance(2)) {
            randomWalk();  // cannot always walk straight. 2% chance to turn off course.
        }
        else {
            headRoughlyTowards(home);   // first head towards home...
            deltaX = -deltaX;           // ...then turn 180 degrees
            deltaY = -deltaY;
            walk();
        }
    }
    */
    /**
     * Adjust the walking direction to head towards the given actor.
     */
    public void headTowards(Actor target)
    {
        deltaX = capSpeed(target.getX() - getX());
        deltaY = capSpeed(target.getY() - getY());
        walk();
    }
    
    /**
     * Walk forward in the current direction with the current speed. 
     * (Does not change direction or speed.)
     */
    public void walk()
    {
        setLocation(getX() + deltaX, getY() + deltaY);
        setRotation((int) (180 * Math.atan2(deltaY, deltaX) / Math.PI));
    }

    /**
     * Adjust the walking direction to head somewhat towards the given co-ordinates. This does not 
     * always head in the same direction. The heading is slightly random (but likely to be somewhat
     * towards the target) to make it look more natural.
     */
    private void headRoughlyTowards(Actor target)
    {
        int distanceX = Math.abs(getX() - target.getX());
        int distanceY = Math.abs(getY() - target.getY());
        boolean moveX = (distanceX > 0) && (Greenfoot.getRandomNumber(distanceX + distanceY) < distanceX);
        boolean moveY = (distanceY > 0) && (Greenfoot.getRandomNumber(distanceX + distanceY) < distanceY);

        deltaX = computeHomeDelta(moveX, getX(), target.getX());
        deltaY = computeHomeDelta(moveY, getY(), target.getY());
    }
    
    /**
     * Compute and return the direction (delta) that we should steer in when
     * we're on our way home.
     */
    private int computeHomeDelta(boolean move, int current, int home)
    {
        if (move) {
            if (current > home)
                return -SPEED;
            else
                return SPEED;
        }
        else {
            return 0;
        }
    }

    /**
     * Adjust the speed randomly (start moving, continue or slow down). The
     * speed returned is in the range [-SPEED .. SPEED].
     */
    private int adjustSpeed(int speed)
    {
        speed = speed + Greenfoot.getRandomNumber(2 * SPEED - 1) - SPEED + 1;
        return capSpeed(speed);
    }

    /**
     * Make sure the speed returned is in the range [-SPEED .. SPEED].
     */
    private int capSpeed(int speed)
    {
        if (speed < -SPEED)
            return -SPEED;
        else if (speed > SPEED)
            return SPEED;
        else
            return speed;
    }

    /**
     * Return 'true' in exactly 'percent' number of calls. That is: a call
     * randomChance(25) has a 25% chance to return true.
     */
    public boolean randomChance(int percent)
    {
        return Greenfoot.getRandomNumber(100) < percent;
    }
    
    /**
     * Turn 'angle' degrees towards the right (clockwise).
     */
    public void turn(int angle)
    {
        setRotation(getRotation() + angle);
    }
    
    /**
     * Return true if we have just seen water in front of us.
     */
    public boolean atWater()
    {
        return atWater;
    }
    
    /**
     * Move forward roughly in the current direction. Sometimes we get a 
     * little off course.
     */
    public void move()
    {
        if(moved)   // can move only once per 'act' round
            return;
            
        // there's a 3% chance that we randomly turn a little off course
        if (randomChance(3)) {
            turn((Greenfoot.getRandomNumber(3) - 1) * 10);
        }

        double angle = Math.toRadians( getRotation() );
        int x = (int) Math.round(getX() + Math.cos(angle) * WALKING_SPEED);
        int y = (int) Math.round(getY() + Math.sin(angle) * WALKING_SPEED);
        
        // now make sure that we are not stepping out of the world
        if (x >= getWorld().getWidth()) {
            x = getWorld().getWidth() - 1;
        }
        if (x < 0) {
            x = 0;
        }
        if (y >= getWorld().getHeight()) {
            y = getWorld().getHeight() - 1;
        }
        if (y < 0) {
            y = 0;
        }
        
        
            
        moved = true;
    }
    
    /**
     * Test if we are close to one of the edges of the world. Return true if we are.
     */
    public boolean atWorldEdge()
    {
        if (getX() < 3 || getX() > getWorld().getWidth() - 3)
            return true;
        if (getY() < 3 || getY() > getWorld().getHeight() - 3)
            return true;
        else
            return false;
    }
    
    public void act()
    {
        moved = false;
    }
    
    
}
