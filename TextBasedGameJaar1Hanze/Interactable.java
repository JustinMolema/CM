public abstract class Interactable
{
    // the name of the interactable object
    protected String name;
    // the command used to interact with this object
    protected String command;
    // description of the object
    protected String description;

    public Interactable(String name, String command)
    {
        this.name = name;
        this.command = command;
    }

    public abstract void onInteract();
}