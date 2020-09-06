package dx_ball;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Gameplay extends JPanel implements ActionListener, KeyListener
{
    private boolean play = false;
    private int score = 0;
    private int totalbricks = 50;

    // Timer
    private Timer timer;
    private int delay = 8;

    // countdown Timer
    private int s_delay = 5000;

    // paddle
    private int playerx = 350;

    // ball variables - position ,speed,etc
    private int ballposx = 120;
    private int ballposy = 350;
    private int ballxdir = -2;
    private int ballydir = -4;

    // bgimage
    Image img = Toolkit.getDefaultToolkit().createImage("bgimage.jpg");

    private MapGenerator map;
    // Main constructor
    public Gameplay()
    {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
        map = new MapGenerator(5,10);
    }
    public void paint(Graphics g)
    {
        // BACKGROUND
        g.drawImage(img, 0, 0, null);

        // paddle
        g.setColor(Color.white);
        g.fillRoundRect(playerx, 540, 150, 15, 30, 20);

        // the ball
        g.setColor(Color.magenta);
        g.fillOval(ballposx, ballposy, 20, 20);

        // bricks
        map.draw((Graphics2D)g);

        // score
        g.setColor(Color.white);
        g.setFont(new Font("serif",Font.BOLD,30));
        g.drawString("Score : "+score,550,30);

        // timer
        if(s_delay <= 1000 && s_delay >=0) {
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Time : " + s_delay / 100 + " s", 10, 30);
        }
        else if(s_delay <= 2000 && s_delay >=1000)
        {
            g.setColor(Color.yellow);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Time : " + s_delay / 100 + " s", 10, 30);
        }
        else
        {
            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Time : " + s_delay / 100 + " s", 10, 30);
        }

        // gameover
        if(ballposy>=590 || s_delay <= 10) {
            play = false;
            ballxdir=0;
            ballydir=0;

            g.setColor(Color.white);
            g.setFont(new Font("serif",Font.BOLD,40));
            g.drawString("Game Over !!! ",220,300);

            g.setColor(Color.red);
            g.drawString("You Lost",250, 350);

            g.setColor(Color.white);
            g.drawString("Press Enter to restart ",150, 400);
        }

        // if player wins then....
        if(totalbricks <= 0)
        {
            play = false;
            ballxdir=0;
            ballydir=0;

            g.setColor(Color.white);
            g.setFont(new Font("serif",Font.BOLD,40));
            g.drawString("Game Over !!! ",220,300);

            g.setColor(Color.green);
            g.drawString("You Won !! 😊",240, 350);

            g.setColor(Color.white);
            g.drawString("Press Enter to restart ",150, 400);
        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(play)
        {
            s_delay--;
            // initially ball will move towards top left
            ballposx += ballxdir;
            ballposy += ballydir;

            // boundary conditions
            if(ballposx < 0)
                ballxdir = -ballxdir;    // will change ball direction
            if(ballposy < 0)
                ballydir = -ballydir;   // will change ball direction
            if(ballposx > 670)
                ballxdir = -ballxdir;

            // collision with slider
            Rectangle ballrect = new Rectangle(ballposx,ballposy, 20, 20);
            Rectangle slider_rect = new Rectangle(playerx, 540, 150, 15);
            if(ballrect.intersects(slider_rect))
                ballydir = -ballydir;

            A:for(int i=0;i<map.map.length;i++)
            {
                for(int j=0;j<map.map[0].length;j++)
                {
                    if(map.map[i][j]>0)
                    {
                        int width=map.brickwidth;
                        int height=map.brickheight;
                        int brickxpos=80 + j*width;
                        int brickypos=50 + i*height;

                        Rectangle brickRect = new Rectangle(brickxpos,brickypos, width, height);
                        if(ballrect.intersects(brickRect))
                        {
                            map.setBrick(0, i, j);
                            totalbricks--;
                            score += 1;
                                         // 19                      // 1
                            if((ballposx + 25 <= brickxpos )||(ballposx + 1 >=brickxpos + width ))
                                ballxdir = -ballxdir;
                            else
                                ballydir = -ballydir;

                            break A;
                        }

                    }
                }
            }
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) { }

    // player movement
    @Override
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            if(playerx >= 530)
                playerx = 530;
            else
            {
                play = true;
                playerx += 20;
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            if(playerx <= 0)
                playerx = 0;
            else
            {
                play = true;
                playerx -= 20;
            }
        }
        // Restart option
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if(!play)
            {
                score = 0;
                totalbricks = 50;
                ballposx = 120;
                ballposy = 350;
                ballxdir = -2;
                ballydir = -4;
                playerx = 350;
                s_delay = 7500;
                map = new MapGenerator(5,10);
            }
        }
    }

}
