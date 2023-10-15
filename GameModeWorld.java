import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameModeWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameModeWorld extends World
{
    String[] menuItems = {
    "1 vs. 1", "1 vs. AI", "Practice"
    };
    
    /**
     * Constructor for objects of class GameModeWorld.
     * 
     */
    public GameModeWorld()
    {    
        super(GameManager.WORLD_WIDTH, GameManager.WORLD_HEIGHT, 1); 
    
        WorldManager.gameModeWorld = this;
        WorldManager.setBlackBackground(this);
        
        int wWidth = getWidth();
        int wHeight = getHeight();
            
        int moveY = 50;
        int posX = wWidth / 3, posY = wHeight / 2;
        int height = 40, padding = 55;
        
        
        //Vi tilføjer en header text
        GreenfootImage textImage = new GreenfootImage("GAME MODE", 55, new Color(255, 255, 255), new Color(0, 0, 0, 0));
        getBackground().drawImage(textImage, 100, 120);
        
        for (String name : menuItems) {

            Button btn = new Button(name, posX, posY + moveY, posX, height, Color.BLACK, Color.RED, this);
            addObject(btn, wWidth / 2, wHeight / 2 + moveY);
            moveY += padding;
        }
        
        //Lad os tilføje en tilbage knap
        Button btn = new Button("Back", wWidth / 2, wHeight - 25, wWidth / 3, 40, Color.BLACK, Color.RED, this);
        addObject(btn, wWidth / 2, wHeight - 45);
    }
    
    public void act()
    {
        GameManager.checkEscExit(this);
    }
}
