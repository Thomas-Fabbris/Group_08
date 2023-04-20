package sharedgamearea;

public class Pouch {
	private static int tyle_counter = 22;
	private static int books_counter = tyle_counter;
	private static int cats_counter = tyle_counter;
	private static int frames_counter = tyle_counter;
	private static int games_counter = tyle_counter;
	private static int plants_counter = tyle_counter;
	private static int throphies_counter = tyle_counter;
	
	public static boolean extract(int tyle_id) {
		switch(tyle_id) {
		case 0:
			if(books_counter > 1) {
				books_counter--;
				return true;
			}else {
				return false;
			}
		case 1:
			if(cats_counter > 1) {
				cats_counter--;
				return true;
			}else {
				return false;
			}
		case 2:
			if(frames_counter > 1) {
				frames_counter--;
				return true;
			}else {
				return false;
			}
		case 3:
			if(games_counter > 1) {
				games_counter--;
				return true;
			}else {
				return false;
			}
		case 4:
			if(plants_counter > 1) {
				plants_counter--;
				return true;
			}else {
				return false;
			}
		case 5:
			if(throphies_counter > 1) {
				throphies_counter--;
				return true;
			}else {
				return false;
			}
		default:
				return false;
			}
	}
		
			
		public static void add(int tyle_id) {
			switch(tyle_id) {
			case 0:
				books_counter++;
			case 1:
				cats_counter++;
			case 2:
				frames_counter++;
			case 3:
				games_counter++;
			case 4:
				plants_counter++;
			case 5:
				throphies_counter++;
			}
		
	
		
	}

}

