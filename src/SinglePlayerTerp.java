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
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;

/**
 *
 * @author valet8115
 */




// make sure you rename this class if you are doing a copy/paste
public class SinglePlayerTerp extends JComponent implements KeyListener{

    // Height and Width of our game
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000)/desiredFPS;
    //screens
    final int MAIN = 0;
    final int MODE = 1;
    final int HOWTO = 6;
    final int SLVL = 2;
    final int LVL1 = 3;
    final int LVL2 = 4;
    final int LVL3 = 5;
    final int NOPE = 7;
    
    //images
    BufferedImage main = ImageHelper.loadImage("MainScreen.png");
    BufferedImage mode = ImageHelper.loadImage("Load Screen.png");
    BufferedImage background = ImageHelper.loadImage("background.png");
    BufferedImage b1 = ImageHelper.loadImage("100x50 block.png");
    BufferedImage lvlselect = ImageHelper.loadImage("lvlselect.png");
    BufferedImage nope = ImageHelper.loadImage("Nope_edited-1.png");
    int screen = MAIN;
    boolean change = false;
    //player
    Rectangle player1 = new Rectangle (20, 550, 30, 30);
    
    //misc
    int ground = 550;
    int gravity = 2;
    int p1dy = 0;
    boolean p1inAir = false;
    int p1sjumpy = 10;
    boolean sjumpEnd = false;
    int speed = 5;
    boolean p1sjumping = false;
    boolean p1sinAir = false;
    boolean p1inAirs = false;
    int camx = 0;
    int camy = 0;
    
    //player controls
    boolean p1jump = false;
    boolean p1left = false;
    boolean p1right = false;
    boolean p1sjump = false;
    int p1count = 5;
    boolean p1nomove = false;
    
    //lvl 1
    Rectangle l1b1 = new Rectangle(100, 450, 100, 50);
    Rectangle l1b2 = new Rectangle(250, 450, 50, 50);
    Rectangle l1b3 = new Rectangle(400, 400, 100, 50);
    Rectangle l1b4 = new Rectangle(550, 300, 100, 50);
    Rectangle l1b5 = new Rectangle(750, 200, 40, 40);
    Rectangle l1b6 = new Rectangle(950, 400, 300, 40);
    
    //lvl 2
    Rectangle l2b1 = new Rectangle(150, 250, 100, 50);
    Rectangle l2b2 = new Rectangle(450, 450, 40, 40);
    Rectangle l2b3 = new Rectangle(600, 400, 100, 10);
    Rectangle l2b4 = new Rectangle(750, 150, 100, 10);
    Rectangle l2b5 = new Rectangle(1000, 475, 40, 40);
    Rectangle l2b6 = new Rectangle(1200, 400, 40, 40);
    
    //lvl 3
    
    //dialog boxes
    Object[] modeSelect = {"Single Player" , "Co-Op" , "Race Mode"};
    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g)
    {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);
        
        // GAME DRAWING GOES HERE 
        if(screen == 0){
            g.drawImage(main, 0, 0, null);
        }
        if(screen == 1){
            g.drawImage(mode, 0, 0, null);
        }
        if (screen == 2){
            g.drawImage(lvlselect, 0, 0, null);
            
        }
        if (screen == 3){
        g.drawImage(background, 0, 0, null);
        g.setColor(Color.RED);
        g.fillRect(player1.x - camx, player1.y, player1.height, player1.width);
        
        
        g.drawImage(b1, l1b1.x - camx, l1b1.y, null);
        g.fillRect(l1b2.x - camx, l1b2.y, l1b2.width, l1b2.height);
        g.drawImage(b1, l1b3.x - camx, l1b3.y, null);
        g.drawImage(b1, l1b4.x - camx, l1b4.y, null);
        g.fillRect(l1b5.x - camx, l1b5.y, l1b5.width, l1b5.height);
        g.fillRect(l1b6.x - camx, l1b6.y, l1b6.width, l1b6.height);
        
        }
        if (screen == 4){
        g.drawImage(background, 0, 0, null);
        g.setColor(Color.RED);
        g.fillRect(player1.x - camx, player1.y, player1.height, player1.width);    
            
        g.fillRect(l2b1.x - camx, l2b1.y, l2b1.width, l2b1.height);
        g.fillRect(l2b2.x - camx, l2b2.y, l2b2.width, l2b2.height);
        g.fillRect(l2b3.x - camx, l2b3.y, l2b3.width, l2b3.height);
        g.fillRect(l2b4.x - camx, l2b4.y, l2b4.width, l2b4.height);
        g.fillRect(l2b5.x - camx, l2b5.y, l2b5.width, l2b5.height);
        g.fillRect(l2b6.x - camx, l2b6.y, l2b6.width, l2b6.height);
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
//        if (screen == 1){
//        String s = (String)JOptionPane.showInputDialog(null, "Please select a mode", "Mode", JOptionPane.QUESTION_MESSAGE, null, modeSelect, "Single Player");
//        }
        // the main game loop section
        // game will end if you set done = false;
        boolean done = false; 
        while(!done)
        {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();
            
            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE 
            //screen logic\
            
            String s = "";
            if(screen == 1){
                s = (String)JOptionPane.showInputDialog(null, "Please select a mode", "Mode", JOptionPane.QUESTION_MESSAGE, null, modeSelect, "Single Player");
            }
            if( s == modeSelect[0])
            {
               screen = SLVL;
               change = false;
            } else {
                screen = NOPE;
            }
   
            if(change){
                if (screen == MAIN){
                    screen = MODE;
                    change = false;
                } else if (screen == MODE){
                    screen = SLVL;
                    change = false;
//                } else if (screen == SLVL){
//                    screen = LVL1;
//                    change = false;
//                } 
            }
            if(screen == 3){//players cant stall jump on level 1
                p1sjump = false;
            }
            
            //game logic
            if(player1.x < WIDTH/2){
                camx = 0;
            } else {
                camx = player1.x - WIDTH/2;
            }
            if(player1.y > HEIGHT/2){
                camy = 0;
            } else {
                camy = player1.y - HEIGHT/2;
            }
            //jumping and moving
             p1dy = p1dy + gravity;
             if (p1sjump){//player 1 sjumps
                p1count = p1count - 1;
            }
             if (p1right && !p1inAirs){//player 1 move right
                player1.x = player1.x + speed;
            }
            if(p1left && !p1inAirs){//player 1 move left
                player1.x = player1.x - speed;
            }
            if (p1sjumpy > 0) {
                p1dy = 0;
                p1sjumpy -= gravity;
            }
            if (p1jump && !p1inAir) {
                p1sjumping = false;
                p1dy = -30;
                p1inAir = true;
                p1inAirs = false;
            }
            if (p1sjumping) {
                p1dy = 0;
                player1.y -= p1sjumpy;
            }
            
            player1.y += p1dy;
            if (player1.y > ground) {
                player1.y = ground;
                p1dy = 0;
                p1inAir = false;
            }
            if (p1sjump & !p1sjumping & !p1inAir) {

                p1dy = 0;
                p1sjumpy = 30;
                p1sjumping = true;
                p1inAirs = true;
            }
            if(p1dy > 30)
            {
                p1dy = 30;
            }
 
//            if(p1nomove == true){
//                player1.x = player1.x;
//            }
            
            //lvl data and commands
            if(player1.x >= 100  && player1.y == 550 ){//if the player hits the ground he/she is sent back to start
                player1.x = 20; 
                
            }
            if (player1.y <= 0){//cannot go into the cieling
                player1.y = 0;
            }
            if (player1.x <= 0){//cannot go past x = 0
                player1.x = 0;
            }
            if(screen == 3 && player1.x == 1050){//ends lvl 1 at 950 coords and starts lvl 2
                screen = LVL2;
                player1.x = 20;
                player1.y = 550;
            }
            if(screen == 4 && player1.x == 1250){
                screen = LVL3;
            }
            
            
            
            
            
            
            
            
            
            
            
            
            //collisions
            if (screen == 3){
            if(player1.intersects(l1b1)){//player 1 collides with a block
                handleCollision(player1,l1b1);
            }
            if(player1.intersects(l1b2)){//player 1 collides with a block
                handleCollision(player1,l1b2);
            }
            if(player1.intersects(l1b3)){//player 1 collides with a block
                handleCollision(player1,l1b3);
            }
            if(player1.intersects(l1b4)){//player 1 collides with a block
                handleCollision(player1,l1b4);
            }
            if(player1.intersects(l1b5)){//player 1 collides with a block
                handleCollision(player1,l1b5);
            }
            if(player1.intersects(l1b6)){//player 1 collides with a block
                handleCollision(player1,l1b6);
            }
            }
            if (screen == 4){
                if(player1.intersects(l2b1)){//player 1 collides with a block
                handleCollision(player1,l2b1);
            }
            if(player1.intersects(l2b2)){//player 1 collides with a block
                handleCollision(player1,l2b2);
            }
            if(player1.intersects(l2b3)){//player 1 collides with a block
                handleCollision(player1,l2b3);
            }
            if(player1.intersects(l2b4)){//player 1 collides with a block
                handleCollision(player1,l2b4);
            }
            if(player1.intersects(l2b5)){//player 1 collides with a block
                handleCollision(player1,l2b5);
            }
            if(player1.intersects(l2b6)){//player 1 collides with a block
                handleCollision(player1,l2b6);
            }
                
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
        SinglePlayerTerp game = new SinglePlayerTerp();
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
            p1jump = true;
        } else if (keyCode == KeyEvent.VK_A){
            p1left = true;
        }else if (keyCode == KeyEvent.VK_D){
            p1right = true;
        }else if (keyCode == KeyEvent.VK_SPACE){
            p1sjump = true;
        }else if (keyCode == KeyEvent.VK_G){
            change = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
         int keyCode = e.getKeyCode();
         if (keyCode == KeyEvent.VK_W) {
            p1jump = false;
        } else if (keyCode == KeyEvent.VK_A){
            p1left = false;
        }else if (keyCode == KeyEvent.VK_D){
            p1right = false;
        }else if (keyCode == KeyEvent.VK_SPACE){
            p1sjump = false;
        }
    }
    
    void handleCollision(Rectangle player, Rectangle block){
        Rectangle overlap = player.intersection(block);
        
        if(overlap.height > overlap.width){
            if (player.x < block.x){
                player.x -= overlap.width;
            } else if (player.x + player.width > block.x + block.width){
                player.x += overlap.width;
            }
        } else {
            if (p1dy > 0){
                player.y -= overlap.height;
                p1inAir = false;
            } else {
                player.y += overlap.height;
            }
            p1dy = 0;
        }
    }
}