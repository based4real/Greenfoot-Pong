import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DrawItems here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DrawItem extends Actor
{
    private ListItem listItem;
    
    /**
     * Act - do whatever the DrawItems wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public DrawItem(ListItem listItem) 
    {
        GreenfootImage item = new GreenfootImage(listItem.getPicture());
        item.setColor(Color.BLACK);
        item.drawImage(item, 0, 0);
        setImage(item);
        
    }
    
    public void act()
    {
        // Add your action code here.
    }
}
