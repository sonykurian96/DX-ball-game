package dx_ball;

import javax.swing.*;

public class Main {
    public static void main(String[] args)
    {
        //music (only .wav file)
        String filepath = "bgsound.wav";
        Music object = new Music();
        object.playmusic(filepath);

        //creating two objects
        JFrame obj = new JFrame();
        Gameplay gameplay = new Gameplay();

        // basic operations for our window
        obj.setSize(700, 600);
        obj.setTitle("DX-Ball");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // adding another class object to our main class
        obj.add(gameplay);
    }

}
