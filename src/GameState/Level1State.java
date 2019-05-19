package GameState;

import TileMap.Background;
import TileMap.TileMap;
import Entity.*;
import java.awt.Color;
import java.awt.Font;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Level1State extends GameState {

    private TileMap tileMap;
    private Background bg;
    private Player player;
    private Player player2;
    private HUD hud;
    private boolean over;

    public Level1State(GameStateManager gsm) {
        this.gsm = gsm;

        init();

    }

    public void init() {
        bg = new Background("/Res/Backgrounds/city3.jpg", 1);
        tileMap = new TileMap(50);
        tileMap.loadTiles("/Res/Tilesets/tiles50px.png");
        tileMap.loadMap("/Res/Maps/ezaz.map");
        tileMap.setPosition(0, 0);

        player = new Player(tileMap);
        player.setPosition(100, 530);

        player2 = new Player(tileMap);
        player2.setPosition(1200, 530);

        hud = new HUD(player, player2);
        over = false;

    }

    public void update() {

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
    }

    public void keyPressed(int k) {

        //player1
        if (k == KeyEvent.VK_A) {
            player.setLeft(true);
        }
        if (k == KeyEvent.VK_D) {
            player.setRight(true);
        }
        if (k == KeyEvent.VK_W) {
            player.setJumping(true);
        }
        if (k == KeyEvent.VK_C) {
            player.setGliding(true);
        }
        if (k == KeyEvent.VK_V) {
            player.setScratching();
        }
        if (k == KeyEvent.VK_B) {
            player.setFiring();
        }

        //player2
        if (k == KeyEvent.VK_LEFT) {
            player2.setLeft(true);
        }
        if (k == KeyEvent.VK_RIGHT) {
            player2.setRight(true);
        }
        if (k == KeyEvent.VK_UP) {
            player2.setJumping(true);
        }
        if (k == KeyEvent.VK_I) {
            player2.setGliding(true);
        }
        if (k == KeyEvent.VK_O) {
            player2.setScratching();
        }
        if (k == KeyEvent.VK_P) {
            player2.setFiring();
        }

    }

    public void keyReleased(int k) {
        //player1
        if (k == KeyEvent.VK_A) {
            player.setLeft(false);
        }
        if (k == KeyEvent.VK_D) {
            player.setRight(false);
        }
        if (k == KeyEvent.VK_W) {
            player.setJumping(false);
        }
        if (k == KeyEvent.VK_C) {
            player.setGliding(false);
        }

        //player2
        if (k == KeyEvent.VK_LEFT) {
            player2.setLeft(false);
        }
        if (k == KeyEvent.VK_RIGHT) {
            player2.setRight(false);
        }

        if (k == KeyEvent.VK_UP) {
            player2.setJumping(false);
        }
        if (k == KeyEvent.VK_I) {
            player2.setGliding(false);
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

        }else if(player2.getHealth() == 0)
        {
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
