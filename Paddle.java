import greenfoot.*;
import java.util.List;


/**
 * A paddle is an object that goes back and forth. Though it would be nice if balls would bounce of it.
 * 
 * @author The teachers 
 * @version 1
 */
public class Paddle extends Actor
{
    private int width;
    private int height;
    private int directionX;
    private int paddleVelocity = 0;
    private boolean paddleVelocityReset;
    
    private static final int MOVEMENT_SPEED = 2;
    private static final int VELOCITY_MULTIPLIER = 1;    
    private static final int VELOCITY_MAX = 75; 
    
    private boolean isPlayable;
    private boolean isBot;
    private boolean isPlayer2;

    /**
     * Constructs a new paddle with the given dimensions.
     */
    public Paddle(int width, int height, boolean isPlayable, boolean isBot,boolean isPlayer2)
    {
        this.width = width;
        this.height = height;
        this.isPlayable = isPlayable;
        this.isBot = isBot;
        this.isPlayer2 = isPlayer2;
        
        if (isPlayable)
            this.width += GameManager.getCheatPaddle();
            
        createImage();
    }

    /**
     * Act - do whatever the Paddle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (isPlayable && isPlayer2)
            player2Movement();
        else if (isPlayable)
            playerMovement(); 
        else if(isBot)
            botMovement();
        else
            enemyMovement();
        
    }
    
    public boolean getIsBot()
    {
        return isBot;
    }

    public boolean getIsPlayable()
    {
        return isPlayable;
    }
    
     /**
     * Returns true if the paddle is touching right side of the wall.
     */
    private boolean isTouchingRightWall()
    {
        return (getX() >= getWorld().getWidth() - width / 2);
    }
    
    private void enemyMovement()
    {
        if (isTouchingRightWall()) {
            setLocation(0, Greenfoot.getRandomNumber(getWorld().getHeight() - 275) + 50);
        }
        
        move(2);
    }
    
    private void player2Movement()
    {
        if (Greenfoot.isKeyDown("D"))
            move(MOVEMENT_SPEED);
            
        if (Greenfoot.isKeyDown("A"))
            move(-MOVEMENT_SPEED);
    }

    private void botMovement()
    {
        /*
         * List over bolde i world, tager index 0 (Den eneste bold i verdenen) og finder dens X.
         */
        List balls = getWorld().getObjects(Ball.class);
        AIWorld world = (AIWorld) getWorld();
        if (!balls.isEmpty())
        {
            Ball ball = (Ball)balls.get(0);
            int ballX = ball.getX();
            int ballY = ball.getY();
            
            if(!ball.isPlayerBall())
            {
                if(this.getX() == world.getCenterX())
                move(0);
                else if (this.getX() <= world.getCenterX())
                move(MOVEMENT_SPEED);
                else
                move(-MOVEMENT_SPEED);
            }
            if(ball.isPlayerBall()){
                if (this.getX() == ballX)
                    move(0);
                else if (this.getX() <= ballX)
                    move(MOVEMENT_SPEED);
                else
                    move(-MOVEMENT_SPEED);
            }
        }
    }    

    private void playerMovement()
    {
        boolean right = Greenfoot.isKeyDown("right");
        boolean left = Greenfoot.isKeyDown("left");

        if (right) {
            move(MOVEMENT_SPEED);
            if (paddleVelocity < 0)
                paddleVelocity = 0;
                
            paddleVelocity += VELOCITY_MULTIPLIER;
            paddleVelocityReset = true;
        }
        
        if (left) {
            move(-MOVEMENT_SPEED);
            if (paddleVelocity > 0)
                paddleVelocity = 0;
            paddleVelocity -= VELOCITY_MULTIPLIER;
            paddleVelocityReset = true;
        }
        
        if (!right && !left && paddleVelocityReset) {
            paddleVelocity = 0;
            paddleVelocityReset = false;
        }
        
        if (paddleVelocity > VELOCITY_MAX)
            paddleVelocity = VELOCITY_MAX;
            
        if (paddleVelocity < -VELOCITY_MAX)
            paddleVelocity = -VELOCITY_MAX;
        
        GameManager.setPaddleVel(paddleVelocity);
    }
    
    public int getVelocity()
    {
        return paddleVelocity / 5;
    }
    
    private void storePos()
    {
       GameManager.setPaddleX(this.getX());
    }

    /**
     * Creates and sets an image for the paddle, the image will have the same dimensions as the paddles width and height.
     */
    private void createImage()
    {
        GreenfootImage image = new GreenfootImage(width, height);
        
        if (GameManager.getPaddlePicture() != null) {
            image = new GreenfootImage(GameManager.getPaddlePicture());
            setLocation(0, 45);
            image.scale(100 + GameManager.getCheatPaddle(), 30);
        } else {
            image.setColor(Color.WHITE);
            image.fill(); 
        }
        setImage(image);
    }

}
