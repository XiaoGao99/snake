
/**
 * Main function for the snake game. 
 * The function use keylisteners to detect commands from user, and perform the corresponding action.
 *
 * @Xiao Gao
 * @June 10 2021
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel{
    
    // store&set the location of snake
    int[] snakeX = new int[600]; 
    int[] snakeY = new int [600];
    
    // set the location of food 
    int foodX;
    int foodY;
    
    boolean unique;
    // use random to generate the coordinates of food 
    Random random = new Random();
    
    // store&set the length of snake
    int length;
    
    // set the status of game
    boolean isStart = false;
    
    // death/alive 
    boolean isAlive;
    
    // record the score 
    int score;
    
    // set the direction of snake 
    char direction;
    
    // set a timer
    Timer timer;
    
    
    public GamePanel()
    {
        initi();
        
        // make the cursor focus on the frame 
        this.setFocusable (true);
        // add key listener
        this.addKeyListener (new KeyAdapter()
        {
            @Override
            public void keyPressed (KeyEvent e)
            {
                super.keyPressed (e);
                int keycode = e.getKeyCode();
                if (keycode == KeyEvent.VK_SPACE)
                {
                    isStart = !isStart;
                    repaint();
                }
                
                // some keys are conflict
                // e.g. when the snake is moving up, user cannot ask the snake to move down 
                if (e.getKeyCode() == KeyEvent.VK_UP && direction != 'd') {
                    direction = 'u';
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN && direction != 'u') {
                    direction = 'd';
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT && direction != 'r') {
                    direction = 'l';
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && direction != 'l') {
                    direction = 'r';
                }

            }
        });
        
        timer = new Timer(100, new ActionListener()
        {
            @Override
            public void actionPerformed (ActionEvent e)
            {
                // the snake only move if the game is start
                if (isStart)
                {
                    // change the coordinates of the snake body to move the snake
                    for (int i = length - 1; i > 0; i--)
                    {
                        snakeX[i] = snakeX[i-1];
                        snakeY[i] = snakeY[i-1];
                    }

                    // move the head
                    if (direction == 'r')
                    {
                        snakeX[0] += 20;
                        // the snake will die if it touches the bound
                        if (snakeX[0] > 560)
                        {
                            isAlive = false;
                            isStart = false;
                        }
                    }
                    else if (direction == 'l')
                    {
                        snakeX[0] -= 20;
                        // the snake will die if it touches the bound 
                        if (snakeX[0] < 0)
                        {
                            isAlive = false;
                            isStart = false;
                        }
                    }
                    else if (direction == 'u')
                    {
                        snakeY[0] -= 20;
                        // the snake will die if it touches the bound
                        if (snakeY[0] < 100)
                        {
                            isAlive = false;
                            isStart = false;
                        }
                    }
                    else if (direction == 'd')
                    {
                        snakeY[0] += 20;
                        // the snake will die if it touches the bound
                        if (snakeY[0] > 640)
                        {
                            isAlive = false;
                            isStart = false;
                        }
                    }
                    
                    // eat food
                    if (snakeX[0] == foodX && snakeY[0] == foodY)
                    {
                        length ++;
                        score += 10;
                        
                        // make sure food will be refreshed in a unique place 
                        // i.e. food will not overlap the snake 
                        int x = random.nextInt(29) * 20;
                        int y = 100 + random.nextInt(28)* 20;
                        for (int i = 0; i < length; i++)
                        {       
                            while (x == snakeX[i] && y == snakeY[i])
                            {
                                x = random.nextInt(29) * 20;
                                y = 100 + random.nextInt(28)* 20;
                            }
                        }          
                        foodX = x;
                        foodY = y;
                    }
                    
                    // check if die 
                    for (int i = 1; i < length; i++)
                    {
                        if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i])
                        {
                            isAlive = false;
                            isStart = false;
                        }
                    }
                    repaint();
                }
            }
        });
        timer.start();
    }

    
    public void initi ()
    {
        // initialize the start point 
        snakeX[0] = 300;
        snakeY[0] = 300;
        snakeX[1] = 280;
        snakeY[1] = 300;
        snakeX[2] = 260;
        snakeY[2] = 300;
        
        // initialize the length of snake
        length = 3;   
        
        // initialize the direction of snake head
        direction = 'r';
        
        // initialize the score to 0
        score = 0;
        
        // initialize the food location 
        // make sure food will be refreshed in a unique place 
        // i.e. food will not overlap the snake 
        int x = random.nextInt(29) * 20;
        int y = 100 + random.nextInt(28)* 20;

        for (int i = 0; i < length; i++)
        {       
            while (x == snakeX[i] && y == snakeY[i])
            {
                x = random.nextInt(29) * 20;
                y = 100 + random.nextInt(28)* 20;
            }
        }          
        foodX = x;
        foodY = y;
        isAlive = true;
    }
    
    @Override
    protected void paintComponent (Graphics g)
    {
        super.paintComponent (g);
        this.setVisible (true);
        // fill background color (beige)
        g.setColor (new Color (226, 224, 217));
        g.fillRect (0, 100, 600, 700);
        
        // paint the head of snake
        if (direction == 'r')
        {
            Data.rightImg.paintIcon (this, g, snakeX[0], snakeY[0]);
        }      
        else if (direction == 'l')
        {
            Data.leftImg.paintIcon (this, g, snakeX[0], snakeY[0]);
        }
        else if (direction == 'u')
        {
            Data.upImg.paintIcon (this, g, snakeX[0], snakeY[0]);
        }
        else if (direction == 'd')
        {
            Data.downImg.paintIcon (this, g, snakeX[0], snakeY[0]);
        }
            
        
        // paint the body of snake
        for (int i = 1; i < length; i++)
        {
            Data.bodyImg.paintIcon (this, g, snakeX[i], snakeY[i]);
        }
        // paint food
        Data.foodImg.paintIcon(this, g, foodX, foodY);
        
        // 
        if (isStart == false)
        {
            g.setColor (Color.RED);
            g.setFont (new Font("Serif", Font.BOLD, 40));
            g.drawString ("Enter Space Key To Start Game!", 20, 300);
        }
        
        g.setColor(Color.RED);
        g.setFont(new Font("Serif", Font.BOLD, 18));
        g.drawString("SCORE:" + score, 20, 50);
        g.drawString("LENGTH:" + length, 450, 50);
        
        if (!isAlive)
        {
            g.setColor (Color.blue);
            g.setFont (new Font("Serif", Font.BOLD, 40));
            g.drawString ("GAME OVER!", 150, 400); 
            
            //g.setColor (Color.blue);
            g.drawString ("YOUR SCORE IS " + score + "!", 50, 450);
            
            //restart
            initi();
            
        }
        
      
    }
    
    
}
