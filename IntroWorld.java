import greenfoot.*;

/**
 * Write a description of class IntroWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IntroWorld extends World
{

    String[] menuItems = {
    "Play", "Store", "Cheats",
    };
    
    /**
     * Constructor for objects of class IntroWorld.
     */
    public IntroWorld()
    {
        super(GameManager.WORLD_WIDTH, GameManager.WORLD_HEIGHT, 1); 
        
        WorldManager.introWorld = this;
        WorldManager.setBlackBackground(this);
        
        int wWidth = getWidth();
        int wHeight = getHeight();
        
        int moveY = 50;
        int posX = wWidth / 3, posY = wHeight / 2;
        int height = 40, padding = 55;
        
        //Vi tilføjer en header text
        GreenfootImage textImage = new GreenfootImage("PING GAME", 55, new Color(255, 255, 255), new Color(0, 0, 0, 0));
        getBackground().drawImage(textImage, 125, 120);
        
        for (String name : menuItems) {

            Button btn = new Button(name, posX, posY + moveY, posX, height, Color.BLACK, Color.RED, this);
            addObject(btn, wWidth / 2, wHeight / 2 + moveY);
            moveY += padding;
        }
    }
    
}
