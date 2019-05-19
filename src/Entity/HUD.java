
package Entity;


import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class HUD {
    
    private Player player;
    private Player player2;
    
    private BufferedImage image;
    private BufferedImage image2;
    private Font font;
    
    public HUD(Player p,Player p2)
    {
        player = p;
        player2 = p2;
        try{
          image = ImageIO.read(getClass().getResourceAsStream("/Res/HUD/hud.gif"));
           image2 = ImageIO.read(getClass().getResourceAsStream("/Res/HUD/hud_1.gif"));
            font = new Font("Arial",Font.PLAIN,20);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void draw(Graphics2D g)
    {
        g.drawImage(image, 0, 250,null);
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(player.getHealth() + "/" + player.getMaxHealth(),80,275);
        g.drawString(player.getFire()/100 + "/" + player.getMaxFire()/100,70,320);
        
        
        g.drawImage(image2, 1100, 250,null);
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(player2.getHealth() + "/" + player2.getMaxHealth(),1160,275);
        g.drawString(player2.getFire()/100 + "/" + player2.getMaxFire()/100,1130,320);
        
        
        
    }
    
}
