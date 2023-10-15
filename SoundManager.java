import greenfoot.*;

/**
 * Write a description of class SoundManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SoundManager  
{
    private static GreenfootSound music = new GreenfootSound("Music_Sounds.mp3");

    /**
     * Constructor for objects of class SoundManager
     */
    public SoundManager()
    {
        
    }

    //playHit playMusic GameOver ScorePoint Purchase, Button Hover, Button click
    
    public static void playHit()
    {  
        Greenfoot.playSound("HittingWood_Sounds.mp3");
    }
    
    public static void playMusic()
    {  
        music.playLoop();
    }
    
    public static void stopMusic()
    {  
        music.stop();
    }
    
    public static void playGameOver()
    {  
        Greenfoot.playSound("Death_Sounds.mp3");
    }
    
    public static void playScorePoint()
    {  
        Greenfoot.playSound("CoinCollect_Sounds.mp3");
    }
    
    public static void playPurchase()
    {  
        Greenfoot.playSound("MoneyRegister_Sounds.mp3");
    }
    
    public static void playBtnHover()
    {  
        Greenfoot.playSound("UI_Sounds.mp3");
    }
    
    public static void playBtnClick()
    {  
        Greenfoot.playSound("HittingWood_Sounds.mp3");
    }
    
    public static void playBtnEquip()
    {  
        Greenfoot.playSound("Equip_Sounds.mp3");
    }
}
