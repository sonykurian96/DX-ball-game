package dx_ball;

import java.awt.*;

public class MapGenerator
{
    public int map[][];
    public int brickwidth;
    public int brickheight;

    public MapGenerator(int row,int col)
    {
        map = new int[row][col];
        for(int i=0;i<row;i++)
        {
            for(int j=0;j<col;j++)
            {
                map[i][j] = 1;
            }
        }

        brickwidth = 540/col;
        brickheight = 150/row;

    }
    public void setBrick(int value, int r, int c)
    {
        map[r][c] = value;
    }

    public void draw(Graphics2D g)
    {
        // first row
        for (int i = 0; i < 1; i++)
        {
            for (int j = 2; j < map[0].length - 2; j++)
            {
                if (map[i][j] > 0) {
                    g.setColor(Color.red);
                    g.fillRoundRect(j * brickwidth + 80, i * brickheight + 50, brickwidth, brickheight, 20, 20);

                    g.setColor(Color.black);
                    g.setStroke(new BasicStroke(3));
                    g.drawRoundRect(j * brickwidth + 80, i * brickheight + 50, brickwidth, brickheight, 20, 20);
                }
            }
        }

        // second row
        for (int i = 1; i < 2; i++) 
        {
            for (int j = 1; j <map[0].length - 1; j++) 
            {
                if(map[i][j]>0) {
                    g.setColor(Color.pink);
                    g.fillRoundRect(j*brickwidth+80,i*brickheight+50,brickwidth,brickheight,20,20);

                    g.setColor(Color.black);
                    g.setStroke(new BasicStroke(3));
                    g.drawRoundRect(j*brickwidth+80,i*brickheight+50,brickwidth,brickheight,20,20);
                }
            }
        }

        // third row
        for (int i = 2; i < 3; i++)
        {
            for (int j = 0; j <map[0].length; j++)
            {
                if(map[i][j]>0) {
                    g.setColor(Color.orange);
                    g.fillRoundRect(j*brickwidth+80,i*brickheight+50,brickwidth,brickheight,20,20);

                    g.setColor(Color.black);
                    g.setStroke(new BasicStroke(3));
                    g.drawRoundRect(j*brickwidth+80,i*brickheight+50,brickwidth,brickheight,20,20);
                }
            }
        }

        // fourth row
        for (int i = 3; i < 4; i++) 
        {
            for (int j = 1; j <map[0].length - 1; j++) 
            {
                if(map[i][j]>0) {
                    g.setColor(Color.red);
                    g.fillRoundRect(j*brickwidth+80,i*brickheight+50,brickwidth,brickheight,20,20);

                    g.setColor(Color.black);
                    g.setStroke(new BasicStroke(3));
                    g.drawRoundRect(j*brickwidth+80,i*brickheight+50,brickwidth,brickheight,20,20);
                }
            }
        }

        // fifth row
        for (int i = 4; i < 5; i++)
        {
            for (int j = 2; j <map[0].length - 2; j++) 
            {
                if(map[i][j]>0) {
                    g.setColor(Color.green);
                    g.fillRoundRect(j*brickwidth+80,i*brickheight+50,brickwidth,brickheight,20,20);

                    g.setColor(Color.black);
                    g.setStroke(new BasicStroke(3));
                    g.drawRoundRect(j*brickwidth+80,i*brickheight+50,brickwidth,brickheight,20,20); 
                } 
            }
        }
    }
}
