import greenfoot.*;
import java.util.List;

/**
 * Write a description of class GameManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameManager  
{
    private static String currentBall = "def";
    private static String currentBg;
    private static String currentPaddle;
    
    public static final int WORLD_WIDTH = 500;
    public static final int WORLD_HEIGHT = 700;
    
    private static int ballRotation;
    private static int paddleX;
    private static int paddleVelocity;
    
    private static DisplayText text;
    private static int currency = 0;
    private static int gameLevel = 1;
    public static int textHeight = 20;
    public static int textWidth = 400;
    public static int textWidth2 = 100;
    
    private static int player1Score = 0;
    private static int player2Score = 0;
    public static int player1ScoreTextHeight = 680;
    
    
    
    private static int extraPaddleSize = 0;
    private static boolean extraLikeColors;
    
    private static DisplayText player1ScoreText = null;
    private static DisplayText player2ScoreText = null;
    
    public int worldIdx;
    
    /**
     * Constructor for objects of class GameManager
     */
    public GameManager()
    {
        
    }
    
    public static int getCurrency()
    {
        return currency;
    }
    
    public static void updateCurrency(boolean sale, int amount)
    {
        if (sale)
            currency -= amount;
        else
            currency += amount;
    }
    
    public static DisplayText textValueAssign(){
        return new DisplayText("Level: "+ gameLevel, 24, Color.WHITE, new Color(0,0,0,0));
    }
    
    public static DisplayText textValueAssignPVPPlayer1(){
        return new DisplayText("Player 1 Score: "+ player1Score, 24, Color.WHITE, new Color(0,0,0,0));
    }
    
    public static DisplayText textValueAssignPVPPlayer2(){
        return new DisplayText("Player 2 Score: "+ player2Score, 24, Color.WHITE, new Color(0,0,0,0));
    }
    
    public static void increaseGameLevel(World world)
    {
        world.removeObjects(world.getObjectsAt(textWidth, textHeight, DisplayText.class));
        gameLevel++;
        world.addObject(textValueAssign(), textWidth, textHeight);
    }
    
    public static void resetGameLevel(World world)
    {
        world.removeObjects(world.getObjectsAt(textWidth, textHeight, DisplayText.class));
        gameLevel = 1;
        world.addObject(textValueAssign(), textWidth, textHeight);
    }
    
    public static void updateTextCurrency(World world)
    {
        //Viser ens penge
        world.removeObjects(world.getObjectsAt(240, 105, DisplayText.class));
        world.addObject(new DisplayText("You have: " + GameManager.getCurrency() + " $", 24, Color.WHITE, new Color(0,0,0,0)), 240, 105);
    }
    
    public static void updateTextCurrencyGame(World world)
    {
        //Viser ens penge
        world.removeObjects(world.getObjectsAt(textWidth2, textHeight, DisplayText.class));
        world.addObject(new DisplayText("You have: " + GameManager.getCurrency() + " $", 24, Color.WHITE, new Color(0,0,0,0)), textWidth2, textHeight);
    }    
    
    public static void increasePlayer1Score(World world)
    {
        world.removeObjects(world.getObjectsAt(textWidth, player1ScoreTextHeight, DisplayText.class));
        player1Score++;
        world.addObject(textValueAssignPVPPlayer1(), textWidth, player1ScoreTextHeight);
    }
    
    public static void increasePlayer2Score(World world)
    {
        world.removeObjects(world.getObjectsAt(textWidth2, textHeight, DisplayText.class));
        player2Score++;
        world.addObject(textValueAssignPVPPlayer2(), textWidth2, textHeight);
    }
    
    public static void handleCheats(String inputCode)
    {
        if (inputCode == null)
            return;
            
        if (inputCode.equals("makemerich")) {
            currency += 100;
            SoundManager.playPurchase();
        }
        
        if (inputCode.equals("imbad")) {
            extraPaddleSize = 100;
            SoundManager.playPurchase();
        }
        
        if (inputCode.equals("reset")) {
            extraPaddleSize = 0;
            SoundManager.playPurchase();
        }
    }
    
    public static int getCheatPaddle() {
        return extraPaddleSize;
    }
    
    public static void setGameLevel(int value)
    {
        gameLevel = value;
    }

    public static String getBallPicture()
    {
        return currentBall;
    }
    
    public static String getBgPicture()
    {
        return currentBg;
    }
    
    public static String getPaddlePicture()
    {
        return currentPaddle;
    }
    
    public static int getBallRotation()
    {
        return ballRotation;
    }
    
    public static void setBallRotation(int newRotation)
    {
        ballRotation = newRotation;
    }
    
    public static int getPaddleX()
    {
        return paddleX;
    }
    
    public static void setPaddleX(int newX)
    {
        paddleX = newX;
    }
    
    public static int getPaddleVel()
    {
        return paddleVelocity;
    }
    
    public static void setPaddleVel(int newVel)
    {
        paddleVelocity = newVel;
    }
    
    public static void setPaddlePicture(String picture)
    {
        currentPaddle = picture;
    }
    
    public static void setBgPicture(String picture)
    {
        String modifiedPic = picture.replace("_preview", "");
        currentBg = modifiedPic;
    }
    
    public static void setBallPicture(String picture)
    {
        currentBall = picture;
    }
    
    public static void removeObjects(World world)
    {
       List objects = world.getObjects(null);
       world.removeObjects(objects);
    }
    
    public static void checkEscExit(World world)
    {
        if (Greenfoot.isKeyDown("escape")) {
            
            removeObjects(world);
            
            player1Score = 0;
            player2Score = 0;
            gameLevel = 1;
            
            WorldManager.setIntroWorld();
            SoundManager.stopMusic();
        }        
    }
}
