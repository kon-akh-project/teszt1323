package GameState;

import TileMap.Background;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

public class Multiplayerpre extends MenuState {

    private Background bg;

    private int currentChoice = 0;
    private String[] options = {
        "Start a server", "Join Server", "Back"

    };

    private Color titleColor;
    private Font titleFont;

    private Font font;

    public Multiplayerpre(GameStateManager gsm) {
        super(gsm);

        this.gsm = gsm;

        try {
            bg = new Background("/Res/Backgrounds/menubgnagy.gif", 1);
            bg.setVector(-0.1, 0);

            titleColor = new Color(0, 0, 0);
            titleFont = new Font("Century Gothic", Font.PLAIN, 72);
            font = new Font("Arial", Font.PLAIN, 30);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void init() {

    }

    public void update() {
        bg.update();
    }

    public void draw(java.awt.Graphics2D g) {
        bg.draw(g);

        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("Multiplayer", 450, 100);

        g.setFont(font);
        for (int i = 0; i < options.length; ++i) {
            if (i == currentChoice) {
                g.setColor(Color.RED);

            } else {
                g.setColor(Color.BLACK);
            }
            g.drawString(options[i], 640, 400 + i * 30);

        }

    }

    private void select() {
        if (currentChoice == 0) {
            
            gsm.setState(GameStateManager.MULTISERVER);

        } else if (currentChoice == 1) {
            
            gsm.setState(GameStateManager.MULTICLIENT);

        } else if (currentChoice == 2) {

            gsm.setState(GameStateManager.MENUSTATE);
        }

    }

    public void keyPressed(int k) {

        if (k == KeyEvent.VK_ENTER) {
            select();
        }

        if (k == KeyEvent.VK_UP) {
            currentChoice--;
            if (currentChoice == -1) {
                currentChoice = options.length - 1;
            }
        }
        if (k == KeyEvent.VK_DOWN) {
            currentChoice++;
            if (currentChoice == options.length) {
                currentChoice = 0;
            }
        }

    }

}
