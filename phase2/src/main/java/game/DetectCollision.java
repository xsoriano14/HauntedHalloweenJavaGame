package game;

public class DetectCollision {

	GameScreen screen;
	
	/**constructor
	 * @param screen
	 */
	public DetectCollision(GameScreen screen) {
		
		this.screen = screen;
		
	}
	
	/**
	 * get animate class object hibox absolute positions
	 * @param animate
	 */
	public void tileCheck (Animate animate) {
		
		int L_animateX = animate.absX + animate.hitbox.x;
		int R_animateX = animate.absX + animate.hitbox.x + animate.hitbox.width;
		
		int T_animateY = animate.absY + animate.hitbox.y;
		int B_animateY = animate.absY + animate.hitbox.y + animate.hitbox.height;
		
		int L_animateCol = L_animateX/screen.scaledTileSize;
		int R_animateCol = R_animateX/screen.scaledTileSize;
		
		int T_animateRow = T_animateY/screen.scaledTileSize;
		int B_animateRow = B_animateY/screen.scaledTileSize;
		
		int collisionTile1, collisionTile2;
		
		switch (animate.direction) {
		
		case "up":
			T_animateRow = (T_animateY - animate.speed) / screen.scaledTileSize;
			collisionTile1 = screen.tCon.tileMapNum[L_animateCol][T_animateRow];
			collisionTile2 = screen.tCon.tileMapNum[R_animateCol][T_animateRow];
			if (screen.tCon.tile[collisionTile1].collision == true || screen.tCon.tile[collisionTile2].collision == true) {
				animate.collisionDetection = true;
			}
			break;
			
		case "down":
			B_animateRow = (B_animateY - animate.speed) / screen.scaledTileSize;
			collisionTile1 = screen.tCon.tileMapNum[L_animateCol][B_animateRow];
			collisionTile2 = screen.tCon.tileMapNum[R_animateCol][B_animateRow];
			if (screen.tCon.tile[collisionTile1].collision == true || screen.tCon.tile[collisionTile2].collision == true) {
				animate.collisionDetection = true;
			}
			break;
			
		case "left":
			L_animateCol = (L_animateX - animate.speed) / screen.scaledTileSize;
			collisionTile1 = screen.tCon.tileMapNum[L_animateCol][T_animateRow];
			collisionTile2 = screen.tCon.tileMapNum[L_animateCol][B_animateRow];
			if (screen.tCon.tile[collisionTile1].collision == true || screen.tCon.tile[collisionTile2].collision == true) {
				animate.collisionDetection = true;
			}
			break;	
			
		case "right":
			R_animateCol = (R_animateX + animate.speed) / screen.scaledTileSize;
			collisionTile1 = screen.tCon.tileMapNum[R_animateCol][T_animateRow];
			collisionTile2 = screen.tCon.tileMapNum[R_animateCol][B_animateRow];
			if (screen.tCon.tile[collisionTile1].collision == true || screen.tCon.tile[collisionTile2].collision == true) {
				animate.collisionDetection = true;
			}
			break;		
		}
	}
	
	/**collision check between animate classes
	 * @param animate
	 * @param enemy
	 * @return
	 */
	public int enemyCheck(Animate animate, Animate[] enemy) {
		
		int index = 999;

		for(int i=0; i<enemy.length; i++) {
			if(enemy[i] != null)
			{
				//get characters hit box
				animate.hitbox.x = animate.absX + animate.hitbox.x;
				animate.hitbox.y = animate.absY + animate.hitbox.y;
				//get enemy hit box
				enemy[i].hitbox.x = enemy[i].absX + enemy[i].hitbox.x;
				enemy[i].hitbox.y = enemy[i].absY + enemy[i].hitbox.y;
			

				switch(animate.direction){
					case "up":
						animate.hitbox.y -= animate.speed;
						break;
					case "down":
						animate.hitbox.y += animate.speed;
						break;

					case "left":
						animate.hitbox.x -= animate.speed;
						break;

					case "right":
						animate.hitbox.x += animate.speed;
						break;
				}
				
				if(animate.hitbox.intersects(enemy[i].hitbox)) {
					if (enemy[i] != animate) {
						animate.collisionDetection = true;
						index = i;
					}
				}
				
				animate.hitbox.x = animate.hitboxDefaultX;
				animate.hitbox.y = animate.hitboxDefaultY;
				enemy[i].hitbox.x = enemy[i].hitboxDefaultX;
				enemy[i].hitbox.y = enemy[i].hitboxDefaultY;
				
			}
		}

		return index;
	}


	/** check player collision with other animate
	 * @param animate
	 * @return
	 */
	public boolean playerCheck(Animate animate) {
		
		boolean contact = false;
		
		animate.hitbox.x = animate.absX + animate.hitbox.x;
		animate.hitbox.y = animate.absY + animate.hitbox.y;
		//get objects hitbox
		screen.Main_character.hitbox.x = screen.Main_character.absX + screen.Main_character.hitbox.x;
		screen.Main_character.hitbox.y = screen.Main_character.absY + screen.Main_character.hitbox.y;
	

		switch(animate.direction){
			case "up":
				animate.hitbox.y -= animate.speed;
				break;
			case "down":
				animate.hitbox.y += animate.speed;
				break;

			case "left":
				animate.hitbox.x -= animate.speed;
				break;

			case "right":
				animate.hitbox.x += animate.speed;
				break;
		}
		if(animate.hitbox.intersects(screen.Main_character.hitbox))
		{
			animate.collisionDetection = true;
			contact = true;
		}

		animate.hitbox.x = animate.hitboxDefaultX;
		animate.hitbox.y = animate.hitboxDefaultY;
		screen.Main_character.hitbox.x = screen.Main_character.hitboxDefaultX;
		screen.Main_character.hitbox.y = screen.Main_character.hitboxDefaultY;
		
		return contact;
	}

	/**collision check with player and objects
	 * @param animate_obj
	 * @param isCharacter
	 * @return
	 */
	public int ObjectCheck(Animate animate_obj, boolean isCharacter){
		int index = 999;

		for(int i=0; i<screen.myObjects.length; i++){
			if(screen.myObjects[i] != null)
			{
				//get characters hit box
				animate_obj.hitbox.x = animate_obj.absX + animate_obj.hitbox.x;
				animate_obj.hitbox.y = animate_obj.absY + animate_obj.hitbox.y;
				//get objects hitbox
				screen.myObjects[i].hitbox.x = screen.myObjects[i].xVal + screen.myObjects[i].hitbox.x;
				screen.myObjects[i].hitbox.y = screen.myObjects[i].yVal + screen.myObjects[i].hitbox.y;
			

				switch(animate_obj.direction){
					case "up":
						animate_obj.hitbox.y -= animate_obj.speed;
						break;
						
					case "down":
						animate_obj.hitbox.y += animate_obj.speed;
						break;
						
					case "left":
						animate_obj.hitbox.x -= animate_obj.speed;
						break;
						
					case "right":
						animate_obj.hitbox.x += animate_obj.speed;
						break;
						
				}
				
				if(animate_obj.hitbox.intersects(screen.myObjects[i].hitbox)){
					if(screen.myObjects[i].collision == true) {
						animate_obj.collisionDetection = true;
					}

					if(isCharacter == true) {
						index = i;
					}
					
				}
				

				animate_obj.hitbox.x = animate_obj.hitboxDefaultX;
				animate_obj.hitbox.y = animate_obj.hitboxDefaultY;
				screen.myObjects[i].hitbox.x = screen.myObjects[i].hitboxDefault_X;
				screen.myObjects[i].hitbox.y = screen.myObjects[i].hitboxDefault_Y;

				
				
			}
		}

		return index;

	}
	
}
