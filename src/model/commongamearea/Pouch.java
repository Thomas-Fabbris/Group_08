package model.commongamearea;

import model.shared.TileType;

public class Pouch {
	private static int tyle_counter = 22;
	private static int books_counter = tyle_counter;
	private static int cats_counter = tyle_counter;
	private static int frames_counter = tyle_counter;
	private static int games_counter = tyle_counter;
	private static int plants_counter = tyle_counter;
	private static int throphies_counter = tyle_counter;
	
	public static boolean extract(TileType tyle_id) {
		switch(tyle_id) {
		case BOOKS:
			if(books_counter > 0) {
				books_counter--;
				return true;
			}else {
				return false;
			}
		case CATS:
			if(cats_counter > 0) {
				cats_counter--;
				return true;
			}else {
				return false;
			}
		case FRAMES:
			if(frames_counter > 0) {
				frames_counter--;
				return true;
			}else {
				return false;
			}
		case GAMES:
			if(games_counter > 0) {
				games_counter--;
				return true;
			}else {
				return false;
			}
		case PLANTS:
			if(plants_counter > 0) {
				plants_counter--;
				return true;
			}else {
				return false;
			}
		case TROPHIES:
			if(throphies_counter > 0) {
				throphies_counter--;
				return true;
			}else {
				return false;
			}
		default:
				return false;
			}
	}
		
			
		public static void add(TileType tyle_id) {
			switch(tyle_id) {
			case BOOKS:
				books_counter++;
			case CATS:
				cats_counter++;
			case FRAMES:
				frames_counter++;
			case GAMES:
				games_counter++;
			case PLANTS:
				plants_counter++;
			case TROPHIES:
				throphies_counter++;
			default:
				break;
			}
	}
}

