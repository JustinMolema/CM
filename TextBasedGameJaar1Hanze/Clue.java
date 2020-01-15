public class Clue extends Interactable
{
    private String content;

    public Clue(String name, String command, String content)
    {
        super(name, command);
        this.content = content;
    }

    public void onInteract()
    {
        System.out.println(ConsoleColors.PURPLE_UNDERLINED + content + ConsoleColors.RESET);
    }
}