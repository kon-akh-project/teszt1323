package GameState;

import Entity.HUD;
import Entity.Player;
import TileMap.Background;
import TileMap.TileMap;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MultiServer extends GameState {

    
  private  Player playerbuffer;
    
    private TileMap tileMap;
    private Background bg;
    private Player player;
    private Player player2;
    private HUD hud;
    private boolean over;
   public String line;
    private boolean startedServer = false;
   public Scanner sc;
public	PrintWriter pw;
ClientData client;

    public MultiServer(GameStateManager gsm) {
        this.gsm = gsm;

        init();
        

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
        line = "";
       

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

        if(startedServer == false)
        {
         try {
            server();

        } catch (Exception e) {

            e.printStackTrace();
     
        }
        }
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

    public void keyPressed(int k) {
         if(k == KeyEvent.VK_ESCAPE)
        {
            gsm.setState(GameStateManager.MENUSTATE);
        }

        //player1
        if (k == KeyEvent.VK_A) {
           pw.println("AA");
            player.setLeft(true);
            pw.flush();
        }
        if (k == KeyEvent.VK_D) {
            pw.println("DD");
            player.setRight(true);
             pw.flush();
        }
        if (k == KeyEvent.VK_W) {
            pw.println("WW");
            player.setJumping(true);
             pw.flush();
        }
        if (k == KeyEvent.VK_C) {
            pw.println("CC");
            player.setGliding(true);
             pw.flush();
        }
        if (k == KeyEvent.VK_V) {
            pw.println("VV");
            player.setScratching();
             pw.flush();
        }
        if (k == KeyEvent.VK_B) {
            pw.println("BB");
            player.setFiring();
             pw.flush();
        }
    }

    public void keyReleased(int k) {
  //player1
        if (k == KeyEvent.VK_A) {
            System.out.println("balra");
            pw.println("A OVER");
            player.setLeft(false);
             pw.flush();
        }
        if (k == KeyEvent.VK_D) {
            System.out.println("jobbra");
            pw.println("D OVER");
            player.setRight(false);
             pw.flush();
        }
        if (k == KeyEvent.VK_W) {
            pw.println("W OVER");
            player.setJumping(false);
             pw.flush();
        }
        if (k == KeyEvent.VK_C) {
            pw.println("C OVER");
            player.setGliding(false);
             pw.flush();
        }
    }

    public void server() throws IOException, ClassNotFoundException, Exception {
        {

            ServerSocket ss = new ServerSocket(40000);
            startedServer = true;
            System.out.println("Waiting for the second player");
            Socket socket = ss.accept(); // blocking call, this will wait until a connection is attempted on this port.
            System.out.println("Connection from") ;
            ClientData client = new ClientData(ss);
               sc=new Scanner(socket.getInputStream());
	pw = new PrintWriter(socket.getOutputStream());
            // get the input stream from the connected socket
      //  InputStream inputStream = socket.getInputStream();
            // create a DataInputStream so we can read data from it.
          //  ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

        //   OutputStream outputStream = socket.getOutputStream();
        // create an object output stream from the output stream so we can send an object through it
       // ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
      //   ObjectOutputStream os = new ObjectOutputStream(client.s.getOutputStream());
                  
      //   ObjectInputStream is = new ObjectInputStream(client.s.getInputStream());
        
        
       // playerbuffer = null;
       //  playerbuffer = (Player) is.readObject();
      //  if(playerbuffer !=null)
       //  System.out.println(playerbuffer.getMaxHealth());
       //   if(playerbuffer != null)
       //   player2= playerbuffer;
            new Thread(() -> {
                
                 new Thread(() -> {
                   while(true)
                  {
                       
                      synchronized (line) {
                    switch(line){
                        
                       
                        case "LEFT": player2.setLeft(true);break;
                        case "RIGHT": player2.setRight(true);break;
                        case "UP": player2.setJumping(true);break;
                        case "I":player2.setGliding(true);break;
                         case "O":player2.setScratching(); line = "";break;
                         case"P": player2.setFiring(); line = "";break;
                         
                         case "LEFT OVER":player2.setLeft(false);break;
                         case "RIGHT OVER":player2.setRight(false);break;
                         case "UP OVER": player2.setJumping(false);break;
                         case "I OVER":player2.setGliding(false);break;
                        
                   }
                      }
                   }
                    }).start();
                
                
                while(true)
                {

                    
                    
                    line = client.sc.nextLine();
                     System.out.println(line);
                    
                    
                  
                    if(line.equals("END"))
                    
                        break;
                    
                
                }
                
                try {
                   socket.close();
                     ss.close();
                } catch (IOException ex) {
                    
                }
               

        }).start();
            
            
            // read the list of messages from the socket
            //  List<Message> listOfMessages = (List<Message>) objectInputStream.readObject();
            //    System.out.println("Received [" + listOfMessages.size() + "] messages from: " + socket);
            // print out the text of every message
            System.out.println("All messages:");
            //   listOfMessages.forEach((msg)-> System.out.println(msg.getText()));

            System.out.println("Closing sockets.");
         //   ss.close();
          

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

class ClientData implements AutoCloseable {
	Socket s;
	Scanner sc;
	PrintWriter pw;

	ClientData(ServerSocket ss) throws Exception {
		s = ss.accept();
		sc = new Scanner(s.getInputStream());
		pw = new PrintWriter(s.getOutputStream());
	}

	public void close() throws Exception {
		if (s == null) return;
		s.close();
	}
}
