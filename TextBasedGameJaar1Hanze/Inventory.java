import java.util.ArrayList;
/**
*   Deze klasse is de inventaris, en zowel de speler als elke kamer heeft er een
*   @author Justin Molema, Johan van Hengelaar
*   @version 23-1-2020
*/
public class Inventory
{
    
    private ArrayList<Item> items = new ArrayList<>();
    private int maxCapacity;
    private int capacity = 0;
    /**
     * constructor waarbij de maximale capaciteit wordt gezet
     * @param maxCapacity de maximale capaciteit
     */
    public Inventory(int maxCapacity)
    {
        this.maxCapacity = maxCapacity;
        items = new ArrayList<>();
    }
    /**
     * methode om item toe te voegen
     * @param item
     */
    public void addItem(Item item)
    {
        items.add(item);
        capacity += item.getWeight();
    }
    /**
     * methode om item te verwijderen
     * @param item
     */
    public void removeItem(Item item)
    {
        if(items.contains(item))
        {
            capacity -= item.getWeight();
            items.remove(item);
        }
        
    }
    /**
     * getter voor de item arraylist
     * @return items
     */
    public ArrayList<Item> getItems() {
        return items;
    }
    /**
     * setter voor de item arraylist
     * @param items
     */
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
    /**
     * getter voor de huidige capaciteit van de inventaris
     * @return de huidige capaciteit
     */
    public int getCapacity() {
        return capacity;
    }
    /**
     * setter voor de huidige capaciteit
     * @param capacity
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    /**
     * getter voor de maximale capaciteit van de inventaris
     * @return de maximale capaciteit
     */
    public int getMaxCapacity() {
        return maxCapacity;
    }
    /**
     * setter voor de maximale capaciteit van de inventaris
     * @param maxCapacity
     */
    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
    
}