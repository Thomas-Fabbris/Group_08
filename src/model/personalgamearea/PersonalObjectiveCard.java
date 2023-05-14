package model.personalgamearea;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.NavigableMap;
import java.util.Scanner;

import model.shared.TileType;
import java.util.TreeMap;
public class PersonalObjectiveCard {

	public final static int MAX_CARD_ID = 12;
	public static NavigableMap<Integer,Integer> pointsMap;
	public final int cardId;

	/**
	 * This is a list of TileTypes, each TileType has a pair of coordinates
	 * associated with it.
	 * 
	 * When checking the bookshelf, we can iterate through each element of the list
	 * and compare it to the tiles on the bookshelf.
	 */
	private List<BookshelfTileGoal> tileGoals = new LinkedList<>(); // Stores the TileType and its position for
	
	//Important: pointsMap is initiliazed in the constructor even though it's a static field, because it has no reason for existing if no personal object card has been created before
	public PersonalObjectiveCard(int card_id) {
		this.cardId = card_id;
		this.tileGoals = readTypePositionsFromFile(selectFile(card_id));
		if(PersonalObjectiveCard.pointsMap == null) {
			PersonalObjectiveCard.pointsMap = readPersonalGoalPointsFromFile(new File("Assets/Carte_Obiettivo_Personale/Punteggi_Carte_Obiettivo_Personali.csv"));
		}
	}

	private File selectFile(int card_id) {
		String path = "Assets/Carte_Obiettivo_Personale/Carta_X.txt".replaceAll("X",
				Integer.valueOf(card_id).toString());
		return new File(path);
	}
	
	/* This method reads the csv file containing the number of points assigned if the player has tiles in its bookshelf in the exact position illustrated by its personal goal card
	 * @param file
	 * @return
	 */
	private NavigableMap<Integer, Integer> readPersonalGoalPointsFromFile(File file){
		
		Scanner scanner = null;
		
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		NavigableMap<Integer,Integer> pointsMap = new TreeMap<Integer,Integer>();
		String line;
		
		while(scanner.hasNextLine()) {
			line = scanner.nextLine();
			String lineData[] = line.split(";");
			pointsMap.put(Integer.parseInt(lineData[0]), Integer.parseInt(lineData[1]));
		}
		return pointsMap;
	}

	// Read the file with the positions of each TileType on the card
	private List<BookshelfTileGoal> readTypePositionsFromFile(File file) {

		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		List<BookshelfTileGoal> coords = new LinkedList<>();
		String line;

		while (scanner.hasNextLine()) {
			line = scanner.nextLine();
			coords.add(parseStringToCoordinates(line));
		}
		;

		return coords;
	}

	// Parse a line in the format 'x, y, TileType' and create a new
	// TileTypeCoordinate with the parsed data
	private BookshelfTileGoal parseStringToCoordinates(String line) {

		String[] line_elements = line.split(" "); // x, y, TileType

		int x = Integer.valueOf(line_elements[0]);
		int y = Integer.valueOf(line_elements[1]);
		TileType tile_type;

		try {
			tile_type = Enum.valueOf(TileType.class, line_elements[2]);
		} catch (Exception e) {
			throw new IllegalArgumentException(line_elements[2] + " is not a valid TileType!");
		}

		if (x < 0 || x > 4)
			throw new IllegalArgumentException(x + " x coordinate out of bounds!");
		if (y < 0 || y > 5)
			throw new IllegalArgumentException(y + " y coordinate out of bounds!");

		return new BookshelfTileGoal(x, y, tile_type);
	}

	// Returns the number of tiles in the bookshelf that match this personal
	// objective card
	public int countSatisfiedGoals(Bookshelf bookshelf) {
		int goalsCount = 0;

		for (BookshelfTileGoal tileGoal : tileGoals) {
			System.out.println("Row: "+tileGoal.row+ " col: "+tileGoal.column+" type:"+tileGoal.tileType);
			if (bookshelf.tiles[tileGoal.row][tileGoal.column].getType() == tileGoal.tileType) {
				System.out.println("Matched: " + tileGoal.tileType);
				goalsCount++;
			}
		}

		return goalsCount;
	}

	private class BookshelfTileGoal {

		public final int row;
		public final int column;
		public final TileType tileType;

		BookshelfTileGoal(int x, int y, TileType tile_type) {
			this.row = y;
			this.column = x;
			this.tileType = tile_type;
		}
	}
}
