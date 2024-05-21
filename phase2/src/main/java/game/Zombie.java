package game;

import java.awt.geom.Line2D;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Zombie extends Animate {
	
	/** constructor
	 * @param screen
	 */
	public Zombie(GameScreen screen) {
		
		super(screen);
		
		direction = "down";
		speed = 1;
		
		getZombiePNG();
	}
    
	/**
	 * set zombie png image
	 */
	public void getZombiePNG() {

        try {

            left1 = ImageIO.read(getClass().getResourceAsStream("/zombie/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/zombie/left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/zombie/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/zombie/right2.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/zombie/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/zombie/up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/zombie/down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/zombie/down2.png"));
        }
        catch (IOException e) {
            // To Do
        }
    }
	
	/**
	 * start enemy path finding
	 */
	public void activate() {
		
		int diffx, diffy;
		Line2D ray = new Line2D.Double(0,0,0,0);
		
		for (int i = 0 ; i < screen.enemy.length; i++) {
			
			if (screen.enemy[i] != null) {
				if (screen.pf.getPath(screen.enemy[i], screen.Main_character) != null) {
					ray = screen.pf.getPath(screen.enemy[i], screen.Main_character);
					diffx = (int) (ray.getX2()-ray.getX1());
					diffy = (int) (ray.getY2()-ray.getY1());
					
					
					if (diffx > 0) {
						screen.enemy[i].direction = "right";
					}
					else if (diffx < 0) {
						screen.enemy[i].direction = "left";
					}
					else if (diffy > 0) {
						screen.enemy[i].direction = "down";
					}
					else if (diffy < 0) {
						screen.enemy[i].direction = "up";
					}

				}
				else {
					activateCounter ++;
					
					if (activateCounter == 120) {
						
						//random integer generated from 1 to 100
						Random random = new Random();
						int j = random.nextInt(100) + 1;
						
						// randomly changes zombie image depending on i
						if (j <= 25) {
							direction = "up";
						}
						if (j > 25 && j <= 50) {
							direction = "down";
						}
						if (j > 50 && j <= 75) {
							direction = "left";
						}
						if (j > 75 && j <= 100) {
							direction = "right";
						}
						
						activateCounter = 0;
					}
			    }
			}
		}
	}

}
