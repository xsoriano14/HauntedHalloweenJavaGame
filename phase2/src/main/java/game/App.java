package game;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class App 
{
    public static void main( String[] args )
    {
        //initialize game frame
        JFrame Game_Frame = new JFrame();
        Game_Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Game_Frame.setResizable(false);
        Game_Frame.setTitle("Haunted Halloween");

        GameScreen Screen = new GameScreen();
        Game_Frame.add(Screen);
        Game_Frame.pack(); //fits the window to specified size

        Game_Frame.setLocationRelativeTo(null); //place Game_Frame in center of screen
        Game_Frame.setVisible(true); //make it visible

        Screen.setUp();
        Screen.startGameInstance();
    }
}
