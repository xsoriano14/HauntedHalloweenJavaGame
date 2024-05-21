package game;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

public class TileController {

	GameScreen screen;
	public Tile[] tile;
	public int tileMapNum[][];
	
	/** constructor
	 * @param screen
	 */
	public TileController(GameScreen screen) {
		
		this.screen = screen;
		// set number of tiles used for game
		tile = new Tile[5];
		tileMapNum = new int[screen.mapCol][screen.mapRow];
		
		getTilePNG();
		loadTileMap("/maps/house_map_01.txt");
	}
	
	/**
	 * set tile png image
	 */
	public void getTilePNG() {
		
		try {
			
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wood.png"));
			
			tile[1] = new Tile();
			// setting collision to true allows for main_character to collide with wall
			tile[1].collision = true;
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
		
		}
		catch(IOException e) {
			// To do
		}
		
	}
	
	/**
	 * read a text file to generate a map
	 */
	public void loadTileMap(String mapFile) {
		
		try {
			
			InputStream is = getClass().getResourceAsStream(mapFile);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while (col < screen.mapCol && row < screen.mapRow) {
				
				String line = br.readLine();
				
				while(col < screen.mapCol) {
					
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					tileMapNum[col][row] = num;
					col++;
				}
				
				if (col == screen.mapCol) {
					col = 0;
					row++;
				}
			}
			br.close();
			
		}
		catch (Exception e) {
			// To do
		}
		
		
	}
	
	public void draw_tile(Graphics2D detailedImage) {
		
		int col = 0;
		int row = 0;
		
		while (col < screen.mapCol && row < screen.mapRow) {
			
			int tiles = tileMapNum[col][row];
			int mapX = col * screen.scaledTileSize;
			int mapY = row * screen.scaledTileSize;
			// get relative position of Main_character by subtracting relX and relY from absX and absY
			int screenX = mapX - screen.Main_character.absX + screen.Main_character.relX;
			int screenY = mapY - screen.Main_character.absY + screen.Main_character.relY;
			
			// render image around the player of screen size plus (screen.scaledTileSize * 2)
			// instead of rendering entire map every draw()
			if (mapX + (screen.scaledTileSize * 2)> screen.Main_character.absX - screen.Main_character.relX &&
				mapX - (screen.scaledTileSize * 2)< screen.Main_character.absX + screen.Main_character.relX &&
				mapY + (screen.scaledTileSize * 2)> screen.Main_character.absY - screen.Main_character.relY &&
				mapY - (screen.scaledTileSize * 2)< screen.Main_character.absY + screen.Main_character.relY) {
				
				detailedImage.drawImage(tile[tiles].image, screenX, screenY, screen.scaledTileSize, screen.scaledTileSize, null);

			}
			col++;
			
			if (col == screen.mapCol) {
				col = 0;
				row++;
			}
			
		}
		
	}
	
}
