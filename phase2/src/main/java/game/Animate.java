package game;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Animate {

	GameScreen screen;

	public int absX;
	public int absY;
	public int speed;
	public boolean invincibility = false;
	public int invincibleCounter = 0;

	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public String direction;

	public int pngCounter = 0;
	public int pngNum = 1;

	public Rectangle hitbox = new Rectangle(0, 0, 40, 40);
	public int hitboxDefaultX, hitboxDefaultY;
	public boolean collisionDetection = false;

	public int activateCounter = 0;

	public Animate() {

	}
	 
	/**Animate class constructor
	 * @param screen
	 */
	public Animate(GameScreen screen) {

		this.screen = screen;
		// set collision hit box size smaller than character pixel width and height
		hitbox = new Rectangle();
		hitbox.x = 8;
		hitbox.y = 16;
		hitboxDefaultX = hitbox.x;
		hitboxDefaultY = hitbox.y;
		hitbox.width = 32;
		hitbox.height = 32;

	}

	/**
	 * abstract method for enemies state
	 */
	public void activate() {
	}

	/**
	 * animate object collision check
	 */
	public void checkCollision() {

		collisionDetection = false;
		screen.dc.tileCheck(this);
		screen.dc.enemyCheck(this, screen.enemy);
		boolean contact = screen.dc.playerCheck(this);

		if (contact == true) {
			screen.Main_character.reduceScore(2);
		}

	}

	/**
	 * player collision check with ghost
	 */
	public void checkCollisionGhost() {

		collisionDetection = false;
		// screen.dc.tileCheck(this);
		// screen.dc.enemyCheck(this, screen.enemy);
		boolean contact = screen.dc.playerCheck(this);

		if (contact == true) {
			screen.Main_character.reduceScore(2);
		}

	}

	public void update() {

		activate();
		checkCollision();

		if (collisionDetection == false) {

			switch (direction) {
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

		// as pngCounter increases pngNum changes from 1 to 2, and vis creating
		// animation
		pngCounter++;
		if (pngCounter > 10) {
			if (pngNum == 1) {
				pngNum = 2;
			} else if (pngNum == 2) {
				pngNum = 1;
			}

			pngCounter = 0; // reset pngCounter
		}
	}

	/** draw animate class images based on switch condition direction
	 * @param detailedImage
	 */
	public void drawAnimate(Graphics2D detailedImage) {

		BufferedImage image = null;

		int screenX = absX - screen.Main_character.absX + screen.Main_character.relX;
		int screenY = absY - screen.Main_character.absY + screen.Main_character.relY;

		// render image around the player of screen size plus (screen.scaledTileSize *
		// 2)
		// instead of rendering entire map every draw()
		if (absX + (screen.scaledTileSize * 2) > screen.Main_character.absX - screen.Main_character.relX &&
				absX - (screen.scaledTileSize * 2) < screen.Main_character.absX + screen.Main_character.relX &&
				absY + (screen.scaledTileSize * 2) > screen.Main_character.absY - screen.Main_character.relY &&
				absY - (screen.scaledTileSize * 2) < screen.Main_character.absY + screen.Main_character.relY) {

			switch (direction) {
				case "up":
					if (pngNum == 1) {
						image = up1;
					}
					if (pngNum == 2) {
						image = up2;
					}
					break;
				case "down":
					if (pngNum == 1) {
						image = down1;
					}
					if (pngNum == 2) {
						image = down2;
					}
					break;
				case "left":
					if (pngNum == 1) {
						image = left1;
					}
					if (pngNum == 2) {
						image = left2;
					}
					break;
				case "right":
					if (pngNum == 1) {
						image = right1;
					}
					if (pngNum == 2) {
						image = right2;
					}
					break;
			}

			if(image != null){
				detailedImage.drawImage(image, screenX, screenY, screen.scaledTileSize, screen.scaledTileSize, null);
			}


		}

	}
}
