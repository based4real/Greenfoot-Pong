import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StoreSystem here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StoreSystem extends Actor
{
    World world;
    //new Listitem(id, StoreSystem, navn, pris, png_placering, er solgt, er equipped, type (1,2,3))
    ListItem[] storeItems = {
      new ListItem(1, this, "Flappy Bird", 10, "items/flappyBird.png", false, false, 1), 
      new ListItem(2, this, "Happy face", 25, "items/happyface.png", false, false, 1), 
      new ListItem(3, this, "Eye", 50, "items/zombieeye.png", false, false, 1), 
      new ListItem(4, this, "Coin", 150, "items/coin.png", false, false, 1), 
      new ListItem(5, this, "BG: Lava", 10, "items/underworldBg_preview.png", false, false, 2), 
      new ListItem(6, this, "BG: Sky", 25, "items/skyBg_preview.png", false, false, 2), 
      new ListItem(7, this, "BG: Stars", 50, "items/starBg_preview.png", false, false, 2), 
      new ListItem(8, this, "BG: Mario", 150, "items/marioBg_preview.png", false, false, 2), 
      new ListItem(9, this, "PC", 10, "items/oldPc.png", false, false, 3), 
      new ListItem(10, this, "Wood", 25, "items/woodenBoard.png", false, false, 3), 
      new ListItem(11, this, "Baguette", 50, "items/baguette.png", false, false, 3), 
      new ListItem(12, this, "Pencil", 150, "items/pen.png", false, false, 3), 
    };    
    
    public StoreSystem(World world)
    {
        this.world = world;
        
        GreenfootImage text = new GreenfootImage(1,1);  
        setImage(text);
        
        intializeItems();
    }
    
    // Bliver brugt til at loope igennem vores liste med objekter som vi kan tilføje til verden.
    private void intializeItems() {
        //addObject(new StoreSystem(), 45, 45);
        int moveX = 0;
        int moveY = 0;
        int count = 0;
        
        for (ListItem i : storeItems) {
            
            // Bruges til at rykke ned på ny linje samt nulstiller X position.
           if (count % 4 == 0) {
                moveY += 145;
                moveX = getImage().getWidth() / 8;
            }
            
           world.addObject(i, 100 + moveX, 45 + moveY); 
           
           moveX += 100;
           count++;
        }
    }
    
    // Køber en item.
    public void purchaseItem(int pos) {

        if (GameManager.getCurrency() >= this.storeItems[pos].getPrice()) {
            this.storeItems[pos].setSold(true);
            GameManager.updateCurrency(true, this.storeItems[pos].getPrice());
                                
            //Afspiller Money Register lyd
            SoundManager.playPurchase();
        }
        
        GameManager.updateTextCurrency(world);
    }

    //Equipper en item, for hver type bold, background eller paddle.
   public void equipItem(int pos, int type) {
        unequipAll(type);
        this.storeItems[pos].setEquipped(!this.storeItems[pos].isEquipped());
        
         SoundManager.playBtnEquip();

        // In theory this would be the each subclass of the superclass
        switch(type) {
            case 1:
                GameManager.setBallPicture(this.storeItems[pos].getPicture());
                break;
            case 2:
                GameManager.setBgPicture(this.storeItems[pos].getPicture());
                break;
            case 3:
                GameManager.setPaddlePicture(this.storeItems[pos].getPicture());
                break;
        }
    }
    
    //Man kan jo kun equip EN type af gangen
    //Derfor tjekker vi om nogle har den type og hvis ja, så sætter vi deres equipped til false.
    private void unequipAll(int type) {
        for(ListItem i: storeItems) {
            if (i.isEquipped()) {
                if (type == i.getType())
                    i.setEquipped(false);   
            }
        }
    }
}
