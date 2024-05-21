package game;

import javax.swing.JPanel;
import object.Inanimate;
import pathfinder.Obstruction;
import pathfinder.PathFind;

// import java.awt.event.KeyListener;
// import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
  *Represents the main game screen where the logic and the rendering occur.
  * this class going to extends the JPanel and implement runnable interface for game functionality.
 */
public class GameScreen extends JPanel implements Runnable {

    /*
    setting up game dimensions and settings
     */

    //size of the tile as a default
    final int defaultSize = 16; // characters tiles objects are 16x16 pixels

    //scaling factor for the tiles
    final int upScale = 3;

    //getting the size of the scaled tile.
    public final int scaledTileSize = defaultSize * upScale; // 48x48 tile
    public final int rowTiles = 12; // screen is 12x16 tiles
    public final int colTiles = 16;
    public final int screenWidth = scaledTileSize * colTiles; // 768 pixels
    public final int screenHeight = scaledTileSize * rowTiles; // 576 pixels
    int FPS = 60;

    // Map settings
    public final int mapCol = 32;
    public final int mapRow = 36;
    public final int mapWidth = scaledTileSize * mapCol;
    public final int mapHeight = scaledTileSize * mapRow;
    public int map[][] = new int[mapCol][mapRow];
    Sound sound = new Sound();
    public TileController tCon = new TileController(this);
    Thread gameInstance;
    KeyController input = new KeyController(this);
    public DetectCollision dc = new DetectCollision(this);
    public Character Main_character = new Character(this, input);
    public Inanimate myObjects[] = new Inanimate[10];
    public ObjectsSettings objectSet = new ObjectsSettings(this);
    public Animate enemy[] = new Animate[10];
    public Obstruction obs = new Obstruction();
    public PathFind pf = new PathFind(this, tCon.tileMapNum); 
    public UI ui = new UI(this);

    // GAME STATE
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public int titleState = 0;
    public final int gameOverState = 6;


    /**
      *constructs a new GameScreen with default settings and initializes various game components.
      * Sets up the panel size, background color, and initializes game-related controllers.
     */
    public GameScreen() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(input);
        this.setFocusable(true);

    }

    /**
     * Resets the game to its initial state for a retry attempt.
     * Resets the main character's position and life, and sets up game objects and enemies.
     */
    public void retry() {
        Main_character.setDefaultPositions();
        Main_character.restoreLife();

        objectSet.settingTheObject();
        objectSet.setZombie();
        // musicPlayer(0);
    }

    public void restart() {
        Main_character.setDefaultPositions();
        Main_character.restoreLife();
        objectSet.settingTheObject();
        objectSet.setZombie();
    }

    /**
     * prepare the game for the title screen
     * this method set up the initial title screen
     */
    public void setUp() {
        objectSet.settingTheObject();
        objectSet.setZombie();
        musicPlayer(0);

        gameState = titleState;
    }

    /**
     * intitializing the game instance by creating a new thread for the game loop.
     * allow concurrent execution of game logic and user interface rendering.
     */
    public void startGameInstance() {
        gameInstance = new Thread(this);
        gameInstance.start();
    }

    /**
     * this method control the game loop, that updates the game logic and render the game screen based on the frame rate needed.
     * it is reponsible for the timing of the rendering and having a constant frame rate
     */
    public void run() {
        while (gameInstance != null) {

            double repaintInterval = 1000000000 / FPS; // set a interval for which new images are drawn to frame
            double newPaintTime = System.nanoTime() + repaintInterval; // compute the next time an image will be drawn
            // Update information about characters
            update();
            // Draw the Screen
            repaint();

            try { // sleep the thread until the repaint interval is over
                double leftoverTime = newPaintTime - System.nanoTime();
                leftoverTime = leftoverTime / 1000000;

                if (leftoverTime < 0) {
                    leftoverTime = 0;
                }

                newPaintTime += repaintInterval; // update newPaintTime
                Thread.sleep((long) leftoverTime);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

    /**
     * update the game state based on the current game state.
     * no updates happens when the game is on pause
     * if the game is currently on play state it will update the main character's state and the state of the enemies.
     *
     */
    public void update() {
        if (gameState == playState) {
            Main_character.update();
            for (int i = 0; i < enemy.length; i++) {

                if (enemy[i] != null) {
                    enemy[i].update();
                }
            }
        }
        if (gameState == pauseState) {
            // nothing
        }

    }

    /**
     * point the game screen based on the current game state.
     * This method overrides the JPanel's paintComponent method to render the graphical elements of the game.
     *
     * @param image the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics image) {

        super.paintComponent(image);
        // Graphics2D gives more precise control over graphics
        Graphics2D detailedImage = (Graphics2D) image;

        // TITLE SCREEN
        if (gameState == titleState) {
            ui.draw_ui(detailedImage);

        }

        else {
            // tile draw first
            tCon.draw_tile(detailedImage);

            // drawing objects
            for (int i = 0; i < myObjects.length; i++) {
                if (myObjects[i] != null) {
                    myObjects[i].sketch(detailedImage, this);
                }
            }

            // drawing enemies
            for (int i = 0; i < enemy.length; i++) {
                if (enemy[i] != null) {
                    enemy[i].drawAnimate(detailedImage);
                }
            }

            // player drawn above tile
            Main_character.draw(detailedImage);
            ui.draw_ui(detailedImage);

            detailedImage.dispose(); // delete image
        }

    }

    /**
     * Plays the background music for the game based on the provided index.
     *
     * @param i Index of the background music to play.
     */
    public void musicPlayer(int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    /**
     * music stop
     */
    public void stopMusic() {
        sound.stop();
    }

    /** change music using int i
     * @param i
     */
    public void soundEffect(int i) {
        sound.setFile(i);
        sound.play();
    }
}
