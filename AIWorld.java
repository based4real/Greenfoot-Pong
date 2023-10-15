import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AIWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AIWorld extends World
{
    /**
     * Constructor for objects of class AIWorld.
     * 
     */
    public AIWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(GameManager.WORLD_WIDTH, GameManager.WORLD_HEIGHT, 1); 
        
        WorldManager.aiWorld = this;
        
        init();
    }
    
    public void init()
    {
        //Updates til køb i shoppen
        WorldManager.setBackground(this);
        
        int width = getWidth();
        int height = getHeight();
        
        addObject(new Ball(this), width/2, height/2);
        addObject(new Paddle(100,20, true, false, false), width/2, height - 50); //Player
        // addObject(new Paddle(100,20, false, false), 60, 50); //Enemy
        addObject(new Paddle(100,20, false, true, false), width/2, 50); //Bot
        
        DisplayText text = GameManager.textValueAssign();
        
        GameManager.updateTextCurrencyGame(this);
        
        addObject(text, GameManager.textWidth, GameManager.textHeight);       
    }
    
    public void act()
    {
        //Music looper, husk at tilføje at musikken skal stoppe når man dør med "music.stop();"
        SoundManager.playMusic();
        
        GameManager.checkEscExit(this);
    }
    
    public int getCenterX()
    {
        return getWidth() / 2;
    }
}
