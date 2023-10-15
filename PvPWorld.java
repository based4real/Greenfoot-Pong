import greenfoot.*;

public class PvPWorld extends World
{
    /**
     * Constructor for objects of class CopyOfPingWorld.
     */
    public PvPWorld()
    {
        super(GameManager.WORLD_WIDTH, GameManager.WORLD_HEIGHT, 1);
    
        WorldManager.pvpWorld = this;
        
        init();
    }
    
    public void init()
    {
        //Updates til køb i shoppen
        WorldManager.setBackground(this);
        
        addObject(new Ball(this), getWidth()/2, getHeight()/2);
        addObject(new Paddle(100,20, true,false, false), getWidth()/2, getHeight() - 50);
        addObject(new Paddle(100,20, true,false, true), getWidth()/2, 50);
        
        DisplayText p1Score = GameManager.textValueAssignPVPPlayer1();
        DisplayText p2Score = GameManager.textValueAssignPVPPlayer2();
        addObject(p1Score, GameManager.textWidth, GameManager.player1ScoreTextHeight);
        addObject(p2Score, GameManager.textWidth2, GameManager.textHeight);
    }
    
    public void act()
    {
        //Music looper, husk at tilføje at musikken skal stoppe når man dør med "music.stop();"
        SoundManager.playMusic();
        
        GameManager.checkEscExit(this);
    }

}
