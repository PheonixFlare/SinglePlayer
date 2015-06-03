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
public class Terp extends JComponent implements KeyListener {

    // Height and Width of our game
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;
    //screens
    final int MAIN = 0;
    final int MODE = 1;
    final int HOWTO = 6;
    final int SLVL = 2;
    final int LVL1 = 3;
    final int LVL2 = 4;
    final int LVL3 = 5;
    final int LVL4 = 8;
    final int LVL5 = 9;
    final int LVL6 = 10;
    final int NOPE = 7;
    final int END = 11;
    final int RMODE = 12;
    final int RLVL1 = 13;
    final int RLVL2 = 14;
    //images
    BufferedImage main = ImageHelper.loadImage("MainScreen.png");
    BufferedImage mode = ImageHelper.loadImage("Load Screen.png");
    BufferedImage background = ImageHelper.loadImage("background.png");
    BufferedImage b1 = ImageHelper.loadImage("50x10 block.png");
    BufferedImage b2 = ImageHelper.loadImage("50x50 block.png");
    BufferedImage b3 = ImageHelper.loadImage("35x400 block.png");
    BufferedImage b4 = ImageHelper.loadImage("20x400 block.png");
    BufferedImage lvlselect = ImageHelper.loadImage("lvlselect.png");
    BufferedImage nope = ImageHelper.loadImage("Nope_edited-1.png");
    BufferedImage teemo = ImageHelper.loadImage("teemo.png");
    BufferedImage howto = ImageHelper.loadImage("howto.png");
    BufferedImage finish = ImageHelper.loadImage("finish.png");
    BufferedImage gnar = ImageHelper.loadImage("gnar.png");
    BufferedImage b5 = ImageHelper.loadImage("100x10 block.png");
    BufferedImage ENDpic = ImageHelper.loadImage("END.png");
    BufferedImage rmain = ImageHelper.loadImage("racemain.png");
    BufferedImage rbackground = ImageHelper.loadImage("rmodeback.png");
    int screen = MAIN;
    boolean change = false;
    //players
    Rectangle player1 = new Rectangle(20, 550, 30, 30);
    Rectangle player2 = new Rectangle(20, 550, 30, 30);
    Rectangle player3 = new Rectangle(20, 550, 30, 30);
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
    boolean lvls = false;
    boolean count = false;
    //player controls
    boolean p1jump = false;
    boolean p1left = false;
    boolean p1right = false;
    boolean p1sjump = false;
    int p1l2count = 3;
    int p1l5count = 2;
    boolean p1nomove = false;
    //arrays for blocks
    Rectangle[] rectangles1 = {new Rectangle(150, 450, 50, 50), new Rectangle(100, 450, 50, 50), new Rectangle(250, 450, 50, 50), new Rectangle(450, 400, 50, 50), new Rectangle(400, 400, 50, 50), new Rectangle(600, 300, 50, 50), new Rectangle(550, 300, 50, 50), new Rectangle(750, 200, 50, 50), new Rectangle(1000, 400, 50, 50), new Rectangle(950, 400, 50, 50), new Rectangle(1050, 400, 50, 50), new Rectangle(1100, 400, 50, 50), new Rectangle(1150, 400, 50, 50)};
    Rectangle[] rectangles2 = {new Rectangle(150, 250, 50, 10), new Rectangle(200, 250, 50, 10), new Rectangle(450, 450, 50, 10), new Rectangle(600, 400, 50, 10), new Rectangle(750, 150, 50, 10),
        new Rectangle(1000, 475, 50, 10), new Rectangle(1200, 400, 50, 10), new Rectangle(1250, 400, 50, 10), new Rectangle(1300, 400, 50, 10)};
    Rectangle[] rectangles3 = {new Rectangle(100, 500, 35, 400), new Rectangle(200, 400, 35, 400), new Rectangle(300, 325, 35, 400),
        new Rectangle(500, 500, 35, 400), new Rectangle(600, 350, 35, 400)};
    Rectangle[] rectangles4 = {new Rectangle(150, -100, 50, 900), new Rectangle(100, 500, 50, 10), new Rectangle(0, 350, 50, 10), new Rectangle(100, 200, 50, 10), new Rectangle(0, 50, 50, 10), new Rectangle(100, -100, 50, 10), new Rectangle(300, -50, 50, 10), new Rectangle(520, 150, 50, 10), new Rectangle(775, 400, 50, 10),
        new Rectangle(1000, 450, 200, 10), new Rectangle(1050, 350, 50, 100)};
    Rectangle[] rectangles5 = {new Rectangle(100, 400, 30, 30), new Rectangle(220, 300, 30, 30), new Rectangle(350, 200, 40, 40), new Rectangle(350, -150, 40, 290),
        new Rectangle(500, 450, 30, 30), new Rectangle(675, 300, 30, 30), new Rectangle(800, 300, 30, 30), new Rectangle(950, 525, 30, 30), new Rectangle(1150, 525, 30, 30), new Rectangle(1300, 530, 100, 30), new Rectangle(1400, 430, 50, 100)};
    Rectangle[][] allRectangles = {rectangles1, rectangles2, rectangles3, rectangles4, rectangles5};
    int curLevel = 0;
    //lvl 1
    Rectangle l1finish = new Rectangle(1050, 300, 50, 100);
    //lvl 2
    Rectangle l2finish = new Rectangle(1250, 300, 50, 100);
    //lvl 3 labeled as L4 but really L3
    Rectangle l4b1 = new Rectangle(100, 500, 35, 400);
    Rectangle l4b2 = new Rectangle(150, 450, 35, 400);
    Rectangle l4b3 = new Rectangle(200, 400, 35, 400);
    Rectangle l4b4 = new Rectangle(300, 325, 35, 400);
    Rectangle l4b5 = new Rectangle(400, 200, 20, 400);
    Rectangle l4b6 = new Rectangle(500, 500, 35, 400);
    Rectangle l4b7 = new Rectangle(600, 350, 35, 400);
    Rectangle l4b9 = new Rectangle(800, 500, 200, 30);
    Rectangle l4finish = new Rectangle(950, 400, 50, 100);
//    
    //lvl 4 labeled as L3 bu really L4
    Rectangle l3b0 = new Rectangle(150, -100, 50, 900);
    Rectangle l3b1 = new Rectangle(100, 500, 50, 10);
    Rectangle l3b2 = new Rectangle(0, 350, 50, 10);
    Rectangle l3b3 = new Rectangle(100, 200, 50, 10);
    Rectangle l3b4 = new Rectangle(0, 50, 50, 10);
    Rectangle l3b5 = new Rectangle(100, -100, 50, 10);
    Rectangle l3b6 = new Rectangle(300, -50, 50, 10);
    Rectangle l3b7 = new Rectangle(520, 150, 50, 10);
    Rectangle l3b8 = new Rectangle(775, 400, 50, 10);
    Rectangle l3b9 = new Rectangle(1000, 450, 200, 10);
    Rectangle l3finish = new Rectangle(1050, 350, 50, 100);
    //lvl 5
    Rectangle l5b0 = new Rectangle(100, 400, 40, 40);
    Rectangle l5b1 = new Rectangle(220, 300, 40, 40);
    Rectangle l5b2 = new Rectangle(350, 200, 40, 40);
    Rectangle l5b3 = new Rectangle(350, -450, 40, 590);
    Rectangle l5b4 = new Rectangle(500, 450, 40, 40);
    Rectangle l5b5 = new Rectangle(675, 300, 40, 40);
    Rectangle l5b6 = new Rectangle(800, 300, 40, 40);
    Rectangle l5b7 = new Rectangle(950, 525, 40, 30);
    Rectangle l5b8 = new Rectangle(1150, 525, 40, 30);
    Rectangle l5b9 = new Rectangle(1300, 530, 100, 30);
    //dialog boxes
    Object[] modeSelect = {"Single Player", "Co-Op", "Race Mode", "How To Play"};
    Object[] lvlSelect = {"1", "2", "3", "4", "5"};
    Object[] rmodeSelect = {"1", "2"};
    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)

    @Override
    public void paintComponent(Graphics g) {
//        for (int i =0; i < allRectangles[curLevel].length; i ++)
//        {
//
//        }
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // GAME DRAWING GOES HERE
        if (screen == 2) {
            g.drawImage(lvlselect, 0, 0, null);
        }
        if (screen == 0) {
            g.drawImage(main, 0, 0, null);
        }
        if (screen == 1) {
            g.drawImage(mode, 0, 0, null);
        }
        if (screen == 7) {
            g.drawImage(nope, 0, 0, null);
        }
        if (screen == 6) {
            g.drawImage(howto, 0, 0, null);
        }

        if (screen == 3) {
            drawSL1(g);
        }
        if (screen == 4) {
            drawSL2(g);
        }
        if (screen == 5) {
            drawSL3(g);
        }
        if (screen == 8) {
            g.drawImage(background, 0, 0, null);
            g.setColor(Color.GREEN);
            g.drawImage(teemo, player1.x - camx, player1.y - camy, null);

            g.fillRect(l3b0.x - camx, l3b0.y - camy, l3b0.width, l3b0.height);
            g.fillRect(l3b1.x - camx, l3b1.y - camy, l3b1.width, l3b1.height);
            g.fillRect(l3b2.x - camx, l3b2.y - camy, l3b2.width, l3b2.height);
            g.fillRect(l3b3.x - camx, l3b3.y - camy, l3b3.width, l3b3.height);
            g.fillRect(l3b4.x - camx, l3b4.y - camy, l3b3.width, l3b3.height);
            g.fillRect(l3b5.x - camx, l3b5.y - camy, l3b5.width, l3b5.height);
            g.fillRect(l3b6.x - camx, l3b6.y - camy, l3b6.width, l3b6.height);
            g.fillRect(l3b7.x - camx, l3b7.y - camy, l3b7.width, l3b7.height);
            g.fillRect(l3b8.x - camx, l3b8.y - camy, l3b8.width, l3b8.height);
            g.fillRect(l3b9.x - camx, l3b9.y - camy, l3b9.width, l3b9.height);
            g.fillRect(0 - camx, 583 - camy, 100, 100);

        }
        if (screen == 9) {
            g.drawImage(background, 0, 0, null);
            g.setColor(Color.GREEN);
            g.drawImage(teemo, player1.x - camx, player1.y - camy, null);

            g.fillRect(l5b0.x - camx, l5b0.y - camy, l5b0.width, l5b0.height);
            g.fillRect(l5b1.x - camx, l5b1.y - camy, l5b1.width, l5b1.height);
            g.fillRect(l5b2.x - camx, l5b2.y - camy, l5b2.width, l5b2.height);
            g.fillRect(l5b3.x - camx, l5b3.y - camy, l5b3.width, l5b3.height);
            g.fillRect(l5b4.x - camx, l5b4.y - camy, l5b4.width, l5b4.height);
            g.fillRect(l5b5.x - camx, l5b5.y - camy, l5b5.width, l5b5.height);
            g.fillRect(l5b6.x - camx, l5b6.y - camy, l5b6.width, l5b6.height);
            g.fillRect(l5b7.x - camx, l5b7.y - camy, l5b7.width, l5b7.height);
            g.fillRect(l5b8.x - camx, l5b8.y - camy, l5b8.width, l5b8.height);
            g.fillRect(l5b9.x - camx, l5b9.y - camy, l5b9.width, l5b9.height);
            g.fillRect(0 - camx, 583 - camy, 100, 100);
        }
        if (screen == END) {
            g.drawImage(ENDpic, 0, 0, null);
        }
        if (screen == RMODE) {
            g.drawImage(rmain, 0, 0, null);
        }
        if (screen == RLVL1){
            drawRL1(g);
        }


        // GAME DRAWING ENDS HERE
    }

    // The main game loop
    // In here is where all the logic for my game will go
    public void run() {
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
        while (!done) {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();

            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE 
            //screen logic
            String l = "";
            String s = "";
            String r = "";
            if (change) {
                if (screen == MAIN) {
                    screen = MODE;
                    change = false;
                }
            } else if (screen == 1) {
                s = (String) JOptionPane.showInputDialog(null, "Please select a mode", "Mode", JOptionPane.QUESTION_MESSAGE, null, modeSelect, "Single Player");
                if (s == modeSelect[0]) {
                    screen = 2;
                    change = false;
                } else if (s == modeSelect[1]) {//if they select co-op then the screen goes to the nope screen
                    screen = 7;
                    change = false;
                } else if (s == modeSelect[2]) {//if they select race mode then the screen goes to the nope screen
                    screen = 12;
                    change = false;
                } else if (s == modeSelect[3]) {//if they select how to then the screen goes to the how to screen
                    screen = 6;
                    change = false;
                }
            } else if (screen == 2) {
                l = (String) JOptionPane.showInputDialog(null, "Please select a level", "Level", JOptionPane.QUESTION_MESSAGE, null, lvlSelect, "Level Select");
                if (l == lvlSelect[0]) {
                    screen = 3;
                    change = false;
                } else if (l == lvlSelect[1]) {
                    screen = 4;
                    change = false;
                } else if (l == lvlSelect[2]) {
                    screen = 5;
                    change = false;
                } else if (l == lvlSelect[3]) {
                    screen = 8;
                    change = false;
                } else if (l == lvlSelect[4]) {
                    screen = 9;
                    change = false;
                }
            } else if (screen == 12) {
                r = (String) JOptionPane.showInputDialog(null, "Please select a race level", "Level", JOptionPane.QUESTION_MESSAGE, null, rmodeSelect, "Level Select");
                if  (r == rmodeSelect[0]){
                    screen = 13;
                    change = false;
                    
                } else if (r == rmodeSelect[1]){
                    screen = 14;
                    change = false;
                }
            }


            if (screen == 6) {//if player is on the howto and they hit the change button, the game goes to mode screen
                if (change) {
                    screen = 0;
                    change = false;
                }
            }
            if (screen == 7) {//if player is on the nope and they hit the change button, the game goes to mode screen
                if (change) {
                    screen = 0;
                    change = false;
                }
            }

            if (screen == 3 || screen == 4 || screen == 5 || screen == 8 || screen == 9 || screen == 10) { //if the player is in single player mode         

                if (screen == 3) {//players cant stall jump on level 1
                    p1sjump = false;
                }
                if (screen == 5) {//players cannot stall jump on level 3 
                    p1sjump = false;
                }
                if (screen == 8) {//players cannot stall jump on level 4 
                    p1sjump = false;
                }

                //single player logic

                if (player1.x < WIDTH / 2) {
                    camx = 0;
                } else {
                    camx = player1.x - WIDTH / 2;
                }
                if (player1.y > HEIGHT / 2) {
                    camy = 0;
                } else {
                    camy = player1.y - HEIGHT / 2;
                }
                //jumping and moving
                p1dy = p1dy + gravity;

                if (p1right && !p1inAirs) {//player 1 move right
                    player1.x = player1.x + speed;
//                for (Rectangle rect: rectangles)
//                {
//                    if (player1.intersects(rect))
//                    {
//                        player1.y = rect.x+rect.width;
//                    }
//                }
                }
                if (p1left && !p1inAirs) {//player 1 move left
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
                if (p1dy > 30) {
                    p1dy = 30;
                }


                //lvl data and commands
                if (player1.x >= 100 && player1.y == 550) {//if the player hits the ground he/she is sent back to start
                    player1.x = 20;

                }
                if (screen == 3) {
                    if (player1.y <= 0) {//cannot go into the cieling on level 1
                        player1.y = 0;
                    }
                } else if (screen == 4) {
                    if (player1.y <= 0) {//cannot go into the cling on level 2
                        player1.y = 0;
                    }
                } else if (screen == 5) {
                    if (player1.y <= 0) {//cannot go into cieling on level 3
                        player1.y = 0;
                    }
                }
                if (player1.x <= 0) {//cannot go past x = 0
                    player1.x = 0;
                }
                if (screen == 3 && player1.x == 1050) {//ends lvl 1 at 1050 coords and starts lvl 2
                    screen = LVL2;
                    player1.x = 20;
                    player1.y = 550;
                }
                if (screen == 4 && player1.x == 1250) {//ends lvl 2 at 1250 coords and starts lvl 3
                    screen = LVL3;
                    player1.x = 20;
                    player1.y = 550;
                }
                if (screen == 5 && player1.x == 950) {//ends lvl 3 at 950 coords and starts lvl 4
                    screen = LVL4;
                    player1.x = 20;
                    player1.y = 550;
                }
                if (screen == 8 && player1.x == 1100) {//end lvl 4 at 1100 coords and starts lvl 5
                    screen = LVL5;
                    player1.x = 20;
                    player1.y = 550;
                }
                if (screen == 9 && player1.x == 1400) {
                    screen = END;
                }
                if (screen == END && change) {
                    screen = MAIN;
                }












                //collisions
                if (screen == 3) {
                    for (int i = 0; i < rectangles1.length; i++) {
                        if (player1.intersects(rectangles1[i])) {
                            handleCollision(player1, rectangles1[i]);
                        }
                    }
                } else if (screen == 4) {

                    for (int i = 0; i < rectangles2.length; i++) {
                        if (player1.intersects(rectangles2[i])) {
                            handleCollision(player1, rectangles2[i]);
                        }
                    }

                }
                if (screen == 5) {
                    for (int i = 0; i < rectangles3.length; i++) {
                        if (player1.intersects(rectangles3[i])) {
                            handleCollision(player1, rectangles3[i]);
                        }
                    }

                    if (player1.intersects(l4b9)) {//player 1 collides with a block
                        handleCollision(player1, l4b9);

                    }
                }
                if (screen == 8) {

                    if (player1.intersects(l3b0)) {//player 1 collides with a block
                        handleCollision2(player1, l3b0);
                    }
                    if (player1.intersects(l3b1)) {//player 1 collides with a block
                        handleCollision2(player1, l3b1);
                    }
                    if (player1.intersects(l3b2)) {//player 1 collides with a block
                        handleCollision2(player1, l3b2);
                    }
                    if (player1.intersects(l3b3)) {//player 1 collides with a block
                        handleCollision2(player1, l3b3);
                    }
                    if (player1.intersects(l3b4)) {//player 1 collides with a block
                        handleCollision2(player1, l3b4);
                    }
                    if (player1.intersects(l3b5)) {//player 1 collides with a block
                        handleCollision2(player1, l3b5);
                    }
                    if (player1.intersects(l3b6)) {//player 1 collides with a block
                        handleCollision2(player1, l3b6);
                    }
                    if (player1.intersects(l3b7)) {//player 1 collides with a block
                        handleCollision2(player1, l3b7);
                    }
                    if (player1.intersects(l3b8)) {//player 1 collides with a block
                        handleCollision2(player1, l3b8);
                    }
                    if (player1.intersects(l3b9)) {//player 1 collides with a block
                        handleCollision2(player1, l3b9);
                    }
                }
                if (screen == 9) {

                    if (player1.intersects(l5b0)) {//player 1 collides with a block
                        handleCollision(player1, l5b0);
                    }
                    if (player1.intersects(l5b1)) {//player 1 collides with a block
                        handleCollision(player1, l5b1);
                    }
                    if (player1.intersects(l5b2)) {//player 1 collides with a block
                        handleCollision(player1, l5b2);
                    }
                    if (player1.intersects(l5b3)) {//player 1 collides with a block
                        handleCollision(player1, l5b3);
                    }
                    if (player1.intersects(l5b4)) {//player 1 collides with a block
                        handleCollision(player1, l5b4);
                    }
                    if (player1.intersects(l5b5)) {//player 1 collides with a block
                        handleCollision(player1, l5b5);
                    }
                    if (player1.intersects(l5b6)) {//player 1 collides with a block
                        handleCollision(player1, l5b6);
                    }
                    if (player1.intersects(l5b7)) {//player 1 collides with a block
                        handleCollision(player1, l5b7);
                    }
                    if (player1.intersects(l5b8)) {//player 1 collides with a block
                        handleCollision(player1, l5b8);
                    }
                    if (player1.intersects(l5b9)) {//player 1 collides with a block
                        handleCollision(player1, l5b9);
                    }
                }

            } else if (screen == 13 || screen == 14){
                
            }



            // GAME LOGIC ENDS HERE 

            // update the drawing (calls paintComponent)
            repaint();



            // SLOWS DOWN THE GAME BASED ON THE FRAMERATE ABOVE
            // USING SOME SIMPLE MATH
            deltaTime = System.currentTimeMillis() - startTime;
            if (deltaTime > desiredTime) {
                //took too much time, don't wait
            } else {
                try {
                    Thread.sleep(desiredTime - deltaTime);
                } catch (Exception e) {
                };
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
        Terp game = new Terp();
        // sets the size of my game
        game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
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
        } else if (keyCode == KeyEvent.VK_A) {
            p1left = true;
        } else if (keyCode == KeyEvent.VK_D) {
            p1right = true;
        } else if (keyCode == KeyEvent.VK_SPACE) {
            p1sjump = true;
        } else if (keyCode == KeyEvent.VK_G) {
            change = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W) {
            p1jump = false;
        } else if (keyCode == KeyEvent.VK_A) {
            p1left = false;
        } else if (keyCode == KeyEvent.VK_D) {
            p1right = false;
        } else if (keyCode == KeyEvent.VK_SPACE) {
            p1sjump = false;
        }
    }

    void handleCollision(Rectangle player, Rectangle block) {
        Rectangle overlap = player.intersection(block);

        if (overlap.height > overlap.width) {
            if (player.x < block.x) {
                player.x -= overlap.width;
            } else if (player.x + player.width > block.x + block.width) {
                player.x += overlap.width;
            }
        } else {
            if (p1dy > 0) {
                player.y -= overlap.height;
                p1inAir = false;
            } else {
                player.y += overlap.height;
            }
            p1dy = 0;
        }
    }

    void handleCollision2(Rectangle player, Rectangle block) {
        Rectangle overlap = player.intersection(block);

        if (overlap.height > overlap.width) {
            if (player.x < block.x) {
                player.x -= overlap.width;
            } else if (player.x + player.width > block.x + block.width) {
                player.x += overlap.width;
            }
        } else {
            p1dy = 0;
            if (player.y < block.y) {
                player.y -= overlap.height;
                p1inAir = false;
            } else if (player.y + player.height > block.y + block.height) {
                player.y += overlap.height;
            }
        }
    }

    void racemode() {
    }

    void drawSL1(Graphics g) {
        g.drawImage(background, 0, 0, null);
        g.setColor(Color.GREEN);
        g.drawImage(teemo, player1.x - camx, player1.y, null);

        for (int i = 0; i < rectangles1.length; i++) {
            g.drawImage(b2, rectangles1[i].x - camx, rectangles1[i].y, null);
        }
        g.drawImage(finish, l1finish.x - camx, l1finish.y, null);
        g.fillRect(0 - camx, 583, 100, 100);

    }

    void drawSL2(Graphics g) {
        g.drawImage(background, 0, 0, null);
        g.setColor(Color.GREEN);
        g.drawImage(teemo, player1.x - camx, player1.y, null);
        for (int i = 0; i < rectangles2.length; i++) {
            g.drawImage(b1, rectangles2[i].x - camx, rectangles2[i].y, null);
        }

        g.drawImage(finish, l2finish.x - camx, l2finish.y, null);
        g.fillRect(0 - camx, 583, 100, 100);
    }

    void drawSL3(Graphics g) {
        g.drawImage(background, 0, 0, null);
        g.setColor(Color.GREEN);
        g.drawImage(teemo, player1.x - camx, player1.y, null);

        for (int i = 0; i < rectangles3.length; i++) {
            g.drawImage(b3, rectangles3[i].x - camx, rectangles3[i].y, null);
        }
        g.setColor(Color.GREEN);
        g.drawImage(b4, l4b5.x - camx, l4b5.y, null);
        g.fillRect(l4b9.x - camx, l4b9.y, l4b9.width, l4b9.height);
        g.drawImage(finish, l4finish.x - camx, l4finish.y, null);
        g.fillRect(0 - camx, 583, 100, 100);
    }
    void drawRL1(Graphics g){
        g.drawImage(rbackground, 0 , 0, null);
        g.drawImage(teemo, player2.x, player3.y, null);
    }
}