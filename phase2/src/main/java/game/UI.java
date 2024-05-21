package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import object.Candy;
public class UI {
        
    GameScreen gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    BufferedImage keyImage;
    public boolean messageOn = false;   
    public String message = "";
    int messageCounter = 0;
    public int score = 0;
    public int commandNum = 0;
    public boolean gameFinished = false;

    /** constructor
     * @param gp
     */
    public UI(GameScreen gp){
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        Candy candy = new Candy();
        keyImage = candy.image;


    }
    /** set message as text
     * @param text
     */
    public void showMessage(String text){
        message = text;
        messageOn = true;



    }
    /** draw ui
     * @param g2
     */
    public void draw_ui(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.white);

        if(gp.gameState == gp.playState){
            // Do playState stuff later
        }
        if(gp.gameState == gp.pauseState){
            drawPauseScreen();;
        }
        if(gp.gameState ==gp.gameOverState){
            drawGameOverScreen();
        }
        if(gameFinished == true){
            g2.setFont(arial_40);
            g2.setColor(Color.white);

            String text;
            int textLength;
            int x;
            int y;


            g2.setFont(arial_80B);
            g2.setColor(Color.yellow);
            text = "GAME CLEAR!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x =gp.screenWidth/2  - textLength/2;
			y = gp.screenHeight / 2 /* + (gp.scaledTileSize*2) */;
            g2.drawString(text, x, y);

            gp.gameInstance = null;


        }
        
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        g2.drawImage(keyImage, gp.scaledTileSize/2, gp.scaledTileSize/2, gp.scaledTileSize, gp.scaledTileSize, null);
        g2.drawString("x "+ gp.Main_character.numCandy,74,65);

        g2.drawString("Score: " + gp.Main_character.playerScore, 74, 100);
        
        if(gp.gameState == gp.titleState){
            drawTitleScreen();         
        }

         // message
        if(messageOn ==true){
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(message, gp.scaledTileSize/2, gp.scaledTileSize*5);

            messageCounter++;

            if(messageCounter > 120){
                messageCounter = 0;
                messageOn = false;  
            }

    }

    }

    /**
     * draw pause screen
     */
    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }
    
    /**
     * draw title screen
     */
    public void drawTitleScreen(){
        g2.setColor(new Color(0,0,0));
        g2.fillRect(0,0, gp.screenWidth,gp.screenHeight);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,45F));
        String text = "Spooky Halloween Adventure";
        int x = getXforCenteredText(text);
        int y = gp.scaledTileSize*3;

        //Shadow
        g2.setColor(Color.gray);
        g2.drawString(text,x+5,y+5);
        g2.setColor(Color.white);
        g2.drawString(text,x,y);

        // Player Image
        x = gp.screenWidth/2 - (gp.scaledTileSize*2)/2;
        y += gp.scaledTileSize*2;
        g2.drawImage(gp.Main_character.down1,x, y, gp.scaledTileSize*2, gp.scaledTileSize*2,null);

        // Menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,42F));
        text = "NEW GAME";
        x = getXforCenteredText(text);
        y += gp.scaledTileSize*3.5;
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString(">", x-gp.scaledTileSize, y);
        }

//        g2.setFont(g2.getFont().deriveFont(Font.BOLD,42F));
//        text = "LOAD GAME";
//        x = getXforCenteredText(text);
//        y += gp.scaledTileSize;
//        g2.drawString(text, x, y);
//        if(commandNum == 1){
//            g2.drawString(">", x-gp.scaledTileSize, y);
//        }

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,42F));
        text = "QUIT";
        x = getXforCenteredText(text);
        y += gp.scaledTileSize;
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.drawString(">", x-gp.scaledTileSize, y);
        }
    }

    /** center text
     * @param text
     * @return
     */
    public int getXforCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }

    /**
     * draw game over screen
     */
    public void drawGameOverScreen(){
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0,gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));

        text = "Game Over";
        g2.setColor(Color.black);
        x = getXforCenteredText(text);
        y = gp.scaledTileSize*4;
        g2.drawString(text, x, y);
        g2.setColor(Color.white);
        g2.drawString(text, x-4, y-4);

        //Retry
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Retry";
        x = getXforCenteredText(text);
        y += gp.scaledTileSize*4;
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString(">", x-40, y);
        }

        // Back to the Title Screen
        text = "Quit";
        x = getXforCenteredText(text);
        y += 55;
        g2.drawString(text,x,y);
        if(commandNum == 1){
            g2.drawString(">", x-40, y);
        }

    }
}
