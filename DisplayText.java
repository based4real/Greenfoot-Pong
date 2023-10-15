import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DisplayText here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DisplayText extends Actor
{
    public DisplayText(String text, int fontSize, Color textColor, Color backgroundColor){
        GreenfootImage image = new GreenfootImage(text, fontSize, textColor, backgroundColor);
        setImage(image);
    }
}
