package object;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;

import game.GameScreen;

public class Inanimate {
	public BufferedImage image;
	public String name;
	public boolean collision =  false;
	public int xVal, yVal;
	public Rectangle hitbox = new Rectangle(0,0,48,48);
	public int hitboxDefault_X = 0;
	public int hitboxDefault_Y = 0;
	

	/** draw object to world position
	 * @param detailedImage
	 * @param screen
	 */
	public void sketch(Graphics2D detailedImage, GameScreen screen) {
		int screenX = xVal - screen.Main_character.absX + screen.Main_character.relX;
		int screenY = yVal - screen.Main_character.absY + screen.Main_character.relY;
		
		// render image around the player of screen size plus (screen.scaledTileSize * 2)
		// instead of rendering entire map every draw()
		if (xVal + (screen.scaledTileSize * 2)> screen.Main_character.absX - screen.Main_character.relX &&
			xVal - (screen.scaledTileSize * 2)< screen.Main_character.absX + screen.Main_character.relX &&
			yVal + (screen.scaledTileSize * 2)> screen.Main_character.absY - screen.Main_character.relY &&
			yVal - (screen.scaledTileSize * 2)< screen.Main_character.absY + screen.Main_character.relY) {
			
			detailedImage.drawImage(image, screenX, screenY, screen.scaledTileSize, screen.scaledTileSize, null);

		}
		
	}

	/** set relative path
	 * @param path
	 */
	public void set_imagePath(String path){
		try {
			image = ImageIO.read(getClass().getResourceAsStream(path));
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
