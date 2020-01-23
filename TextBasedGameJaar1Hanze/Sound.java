/**
*   Deze klasse is voor het afspelen van muziek.
*   @author Justin Molema, Johan van Hengelaar
*   @version 23-1-2020
*/
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;

public class Sound
{
    /** 
    * Methode om een geluid af te spelen.
    */
    public void play(String soundName)
    {
        try{
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(soundName));
            clip.open(inputStream);
            clip.start();
        }
        catch (Exception e){
            System.out.println("Play sound error: " + e.getMessage() + " for " + soundName);
        }
    }
}
