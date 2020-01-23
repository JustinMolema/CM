/**
*   Deze klasse omschrijft een item
*   @author Justin Molema, Johan van Hengelaar
*   @version 23-1-2020
*/
public class Item
{
    private String name;
    // description of the object
    private String description;
    //private String name;
    private int weight;

    /**
    *   constructor voor het aanmaken van een item
    *   @param String name de naam van het item
    *   @param int weight het gewicht van een item
    */
    public Item(String name, int weight)
    {
        this.name = name;
        this.weight = weight;
    }

    /**
     * getter voor de naam
     * @return name
     */
    public String getName() {
        return name;
    }
    /**
     * setter voor de naam
     * @param String name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * getter voor het gewicht
     * @return gewicht
     */
    public int getWeight() {
        return weight;
    }
    /**
     * setter voor het gewicht
     * @param int weight
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }
}