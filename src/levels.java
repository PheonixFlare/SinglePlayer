/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author valet8115
 */




// make sure you rename this class if you are doing a copy/paste
public class levels extends JComponent implements KeyListener{

    // Height and Width of our game
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000)/desiredFPS;
    boolean up = false;
    boolean down = false;
    boolean right = false;
    boolean left = false;
    int speed = 5;
    int camx = 0;
    int camy = 0;
    
    
    Rectangle player = new Rectangle(20, 550, 30, 30);
    
    Rectangle l3b1 = new Rectangle(100, 500, 10, 400);
    Rectangle l3b2 = new Rectangle(150, 450, 20, 400);
    Rectangle l3b3 = new Rectangle(200, 400, 30, 400);
    Rectangle l3b4 = new Rectangle(300, 325, 40, 400);
    Rectangle l3b5 = new Rectangle(400, 200, 20, 400);
    Rectangle l3b6 = new Rectangle(500, 500, 40, 400);
    Rectangle l3b7 = new Rectangle(600, 350, 50, 400);
    Rectangle l3b8 = new Rectangle(600, 280, 50, -1000);
    Rectangle l3b9 = new Rectangle(800, 500, 200, 30);
    Rectangle l3b10 = new Rectangle(0,0,0,0);
    Rectangle l3b11 = new Rectangle(0,0,0,0);
    Rectangle l3b12 = new Rectangle(0,0,0,0);
    Rectangle l3b13 = new Rectangle(0,0,0,0);
    

    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g)
    {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);
        
        // GAME DRAWING GOES HERE 
        g.fillRect(player.x - camx, player.y - camy, player.height, player.width);
        
        g.fillRect(l4b1.x - camx, l4b1.y - camy , l4b1.width, l4b1.height);
        g.fillRect(l4b2.x - camx, l4b2.y - camy , l4b2.width, l4b2.height);
        g.fillRect(l4b3.x - camx, l4b3.y - camy , l4b3.width, l4b3.height);
        g.fillRect(l4b4.x - camx, l4b4.y - camy , l4b4.width, l4b4.height);
        g.fillRect(l4b5.x - camx, l4b5.y - camy , l4b5.width, l4b5.height);
        g.fillRect(l4b6.x - camx, l4b6.y - camy , l4b6.width, l4b6.height);
        g.fillRect(l4b7.x - camx, l4b7.y - camy , l4b7.width, l4b7.height);
        g.fillRect(l4b8.x - camx, l4b8.y - camy , l4b8.width, l4b8.height);
        g.fillRect(l4b9.x - camx, l4b9.y - camy , l4b9.width, l4b9.height);
        g.fillRect(l4b10.x - camx, l4b10.y - camy , l4b10.width, l4b10.height);
        
        
        
        
        
        
        // GAME DRAWING ENDS HERE
    }
    
    
    // The main game loop
    // In here is where all the logic for my game will go
    public void run()
    {
        // Used to keep track of time used to draw and update the game
        // This is used to limit the framerate later on
        long startTime;
        long deltaTime;
        
        // the main game loop section
        // game will end if you set done = false;
        boolean done = false; 
        while(!done)
        {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();
            
            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE 
            
            if(up){
                player.y = player.y - speed;
            }
            if(down){
                player.y = player.y + speed;
            }
            if(right){
                player.x = player.x + speed;
            }
            if(left){
                player.x = player.x - speed;
            }
            if(player.x < WIDTH/2){
                camx = 0;
            } else {
                camx = player.x - WIDTH/2;
            }
            if(player.y > HEIGHT/2){
                camy = 0;
            } else {
                camy = player.y - HEIGHT/2;
            }
            

            // GAME LOGIC ENDS HERE 
            
            // update the drawing (calls paintComponent)
            repaint();
            
            
            
            // SLOWS DOWN THE GAME BASED ON THE FRAMERATE ABOVE
            // USING SOME SIMPLE MATH
            deltaTime = System.currentTimeMillis() - startTime;
            if(deltaTime > desiredTime)
            {
                //took too much time, don't wait
            }else
            {
                try
                {
                    Thread.sleep(desiredTime - deltaTime);
                }catch(Exception e){};
            }
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creates a windows to show my game
        JFrame frame = new JFrame("My Game");
       
        // creates an instance of my game
        levels game = new levels();
        // sets the size of my game
        game.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        // adds the game to the window
        frame.add(game);
         
        // sets some options and size of the window automatically
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        // shows the window to the user
        frame.setVisible(true);
        frame.addKeyListener(game);
        // starts my game loop
        game.run();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
         if (keyCode == KeyEvent.VK_W) {
            up = true;
        } else if (keyCode == KeyEvent.VK_S){
            down = true;
        }else if (keyCode == KeyEvent.VK_D){
            right = true;
        }else if (keyCode == KeyEvent.VK_A){
            left = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
         if (keyCode == KeyEvent.VK_W) {
            up = false;
        } else if (keyCode == KeyEvent.VK_S){
            down = false;
        }else if (keyCode == KeyEvent.VK_D){
            right = false;
        }else if (keyCode == KeyEvent.VK_A){
            left = false;
        }
    }
}