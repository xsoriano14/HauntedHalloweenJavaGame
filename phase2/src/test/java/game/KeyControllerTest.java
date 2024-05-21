package game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.junit.Before;
import org.junit.Test;

public class KeyControllerTest {

	GameScreen screen;
	KeyController k;
    Character p;
    int inputcode;
    boolean check = false;
    
    @Before
    public void setUp() {
        screen = new GameScreen();
        k = new KeyController(screen);
    }
    
    @Test
    public void wKeypressedTest() {	
        inputcode = KeyEvent.VK_W;
        k.SimulatekeyPressed(inputcode);
        assertTrue(k.upPushed);
    }
    
    @Test
    public void sKeypressedTest() {	
        inputcode = KeyEvent.VK_S;
        k.SimulatekeyPressed(inputcode);
        assertTrue(k.downPushed);
    }
    
    @Test
    public void aKeypressedTest() {	
        inputcode = KeyEvent.VK_A;
        k.SimulatekeyPressed(inputcode);
        assertTrue(k.leftPushed);
    }
	
    @Test
    public void dKeypressedTest() {	
        inputcode = KeyEvent.VK_D;
        k.SimulatekeyPressed(inputcode);
        assertTrue(k.rightPushed);
    }
    
    @Test
    public void enterKeypressedTest() {	
    	screen.ui.commandNum = 0;
        inputcode = KeyEvent.VK_ENTER;
        k.SimulatekeyPressed(inputcode);
        assertTrue(screen.gameState == screen.playState);  
    }
    
    @Test
    public void pKeypressedTest() {	
    	screen.gameState = screen.playState;
        inputcode = KeyEvent.VK_P;
        k.SimulatekeyPressed(inputcode);
        assertTrue(screen.gameState == screen.pauseState);  
    }

}
