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
    
    Rectangle base1 = new Rectangle(0, 580, 3200, 40);
    Rectangle base2 = new Rectangle(0, 270, 3200, 20);
    
    
    Rectangle player = new Rectangle(20, 550, 30, 30);
    
    Rectangle r1 = new Rectangle(0, 0, 0, 0);
    Rectangle r2= new Rectangle(0, 0, 0, 0);
    Rectangle r3 = new Rectangle(0, 0, 0, 0);
    Rectangle r4 = new Rectangle(0, 0, 0, 0);
    Rectangle r5 = new Rectangle(0, 0, 0, 0);
    Rectangle r6 = new Rectangle(0, 0, 0, 0);
    Rectangle r7 = new Rectangle(0, 0, 0, 0);
    Rectangle r8 = new Rectangle(0, 0, 0, 0);
    Rectangle r9 = new Rectangle(0, 0, 0, 0);
    Rectangle r10 = new Rectangle(0, 0, 0, 0);
    Rectangle r11 = new Rectangle(0, 0, 0, 0);
    Rectangle r12 = new Rectangle(0, 0, 0, 0);
    Rectangle r13 = new Rectangle(0, 0, 0, 0);
    Rectangle r14 = new Rectangle(0, 0, 0, 0);
    Rectangle r15= new Rectangle(0, 0, 0, 0);
    Rectangle r16 = new Rectangle(0, 0, 0, 0);
    Rectangle r17 = new Rectangle(0, 0, 0, 0);
    Rectangle r18 = new Rectangle(0, 0, 0, 0);
    
    
    

    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g)
    {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);
        
        // GAME DRAWING GOES HERE 
        g.setColor(Color.GREEN);
        g.fillRect(base1.x, base1.y, base1.width, base1.height);
        g.fillRect(base2.x, base2.y, base2.width, base2.height);
        g.fillRect(player.x - camx, player.y - camy, player.height, player.width);
        
        g.fillRect(r1.x - camx , r1.y - camy , r1.width , r1.height);
        g.fillRect(r2.x - camx , r2.y - camy , r2.width , r2.height);
        g.fillRect(r3.x - camx , r3.y - camy , r3.width , r3.height);
        g.fillRect(r4.x - camx , r4.y - camy , r4.width , r4.height);
        g.fillRect(r5.x - camx , r5.y - camy , r5.width , r5.height);
        g.fillRect(r6.x - camx , r6.y - camy , r6.width , r6.height);
        g.fillRect(r7.x - camx , r7.y - camy , r7.width , r7.height);
        g.fillRect(r8.x - camx , r8.y - camy , r8.width , r8.height);
        g.fillRect(r9.x - camx , r9.y - camy , r9.width , r9.height);
        g.fillRect(r10.x - camx , r10.y - camy , r10.width , r10.height);
        
        
        
        
        
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