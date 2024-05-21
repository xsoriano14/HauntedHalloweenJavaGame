package pathfinder;

import java.awt.geom.Line2D;

import game.GameScreen;

public class Obstruction {

	GameScreen screen;
	public int count = 0;
	public Line2D[] obstacle = new Line2D[25];
	
	/**
	 * constructor, hard code walls placed on map
	 */
	public Obstruction() {
		//outer walls
		obstacle[0] = new Line2D.Double(0,0,31,0);
		obstacle[1] = new Line2D.Double(0,0,0,35);
		obstacle[2] = new Line2D.Double(0,35,31,35);
		obstacle[3] = new Line2D.Double(31,0,31,35);
		
		//inner walls (6,0)(6,3)
		obstacle[4] = new Line2D.Double(6,0,6,3);
		//inner walls (0,5)(4,5)
		obstacle[5] = new Line2D.Double(0,5,4,5);
		//inner walls (10,0)(10,11)
		obstacle[6] = new Line2D.Double(10,0,10,11);
		//inner walls (1,14)(10,14)
		obstacle[7] = new Line2D.Double(1,14,10,14);
		//inner walls (10,9)(27,9)
		obstacle[8] = new Line2D.Double(10,9,27,9);
		//inner walls (6,23)(12,23)
		obstacle[9] = new Line2D.Double(6,23,12,23);
		//inner walls (21,16)(21,27)
		obstacle[10] = new Line2D.Double(21,16,21,27);
		
		count = 11;
	}
	
}

