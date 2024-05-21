package game;

import java.awt.geom.Line2D;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Ghost extends Animate {
	
	/** constructor
	 * @param screen
	 */
	public Ghost(GameScreen screen) {
		
		super(screen);
		
		direction = "down";
		speed = 1;
		
		getGhostPNG();
	}
    
	/**
	 * set ghost png images
	 */
	public void getGhostPNG() {

        try {

            left1 = ImageIO.read(getClass().getResourceAsStream("/ghost/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/ghost/left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/ghost/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/ghost/right2.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/ghost/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/ghost/up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/ghost/right1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/ghost/right2.png"));
        }
        catch (IOException e) {
            // To Do
        }
    }
	
	/**
	 * change direction of ghost based vector difference between ghost and player
	 */
	public void activate() {
		
		activateCounter ++;

		if (activateCounter == 10) {
			// screen.enemy[0].update();
			// System.out.println(screen.enemy[0].collisionDetection);
			
			if(Math.abs(screen.Main_character.absX - absX) > 15) {
					if (screen.Main_character.absX < absX) {
						direction = "left";
					}
					
						// screen.Main_character.absX++;
					if (screen.Main_character.absX > absX) {
						direction = "right";
						// screen.Main_character.absX--;
					}
			}
			
				

			if(Math.abs(screen.Main_character.absY - absY) > 15 ) {
					if (screen.Main_character.absY < absY ) {
						direction = "up";
					}
					
					if (screen.Main_character.absY > absY ) {
						direction = "down";
						
					}
				// System.out.println("chary " + screen.Main_character.absY);
			}
				// System.out.println(screen.);

			
			activateCounter = 0;
		
			
			
		}


		
	}

    public void update() {
    	
    	activate();
    	checkCollisionGhost();
    	
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
}
    


