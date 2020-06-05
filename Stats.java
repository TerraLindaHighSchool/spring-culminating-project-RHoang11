/**
 * This is a helper class that shows the varying speed, score, and life stats.
 * 
 * @author Ryan Hoang
 * @version 1.0
 */
public class Stats  
{
    private static int score = 60;
    private static int life = 3;
    private static int infection = 0;
    public static int getScore()
    {
        return score;
    }
    public static void setScore(int change)
    {
        score += change;
    }
    public static void revive()
    {
        infection = 50;
    }
    
    public static int getLives()
    {
        return life;
    }
    public static void setLives(int change)
    {
        life += change;
    }
    
    public static int getInfection()
    {
        return infection;
    }
    public static void setInfection(int change)
    {
        infection += change;
    }
}
