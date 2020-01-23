import java.util.Timer;
import java.util.HashMap;
import java.util.Random;
import java.util.TimerTask;
import java.util.ArrayList;
/**
*   Deze klasse beschrijft een enemy
*   @author Justin Molema, Johan van Hengelaar
*   @version 23-1-2020
*/
public class Enemy
{
    private Room currentRoom;
    private Room nextRoom;
    private Room nextNextRoom;
    private Random random;

    private RoomManager rManager;

    private ArrayList<Room> currentPath;
    private ArrayList<Room> tempPath;
    
    /**
     * Constructor van de enemy
     * @param startingRoom de kamer waarin de enemy moet starten
     * @param rManager referentie naar de roommanager, nodig om bij de locatie van de speler te kunnen komen
     */
    public Enemy(Room startingRoom, RoomManager rManager)
    {
        this.rManager = rManager;
        rManager.addEnemies(this);
        
        currentRoom = startingRoom;
        Timer timer = new Timer();
        timer.schedule(new EnemyTask(this), 5000);
    }

    /**
     *  returns a random room of all rooms connected to the given room
     * @param room  the room given
     * @return  the chosen room
     */
    private Room getRandomRoomNextToRoom(Room room)
    {
        random = new Random();
        Room[] roomArray = room.connectedRooms.values().toArray(new Room[room.connectedRooms.size()]);
        int rand = 0;
        if(roomArray.length - 1 >= 1)
        {
            rand = random.nextInt(roomArray.length);
        }
        if(roomArray.length > 0)
        {
            return roomArray[rand];
        }
        else 
        {
            // code should only get here if the current room has no connections, which should be never
            return new Room("broken", 69);
        }
    }
    /**
     * methode to go to the next room, don't call this, it gets called by the timertask
     */
    private void goToNextRoom()
    {
        if(nextRoom == null)
        {
            nextRoom = getRandomRoomNextToRoom(currentRoom);
            nextNextRoom = getRandomRoomNextToRoom(nextRoom);
        }
        currentRoom = nextRoom;
        if(rManager.getPlayer().getCurrentRoom() == nextNextRoom)
        {
            System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "You hear steps heading your way..." + ConsoleColors.RESET);
        }
        if(rManager.getPlayer().getCurrentRoom() == nextRoom)
        {
            System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "The steps are really close now..." + ConsoleColors.RESET);
        }
        if(rManager.getPlayer().getCurrentRoom() == currentRoom)
        {
            if(!rManager.getPlayer().getIsHiding())
            {
                if(rManager.getPlayer().checkInventory("gun"))
                {
                    // gunshot sound here
                    System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "You shot the monster" + ConsoleColors.RESET);
                    Die();
                }
                else
                {
                    GameManager.endGame(ConsoleColors.RED_BOLD_BRIGHT + "You have been murdered by the nurse" + ConsoleColors.RESET);
                }
            }
            else
            {
                System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Someone is in the room with you... DON'T MOVE..." + ConsoleColors.RESET);
            }
            
        }
            nextRoom = nextNextRoom;
            nextNextRoom = getRandomRoomNextToRoom(nextRoom);
            Timer timer = new Timer();
            timer.schedule(new EnemyTask(this), 5000);
    }

    /**
     *   Een timer task voor de enemy
     *   @author
     *   @version 23-1-2020
     */
    class EnemyTask extends TimerTask
    {

        private Enemy enemy;
        /**
         * constructor van de task geef de enemy mee
         * @param enemy
         */
        public EnemyTask(Enemy enemy)
        {
            this.enemy = enemy;
        }

        /**
         * wordt aangeroepen in timertask zelf na x seconden, en zegt dan dat de enemy naar de volgende kamer moet
         */
        public void run()
        {
            enemy.goToNextRoom();
        }
    }
    /**
     * getter om de volgende kamer te krijgen
     * @return de volgende kamer
     */
    public Room getNextRoom() {
        return nextRoom;
    }
    /**
     * setter om de volgende kamer te zetten
     * @param de volgende kamer
     */
    public void setNextRoom(Room nextRoom) {
        this.nextRoom = nextRoom;
    }
    /**
     * getter om de volgende kamer na de volgende kamer te krijgen
     * @return de volgende kamer
     */
    public Room getNextNextRoom() {
        return nextNextRoom;
    }
    /**
     * setter om de volgende kamer na de volgende kamer te zetten
     * @param de volgende kamer
     */
    public void setNextNextRoom(Room nextNextRoom) {
        this.nextNextRoom = nextNextRoom;
    }
    /**
     * getter om de huidige kamer te krijgen
     * @return de huidige kamer
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }
    /**
     * setter om de huidige kamer te zetten
     * @param de huidige kamer
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * wordt aangeroepen wanneer de enemy dood gaat, zet de kamers van de enemy op een nieuwe kamer buiten de map
     */
    private void Die()
    {
        currentRoom = new Room("", 100);
        nextRoom = new Room("", 100);
        nextNextRoom = new Room("", 100);
    }
}