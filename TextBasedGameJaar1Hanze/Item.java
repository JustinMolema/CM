public class Item
{
    protected String name;
    // description of the object
    protected String description;
    //private String name;
    private int weight = 1;

    public Item(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}