package pathfinder;

import java.awt.geom.Line2D;

import game.Animate;
import game.GameScreen;

public class PathFind {
	
	GameScreen screen;

	public int col, row;
	public int path[][];
	public int[][] map;
	public int scale;
	int startX, startY, endX, endY;
	int check;
	public Line2D[] segments;
	
	/** constructor
	 * @param screen
	 * @param arr
	 */
	public PathFind(GameScreen screen, int[][] arr) {
		col = screen.mapCol;
		row = screen.mapRow;
		map = new int[col][row];
		map = arr;
		scale = screen.scaledTileSize;
		check = screen.obs.count;
		segments = new Line2D[check];
		
		for (int i = 0 ; i< check; i++) {
			segments[i] = screen.obs.obstacle[i];
		}
	}
	
	
	/** find vector line from enemy to player, check if obstacles intersect with it used for path finding
	 * @param enemy
	 * @param character
	 * @return
	 */
	public Line2D getPath(Animate enemy, Animate character) {
		
		startX = enemy.absX/scale;
		startY = enemy.absY/scale;
		endX = character.absX/scale;
		endY = character.absY/scale;
		
		int count = 0;

		Line2D ray = new Line2D.Double(startX, startY, endX, endY);
		for (int i = 0; i < check; i++) {
			
			if (!ray.intersectsLine(segments[i])) {
				count++;
			}
			if (count == check) {
				return ray;
			}
		}
		return null;
	}

}
