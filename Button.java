import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.font.FontRenderContext;
import javax.swing.JOptionPane;

/**
 * Write a description of class Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button extends Actor
{
    private boolean isHovering;
    private String txt;
    private Color txtClr;
    private int x, y, w, h, txtW;

    private String bg = "btnBg.png";
    GreenfootImage background = new GreenfootImage(bg);
    private boolean updateBtn;
    
    //private GreenfootImage background = new GreenfootImage("btnBg.png");
    
    private World world;
    
    public Button(String name, int x, int y, int w, int h, Color txtClr, Color bgClr, World world)
    {  
       this.txt = name;
       this.w = w;
       this.h = h;
       this.world = world;
        // Sætter baggrund farve og position.
        background.setColor(bgClr);
        setImage(background);
     
       drawText(name, x, y, w, h, txtClr, background);
    }
    
    private void drawText(String txt, int x, int y, int w, int h, Color clr, GreenfootImage bg)
    {
        // Laver en font via. javas Font klasse som vi kan bruge til at udregne text pixel width.
        Font font = new Font("Tahoma", Font.PLAIN, 14);
        
        // Vi laver en greenfoot font, til at sætte vores text til.
        bg.setFont(new greenfoot.Font("Tahoma", false, false, 14));
        bg.setColor(clr);
        
        // Udregner text width.
        FontRenderContext frc = ((Graphics2D)bg.getAwtImage().createGraphics()).getFontRenderContext();
        Rectangle2D textSize = font.getStringBounds(txt, frc);   
        
        int textWidth = (int)textSize.getWidth();
        int textHeight = (int)textSize.getHeight();
        
        this.txtW = textWidth;
        
        getImage().drawString(txt, this.w / 2 - textWidth / 2, this.h / 2 + textHeight / 3);        
    }

    private void isHovering()
    {
        GreenfootImage getBg = getImage();
   
        if (!isHovering && Greenfoot.mouseMoved(this))
        {
            getBg.setColor(Color.WHITE);
            getBg.scale(this.w + 2, this.h + 2);
            
            getImage().setTransparency(240);
            
            isHovering = true;
            
            //Afspiller UI lyd
             SoundManager.playBtnHover();
        }
        
        if (isHovering && Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this)) {
            getImage().setTransparency(255);
            getBg.scale(this.w, this.h);
            isHovering = false;
        }
        
        if (isHovering)
            handleClick(this.txt);
    }
    
    private void handleClick(String curTxt)
    {
        MouseInfo mouse =  Greenfoot.getMouseInfo();
        
        if (mouse != null) {
            if (mouse.getButton() == 1 && Greenfoot.mouseClicked(null)) {
                switch(curTxt) {
                    case "Play":
                       WorldManager.setGameModeWorld();
                        break;
                    case "Store":
                        WorldManager.setStoreWorld();
                        break;
                    case "Cheats":
                        GameManager.handleCheats(JOptionPane.showInputDialog("Please input a value"));
                        break;
                    case "1 vs. AI":
                        WorldManager.setAIWorld();
                        break;
                    case "1 vs. 1":
                        WorldManager.setPvPWorld();
                        break;
                    case "Practice":
                        WorldManager.setPracticeWorld();
                        break;
                    case "Try again":
                        WorldManager.setLastActiveGame();
                        break;                        
                    default:
                        WorldManager.setIntroWorld();
                        SoundManager.stopMusic();
                        break;
                }
            }
        }
    }
    
    /**
     * Act - do whatever the Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    { 
        isHovering();
    }
}
