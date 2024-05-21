package game;

import object.Inanimate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class EnemyTest {

    KeyController k;
    Character p;
    ArrayList<Inanimate> inanimate;
    GameScreen screen;

    @Before
    public void setUp() {
        screen = new GameScreen();
        screen.enemy[0] = new Ghost(screen);
        screen.enemy[0].absX = 336;
        screen.enemy[0].absY = 236;
        screen.enemy[1] = new Zombie(screen);
        screen.enemy[1].absX = 630;
        screen.enemy[1].absY = 520;
    }

    @After
    public void reset () {
    
    	screen = null;

    }
    @Test
    public void GhostTrackingTest() {
        for(int i = 0; i <10; i++) {
            //screen.Main_character.update();
            screen.enemy[0].update();
        }
        assertEquals(true, screen.Main_character.playerScore<0);  // if player is hit tracking is successful
    }

    @Test
    public void ZombieTrackingTest() {
        for(int i = 0; i < 10; i++) {
            //screen.Main_character.update();
            screen.enemy[1].update();
        }
        assertEquals(0, screen.Main_character.playerScore );
    }
}
