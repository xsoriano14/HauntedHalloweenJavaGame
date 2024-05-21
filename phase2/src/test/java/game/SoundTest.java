package game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SoundTest {
    GameScreen screen;

    @Before
    public void setUp() {
        screen = new GameScreen();

    }

    @After
    public void reset () {
       screen = null;
    }
    @Test
    public void SoundTest() {
        screen.musicPlayer(0);
        screen.update();
        assertEquals(true, screen.sound != null);
    }

    @Test
    public void TurnOffSoundTest() {
        screen.musicPlayer(0);
        screen.update();
        screen.stopMusic();
        screen.update();
        assertEquals(false, screen.sound == null);
    }
}
