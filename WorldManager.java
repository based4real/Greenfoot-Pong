import greenfoot.*;
/**
 * Write a description of class WorldManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WorldManager  
{
    
    static AIWorld aiWorld;
    static GameModeWorld gameModeWorld;
    static GameOverWorld gameOverWorld;
    static IntroWorld introWorld;
    static PracticeWorld practiceWorld;
    static PvPWorld pvpWorld;
    static StoreWorld storeWorld;
    
    static World lastActiveWorld;
    
    public WorldManager()
    {
    }
    
    public static void setBlackBackground(World world)
    {
        GreenfootImage background = world.getBackground();
        // sort baggrund
        background.setColor(Color.BLACK);
        background.fillRect(0, 0, world.getWidth(), world.getHeight());          
    }
    
    public static void setBackground(World world)
    {
        setBlackBackground(world);
        if (GameManager.getBgPicture() != null)
            world.setBackground(new GreenfootImage(GameManager.getBgPicture()));          
    }

    public static void setAIWorld()
    {
        if (aiWorld != null)
            aiWorld.init();
            
        Greenfoot.setWorld(aiWorld != null ? aiWorld : new AIWorld());
    }
    
    public static void setGameModeWorld()
    {
        Greenfoot.setWorld(gameModeWorld != null ? gameModeWorld : new GameModeWorld());
    }
    
    public static void setGameOverWorld(World lastWorld)
    {
        if (lastActiveWorld != lastWorld)
            lastActiveWorld = lastWorld;
        
        Greenfoot.setWorld(new GameOverWorld());
    }   
    
    public static void setLastActiveGame()
    {
        
        if (lastActiveWorld == practiceWorld)
            setPracticeWorld();
            
        if (lastActiveWorld == pvpWorld)
            setPvPWorld();       
        
        if (lastActiveWorld == aiWorld)
            setAIWorld();
    }   

    public static void setIntroWorld()
    {   
        Greenfoot.setWorld(introWorld != null ? introWorld : new IntroWorld());
    }
 
    public static void setPracticeWorld()
    {
        if (practiceWorld != null)
            practiceWorld.init();
            
        Greenfoot.setWorld(practiceWorld != null ? practiceWorld : new PracticeWorld());
    }
    
    public static void setPvPWorld()
    {
        if (pvpWorld != null)
            pvpWorld.init();
            
        Greenfoot.setWorld(pvpWorld != null ? pvpWorld : new PvPWorld());
    }
    
    public static void setStoreWorld()
    {
        if (storeWorld != null)
            GameManager.updateTextCurrency(storeWorld);
            
        Greenfoot.setWorld(storeWorld != null ? storeWorld : new StoreWorld());
    }    
}
