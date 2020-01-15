public class Player
{
    private String name;
    private Inventory inventory;

    public String getName() {
        return name;
    }

    public Player(String name)
    {
        this.name = name;
        inventory = new Inventory(100);
    }

    public boolean checkInventory(String itemName)
    {
        //boolean contains = false;

        for (Item item : inventory.getItems()) 
        {
            if(itemName.equalsIgnoreCase(item.getName()))
            {
                return true;
            }
        }

        return false;
    }

    public void addItem(Item item)
    {
        inventory.addItem(item);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}