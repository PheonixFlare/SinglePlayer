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
    int speed = 15;
    int camx = 0;
    int camy = 0;
    Rectangle base1 = new Rectangle(0, 290, 3200, 10);
    Rectangle base2 = new Rectangle(0, 590, 3200, 10);
    
    
    Rectangle player = new Rectangle(20, 560, 30, 30);
    
    
    Rectangle[] recrace1 = { new Rectangle (275, 490, 50, 100), new Rectangle (500, 520, 30, 70),new Rectangle (700, 550, 40, 40),new Rectangle (740, 510, 40, 80), new Rectangle (780, 470, 40, 120), new Rectangle(820, 430, 40, 160),new Rectangle (860, 430, 350, 160),new Rectangle (920, 370, 30, 60),new Rectangle (1100, 360, 50, 70),
    new Rectangle (1470, 510, 240, 20),new Rectangle (1670, 530, 40, 60),new Rectangle (1820, 480, 75, 20),new Rectangle (1990, 460, 75, 20),new Rectangle (2160, 440, 75, 20),new Rectangle (2320, 420, 400, 20),new Rectangle (2100, 530, 40, 60),new Rectangle (2350, 540, 50, 50),new Rectangle (2575, 540, 40, 50),new Rectangle (2800, 500, 20, 90),
    new Rectangle (0, 0, 0, 0),new Rectangle (0, 0, 0, 0),new Rectangle (0, 0, 0, 0),new Rectangle (0, 0, 0, 0),new Rectangle (0, 0, 0, 0),new Rectangle (0, 0, 0, 0),new Rectangle (0, 0, 0, 0),new Rectangle (0, 0, 0, 0),new Rectangle (0, 0, 0, 0),new Rectangle (0, 0, 0, 0),};
    
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
        g.fillRect(base1.x - camx, base1.y - camy, base1.width, base1.height);
        g.fillRect(base2.x - camx, base2.y - camy, base2.width, base2.height);
        g.fillRect(player.x - camx, player.y - camy, player.height, player.width);

        for (int i = 0; i < recrace1.length; i++) {
            g.fillRect(recrace1[i].x - camx, recrace1[i].y - camy, recrace1[i].width, recrace1[i].height);
        }
        
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