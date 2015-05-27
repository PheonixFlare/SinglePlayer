/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author valet8115
 */




// make sure you rename this class if you are doing a copy/paste

    public class TerpMainTest1 extends JComponent implements KeyListener{

    // Height and Width of our game
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000)/desiredFPS;
    
    //player coords
    
    Rectangle player1 = new Rectangle(50, 500, 40, 40);
    Rectangle player2 = new Rectangle(20, 500, 40, 40);
            
    
    //misc
    int ground = 455;
    int gravity = 2;
    int p1dy = 0;
    int p2dy = 0;
    boolean p1inAir = false;
    boolean p2inAir = false;
    int p1sjumpy = 10;
    int p2sjumpy = 10;
    boolean sjumpEnd = false;
    boolean sjumpEnd2 = false;
    int speed = 5;
    boolean p1sjumping = false;
    boolean p2sjumping = false;
    boolean p1sinAir = false;
    boolean p2sinAir = false;
    
    //player controls
    //p1
    boolean p1jump = false;
    boolean p1left = false;
    boolean p1right = false;
    boolean p1sjump = false;
    //p2
    boolean p2jump = false;
    boolean p2left = false;
    boolean p2right = false;
    boolean p2sjump = false;
    int p1count = 5;
    int p2count = 5;
    
    //blocks
    Rectangle block1 = new Rectangle (300, 400, 100, 50);
    
            
    
    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g)
    {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);
        
        // GAME DRAWING GOES HERE 
        g.setColor(Color.RED);
        g.fillRect(player1.x, player1.y, player1.height, player1.width);
        
        g.setColor(Color.BLACK);
        g.fillRect(player2.x, player2.y, player2.height, player2.width);
        
        g.drawString("Player 1 has " + p1count + " stall jumps left", 10,20);
        g.drawString("Player 2 has " + p2count + " stall jumps left", 400, 20);
        
        g.fillRect(block1.x, block1.y, block1.width, block1.height);
        
        // GAME DRAWING ENDS HERE
    }
    
    
    // The main game loop
    // In here is where all the logic for my game will go
    public void run(){
    
        // Used to keep track of time used to draw and update the game
        // This is used to limit the framerate later on
        long startTime;
        long deltaTime;
        
        // the main game loop section
        // game will end if you set done = false;
        boolean done = false; 
        while(!done){
        
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();
            
            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE 
            
            p1dy = p1dy + gravity;
            if (p1sjump){//player 1 jumps
                p1count = p1count - 1;
            }
            if (p2sjump){//player 2 jumps
                p2count = p2count - 1;
            }
            if (p1count == 0){//add 1 everytime player 1 stall jumps
                sjumpEnd = true;
            }
            if (p2count == 0){//add 1 everytime player 2 stall jumps
                sjumpEnd2 = true;
            }    
            if (p1right){//player 1 move right
                player1.x = player1.x + speed;
            }
            if(p1left){//player 1 move left
                player1.x = player1.x - speed;
            }
            if(p2right){//player 2 move right
                player2.x = player2.x + speed;
            }
            if(p2left){//player 2 move left
                player2.x = player2.x - speed;
            }
            //player 1 jump and stall jump controls
            if (p1sjumpy > 0) {
                p1dy = 0;
                p1sjumpy -= gravity;
            }
            if (p1jump && !p1inAir) {
                p1sjumping = false;
                p1dy = -30;
                p1inAir = true;
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
            }
            
            //player1  collision
            if(player1.intersects(block1)){
                p1handleCollision(player1,block1);
            }
            if(player1.intersects(player2)){
                p1handleCollision(player1,player2);
            }
            
            //end of player 1 jump and stall jump controls
            //player 2 jump and stall jump controls
            p2dy = p2dy + gravity;
            if (p2sjumpy > 0) {
                p2dy = 0;
                p2sjumpy -= gravity;
            }
            if (p2jump && !p2inAir) {
                p2sjumping = false;
                p2dy = -30;
                p2inAir = true;
            }
            if (p2sjumping) {
                p2dy = 0;
                player2.y -= p2sjumpy;
            }
            player2.y += p2dy;
            if (player2.y > ground) {
                player2.y = ground;
                p2dy = 0;
                p2inAir = false;
            }
            if (p2sjump & !p2sjumping & !p2inAir) {

                p2dy = 0;
                p2sjumpy = 30;
                p2sjumping = true;
            }
            //player 2 collision
            if(player2.intersects(block1)){
                p2handleCollision(player2,block1);
            }
            if(player1.intersects(player2)){
                p2handleCollision(player1,player2);
            }
            //end of player 2 stall jump and jump controls
             
            
            
//            if(player1.intersects(block1))
//            {
//                boolean result = handleCollision(player1, block1);
//                if(result == false)
//                {
//                    p1inAir = false;
//                }
//            }
            
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
        TerpMainTest1 game = new TerpMainTest1();
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
        }else if (keyCode == KeyEvent.VK_UP){
            p2jump = true;
        }else if (keyCode == KeyEvent.VK_LEFT){
            p2left = true;
        }else if (keyCode == KeyEvent.VK_RIGHT){
            p2right = true;
        }else if (keyCode == KeyEvent.VK_ENTER){
            p2sjump = true;
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
        }else if (keyCode == KeyEvent.VK_UP){
            p2jump = false;
        }else if (keyCode == KeyEvent.VK_LEFT){
            p2left = false;
        }else if (keyCode == KeyEvent.VK_RIGHT){
            p2right = false;
        }else if (keyCode == KeyEvent.VK_ENTER){
            p2sjump = false;
        }
    }
    
    void p1handleCollision(Rectangle player, Rectangle block){
        Rectangle overlap = player.intersection(block);
        
        if(overlap.height > overlap.width){
            if (player.x < block.x){
                player.x -= overlap.width;
            } else if (player.x + player.width > block.x + block.width){
                player.x += overlap.width;
            }
        } else {
            p1dy = 0;
            if (player.y < block.y){
                player.y -= overlap.height;
                p1inAir = false;
            } else if (player.y + player.height > block.y + block.height){
                player.y += overlap.height;
            }
        }
        
    }
    
    void p2handleCollision(Rectangle player, Rectangle block){
        Rectangle overlap2 = player.intersection(block);
        
        if(overlap2.height > overlap2.width){
            if (player.x < block.x){
                player.x -= overlap2.width;
            } else if (player.x + player.width > block.x + block.width){
                player.x += overlap2.width;
            }
        } else {
            p2dy = 0;
            if (player.y < block.y){
                player.y -= overlap2.height;
                p2inAir = false;
            } else if (player.y + player.height > block.y + block.height){
                player.y += overlap2.height;
            }
        }
        
    }
        
        
    
}
