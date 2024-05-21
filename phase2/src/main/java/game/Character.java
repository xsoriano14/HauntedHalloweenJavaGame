package game;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import java.io.IOException;
import javax.imageio.ImageIO;

public class Character extends Animate{

    GameScreen screen;
    KeyController input;

    public final int relX;
    public final int relY;
    int numCandy = 0;
    int playerScore = 0;
    public int hasKey = 0;

    /** constructor for character class
     * @param _screen
     * @param _input
     */
    public Character(GameScreen _screen, KeyController _input){

    	this.screen = _screen;
        this.input = _input;

        relX = screen.screenWidth/2 - (screen.scaledTileSize/2);
        relY = screen.screenHeight/2 - (screen.scaledTileSize/2);
        setDefaultCharacter();
        getPlayerPNG();

    }

    /**
     * set character default position, speed, direction
     */
    public void setDefaultCharacter(){
        absX = screen.scaledTileSize * 7;
        absY = screen.scaledTileSize * 5;
        speed = 4;
        direction = "down";
    }

    /*
     * imageIO read player resources
     */
    public void getPlayerPNG() {

        try {

            left1 = ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/right2.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/down2.png"));
        }
        catch (IOException e) {
            // To Do
        }


    }

    public void update(){
    	
    	if (input.upPushed == true || input.downPushed == true ||
    			input.leftPushed == true || input.rightPushed == true) {

    		if(input.upPushed == true){
                direction = "up";
            }
            else if(input.downPushed == true){
                direction = "down";
            }
            else if(input.rightPushed == true){
                direction = "right";
            }
            else if(input.leftPushed == true){
                direction = "left";
            }

    		// reset collision detection
    		collisionDetection = false;
    		// detect collision
    		screen.dc.tileCheck(this);

            // check object collision
            int ObjectIndex = screen.dc.ObjectCheck(this, true);
            pickUpObject(ObjectIndex);
            
            // check enemy collision
            int enemyIndex = screen.dc.enemyCheck(this, screen.enemy);
            reduceScore(enemyIndex);

    		if (collisionDetection == false) {

    			switch(direction) {
	    			case "up":
	    				absY -= speed;
	    				break;
	    			case "down":
	    				absY += speed;
	    				break;
	    			case "left":
	    				absX -= speed;
	    				break;
	    			case "right":
	    				absX += speed;
	    				break;
    			}
    		}

            // as pngCounter increases pngNum changes from 1 to 2, and vis creating animation
            pngCounter++;
            if (pngCounter > 10) {
            	if (pngNum == 1) {
            		pngNum = 2;
            	}
            	else if (pngNum == 2) {
            		pngNum = 1;
            	}
            	pngCounter = 0; //reset pngCounter
            }

    	}


    	// invincibility counter stop multiple score loss
    	if (invincibility == true) {
    		
    		invincibleCounter++;
    		
    		if (invincibleCounter > 60) {
    			invincibility = false;
    			invincibleCounter = 0;
    		}
    	}
    }
    

    /** using an int value 999 determine collision to trigger event
     * @param _index
     */
    public void pickUpObject(int _index){
	        if(_index != 999) { //if index == 999 means we didnt touch anything
	
	        	// screen.myObjects[_index] = null; //delteobject from array
	        	
	            String objectName = screen.myObjects[_index].name;
	            switch(objectName) {
	                case "Candy":
	                	screen.soundEffect(1);
	                    numCandy++;
	                    playerScore += 50;
	                    screen.myObjects[_index] = null;
						if(numCandy<4){
							screen.ui.showMessage("You got a candy!");
						}
	                    System.out.println("new score is " + playerScore);
						if(numCandy>=4){
							screen.ui.gameFinished = true;
							screen.soundEffect(4);
						}
	                    break;
	
	                case "CandyBasket":
	                    playerScore += 150;
	                    screen.soundEffect(2);
	                    screen.myObjects[_index] = null;
	                    System.out.println("new score is " + playerScore);
	                    break;

	                case "BlackLicorice":
	                	screen.soundEffect(5);
	                    playerScore -= 50;
	                    screen.myObjects[_index] = null;
	                    System.out.println("new score is " + playerScore);
	                    break;

					case "Door":
						if(hasKey>0) {
							//screen.soundEffect(3);
							screen.myObjects[_index] = null;
							screen.ui.showMessage("You opened the  door!");
							hasKey--;
						}
						else{
							screen.ui.showMessage("You need a key!");
						}
						break;

					case "Key":
						//screen.soundEffect(3);
						hasKey++;
						screen.myObjects[_index] = null;
						break;

				}
	        }
	        
    }
    
    
    /** using int 999 to determine collision to reduce score, and start invincibility state
     * @param i
     */
    public void reduceScore(int i) {
    	
    	if (i != 999) {
    		
    		if (invincibility == false) {
    			playerScore -= 100;
    			invincibility = true;
    			System.out.println("reduced score by " + playerScore);
    		}
    	}

		if(playerScore < 0){
			//screen.stopMusic();
			screen.soundEffect(4);
			screen.gameState = screen.gameOverState;
		}
    	
    }
	
	/**
	 * set default position
	 */
	public void setDefaultPositions(){
		absX = screen.scaledTileSize*7;
		absY = screen.scaledTileSize*5;
		direction = "down";
	}
	
	/**
	 * reset player score
	 */
	public void restoreLife(){
		playerScore = 0;
		invincibility = false;
	}
    


    /** draw character image
     * @param detailedImage
     */
    public void draw(Graphics2D detailedImage){

        BufferedImage image = null;

        /**
         * change player image from 1 to 2 to make animation
         */
        switch (direction) {
            case "up":
            	if(pngNum == 1) {
            		image = up1;
            	}
            	if(pngNum == 2) {
            		image = up2;
            	}
                break;
            case "down":
            	if(pngNum == 1) {
            		image = down1;
            	}
            	if(pngNum == 2) {
            		image = down2;
            	}
                break;
            case "left":
            	if(pngNum == 1) {
            		image = left1;
            	}
            	if(pngNum == 2) {
            		image = left2;
            	}
                break;
            case "right":
            	if(pngNum == 1) {
            		image = right1;
            	}
            	if(pngNum == 2) {
            		image = right2;
            	}
                break;
        }
        // change character opacity to 70% when struck
        if (invincibility == true) {
        	detailedImage.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        
        detailedImage.drawImage(image, relX, relY, screen.scaledTileSize, screen.scaledTileSize, null);
        
        // reset opacity back
        detailedImage.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

}
