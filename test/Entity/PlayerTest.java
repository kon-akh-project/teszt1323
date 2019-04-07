/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import TileMap.*;
import Entity.Player;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Krisztian
 */
public class PlayerTest {
    
    public PlayerTest() {
    }

    
    @Test
    public void testGetHealth() {
        TileMap tileMap = new TileMap(50);
        tileMap.setPosition(0, 0);

        Player player = new Player(tileMap);
        
        int actual = player.getHealth();
        int expected = 5;
        assertEquals(expected,actual);
    }

    
    @Test
    public void testGetMaxHealth() {
        TileMap tileMap = new TileMap(50);
        tileMap.setPosition(0, 0);

        Player player = new Player(tileMap);
        
        int actual = player.getMaxHealth();
        int expected = 5;
        assertEquals(expected,actual);        
    }

   
    @Test
    public void testGetFire() {
        TileMap tileMap = new TileMap(50);
        tileMap.setPosition(0, 0);

        Player player = new Player(tileMap);
        
        int actual = player.getFire();
        int expected = 2500;
        assertEquals(expected,actual);         
    }

  
    @Test
    public void testGetMaxFire() {
        TileMap tileMap = new TileMap(50);
        tileMap.setPosition(0, 0);

        Player player = new Player(tileMap);
        
        int actual = player.getMaxFire();
        int expected = 2500;
        assertEquals(expected,actual);          
    }

    
    @Test
    public void testSetFiring() {
        TileMap tileMap = new TileMap(50);
        tileMap.setPosition(0, 0);

        Player player = new Player(tileMap);
        
        player.setFiring();
        int expected = 2500;
        assertTrue(player.getFiring());         
    }

   
    @Test
    public void testSetScratching() {
    }

    
    @Test
    public void testSetGliding() {
    }

    @Test
    public void testUpdate() {
    }

    @Test
    public void testDraw() {
    }
    
}
