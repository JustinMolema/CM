import java.util.ArrayList;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.HashMap;
public class RoomManager
{

    private Room currentRoom;
    private HashMap<Integer, Room> previousRooms;

    public RoomManager()
    {
        previousRooms = new HashMap<Integer, Room>();
        generateMap();
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public HashMap<Integer, Room> getPreviousRooms() {
        return previousRooms;
    }

    public void setPreviousRoom(HashMap<Integer, Room> previousRooms) {
        this.previousRooms = previousRooms;
    }

    public void addPreviousRoom(Room room)
    {
        previousRooms.put(previousRooms.size() + 1, room);
    }

    public void removePreviousRoom(int key)
    {
        previousRooms.remove(key);
    }

    // get a room by its key value in the hashmap
    public Room getRoomById(int id)
    {
        return previousRooms.get(id);
    }

    private void generateMap()
    {
        // 2nd floor and roof rooms
        Room operatingRoom, secondFloorHallway, secondFloorStairWell, recoveryRoom, roof;
        operatingRoom = new Room("operating room" , 1);
        secondFloorHallway = new Room("second floor hallway" , 2);
        secondFloorStairWell = new Room("second floor stairwell" , 3);
        recoveryRoom  = new Room("recovery room" , 4);
        roof  = new Room("roof" , 5);
        // 1st floor rooms
        Room firstFloorStairwell, firstFloorMainHallway, aWingHallway, bWingHallway, recreationalRoom, aWingKeyRoom, aWingRightRoom, utilityCloset, firstFloorElevator, trapRoom, bWingRightRoom, vents;
        firstFloorStairwell = new Room("first floor stairwell" , 6);
        firstFloorMainHallway = new Room("first floor main hallway", 7);
        aWingHallway = new Room("a wing hallway", 8);
        bWingHallway = new Room("b wing hallway", 9);
        recreationalRoom = new Room("recreational room", 10);
        aWingKeyRoom = new Room("a wing key room", 11);
        aWingRightRoom = new Room("a wing right room", 12);
        utilityCloset = new Room("utility closet", 13);
        firstFloorElevator = new Room("firstFloorElevator", 14, "enter the elevator", "crowbar");
        trapRoom = new Room("traproom", 15);
        bWingRightRoom = new Room("b wing right room", 16);
        vents = new Room("vents", 17, "enter the vents", "screwdriver");
        // ground floor rooms
        Room groundFloorMainHall, emergencyRoom, employeeHallway, toilets, giftShop, reception, garage;
        groundFloorMainHall = new Room("ground floor main hall", 18);
        emergencyRoom = new Room("emergency room", 19);
        employeeHallway = new Room("employee hallway", 20);
        toilets = new Room("toilets", 21);
        giftShop = new Room("giftshop", 22);
        reception = new Room("reception", 23);
        garage = new Room("garage", 24);
        // basement rooms
        Room basementHallwayA, basementHallwayB, laboratory, tunnels, generatorRoom, utilityRoom, morgue, basementKeyRoom, storageRoom, secrethallway, basementElevator;
        basementHallwayA = new Room("basementHallwayA", 25);
        basementHallwayB = new Room("basementHallwayB", 28);
        laboratory = new Room("laboratory", 26);
        tunnels = new Room("tunnels", 27);
        generatorRoom = new Room("generatorRoom", 29);
        utilityRoom = new Room("utilityRoom", 30);
        morgue = new Room("morgue", 31);
        basementKeyRoom = new Room("keyRoom", 32, "enter the keyRoom", "rusty key");
        storageRoom = new Room("storageRoom", 33);
        secrethallway = new Room("secrethallway", 34);
        basementElevator = new Room("basementElevator", 35);
        // connections for the 2nd floor and roof
        operatingRoom.addConnectedRoom(secondFloorHallway, false);
        secondFloorHallway.addConnectedRoom(operatingRoom, false);
        secondFloorHallway.addConnectedRoom(secondFloorStairWell, false);
        secondFloorHallway.addConnectedRoom(recoveryRoom, false);
        secondFloorStairWell.addConnectedRoom(firstFloorStairwell, false);
        secondFloorStairWell.addConnectedRoom(roof, false);
        roof.addConnectedRoom(secondFloorStairWell, false);
        recoveryRoom.addConnectedRoom(secondFloorHallway, false);
        // first floor connections
        firstFloorStairwell.addConnectedRoom(firstFloorMainHallway, false);
        firstFloorStairwell.addConnectedRoom(secondFloorStairWell, false);
        firstFloorMainHallway.addConnectedRoom(firstFloorStairwell, false);
        firstFloorMainHallway.addConnectedRoom(aWingHallway, false);
        firstFloorMainHallway.addConnectedRoom(bWingHallway, true);
        firstFloorMainHallway.addConnectedRoom(utilityCloset, false);
        aWingHallway.addConnectedRoom(firstFloorMainHallway, false);
        aWingHallway.addConnectedRoom(aWingKeyRoom, false);
        aWingHallway.addConnectedRoom(aWingRightRoom, false);
        aWingHallway.addConnectedRoom(recreationalRoom, false);
        aWingRightRoom.addConnectedRoom(aWingHallway, false);
        aWingKeyRoom.addConnectedRoom(aWingHallway, false);
        recreationalRoom.addConnectedRoom(aWingHallway, false);
        bWingHallway.addConnectedRoom(firstFloorElevator, true);
        bWingHallway.addConnectedRoom(bWingRightRoom, false);
        bWingHallway.addConnectedRoom(trapRoom, false);
        bWingHallway.addConnectedRoom(firstFloorMainHallway, false);
        firstFloorElevator.addConnectedRoom(bWingHallway, false);
        firstFloorElevator.addConnectedRoom(basementElevator, false);
        trapRoom.addConnectedRoom(bWingHallway, false);
        bWingRightRoom.addConnectedRoom(bWingHallway, false);
        utilityCloset.addConnectedRoom(firstFloorMainHallway, false);
        utilityCloset.addConnectedRoom(vents, true);
        vents.addConnectedRoom(utilityCloset, false);
        vents.addConnectedRoom(groundFloorMainHall, false);
        // ground floor connections
        groundFloorMainHall.addConnectedRoom(vents, false);
        groundFloorMainHall.addConnectedRoom(emergencyRoom, false);
        groundFloorMainHall.addConnectedRoom(employeeHallway, false);
        groundFloorMainHall.addConnectedRoom(giftShop, false);
        groundFloorMainHall.addConnectedRoom(toilets, false);
        emergencyRoom.addConnectedRoom(groundFloorMainHall, false);
        employeeHallway.addConnectedRoom(groundFloorMainHall, false);
        employeeHallway.addConnectedRoom(garage, false);
        garage.addConnectedRoom(employeeHallway, false);
        toilets.addConnectedRoom(groundFloorMainHall, false);
        giftShop.addConnectedRoom(groundFloorMainHall, false);
        giftShop.addConnectedRoom(reception, false);
        reception.addConnectedRoom(giftShop, false);
        groundFloorMainHall.addConnectedRoom(basementHallwayA, false);
        // basement connections
        basementHallwayA.addConnectedRoom(groundFloorMainHall, false);
        basementHallwayA.addConnectedRoom(laboratory, false);
        basementHallwayA.addConnectedRoom(basementHallwayB, false);
        basementHallwayB.addConnectedRoom(basementHallwayA, false);
        basementHallwayB.addConnectedRoom(generatorRoom, false);
        basementHallwayB.addConnectedRoom(basementKeyRoom, true);
        basementHallwayB.addConnectedRoom(utilityRoom, false);
        basementHallwayB.addConnectedRoom(morgue, false);
        laboratory.addConnectedRoom(basementHallwayA, false);
        laboratory.addConnectedRoom(tunnels, false);
        tunnels.addConnectedRoom(laboratory, false);
        tunnels.addConnectedRoom(utilityRoom, false);
        generatorRoom.addConnectedRoom(basementHallwayB, false);
        basementKeyRoom.addConnectedRoom(storageRoom, false);
        basementKeyRoom.addConnectedRoom(basementHallwayB, true);
        storageRoom.addConnectedRoom(basementKeyRoom, false);
        storageRoom.addConnectedRoom(secrethallway, false);
        secrethallway.addConnectedRoom(basementElevator, false);
        morgue.addConnectedRoom(basementHallwayB, false);
        // add item to rooms
        aWingKeyRoom.addItem(new Item("b wing hallway key"));
        morgue.addItem(new Item("front door key"));
        garage.addItem(new Item("crowbar"));
        roof.addItem(new Item("screwdriver"));
        basementKeyRoom.addItem(new Item("rusty key"));
        // the escape
        Escape escape = new Escape("front door", 36, "escape this place", "front door key");
        reception.addConnectedRoom(escape, true);

        currentRoom = operatingRoom;
        currentRoom.onEnter();
        addPreviousRoom(currentRoom);
    } 
}