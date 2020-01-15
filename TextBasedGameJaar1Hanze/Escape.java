public class Escape extends Room
{
    public Escape(String roomName, int id, String command, String keyName)
    {
        super(roomName, id, command, keyName);
    }

    public void OnEnter()
    {
        System.out.println("You've Escaped the hospital!");
    }
}