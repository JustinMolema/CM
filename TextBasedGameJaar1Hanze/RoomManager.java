import java.util.ArrayList;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.HashMap;
/**
*   Deze klasse is verantwoordelijk voor het aanmaken en vullen van kamers
*   @author Justin Molema, Johan van Hengelaar
*   @version 23-1-2020
*/
public class RoomManager
{
    private Player player;

    private Room trapTarget;
    private ArrayList<Enemy> enemies;

    /**
    *   @param player zodat hij deze aan de enemies kan geven
    */
    public RoomManager(Player player)
    {
        this.player = player;
        enemies = new ArrayList<>();
    }

    
    /** 
     *  methode die de kamers aanmaakt en vult, met items en enemies
     * 
     */
    public void generateMap()
    {
        // 2nd floor and roof rooms
        Room operatingRoom, secondFloorHallway, secondFloorStairWell, recoveryRoom, roof;
        operatingRoom = new Room("operating room" , 1,"a operating room, there is a bed with blood on it");
        secondFloorHallway = new Room("second floor hallway" , 2, "a dark hallway");
        secondFloorStairWell = new Room("second floor stairwell" , 3, "the second floor stairwell");
        recoveryRoom  = new Room("recovery room" , 4, "a room with beds in it, not really anything to see");
        roof  = new Room("roof" , 5, "the roof of the building, you see a open toolbox in the center");
        // 1st floor rooms
        Room firstFloorStairwell, firstFloorMainHallway, aWingHallway, bWingHallway, recreationalRoom, aWingKeyRoom, aWingRightRoom, utilityCloset, firstFloorElevator, trapRoom, bWingRightRoom, vents;
        firstFloorStairwell = new Room("first floor stairwell" , 6, "the first floor stairwell, you can't go any further down, the stairs have collapsed!");
        firstFloorMainHallway = new Room("first floor main hallway", 7, "a dimly lit hallway, with rubble scattered across the floor");
        aWingHallway = new Room("a wing hallway", 8, "the main hallway of the a wing of the hospital");
        bWingHallway = new Room("b wing hallway", 9, "the main hallway of the b wing of the hospital");
        recreationalRoom = new Room("recreational room", 10, "a room used for recreational purposes");
        aWingKeyRoom = new Room("a wing left room", 11, "a decayed bedroom, there might be an item of interest hidden somewhere!");
        aWingRightRoom = new Room("a wing right room", 12, "a empty bedroom, there seems to be nothing here");
        utilityCloset = new Room("utility closet", 13, "a utility closet");
        firstFloorElevator = new Room("first floor elevator", 14, "a defect elevator, with a hatch with a ladder down open", "crowbar");
        
        bWingRightRoom = new Room("b wing right room", 16, "a empty bedroom, with a foul stench. Better leave quick!");
        vents = new Room("vents", 17, "the vents", "screwdriver");
        // ground floor rooms
        Room groundFloorMainHall, emergencyRoom, employeeHallway, toilets, giftShop, reception, garage;
        groundFloorMainHall = new Room("ground floor main hall", 18, "the hospitals main hall on the ground floor, parts of the roof seem to have collapsed");
        emergencyRoom = new Room("emergency room", 19, "the hospital's emergency room. There seem to still be bodies decaying here, as if left behind by the hospital's staff");
        employeeHallway = new Room("employee hallway", 20, "the employee hallway");
        toilets = new Room("toilets", 21, "the toilets");
        giftShop = new Room("giftshop", 22, "the hospital's abandoned gift shop");
        reception = new Room("reception", 23, "the hospital's reception");
        garage = new Room("garage", 24, "the hospital's garage. There might be something worth taking in the ambulances!");
        // basement rooms
        Room basementHallwayA, basementHallwayB, laboratory, tunnels, generatorRoom, utilityRoom, morgue, basementKeyRoom, storageRoom, secrethallway, basementElevator;
        basementHallwayA = new Room("dark basement hallway", 25, "a dark basement hallway");
        basementHallwayB = new Room("flooded hallway", 28, "a dimly lit basement hallway. The floor seems to be slightly flooded here due to a burst pipe");
        laboratory = new Room("laboratory", 26, "a dark laboratory. There seems to be body parts scattered around the place. There also is a hole in the wall, who knows where that will lead to");
        tunnels = new Room("tunnels", 27, "a dark tunnel");
        generatorRoom = new Room("generator room", 29, "the generator room. The generator seems to be damaged beyond repair");
        utilityRoom = new Room("utility room", 30, "a utility room. There is a lot of medical equipment here, none of which you recognize");
        morgue = new Room("morgue", 31, "the hospitals morgue. There might be an item in one of the lockers.");
        basementKeyRoom = new Room("small storage room", 32, "a dark storage room, with vials of a strange residue sitting on a shelf. There might also be a item around here somewhere", "rusty key");
        storageRoom = new Room("storage room", 33, "a storage room, there is a body of what looks like a security guard in the corner. He is holding a gun");
        secrethallway = new Room("dark hallway", 34, "a short dark hallway");
        basementElevator = new Room("basement elevator", 35, "the bottom of the elevator shaft. There is a ladder going up");
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
        
        bWingHallway.addConnectedRoom(firstFloorMainHallway, false);
        firstFloorElevator.addConnectedRoom(bWingHallway, false);
        firstFloorElevator.addConnectedRoom(basementElevator, false);
        
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
        utilityRoom.addConnectedRoom(tunnels, false);
        utilityRoom.addConnectedRoom(basementHallwayB, false);
        generatorRoom.addConnectedRoom(basementHallwayB, false);
        basementKeyRoom.addConnectedRoom(storageRoom, false);
        basementKeyRoom.addConnectedRoom(basementHallwayB, true);
        storageRoom.addConnectedRoom(basementKeyRoom, false);
        storageRoom.addConnectedRoom(secrethallway, false);
        secrethallway.addConnectedRoom(basementElevator, false);
        secrethallway.addConnectedRoom(storageRoom, false);
        morgue.addConnectedRoom(basementHallwayB, false);
        basementElevator.addConnectedRoom(secrethallway, false);
        basementElevator.addConnectedRoom(firstFloorElevator, false);
        // add item to rooms
        aWingKeyRoom.addItem(new Item("b wing hallway key", 10));
        morgue.addItem(new Item("front door key", 10));
        garage.addItem(new Item("crowbar", 70));
        roof.addItem(new Item("screwdriver", 50));
        storageRoom.addItem(new Item("gun", 30));
        basementKeyRoom.addItem(new Item("rusty key", 10));
        // the escape
        Room escape = new Room("front door", 36, "escape this place", "front door key");
        reception.addConnectedRoom(escape, true);
        // the traproom
        trapRoom = new Room("left room", 15, "enter the left room", "desc");
        bWingHallway.addConnectedRoom(trapRoom, false);
        trapRoom.addConnectedRoom(bWingHallway, false);
        trapTarget = toilets;
        // the enemy
        Enemy enemy = new Enemy(morgue, this);
        Enemy enemy2 = new Enemy(garage, this);
        player.setCurrentRoom(operatingRoom);
        player.getCurrentRoom().onEnter();
        player.addPreviousRoom(player.getCurrentRoom());
    }

    /**
     * getter voor de player
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * setter voor de player
     * @param player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * getter voor de enemies
     * @return enemies
     */
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    /**
    *   Methode om een enemy aan enemies toe te voegen
    *   @param enemy de enemy die toegevoegt moet worden
    */
    public void addEnemies(Enemy enemy) 
    {
        enemies.add(enemy);
    }

    /**
     * getter om het doel van de trapkamer te verkrijgen
     * @return trapTarget
     */
    public Room getTrapTarget() {
        return trapTarget;
    }

    /**
     * setter om het doel van de trapkamer te zetten
     * @param Room traptarget
     */
    public void setTrapTarget(Room trapTarget) {
        this.trapTarget = trapTarget;
    }
}