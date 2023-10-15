import greenfoot.*;


/**
 * A Ball is a thing that bounces of walls and paddles (or at least i should).
 * 
 * @author The teachers 
 * @version 1
 */
public class Ball extends Actor
{
    private static final int BALL_SIZE = 25;
    private static final int BOUNCE_DEVIANCE_MAX = 5;
    private static final int STARTING_ANGLE_WIDTH = 90;
    private static final int DELAY_TIME = 100;
    private static final int HIT_HORIZONTAL_MAX = 10;

    private int speed;
    private boolean hasBouncedHorizontally;
    private boolean hasBouncedVertically;
    
    private int delay;
    private int direction; //Potentiel bug
    private int speedIncreaseBounceCounter;
    private int horizontalBounces;
    
    public boolean updatePicture = true;
    public boolean playerBall;
    
    private World curWorld;
    
    /*
     * Potentiel bug er den del kode der kan forsage at ball bliver stuck i paddle
     * Prøv at spil for at genskabe bug, hvis i finder en løsning så slet kommentarene tak
     */
    /**
     * Contructs the ball and sets it in motion!
     */
    public Ball(World world)
    {
        this.curWorld = world;
   
        createImage(GameManager.getBallPicture());
        
        init();
    }

    /**
     * Creates and sets an image of a black ball to this actor.
     */
    public void createImage(String picture)
    {
        GreenfootImage ballImage = new GreenfootImage(BALL_SIZE, BALL_SIZE);
        
        if (picture == "def") {
            ballImage.setColor(Color.WHITE);
            ballImage.fillOval(0, 0, 25, 25);
        } else {
            ballImage = new GreenfootImage(picture);  
            ballImage.scale(35, 35);
        }
        
        setImage(ballImage);
    }

    /**
     * Act - do whatever the Ball wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
     public void act() 
    {
        if (delay > 0)
        {
            delay--;
        }
        else
        {
            move(speed);
            checkBounceOffWalls();
            checkBounceOffCeiling();
            checkBounceOffPaddle();
            checkIfBallPassed();
            checkRestart();
        }
        
        GameManager.setBallRotation(getRotation());
    }    

     public boolean isPlayerBall()
    {
        return playerBall;
    }    

    /**
     * Returns true if the ball is touching one of the side walls.
     */
    private boolean isTouchingSides()
    {
        return (getX() <= BALL_SIZE/2 || getX() >= getWorld().getWidth() - BALL_SIZE/2);
    }

    /**
     * Returns true if the ball is touching the ceiling.
     */
    private boolean isTouchingCeiling()
    {
        return (getY() <= BALL_SIZE/2);
    }

    /**
     * Returns true if the ball is touching the floor.
     */
    private boolean isTouchingFloor()
    {
        return (getY() >= getWorld().getHeight() - BALL_SIZE/2);
    }
    
    private boolean checkPaddleCollision()
    {
        return isTouching(Paddle.class);
    }
    
    public boolean checkPaddleCollisionPlayer()
    {
        playerBall = true;
        Paddle player = (Paddle)getOneIntersectingObject(Paddle.class);
        return player != null && player.getIsPlayable();
    }
    
    private boolean checkPaddleCollisionBot()
    {
        playerBall = false;
        Paddle bot = (Paddle)getOneIntersectingObject(Paddle.class);
        return bot != null && bot.getIsBot();
    }
    
    /**
     * Check to see if the ball should bounce off one of the walls.
     * If touching one of the walls, the ball is bouncing off.
     */
    private void checkBounceOffWalls()
    {
        if (isTouchingSides())
        {
            horizontalBounces++;
            
            //Afspiller lyd af at bolden rammer noget
            SoundManager.playHit();

            if (!hasBouncedHorizontally)
                revertHorizontally(horizontalBounces > HIT_HORIZONTAL_MAX);
        } 
        else
            hasBouncedHorizontally = false;
    }
    

    /**
     * Check to see if the ball should bounce off the ceiling.
     * If touching the ceiling the ball is bouncing off.
     */
    private void checkBounceOffCeiling()
    {
        if (isTouchingCeiling() && getWorld() instanceof PracticeWorld)
        {
            horizontalBounces = 0;
            //Afspiller lyd af at bolden rammer noget
             SoundManager.playHit();
             
            if (!hasBouncedVertically)
            {
                revertVertically();
                direction = 0;
            }
            else
            hasBouncedVertically = false;
        }
        
        if (isTouchingCeiling() && getWorld() instanceof AIWorld) {
            GameManager.updateCurrency(false, 5);
            GameManager.updateTextCurrencyGame(curWorld);
            init();
        }
    }
    
    private void checkBounceOffPaddle()
    {   
        if(checkPaddleCollision() && direction == 1) //Hvis direction = 1 så kan bolden blive skudt tilbage.
        {

            //Afspiller lyd af at bolden rammer noget
             SoundManager.playHit();
             
            if(! hasBouncedVertically)
            {
                
                if (checkPaddleCollisionPlayer()) {
                    if (getWorld() instanceof AIWorld || getWorld() instanceof PracticeWorld) {
                        GameManager.updateCurrency(false, 1);
                        GameManager.updateTextCurrencyGame(curWorld);
                    }
                }
                
                revertVertically();
                if (checkPaddleCollisionPlayer() || checkPaddleCollisionBot())
                {
                    horizontalBounces = 0;
                    increaseSpeed();
                }
            }
        }
        else
            hasBouncedVertically = false;
    }
    
    private void checkIfBallPassed()
    {
        //Potentiel bug
        if(direction == 0 && this.getY() >= 600) //Hvis retningen er 0, men bolden har passeret enemy target, bliver retningen til 1 så den kan skydes tilbage.
        {
            direction = 1;
        }
    }
    
    
    private void increaseSpeed(){
        if (speedIncreaseBounceCounter >= 2)
        {
            speed++;
            if (getWorld() instanceof PvPWorld) {}
            else
                GameManager.increaseGameLevel(curWorld);

            speedIncreaseBounceCounter = 0;
        }
        else
            speedIncreaseBounceCounter++;
    }
    
    /**
     * Check to see if the ball should be restarted.
     * If touching the floor the ball is restarted in initial position and speed.
     */
    private void checkRestart()
    {
        if (getWorld() instanceof PvPWorld)
            {
                if (isTouchingFloor())
                {
                    init();
                    GameManager.increasePlayer2Score(curWorld);
                }
                else if (isTouchingCeiling())
                {
                    init();
                    GameManager.increasePlayer1Score(curWorld);
                }
            }
        else
        {
            if (isTouchingFloor()) {
                GameManager.removeObjects(curWorld);
                WorldManager.setGameOverWorld(curWorld);
            }
        }
    }

    /**
     * Bounces the ball back from a vertical surface.
     */
    private void revertHorizontally(boolean ballStuck)
    {
        //int someLogic = Paddle.getVelocity() - 360;
        int randomness = Greenfoot.getRandomNumber(BOUNCE_DEVIANCE_MAX)- BOUNCE_DEVIANCE_MAX / 2;
        int rotation = ballStuck ? Greenfoot.getRandomNumber(65) + 100 : 180;
        setRotation((rotation - getRotation()+ randomness + 360) % 360);

        if (ballStuck)
            horizontalBounces = 0;
        hasBouncedHorizontally = true;
    }

    /**
     * Bounces the bal back from a horizontal surface.
     */
    private void revertVertically()
    {
        int moveSide = 360;
        Paddle player = (Paddle)getOneIntersectingObject(Paddle.class);
        if (player != null)
            if (player.getIsPlayable())
                moveSide += player.getVelocity();
            else
                moveSide = 360 + Greenfoot.getRandomNumber(BOUNCE_DEVIANCE_MAX)- BOUNCE_DEVIANCE_MAX / 2;
                //moveSide = 345;
        
        if (moveSide >= 375)
            moveSide -= 1;
            
        if (moveSide <= 345)
            moveSide += 1;
            
        if (moveSide == 0)
            moveSide = 360;
            
        setRotation((moveSide - getRotation() + moveSide) % 360);
        hasBouncedVertically = true;
    }

    /**
     * Initialize the ball settings.
     */
    private void init()
    {
        speed = 2;
        direction = 1;
        speedIncreaseBounceCounter = 0;
        delay = DELAY_TIME;
        hasBouncedHorizontally = false;
        hasBouncedVertically = false;
        setLocation(curWorld.getWidth() / 2, curWorld.getHeight() / 2);
        setRotation(Greenfoot.getRandomNumber(STARTING_ANGLE_WIDTH)+STARTING_ANGLE_WIDTH/2);
    }
}
