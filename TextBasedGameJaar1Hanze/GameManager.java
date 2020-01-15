import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.lang.model.util.ElementScanner6;
public class GameManager
{
    
    public static void main(String[] args)
    {
        Player player = new Player("Player One");

        System.out.println(ConsoleColors.RED_UNDERLINED + "You wake up in a decrepid hospital, you don't know how you got here. \n try to look for clues!" + ConsoleColors.RESET);
        RoomManager roomManager = new RoomManager();


        String input;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try 
        { 
            while((input = reader.readLine()) != null)
            {
                
                if(input.equalsIgnoreCase("info"))
                {
                    System.out.println("You are now in: " + ConsoleColors.BLUE_BRIGHT + roomManager.getCurrentRoom().roomName + ConsoleColors.RESET);
                }

                if(input.equalsIgnoreCase("commands"))
                {
                    System.out.println("You can use the following commands:\n"+ConsoleColors.CYAN_BRIGHT+"info"+ConsoleColors.RESET+
                    " for information\n"+ConsoleColors.CYAN_BRIGHT+"enter the 'roomname'"+ConsoleColors.RESET+" to enter a room\n"
                    +ConsoleColors.CYAN_BRIGHT+"use 'itemname'"+ConsoleColors.RESET+" to use an item\n"+ConsoleColors.CYAN_BRIGHT+"back"
                    +ConsoleColors.RESET+" to go back\n"+ConsoleColors.CYAN_BRIGHT+"search the room"+ConsoleColors.RESET+" to look for and pickup items\n"
                    +ConsoleColors.CYAN_BRIGHT+"check inventory"+ConsoleColors.RESET+" to check your items");
                }

                for (int i : roomManager.getCurrentRoom().connectedRooms.keySet()) 
                {
                    Room room = roomManager.getCurrentRoom().connectedRooms.get(i);
                    if(room != null)
                    {
                        if(input.equalsIgnoreCase(room.command))
                        {
                            // check the lock
                            if(!roomManager.getCurrentRoom().connectedRoomsLocks.get(i))
                            {
                                roomManager.setCurrentRoom(room);
                                roomManager.getCurrentRoom().onEnter();
                                roomManager.addPreviousRoom(roomManager.getCurrentRoom());
                            }
                            else
                            {
                                System.out.println("the door is locked");
                            }
                        }
                        if(input.equalsIgnoreCase("use " + room.keyName))
                        {
                            // check the lock
                            if(roomManager.getCurrentRoom().connectedRoomsLocks.get(i))
                            {
                                // check player inventory for right key
                                if(player.checkInventory(room.keyName))
                                {
                                    roomManager.getCurrentRoom().connectedRoomsLocks.replace(i, false);
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
                        }
                    }
                }
                
                // back command
                if(input.equalsIgnoreCase("back"))
                {
                    if(roomManager.getPreviousRooms().size() > 1)
                    {
                        roomManager.setCurrentRoom(roomManager.getPreviousRooms().get(roomManager.getPreviousRooms().size()-1));
                        roomManager.getCurrentRoom().onEnter();
                        roomManager.removePreviousRoom(roomManager.getPreviousRooms().size());
                    }
                    else
                    {
                        System.out.println("can't go further back");
                    }
                }

                if(input.equalsIgnoreCase("search the room"))
                {
                    Item foundItem = null;
                    for (Item item : roomManager.getCurrentRoom().getRoomInventory().getItems()) {
                        System.out.println("You've found a: " + ConsoleColors.GREEN_BRIGHT + item.getName() + ConsoleColors.RESET);
                        foundItem = item;
                    }
                    if(foundItem != null)
                    {
                        player.addItem(foundItem);
                        roomManager.getCurrentRoom().getRoomInventory().removeItem(foundItem);
                    }
                }
                if(input.equalsIgnoreCase("check inventory"))
                {
                    System.out.println("Your backpack is " + ((float)player.getInventory().getCapacity() / (float)player.getInventory().getMaxCapacity() * 100) + "% full");
                    for (Item item : player.getInventory().getItems()) {
                        System.out.println(ConsoleColors.GREEN_BRIGHT + item.getName() + ConsoleColors.RESET);
                    }
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }        
    }

    
}