
package GameState;

import java.util.ArrayList;
public class GameStateManager {
    private ArrayList<GameState> gameStates;
    private int currentState;
    
    public static final int MENUSTATE = 0;
    public static final int LEVEL1STATE = 1;
    public static final int MULTIPLAYERPRE = 2;
    public static final int MULTISERVER = 3;
    public static final int MULTICLIENT = 4; 
     public static final int HELP = 5;
    
    public GameStateManager()
    {
        gameStates = new ArrayList<GameState>();
        
        currentState = MENUSTATE;
        gameStates.add(new MenuState(this));
        gameStates.add(new Level1State(this));
       gameStates.add(new Multiplayerpre(this));
       gameStates.add(new MultiServer(this));
       gameStates.add(new MultiClient(this));
       gameStates.add(new Help(this));
    }
    
    public void setState(int state)
    {
        currentState = state;
        gameStates.get(currentState).init();
        
    }
    
    public void update()
    {
        gameStates.get(currentState).update();
    }
    
    public void draw(java.awt.Graphics2D g)
    {
      gameStates.get(currentState).draw(g);
    }
    
    public void keyPressed(int k)
    {
        gameStates.get(currentState).keyPressed(k);
        
    }
    
    public void keyReleased(int k)
    {
        gameStates.get(currentState).keyReleased(k);
        
    }
    
    
}
