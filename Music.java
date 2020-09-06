package dx_ball;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Music
{
    
    void playmusic(String musiclocation)
    {
            try
            {
                File musicpath = new File(musiclocation);
                if(musicpath.exists())
                {
                    AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicpath);
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInput);
                    clip.start();
                }
            } 
            catch (Exception e)
            {
                e.printStackTrace();
            }
        
    }

}
