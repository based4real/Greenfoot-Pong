import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameOverWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOverWorld extends World
{
    /**
     * Constructor for objects of class GameOverWorld.
     * 
     */
    public GameOverWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(GameManager.WORLD_WIDTH, GameManager.WORLD_HEIGHT, 1); 
        
        WorldManager.gameOverWorld = this;
        WorldManager.setBlackBackground(this);
        
        int width = getWidth();
        int height = getHeight();
        
        //Try again knap
        Button playBtn = new Button("Try again", width / 2, height - 25, width / 3, 40, Color.BLACK, Color.RED, this);
        addObject(playBtn, width / 2, height - 90);
        
        //Lad os tilføje en tilbage knap
        Button btn = new Button("Back", width / 2, height - 25, width / 3, 40, Color.BLACK, Color.RED, this);
        addObject(btn, width / 2, height - 45);
        
        //Vi tilføjer en header text
        GreenfootImage textImage = new GreenfootImage("GAME OVER", 55, new Color(255, 0, 0), new Color(0, 0, 0, 0));
        getBackground().drawImage(textImage, 110, height / 3);

        GameManager.setGameLevel(1);
        SoundManager.stopMusic();
    }
    
}
