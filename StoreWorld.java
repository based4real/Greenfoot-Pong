import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StoreWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StoreWorld extends World
{
    /**
     * Constructor for objects of class StoreWorld.
     * 
     */
    public StoreWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(GameManager.WORLD_WIDTH, GameManager.WORLD_HEIGHT, 1);
        
        WorldManager.storeWorld = this;
        WorldManager.setBlackBackground(this);
        
        int width = getWidth();
        int height = getHeight();
    
        //Lad os tilføje en tilbage knap i butikken
        Button btn = new Button("Back", width / 2, height - 25, width / 3, 40, Color.BLACK, Color.RED, this);
        addObject(btn, width / 2, height - 45);
        
        //Vi tilføjer en header text
        GreenfootImage textImage = new GreenfootImage("PING SHOP", 55, new Color(255, 255, 255), new Color(0, 0, 0, 0));
        getBackground().drawImage(textImage, 130, 45);
        
        GameManager.updateTextCurrency(this);
        
        //Det generelle butikssystem
        addObject(new StoreSystem(this), 0, 0);
    }
    
    
    public void act()
    {
        GameManager.checkEscExit(this);
    }
}
