package game;

// import object.Candy;
import com.sun.org.apache.bcel.internal.generic.ObjectType;
import object.*;

public class ObjectsSettings {
	GameScreen screen;
	

	/** constructor
	 * @param screen
	 */
	public ObjectsSettings(GameScreen screen) {

		this.screen = screen;
	}


	/** create object class objects
	 * @param type
	 * @param index
	 * @param x
	 * @param y
	 */
	public void makeObject(String type, int index, int x, int y){
		switch (type){
			case "CANDY":
				screen.myObjects[index] = new Candy();
				break;
			case "BLACK_LICORICE":
				screen.myObjects[index] = new BlackLicorice();
				break;
			case "CANDY_BASKET":
				screen.myObjects[index] = new CandyBasket();
				break;
		}
		screen.myObjects[index].xVal = screen.scaledTileSize * x;
		screen.myObjects[index].yVal = screen.scaledTileSize * y;
	}

	/** create animate class enemy objects
	 * @param type
	 * @param index
	 * @param x
	 * @param y
	 */
	public void makeEnemy(String type, int index, int x, int y){
		switch (type) {
			case "GHOST":
				screen.enemy[index] = new Ghost(screen);
				break;
			case "ZOMBIE":
				screen.enemy[index] = new Zombie(screen);
				break;
			// Add cases for other enemy types if needed
		}
		screen.enemy[index].absX = screen.scaledTileSize * x;
		screen.enemy[index].absY = screen.scaledTileSize * y;
	}
	
	/**
	 * set object type and location
	 */
	public void settingTheObject() {
		makeObject("CANDY", 0, 8, 2);
		makeObject("CANDY", 1, 10, 13);
		makeObject("BLACK_LICORICE", 2, 4, 9);
		makeObject("BLACK_LICORICE", 3, 25, 5);
		makeObject("CANDY_BASKET", 4, 20, 5);
		makeObject("CANDY_BASKET", 5, 2, 2);
		makeObject("CANDY", 6, 10, 30);
		makeObject("CANDY", 7, 30, 30);
	
	}
	
	/**
	 * set enemy type and location
	 */
	public void setZombie() {

		makeEnemy("GHOST", 0, 5, 30);
		makeEnemy("ZOMBIE", 1, 5, 10);
		makeEnemy("ZOMBIE", 2, 30, 7);
		makeEnemy("GHOST", 3, 11, 32);
		makeEnemy("ZOMBIE", 4, 10, 20);
		makeEnemy("ZOMBIE", 5, 20, 18);
		
	}
}
