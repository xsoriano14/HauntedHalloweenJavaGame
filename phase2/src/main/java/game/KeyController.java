package game;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyController implements KeyListener {
    
    GameScreen gp;
    public boolean leftPushed, downPushed, rightPushed, upPushed;

    /** constructor
     * @param gp
     */
    public KeyController(GameScreen gp){
        this.gp = gp;
    }
    /**
     * check keyboard input using key event i
     */
    @Override
    public void keyPressed(KeyEvent i) {
         int input_code = i.getKeyCode();

        if(gp.gameState == gp.gameOverState){
            gameOverState(input_code);
        }
        if(gp.gameState ==gp.titleState){
        	
            if(input_code == KeyEvent.VK_W){ // if player pressed W
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0){
                    gp.ui.commandNum = 1;
                }
        }
            if(input_code == KeyEvent.VK_S){ // if player pressed S
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 1){
                    gp.ui.commandNum = 0;
                }
        }

            if(input_code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNum == 0){
                    gp.gameState = gp.playState;
                    //gp.musicPlayer(0); 
                }
//                if(gp.ui.commandNum == 1){
//                    // add later
//                }
                if(gp.ui.commandNum == 1){
                    System.exit(0);
                }
            }
        } 


        if(input_code == KeyEvent.VK_W){ // if player pressed W
            upPushed = true;
        }
        if(input_code == KeyEvent.VK_A){ // if player pressed A
            leftPushed = true;
        }
        if(input_code == KeyEvent.VK_S){ // if player pressed S
            downPushed = true;
        }
        if(input_code == KeyEvent.VK_D){ // if player pressed D
            rightPushed = true;
        }
        if(input_code == KeyEvent.VK_P){ // if player pressed P
            if(gp.gameState ==gp.playState){
                gp.gameState = gp.pauseState;
            }
            else if(gp.gameState == gp.pauseState && i.getKeyCode() == KeyEvent.VK_P){
                gp.gameState = gp.playState;
            }
        }
        
    }

    public void SimulatekeyPressed(int _input_code) {
         int input_code = _input_code;

        if(gp.gameState ==gp.titleState){
            if(input_code == KeyEvent.VK_W){ // if player pressed W
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0){
                    gp.ui.commandNum = 2;
                }
        } 
            if(input_code == KeyEvent.VK_S){ // if player pressed S
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2){
                    gp.ui.commandNum = 0;
                }
        }

            if(input_code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNum == 0){
                    gp.gameState = gp.playState;
                    //gp.musicPlayer(0); 
                }
                if(gp.ui.commandNum == 1){
                    // add later
                }
                if(gp.ui.commandNum == 2){
                    System.exit(0);
                }
            }
        }


        if(input_code == KeyEvent.VK_W){ // if player pressed W
            upPushed = true;
        }
        if(input_code == KeyEvent.VK_A){ // if player pressed A
            leftPushed = true;
        }
        if(input_code == KeyEvent.VK_S){ // if player pressed S
            downPushed = true;
        }
        if(input_code == KeyEvent.VK_D){ // if player pressed D
            rightPushed = true;
        }
        if(input_code == KeyEvent.VK_P){ // if player pressed P
            if(gp.gameState ==gp.playState){
                gp.gameState = gp.pauseState;
            }
            else if(gp.gameState == gp.pauseState && input_code == KeyEvent.VK_P){
                gp.gameState = gp.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent i) {
        int input_code = i.getKeyCode();

        if(input_code == KeyEvent.VK_W){ // if player pressed W
            upPushed = false;
        }
        if(input_code == KeyEvent.VK_A){ // if player pressed A
            leftPushed = false;
        }
        if(input_code == KeyEvent.VK_S){ // if player pressed S
            downPushed = false;
        }
        if(input_code == KeyEvent.VK_D){ // if player pressed D
            rightPushed = false;
        }
    }

    public void gameOverState(int code){
    	
        if(code == KeyEvent.VK_W){
            gp.ui.commandNum--;
            if(gp.ui.commandNum<0){
                gp.ui.commandNum = 1;
            }
        }
        if(code == KeyEvent.VK_S){
            gp.ui.commandNum++;
            if(gp.ui.commandNum>1){
                gp.ui.commandNum = 0;
            }
        }
        if(code ==  KeyEvent.VK_ENTER){
            if(gp.ui.commandNum ==0){
                gp.gameState = gp.playState;
                gp.stopMusic();
                gp.retry();
            }
            else if(gp.ui.commandNum == 1){
                gp.gameState = gp.titleState;
                gp.stopMusic();
                gp.restart();
            }
        }
    }
    @Override
    public void keyTyped(KeyEvent i) {
        // dont use
    }    
}
