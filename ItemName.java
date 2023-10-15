import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.font.FontRenderContext;

/**
 * Write a description of class Price here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ItemName extends Actor
{
    private ListItem listItem;
    private int x, y;
    private String name;

    protected void addedToWorld(World world)
    {
        //Lad os fjerne den dumme greenfoot fod.
        getImage().clear();
        
        // Laver en font via. javas Font klasse som vi kan bruge til at udregne text pixel width.
        Font font = new Font("Tahoma", Font.PLAIN, 14);
        
        // Vi laver en greenfoot font, til at sætte vores text til.
        GreenfootImage timerImage = new GreenfootImage(this.name, 18, Color.WHITE, Color.BLACK);
        timerImage.setFont(new greenfoot.Font("Tahoma", false, false, 14));
        
        // Udregner text width.
        FontRenderContext frc = ((Graphics2D)timerImage.getAwtImage().createGraphics()).getFontRenderContext();
        Rectangle2D textSize = font.getStringBounds(this.name, frc);   
        
        int textWidth = (int)textSize.getWidth();
        int textHeight = (int)textSize.getHeight();
        

        getWorld().getBackground().drawImage(timerImage, this.x - 3 - (textWidth / 2), this.y - 5); 
    }
    
    public ItemName(ListItem listItem, int x, int y)
    {
        this.x = x;
        this.y = y;
        this.name = listItem.getName();
    }
}
