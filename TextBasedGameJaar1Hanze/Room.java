import java.util.HashMap;
public class Room
{
    public String roomName;
    public int id;
    public String command;
    //public boolean locked;
    public String keyName;

    public HashMap<Integer, Room> connectedRooms;
    public HashMap<Integer, Boolean> connectedRoomsLocks;

    private Inventory roomInventory;

    public Room(String roomName, int id)
    {
        this.roomName = roomName;
        this.id = id;
        command = "enter the " + roomName;
        keyName = roomName + " key";
        setup();
    }

    public Room(String roomName, int id, String command)
    {
        this.roomName = roomName;
        this.id = id;
        this.command = command;
        keyName = roomName + " key";
        setup();
    }

    public Room(String roomName, int id, String command, String keyName)
    {
        this.roomName = roomName;
        this.id = id;
        this.command = command;
        this.keyName = keyName;
        setup();
    }

    private void setup()
    {
        connectedRooms = new HashMap<>();
        connectedRoomsLocks = new HashMap<>();
        roomInventory = new Inventory(100000000);
    }

    public void addConnectedRoom(Room room, boolean locked)
    {
        connectedRooms.put(room.id, room);
        connectedRoomsLocks.put(room.id, locked);
    }

    public void addItem(Item item)
    {
        roomInventory.addItem(item);
    }

    public void removeItem(Item item)
    {
        roomInventory.removeItem(item);
    }

    public void onEnter()
    {
        System.out.println("You've entered " + ConsoleColors.BLUE_BRIGHT + roomName + ConsoleColors.RESET);
        for (int i : connectedRooms.keySet()) 
        {
            System.out.println("You can go to: " + ConsoleColors.BLUE_BRIGHT + connectedRooms.get(i).roomName + ConsoleColors.RESET);
        }
    }

    public Inventory getRoomInventory() {
        return roomInventory;
    }

    public void setRoomInventory(Inventory roomInventory) {
        this.roomInventory = roomInventory;
    }
}