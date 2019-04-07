package GameState;

import Main.GamePanel;
import TileMap.Background;
import TileMap.TileMap;
import java.awt.Color;
import Entity.*;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Level1State extends GameState {

    private TileMap tileMap;
    private Background bg;
    private Player player;

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

    }

    public void update() {
        player.update();
    }

    public void draw(Graphics2D g) {
        bg.draw(g);

        tileMap.draw(g);

        player.draw(g);
    }

    public void keyPressed(int k) {
        if (k == KeyEvent.VK_LEFT) {
            player.setLeft(true);
        }
        if (k == KeyEvent.VK_RIGHT) {
            player.setRight(true);
        }
        if (k == KeyEvent.VK_UP) {
            player.setUp(true);
        }
        if (k == KeyEvent.VK_DOWN) {
            player.setDown(true);
        }
        if (k == KeyEvent.VK_Q) {
            player.setJumping(true);
        }
        if (k == KeyEvent.VK_W) {
            player.setGliding(true);
        }
        if (k == KeyEvent.VK_E) {
            player.setScratching();
        }
        if (k == KeyEvent.VK_R) {
            player.setFiring();
        }
    }

    public void keyReleased(int k) {
        if (k == KeyEvent.VK_LEFT) {
            player.setLeft(false);
        }
        if (k == KeyEvent.VK_RIGHT) {
            player.setRight(false);
        }
        if (k == KeyEvent.VK_UP) {
            player.setUp(false);
        }
        if (k == KeyEvent.VK_DOWN) {
            player.setDown(false);
        }
        if (k == KeyEvent.VK_Q) {
            player.setJumping(false);
        }
        if (k == KeyEvent.VK_W) {
            player.setGliding(false);
        }

    }

}
