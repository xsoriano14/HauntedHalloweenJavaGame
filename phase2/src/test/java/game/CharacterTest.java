package game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import object.*;
import game.*;
import org.junit.experimental.theories.suppliers.TestedOn;

import javax.swing.*;

import static org.junit.Assert.*;


public class CharacterTest {

    KeyController k;
    Character p;

    private int candyNum;
    private int score;
    private int hasKey;
    ArrayList<Inanimate> inanimate;
    GameScreen screen;
    
    @Before
    public void setUp() {
        screen = new GameScreen();
        k = new KeyController(screen);
        p = new Character(screen,k);
        candyNum = 0;
        score = 0;
        hasKey = 1;
        // inanimate = new ArrayList<>();
        // screen.setUp();
        // screen.startGameInstance();
    }

    @After
    public void reset () {
        k = null;
        p = null;
        inanimate = null;
//        screen = null;
    }

    @Test
    public void updateNoDirection() {
        screen.update(); 
        // x and y values are by default 336 and 240
        // each movement is 4
        assertEquals(336, p.absX);
        assertEquals(240, p.absY);
    }

    @Test
    public void moveUpNoCollisions() {
        k.SimulatekeyPressed(KeyEvent.VK_W);
        p.update();
        // up decreased y position
        assertEquals(336, p.absX);
        assertEquals(236, p.absY);
    }


    @Test
    public void moveDownNoCollisions() {
        k.SimulatekeyPressed(KeyEvent.VK_S);
        p.update();
        // up decreased y position
        assertEquals(336, p.absX);
        assertEquals(244, p.absY);
    }

    @Test
    public void moveLeftNoCollisionTest() {
        k.SimulatekeyPressed(KeyEvent.VK_A);
        p.update();
        // left decreased x position
        assertEquals(332, p.absX);
        assertEquals(240, p.absY);
    }

    @Test
    public void moveRightNoCollisionTest() {
        k.SimulatekeyPressed(KeyEvent.VK_D);
        p.update();
        assertEquals(340, p.absX);
        assertEquals(240, p.absY);


    }

    @Test
    public void CollideGhostTest() {
        screen.enemy[0] = new Ghost(screen);
		screen.enemy[0].absX = 340;
		screen.enemy[0].absY = 240;
        k.SimulatekeyPressed(KeyEvent.VK_D);
        p.update();
        assertEquals(-100, p.playerScore);
        assertEquals(p.collisionDetection, true);
        assertEquals(true, screen.dc.playerCheck(p));

    }

    @Test
    public void CollideZombieTest() {
        screen.enemy[0] = new Zombie(screen);
		screen.enemy[0].absX = 340;
		screen.enemy[0].absY = 240;
        k.SimulatekeyPressed(KeyEvent.VK_D);
        p.update();
        assertEquals(-100, p.playerScore);
        assertEquals(p.collisionDetection, true);
        assertEquals(true, screen.dc.playerCheck(p));
    }

    @Test
    public void CollideWallTest() {
        // walk up untill you collide with wall
        for(int i = 0; i < 100; i++){ 
            k.SimulatekeyPressed(KeyEvent.VK_W);
            p.update();
        }
        
        assertEquals(0, p.playerScore);
        assertEquals(p.collisionDetection, true);
    }

    @Test
    public void CollectBasicRewardTest() {
        screen.myObjects[0] = new Candy();
		screen.myObjects[0].xVal = 340;
		screen.myObjects[0].yVal = 240;
        k.SimulatekeyPressed(KeyEvent.VK_D);
        p.update();
        assertEquals(50, p.playerScore);
    }

    @Test
    public void CollectBonusRewardTest() {
        screen.myObjects[0] = new CandyBasket();
		screen.myObjects[0].xVal = 340;
		screen.myObjects[0].yVal = 240;
        k.SimulatekeyPressed(KeyEvent.VK_D);
        p.update();
        assertEquals(150, p.playerScore);
    }

    @Test
    public void CollectBadRewardTest() {
        screen.myObjects[0] = new BlackLicorice();
		screen.myObjects[0].xVal = 340;
		screen.myObjects[0].yVal = 240;
        k.SimulatekeyPressed(KeyEvent.VK_D);
        p.update();
        assertEquals(-50, p.playerScore);
    }

//    @Test
//    public void GhostTrackingTest() {
//        screen.enemy[0] = new Ghost(screen);
//        screen.enemy[0].absX = 360;
//        screen.enemy[0].absY = 245;
//        screen.update();
//        assertEquals(screen.Main_character.absX, screen.enemy[0].absX);
//    }

    @Test
    public void ObjectCollisionTestDown() {
        screen.myObjects[0] = new Candy();
        screen.myObjects[0].xVal = 340;
        screen.myObjects[0].yVal = 240;
        screen.Main_character.direction = "down";
        screen.Main_character.update();
        int temp = screen.dc.ObjectCheck(screen.Main_character,true);

        assertEquals(0, temp); // 999 = no collision
    }

    @Test
    public void ObjectCollisionTestRight() {
        screen.myObjects[0] = new Candy();
        screen.myObjects[0].xVal = 340;
        screen.myObjects[0].yVal = 240;
        screen.Main_character.direction = "right";
        screen.Main_character.update();
        int temp = screen.dc.ObjectCheck(screen.Main_character,true);

        assertEquals(0, temp); // 999 = no collision
    }

    @Test
    public void ObjectCollisionTestLeft() {
        screen.myObjects[0] = new Candy();
        screen.myObjects[0].xVal = 340;
        screen.myObjects[0].yVal = 240;
        screen.Main_character.direction = "left";
        screen.Main_character.update();
        int temp = screen.dc.ObjectCheck(screen.Main_character,true);

        assertEquals(0, temp); // 999 = no collision
    }

    @Test
    public void ObjectCollisionTestUp() {
        screen.myObjects[0] = new Candy();
        screen.myObjects[0].xVal = 340;
        screen.myObjects[0].yVal = 240;
        screen.Main_character.direction = "up";
        screen.Main_character.update();
        int temp = screen.dc.ObjectCheck(screen.Main_character,true);

        assertEquals(0, temp); // 999 = no collision 0 = collsion
    }




}
