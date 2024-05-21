package game;

import object.Inanimate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class UITest {

    KeyController k;
    Character p;
    ArrayList<Inanimate> inanimate;
    GameScreen screen;

    Graphics2D g2d;

    BufferedImage image;

    @Before
    public void setUp() {
        screen = new GameScreen();
        k = new KeyController(screen);
        p = new Character(screen,k);
        image = new BufferedImage(400, 300, BufferedImage.TYPE_INT_ARGB);
        g2d = image.createGraphics();
        g2d.setColor(Color.RED);

    }

    @After
    public void reset () {
        k = null;
        p = null;
        inanimate = null;
        image = null;
        g2d = null;
    }
    @Test
    public void GameInstanceRunnningTest() {
        screen.setUp();
        screen.startGameInstance();
        assertEquals(true, screen.gameInstance != null);
    }

    @Test
    public void UiDrawTest() {

        // create generic graphics2d image
        screen.ui.draw_ui(g2d);
        assertEquals(true, screen.ui.gp != null);
        assertEquals(true, screen.ui.g2 != null);
    }

    @Test
    public void UiDrawPauseScreenTest() {

        // create generic graphics2d image
        screen.ui.draw_ui(g2d);
        screen.gameState = 5;
        screen.ui.drawPauseScreen();
        screen.paintComponent(g2d);
        assertEquals(true, screen.ui.gp != null);
        assertEquals(true, screen.ui.g2 != null);
    }

    @Test
    public void UiDrawTitleScreenTest() {

        // create generic graphics2d image
        screen.ui.draw_ui(g2d);
        screen.ui.drawTitleScreen();
        screen.gameState = 0;
        screen.paintComponent(g2d);
        assertEquals(true, screen.ui.gp != null);
        assertEquals(true, screen.ui.g2 != null);
    }

    @Test
    public void CharacterDrawWalkRightTest() {

        // create generic graphics2d image
        screen.Main_character.direction = "right";
        screen.Main_character.draw(g2d);
        assertEquals(true, (screen.Main_character.pngNum == 1 || screen.Main_character.pngNum == 2));
    }
    @Test
    public void CharacterDrawWalkDownTest() {

        // create generic graphics2d image
        screen.Main_character.direction = "down";
        screen.Main_character.draw(g2d);
        assertEquals(true, (screen.Main_character.pngNum == 1 || screen.Main_character.pngNum == 2));
    }
    @Test
    public void CharacterDrawWalkLeftTest() {

        // create generic graphics2d image
        screen.Main_character.direction = "left";
        screen.Main_character.draw(g2d);
        assertEquals(true, (screen.Main_character.pngNum == 1 || screen.Main_character.pngNum == 2));
    }
    @Test
    public void CharacterDrawWalkUpTest() {

        // create generic graphics2d image
        screen.Main_character.direction = "up";
        screen.Main_character.draw(g2d);
        assertEquals(true, (screen.Main_character.pngNum == 1 || screen.Main_character.pngNum == 2));
    }


}
