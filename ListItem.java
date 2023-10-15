import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class listItems here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ListItem extends Actor
{
    private boolean isHovering, sold, isEquipped;
    private String name, png;
    private int id, w, h, price, type;
    
    private StoreSystem s;

    /**
     * Act - do whatever the listItems wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    //Vi tilføjer vores 3 objekter.
    protected void addedToWorld(World world)
    {
        world.addObject(new DrawItem(this), getX(), getY());
        world.addObject(new PurchaseButton(this.s, this, getX(), getY()), getX(), getY() + 52);
        world.addObject(new ItemName(this, getX(), getY() - 52), getX() - 30, getY() - 48);
    }
    
    // Konstruktøren som gemmer de vigtige ting
    public ListItem(int id, StoreSystem s, String name, int price, String png, boolean isSold, boolean isEquipped, int type)
    {
        this.id = id;
        this.s = s;
        this.name = name;
        this.png = png;
        this.price = price;
        this.w = getImage().getWidth();
        this.h = getImage().getWidth();    
        this.sold = isSold;
        this.isEquipped = isEquipped;
        this.type = type;
    }

    //Nedenstående er hjælpefunktioner til vores items.
    public boolean isSold()
    {
        return this.sold;
    }
    
    public boolean isEquipped()
    {
        return this.isEquipped;
    }
    
    public void setEquipped(boolean isEquipped)
    {
        this.isEquipped = isEquipped;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public int getId()
    {
        return this.id - 1;
    }    
    
    public int getPrice()
    {
        return this.price;
    }    
    
    public int getType()
    {
        return this.type;
    }
    
    public String getPicture()
    {
        return this.png;
    }
    
   public void setSold(boolean sold) 
    {
        this.sold = sold;
    }
}
