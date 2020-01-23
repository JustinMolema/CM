import java.util.HashMap;
/**
*   Deze klasse omschrijft een kamer in de game
*   @author Justin Molema, Johan van Hengelaar
*   @version 23-1-2020
*/
public class Room
{
    public String roomName;
    public int id;
    public String command;
    public String keyName;
    private String description;

    public HashMap<Integer, Room> connectedRooms;
    public HashMap<Integer, Boolean> connectedRoomsLocks;

    private Inventory roomInventory;

    /**
     * constructor met alleen een kamer naam & id OBSOLETE
     * @param roomName
     * @param id
     */
    public Room(String roomName, int id)
    {
        this.roomName = roomName;
        this.id = id;
        command = "enter the " + roomName;
        keyName = roomName + " key";
        setup();
    }
    /**
     * constructor van de kamer waarbij een aantal dingen worden meegegeven
     * @param roomName de naam van de kamer
     * @param id het id van de kamer
     * @param command het commando dat gebruikt moet worden om deze kamer te betreden
     * @param description de omschrijving die geprint moet worden wanneer de speler de kamer betreed
     */
    public Room(String roomName, int id, String description)
    {
        this.roomName = roomName;
        this.id = id;
        command = "enter the " + roomName;
        this.description = description;
        keyName = roomName + " key";
        setup();
    }
    /**
     * constructor van de kamer waarbij een aantal dingen worden meegegeven, met optie voor een keyname
     * @param roomName de naam van de kamer
     * @param id het id van de kamer
     * @param command het commando dat gebruikt moet worden om deze kamer te betreden
     * @param description de omschrijving die geprint moet worden wanneer de speler de kamer betreed
     * @param keyName de naam van de key die gebruikt moet worden om deze kamer te openen
     */
    public Room(String roomName, int id, String description, String keyName)
    {
        this.roomName = roomName;
        this.id = id;
        command = "enter the " + roomName;
        this.description = description;
        this.keyName = keyName;
        setup();
    }
    /**
    * methode om dingen klaar te zetten in de kamer
    */
    private void setup()
    {
        connectedRooms = new HashMap<>();
        connectedRoomsLocks = new HashMap<>();
        roomInventory = new Inventory(100000000);
    }
    /**
     * methode om een connectie toe te voegen
     * @param room de kamer
     * @param locked of deze connectie wel of niet op slot zit
     */
    public void addConnectedRoom(Room room, boolean locked)
    {
        connectedRooms.put(room.id, room);
        connectedRoomsLocks.put(room.id, locked);
    }
    /**
     * methode om een item toe te voegen aan de inventaris van de kamer
     * @param item
     */
    public void addItem(Item item)
    {
        roomInventory.addItem(item);
    }
    /**
     * methode om een item te verwijderen uit de inventaris van de kamer
     * @param item
     */
    public void removeItem(Item item)
    {
        roomInventory.removeItem(item);
    }
    /**
     * deze methode wordt aangeroepen wanneer de speler de kamer betreed, en hij print hier informatie over de kamer
     */
    public void onEnter()
    {
        if(description == null || description.equalsIgnoreCase(""))
        {
            System.out.println("You've entered " + ConsoleColors.BLUE_BRIGHT + roomName + ConsoleColors.RESET);
        }
        else
        {
            System.out.println("You've entered " + description);
        }
        for (int i : connectedRooms.keySet()) 
        {
            System.out.println("You can go to: " + ConsoleColors.BLUE_BRIGHT + connectedRooms.get(i).roomName + ConsoleColors.RESET);
        }
    }
    /**
     * getter voor het inventaris van de kamer
     * @return roomInventory
     */
    public Inventory getRoomInventory() {
        return roomInventory;
    }
    /**
     * setter voor de roomInventory van deze kamer
     * @param roomInventory
     */
    public void setRoomInventory(Inventory roomInventory) {
        this.roomInventory = roomInventory;
    }
}