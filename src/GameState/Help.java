
package GameState;

import TileMap.Background;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Help extends GameState {

    
    
     private Background bg;
          private Color titleColor;
          private Font titleFont;
    
    private Font font;
    
    
    
    public Help(GameStateManager gsm) {
        this.gsm = gsm;
        
        init();
    }
         

    public void init() {
       
        
        try{
            bg = new Background("/Res/Backgrounds/menubgnagy.gif",1);
            bg.setVector(-0.1, 0);
            
            titleColor = new Color(0,0,0);
            titleFont = new Font("Century Gothic",Font.PLAIN,72);
             font = new Font("Arial",Font.PLAIN,30);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    public void update() {
         bg.update();
    }


    public void draw(Graphics2D g) {
        bg.draw(g);
    
    
    g.setColor(titleColor);
    g.setFont(titleFont);
    g.drawString("Controls",500,100);
    
    
    g.setFont(font);
    
    
    g.drawString("Player1 controls", 300, 250);
    
    g.drawString("A: run left", 300, 300);
    g.drawString("D: run right", 300, 325);
    g.drawString("W: jump", 300, 350);
    g.drawString("C: glide", 300, 375);
    g.drawString("V: melee attack", 300, 400);
    g.drawString("B: tornado attack", 300, 425);
    
    
    
    g.drawString("Player2 controls", 700, 250);
    
    g.drawString("LEFT: run left", 700, 300);
    g.drawString("RIGHT: run right", 700, 325);
    g.drawString("UP: jump", 700, 350);
    g.drawString("I: glide", 700, 375);
    g.drawString("O: melee attack", 700, 400);
    g.drawString("P: tornado attack", 700, 425);
    
    
   g.drawString("Press ESC to get back to the menu", 425, 550);
    
     }
    


    public void keyPressed(int k) {
       
         if(k == KeyEvent.VK_ESCAPE)
    {
       gsm.setState(GameStateManager.MENUSTATE);
    }
        
        
        
        
        
    }

 
    public void keyReleased(int k) {
       
    }
    
}
