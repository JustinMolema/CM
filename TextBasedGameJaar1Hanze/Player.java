import java.util.HashMap;
/**
*   Deze klasse is de hoofdklasse van de toepassing, als je bluej gebruikt, roep dan de main method aan
*   @author Justin Molema, Johan van Hengelaar
*   @version 23-1-2020
*/
public class Player
{
    private Inventory inventory;
    private Boolean isHiding = false;

    private Room currentRoom;
    private HashMap<Integer, Room> previousRooms;
    private RoomManager roomManager;

    public boolean isKilled = false;

    /** 
     * constructor voor de player
    */
    public Player()
    {
        inventory = new Inventory(100);
        previousRooms = new HashMap<Integer, Room>();
    }

    /**
     * getter voor de currentroom
     * @return Room currentroom
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }
    /**
     * // setter voor de currentroom
     * @param currentRoom
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
    /**
     * getter voor alle previousrooms
     * @return HashMap<Integer, Room> previousrooms
     */
    public HashMap<Integer, Room> getPreviousRooms() {
        return previousRooms;
    }

    /**
     * // setter voor de previousrooms
     * @param previousRooms
     */
    public void setPreviousRoom(HashMap<Integer, Room> previousRooms) {
        this.previousRooms = previousRooms;
    }

    /**
     * Methode om een previous room toe te voegen
     * @param room de kamer om te te voegen
     */
    public void addPreviousRoom(Room room)
    {
        previousRooms.put(previousRooms.size() + 1, room);
    }
    /**
     * Methode om een previous room te verwijderen, bij de key in de hashmap(de roomid)
     * @param key
     */
    public void removePreviousRoom(int key)
    {
        previousRooms.remove(key);
    }

    /**
     * Methode om een kamer te krijgen via de id
     * @param id van de kamer
     * @return  de kamer bij het bijbehorende id
     */
    public Room getRoomById(int id)
    {
        return previousRooms.get(id);
    }

    /**
     * Methode om te checken of een bepaald item in de inventory zit
     * @param itemName de naam van het item
     * @return true als hij er in zit, anders false
     */
    public boolean checkInventory(String itemName)
    {
        for (Item item : inventory.getItems()) 
        {
            if(itemName.equalsIgnoreCase(item.getName()))
            {
                return true;
            }
        }

        return false;
    }

    /**
     * methode om een item toe te voegen aan de inventaris van de gebruiker
     * @param item het item
     */
    public void addItem(Item item)
    {
        inventory.addItem(item);
    }

    /**
     * methode om een item te verwijderen uit het inventaris van de gebruiker
     * @param item het item
     */
    public void removeItem(Item item)
    {
        inventory.removeItem(item);
    }

    /**
     * getter voor de spelers' inventaris
     * @return inventory
     */
    public Inventory getInventory() {
        return inventory;
    }
    /**
     * setter voor de spelers' inventaris
     * @param inventory
     */
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    /**
     * getter voor de roommanager
     * @return roommanager
     */
    public RoomManager getRoomManager() {
        return roomManager;
    }
    /**
     * setter voor de roommanager
     * @param roommanager
     */
    public void setRoomManager(RoomManager roomManager) {
        this.roomManager = roomManager;
    }
    /**
     * getter voor de hiding bool
     * @return isHiding
     */
    public Boolean getIsHiding() {
        return isHiding;
    }
    /**
     * setter voor de hiding bool
     * @param isHiding
     */
    public void setIsHiding(Boolean isHiding) {
        this.isHiding = isHiding;
    }
}