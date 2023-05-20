package model.commongamearea;

import model.shared.TileType;

public class Pouch {
	private static int TYLE_COUNTER = 22;
	private static int BOOKS_COUNTER = TYLE_COUNTER;
	private static int CATS_COUNTER = TYLE_COUNTER;
	private static int FRAMES_COUNTER =TYLE_COUNTER;
	private static int GAMES_COUNTER = TYLE_COUNTER;
	private static int PLANTS_COUNTER  = TYLE_COUNTER;
	private static int TROPHIES_COUNTER  = TYLE_COUNTER;
	
	public static boolean extract(TileType tyle_id) {
		switch(tyle_id) {
		
		case BOOKS:
			if(BOOKS_COUNTER > 0) {
			BOOKS_COUNTER--;
				return true;
			}
			return false;
			
		case CATS:
			if(CATS_COUNTER > 0) {
				CATS_COUNTER--;
				return true;
			}
			return false;
			
		case FRAMES:
			if(FRAMES_COUNTER > 0) {
				FRAMES_COUNTER--;
				return true;
			}
			return false;
			
		case GAMES:
			if(GAMES_COUNTER > 0) {
				GAMES_COUNTER--;
				return true;
			}
			return false;
			
		case PLANTS:
			if(PLANTS_COUNTER > 0) {
				PLANTS_COUNTER--;
				return true;
			}
			return false;
			
		case TROPHIES:
			if(TROPHIES_COUNTER > 0) {
				TROPHIES_COUNTER--;
				return true;
			}
			return false;
			
		default:
			return false;
		}
	}
		
			
	public static void add(TileType tyle_id) {
		switch(tyle_id) {
			case BOOKS:
				BOOKS_COUNTER++;
			case CATS:
				CATS_COUNTER++;
			case FRAMES:
				FRAMES_COUNTER++;
			case GAMES:
				GAMES_COUNTER++;
			case PLANTS:
				PLANTS_COUNTER++;
			case TROPHIES:
				TROPHIES_COUNTER++;
			default:
				break;
		}
	}
}

