import java.util.ArrayList;
public class Inventory
{
    private ArrayList<Item> items = new ArrayList<>();
    private int maxCapacity;
    private int capacity = 0;
    public Inventory(int maxCapacity)
    {
        this.maxCapacity = maxCapacity;
        items = new ArrayList<>();
    }

    public void addItem(Item item)
    {
        items.add(item);
        capacity += item.getWeight();
    }

    public void removeItem(Item item)
    {
        if(items.contains(item))
        {
            capacity -= item.getWeight();
            items.remove(item);
        }
        
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
}