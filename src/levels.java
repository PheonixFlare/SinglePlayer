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
    
    Rectangle l5b0 = new Rectangle(100, 400, 30, 30);
    Rectangle l5b1 = new Rectangle(220, 300, 30, 30);
    Rectangle l5b2 = new Rectangle(350, 200, 40, 40);
    Rectangle l5b3 = new Rectangle(350, -150, 40, 290);
    Rectangle l5b4 = new Rectangle(500, 450, 30, 30);
    Rectangle l5b5 = new Rectangle(675, 300, 30, 30);
    Rectangle l5b6 = new Rectangle(800, 300, 30, 30);
    Rectangle l5b7 = new Rectangle(950, 525, 30, 30);
    Rectangle l5b8 = new Rectangle(1150, 525, 30, 30);
    Rectangle l5b9 = new Rectangle(1300, 530, 30, 300);
    
    

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
        
        g.fillRect(l5b0.x - camx , l5b0.y - camy , l5b0.width , l5b0.height);
        g.fillRect(l5b1.x - camx , l5b1.y - camy , l5b1.width , l5b1.height);
        g.fillRect(l5b2.x - camx , l5b2.y - camy , l5b2.width , l5b2.height);
        g.fillRect(l5b3.x - camx , l5b3.y - camy , l5b3.width , l5b3.height);
        g.fillRect(l5b4.x - camx , l5b4.y - camy , l5b4.width , l5b4.height);
        g.fillRect(l5b5.x - camx , l5b5.y - camy , l5b5.width , l5b5.height);
        g.fillRect(l5b6.x - camx , l5b6.y - camy , l5b6.width , l5b6.height);
        g.fillRect(l5b7.x - camx , l5b7.y - camy , l5b7.width , l5b7.height);
        g.fillRect(l5b8.x - camx , l5b8.y - camy , l5b8.width , l5b8.height);
        g.fillRect(l5b9.x - camx , l5b9.y - camy , l5b9.width , l5b9.height);
        
        
        
        
        
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
            if (player.x <= 0){
                player.x = 0;
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