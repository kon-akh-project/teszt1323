
package GameState;

import Entity.HUD;
import Entity.Player;
import TileMap.Background;
import TileMap.TileMap;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MultiClient extends GameState {
 private TileMap tileMap;
    private Background bg;
    private Player player;
    private Player player2;
    private HUD hud;
    private boolean over;
   public String line2;
    boolean connected;
    
    public Socket s;
   public Scanner sc;
         public   PrintWriter pw;
    public MultiClient(GameStateManager gsm) {
         this.gsm = gsm;

        init();
        
        
    }
    
    
    
    public void keyPressed(int k) {
         if(k == KeyEvent.VK_ESCAPE)
        {
            pw.println("END");
             pw.flush();
            gsm.setState(GameStateManager.MENUSTATE);
             try {
                 s.close();
             } catch (IOException ex) {
                 ex.printStackTrace();
             }
        }
         //player2
        if (k == KeyEvent.VK_LEFT) {
            pw.println("LEFT");
            player2.setLeft(true);
           pw.flush();
        }
        if (k == KeyEvent.VK_RIGHT) {
             pw.println("RIGHT");
            player2.setRight(true);
             pw.flush();
        }
        if (k == KeyEvent.VK_UP) {
             pw.println("UP");
            player2.setJumping(true);
             pw.flush();
        }
        if (k == KeyEvent.VK_I) {
             pw.println("I");
            player2.setGliding(true);
             pw.flush();
        }
        if (k == KeyEvent.VK_O) {
            pw.println("O");
            player2.setScratching();
             pw.flush();
        }
        if (k == KeyEvent.VK_P) {
             pw.println("P");
            player2.setFiring();
            pw.flush();
        }
        
      

    }

    public void keyReleased(int k) {
       if (k == KeyEvent.VK_LEFT) {
            pw.println("LEFT OVER");
            player2.setLeft(false);
             pw.flush();
        }
        if (k == KeyEvent.VK_RIGHT) {
             pw.println("RIGHT OVER");
            player2.setRight(false);
            pw.flush();
        }

        if (k == KeyEvent.VK_UP) {
            pw.println("UP OVER");
            player2.setJumping(false);
            pw.flush();
        }
        if (k == KeyEvent.VK_I) {
             pw.println("I OVER");
            player2.setGliding(false);
            pw.flush();
        }

    }


    public void init() {
        
        
        
        
         bg = new Background("/Res/Backgrounds/city3.jpg", 1);
        tileMap = new TileMap(50);
        tileMap.loadTiles("/Res/Tilesets/tiles50px.png");
        tileMap.loadMap("/Res/Maps/ezaz.map");
        tileMap.setPosition(0, 0);

        player = new Player(tileMap, true);
        player.setPosition(100, 530);

        player2 = new Player(tileMap, false);
        player2.setPosition(1200, 530);

        hud = new HUD(player, player2);
        over = false;
        line2 = "2222222";
        connected = false;
    }


    public void update() {
        if (player.fellDown()) {
            player.setPosition(100, 530);
        }

        if (player2.fellDown()) {
            player2.setPosition(1200, 530);
        }

        player.update();
        player2.update();

        player.checkAttack(player2);
        player2.checkAttack(player);
    }


    public void draw(Graphics2D g) {
         bg.draw(g);

        tileMap.draw(g);

        player.draw(g);
        player2.draw(g);

        hud.draw(g);
        checkIfOver(g);
        
        
        if(connected == false)
        {
     try {
         client();
     } catch (IOException ex) {
        
     }
     
        }
    }
    
    
    
    public void client() throws IOException
    {
        
        
         // need host and port, we want to connect to the ServerSocket at port 7777
         s = new Socket("localhost", 40000);
        System.out.println("Connected!");
        connected = true;

        
        // get the output stream from the socket.
     //   OutputStream outputStream = s.getOutputStream();
        // create an object output stream from the output stream so we can send an object through it
       // ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
          sc = new Scanner(s.getInputStream());
          pw = new PrintWriter(s.getOutputStream());
          
          
          
          
          
            //System.out.println("hehehehe");  
            // ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
  
//             ObjectInputStream is = new ObjectInputStream(s.getInputStream());
           // os.writeObject(player2);
            
           new Thread(() -> {
                
                 new Thread(() -> {
                   while(true)
                  {
                      
                      synchronized (line2) {
                    switch(line2){
                       
                        case "AA": player.setLeft(true);break;
                        case "DD": player.setRight(true);break;
                        case "WW": player.setJumping(true);break;
                        case "CC":player.setGliding(true);break;
                         case "VV":player.setScratching(); line2 = "";break;
                         case"BB": player.setFiring(); line2 = "";break;
                         
                         case "A OVER":player.setLeft(false);break;
                         case "D OVER":player.setRight(false);break;
                         case "W OVER": player.setJumping(false);break;
                         case "C OVER":player.setGliding(false);break;
                        
                   }
                      }
                   }
                    }).start();
                
                
                while(true)
                {
                    System.out.println("olvasom");
                    line2 = sc.nextLine();
                     System.out.println(line2);
                    
                    
                  
                    if(line2.equals("END"))
                    {
                        break;
                    }
                
                }
                
             //   try {
              //     s.close();
                    
              //  } catch (IOException ex) {
                    
            //    }
               
 System.out.println("nézem");
        }).start();
            
        

     
       
    }
        
        
        
    
    

      private void checkIfOver(Graphics2D g) {
        if (player.getHealth() == 0) {

            g.setColor(Color.BLUE);
            g.setFont(new Font("Arial", Font.PLAIN, 80));
            g.drawString("PLAYER 1 WON", 350, 300);
            g.setFont(new Font("Arial", Font.PLAIN, 40));
            g.drawString("Game is restarting in 5 seconds", 375, 400);
            if (over == false) {
                restart(g);
            }
            over = true;

        } else if (player2.getHealth() == 0) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.PLAIN, 80));
            g.drawString("PLAYER 1 WON", 350, 300);
            g.setFont(new Font("Arial", Font.PLAIN, 40));
            g.drawString("Game is restarting in 5 seconds", 375, 400);
            if (over == false) {
                restart(g);
            }
            over = true;
        }
    }
      
      
      private void restart(Graphics2D g) {

        new Thread(() -> {
            synchronized (gsm) {

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }

                gsm.setState(GameStateManager.LEVEL1STATE);
            }

        }).start();

    }
    
}
