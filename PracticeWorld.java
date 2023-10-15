import greenfoot.*;


/**
 * The Ping World is where Balls and Paddles meet to play pong.
 * 
 * @author The teachers 
 * @version 1
 */
public class PracticeWorld extends World
{
    /**
     * Constructor for objects of class PingWorld.
     */
    public PracticeWorld()
    {
        super(GameManager.WORLD_WIDTH, GameManager.WORLD_HEIGHT, 1);
        
        WorldManager.practiceWorld = this;

        init();
    }
    
    public void init()
    {
        //Updates til køb i shoppen
        WorldManager.setBackground(this);
        
        addObject(new Ball(this), getWidth()/2, getHeight()/2);
        addObject(new Paddle(100,20, true, false, false), getWidth()/2, getHeight() - 50);
        addObject(new Paddle(100,20, false, false, false), getWidth()/2, 50);
        
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
