import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
/**
*   Deze klasse is de hoofdklasse van de toepassing, als je bluej gebruikt, roep dan de main method aan
*   @author Justin Molema, Johan van Hengelaar
*   @version 23-1-2020
*/
public class GameManager
{
    public enum GameState
    {
        INGAME,
        INMENU
    }

    
    public static void main(String[] args)
    {
        startGame();
    }
    /** 
    * Methode om de game te starten, dit is een aparte methode zodat hij in run time opnieuw gestart kan worden
    */
    private static void startGame()
    {
        Player player = new Player();
        GameState gameState = GameState.INMENU;
        System.out.println("Welcome!\nPress "+ConsoleColors.CYAN_BRIGHT+"Start"+ConsoleColors.RESET+
        " to start the game\nPress "+ConsoleColors.CYAN_BRIGHT+"About"+ConsoleColors.RESET+" to learn about the game\nPress "+ConsoleColors.CYAN_BRIGHT+"Quit"+ConsoleColors.RESET+" to quit the game");
        RoomManager roomManager = new RoomManager(player);
        
        String input;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try 
        { 
            while((input = reader.readLine()) != null)
            {
                // the menu
                if(gameState == GameState.INMENU)
                {
                    if(input.equalsIgnoreCase("start"))
                    {
                        gameState = GameState.INGAME;
                        System.out.println(ConsoleColors.RED_UNDERLINED + "You wake up in a decrepid hospital, you don't know how you got here. \n try to look for clues!" + ConsoleColors.RESET);
                        roomManager = new RoomManager(player);
                        player.setRoomManager(roomManager);
                        roomManager.generateMap();
                    }
                    else if(input.equalsIgnoreCase("about"))
                    {
                        System.out.println("This game is a survival horror experience not for the faint of heart! \nThe player is tasked with escaping a abandoned haunted hospital \nTo do so you must collect items to open a path towards the exit, but beware of the monsters!");
                    }
                    else if(input.equalsIgnoreCase("quit"))
                    {
                        System.out.println("you quit the game");
                        System.exit(0);
                    }
                    else
                    {
                        System.out.println("command doesn't exist");
                    }
                }

                // ingame
                else if(gameState == GameState.INGAME)
                {
                    if(!player.getIsHiding())
                    {
                        // used for checking whether a actual command is put in
                        Boolean hasInputted = false;
                        if(input.equalsIgnoreCase("info"))
                        {
                            System.out.println("You are now in: " + ConsoleColors.BLUE_BRIGHT + player.getCurrentRoom().roomName + ConsoleColors.RESET);
                            hasInputted = true;
                        }

                        if(input.equalsIgnoreCase("commands"))
                        {
                            System.out.println("You can use the following commands:\n"+ConsoleColors.CYAN_BRIGHT+"info"+ConsoleColors.RESET+
                            " for information\n"+ConsoleColors.CYAN_BRIGHT+"enter the 'roomname'"+ConsoleColors.RESET+" to enter a room\n"
                            +ConsoleColors.CYAN_BRIGHT+"use 'itemname'"+ConsoleColors.RESET+" to use an item\n"+ConsoleColors.CYAN_BRIGHT+"back"
                            +ConsoleColors.RESET+" to go back\n"+ConsoleColors.CYAN_BRIGHT+"search the room"+ConsoleColors.RESET+" to look for items\n"
                            +ConsoleColors.CYAN_BRIGHT+"check inventory"+ConsoleColors.RESET+" to check your items\n"+ConsoleColors.CYAN_BRIGHT+"(un)hide "+ConsoleColors.RESET + "to hide/unhide when hidden\n"
                            +ConsoleColors.CYAN_BRIGHT+"get 'itemname'"+ConsoleColors.RESET + " to get item from room\n"
                            +ConsoleColors.CYAN_BRIGHT+"drop 'itemname'"+ConsoleColors.RESET + " to drop item in room\n"
                            +ConsoleColors.CYAN_BRIGHT+"exit"+ConsoleColors.RESET + " to return to the main menu");
                            hasInputted = true;
                        }

                        for (int i : player.getCurrentRoom().connectedRooms.keySet()) 
                        {
                            Room room = player.getCurrentRoom().connectedRooms.get(i);
                            if(room != null)
                            {
                                if(input.equalsIgnoreCase(room.command))
                                {
                                    // check the lock
                                    if(!player.getCurrentRoom().connectedRoomsLocks.get(i))
                                    {
                                        if(room.roomName == "front door")
                                        {
                                            endGame("You succesfully escaped!");
                                            break;
                                        }

                                        if(room.roomName == "traproom")
                                        {
                                            System.out.println("You've fallen in a trap!");
                                            player.setCurrentRoom(roomManager.getTrapTarget());
                                            player.getCurrentRoom().onEnter();
                                            player.getPreviousRooms().clear();
                                        }
                                        else
                                        {
                                            player.setCurrentRoom(room);
                                            player.getCurrentRoom().onEnter();
                                            player.addPreviousRoom(player.getCurrentRoom());
                                        }
                                        
                                    }
                                    else
                                    {
                                        System.out.println("the door is locked");
                                    }
                                    hasInputted = true;
                                }
                                if(input.equalsIgnoreCase("use " + room.keyName))
                                {
                                    // check the lock
                                    if(player.getCurrentRoom().connectedRoomsLocks.get(i))
                                    {
                                        // check player inventory for right key
                                        if(player.checkInventory(room.keyName))
                                        {
                                            player.getCurrentRoom().connectedRoomsLocks.replace(i, false);
                                            System.out.println("unlocked the " + ConsoleColors.BLUE_BRIGHT + room.roomName + ConsoleColors.RESET + " door");
                                        }
                                        else
                                        {
                                            System.out.println("you do not have the right key for this door");
                                        }
                                    }
                                    else
                                    {
                                        System.out.println("You've already unlocked this room");
                                    }
                                    hasInputted = true;
                                }
                            }
                        }
                        
                        // back command
                        if(input.equalsIgnoreCase("back"))
                        {
                            if(player.getPreviousRooms().size() > 1)
                            {
                                player.setCurrentRoom(player.getPreviousRooms().get(player.getPreviousRooms().size()-1));
                                player.getCurrentRoom().onEnter();
                                player.removePreviousRoom(player.getPreviousRooms().size());
                            }
                            else
                            {
                                System.out.println("can't go further back");
                            }
                            hasInputted = true;
                        }

                        if(input.equalsIgnoreCase("search the room"))
                        {
                            for (Item item : player.getCurrentRoom().getRoomInventory().getItems()) 
                            {
                                System.out.println("You've found a: " + ConsoleColors.GREEN_BRIGHT + item.getName() + ConsoleColors.RESET);
                            }
                            if(player.getCurrentRoom().getRoomInventory().getItems().size() <= 0)
                            {
                                System.out.println("You've found nothing");
                            }
                            hasInputted = true;
                        }
                        Item foundItem = null;
                        Item droppedItem = null;
                        for (Item item : player.getInventory().getItems()) 
                        {
                            if(input.equalsIgnoreCase("drop " + item.getName()))
                            {
                                System.out.println("dropped: " + ConsoleColors.GREEN_BRIGHT + item.getName() + ConsoleColors.RESET);
                                droppedItem = item;
                                hasInputted = true;
                            }
                        }
                        for (Item item : player.getCurrentRoom().getRoomInventory().getItems()) 
                        {
                            
                            if(input.equalsIgnoreCase("get " + item.getName()))
                            {
                                if(player.getInventory().getCapacity() + item.getWeight() <= player.getInventory().getMaxCapacity())
                                {
                                System.out.println("Added: " + ConsoleColors.GREEN_BRIGHT + item.getName() + ConsoleColors.RESET + " to inventory");
                                foundItem = item;
                                hasInputted = true;
                                }
                                else
                                {
                                    System.out.println("Your inventory is full!");
                                }
                            }
                        }
                        if(foundItem != null)
                        {
                            player.addItem(foundItem);
                            player.getCurrentRoom().getRoomInventory().removeItem(foundItem);
                        }
                        else if(droppedItem != null)
                        {
                            player.getCurrentRoom().getRoomInventory().addItem(droppedItem);
                            player.removeItem(droppedItem);
                        }

                        if(input.equalsIgnoreCase("check inventory"))
                        {
                            System.out.println("Your backpack is " + ((float)player.getInventory().getCapacity() / (float)player.getInventory().getMaxCapacity() * 100) + "% full");
                            for (Item item : player.getInventory().getItems()) {
                                System.out.println(ConsoleColors.GREEN_BRIGHT + item.getName() + ConsoleColors.RESET);
                            }
                            hasInputted = true;
                        }
                        if(input.equalsIgnoreCase("hide"))
                        {
                            System.out.println("You are now hiding");
                            player.setIsHiding(!player.getIsHiding());
                            hasInputted = true;
                        }

                        if(input.equalsIgnoreCase("exit"))
                        {
                            endGame("returning to the menu");
                            hasInputted = true;
                        }
                        else if(!hasInputted)
                        {
                            System.out.println("command doesn't exist");
                        }
                        hasInputted = false;
                    }
                    else 
                    {
                        if(input.equalsIgnoreCase("unhide"))
                        {
                            
                            for(Enemy enemy : roomManager.getEnemies())
                            {
                                if(player.getCurrentRoom() == enemy.getCurrentRoom())
                                {
                                    endGame(ConsoleColors.RED_BOLD_BRIGHT + "You have been murdered by the nurse" + ConsoleColors.RESET);
                                }
                                else
                                {
                                    System.out.println("You are no longer hiding");
                                    player.setIsHiding(!player.getIsHiding());
                                }
                            }
                        }
                        else
                        {
                            System.out.println("You can't move, you are currently in hiding");
                        }
                    }
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }        
    }

    /**
    *   Deze methode brengt de speler terug naar het hoofdmenu   
    *   @param String text een tekst die eerst uit wordt geprint voordat de speler terug wordt gebracht
    */
    public static void endGame(String text)
    {
        System.out.println(text);
        startGame();
    }
}