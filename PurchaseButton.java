import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PurchaseButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PurchaseButton extends Actor
{
    private boolean isHovering;
    private int x, y, w, h;
    
    private static final String PNG_PURCHASE = "purchase2.png";
    private static final String PNG_SOLDOUT = "soldout.png";
    private static final String PNG_EQUIP = "equip.png";
    private static final String PNG_EQUIPPED = "equipped.png";
    
    private static final int FONT_SIZE = 16;
    
    private ListItem listItem;
    private StoreSystem storeSystem;
    
    GreenfootImage background;
    
    // Vi starter med at gemme de vigtige dataer.
    public PurchaseButton(StoreSystem s, ListItem listItem, int x, int y)
    {
        this.storeSystem = s;
        this.listItem = listItem;
        this.x = x;
        this.y = y;

        updateImg(PNG_PURCHASE);
        
        this.w = getImage().getWidth();
        this.h = getImage().getHeight();
        
        GreenfootImage text = new GreenfootImage("PURCHASE", FONT_SIZE, Color.WHITE, null);
        background.drawImage(text, 5, 4);
        
    }
    /**
     * Act - do whatever the PurchaseButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        updateBtn();
        
        isHovering();
    }
    
    
    private void updateImg(String picture)
    {
        background = new GreenfootImage(picture);
        setImage(background);     
    }
    
    private void updateBtn()
    {
        //Hvis hover over knap så:
        if (isHovering) {
            background.clear();
            
            //Billede sammenligniner til knappen.
            if (listItem.isSold() && !listItem.isEquipped())
                updateImg(PNG_EQUIP);
             else if (listItem.isSold() && listItem.isEquipped())
                updateImg(PNG_EQUIPPED);
            else {
                updateImg(PNG_PURCHASE);
                GreenfootImage text = new GreenfootImage(Integer.toString(listItem.getPrice()) + " $", FONT_SIZE, Color.WHITE, null);
                background.drawImage(text, 25, 5);
            }
            
          background.scale(w + 2, h + 2);
        } else {
            background.clear();
            
            if (listItem.isSold() && !listItem.isEquipped())
                updateImg(PNG_EQUIP);
             else if (listItem.isSold() && listItem.isEquipped())
                updateImg(PNG_EQUIPPED);
            else {
                updateImg(PNG_PURCHASE);
                GreenfootImage text = new GreenfootImage("PURCHASE", FONT_SIZE, Color.WHITE, null);
                background.drawImage(text, 5, 4);
            }      
            background.scale(w, h);
        }
    }
    
    //Brugt til at lave effekter når vi hover vores knap
    private void isHovering()
    {
        if (!isHovering && Greenfoot.mouseMoved(this))
        {
            background.setColor(Color.WHITE);
            background.scale(w + 2, h + 2);
            isHovering = true;
            
            //Afspiller UI lyd
            Greenfoot.playSound("UI_Sounds.mp3");
        }
        
        if (isHovering && Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this)) {
            background.scale(w, h);
            isHovering = false;
        }
        
        //Var der et klik mens vi hovered?
        if (isHovering)
            handleClick();
            
    }
    
    private void handleClick()
    {
        MouseInfo mouse =  Greenfoot.getMouseInfo();
        
        if (mouse != null) {
            if (mouse.getButton() == 1 && Greenfoot.mouseClicked(null)) {
                int itemId = listItem.getId();
                //Hvis den ikke var solgt, så går vi udfra den ikke er købt.
                //Sæt den til solgt samt return så den ikke instant equipper.
                if (!listItem.isSold()) {
                    storeSystem.purchaseItem(itemId);
                    return;
                } else {
                    storeSystem.equipItem(itemId, listItem.getType());
                    
                }

            }
        }
    }
}
