package model.personalgamearea;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.NavigableMap;
import java.util.Scanner;
import java.util.TreeMap;

import model.shared.IdGenerator;
import model.shared.TileType;
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
	
	/*
	 * Important: pointsMap is initialized in the constructor even though it's a static field
	 * as it has no reason to exist if no personal object card has been created before
	 */
		
	/**
	 * This is the constructor of the PersonalObjectiveCard class
	 *   
	 * @param idGenerator
	 */
	public PersonalObjectiveCard(IdGenerator idGenerator) {
		this.cardId = idGenerator.getNewPersonalObjectiveCardId();

		this.tileGoals = readTypePositionsFromFile(selectFile(this.cardId));
		if(PersonalObjectiveCard.pointsMap == null) {
			PersonalObjectiveCard.pointsMap = readPersonalGoalPointsFromFile(new File("Assets/Carte_Obiettivo_Personale/Punteggi_Carte_Obiettivo_Personali.csv"));
		}
	}
	
	/**
	 * This method allows to get the file path to the personal objective card by using its id as parameter
	 * 
	 * @param card_id
	 * @return new File(path)
	 */
	private File selectFile(int card_id) {
		String path = "Assets/Carte_Obiettivo_Personale/Carta_X.txt".replaceAll("X",
				Integer.valueOf(card_id).toString());
		return new File(path);
	}
	
	/** 
	 * This method reads the csv file containing the number of points assigned if the player 
	 * has tiles in its bookshelf in the exact position illustrated by its own goal card
	 * 
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
	
	/**
	 * This method reads the file with the positions of each TileType on the card
	 * 
	 * @param file
	 * @return coords
	 */
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
	
	/**
	 * This method parses a line in the format 'x, y, TileType' and creates a new
	 * TileTypeCoordinate with the parsed data
	 * 
	 * @param line 
	 * @return new BookshelfTileGoal(x, y, tile_type)  
	 */
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

	/**
	 * This method returns the number of tiles in the bookshelf that match this personal
	 * objective card
	 * 
	 * @param bookshelf
	 * @return goalsCount
	 */
	public int countSatisfiedGoals(Bookshelf bookshelf) {
		int goalsCount = 0;

		for (BookshelfTileGoal tileGoal : tileGoals) {

			if (bookshelf.tiles[tileGoal.row][tileGoal.column].getType() == tileGoal.tileType) {

				goalsCount++;
			}
		}

		return goalsCount;
	}
	
	/**
	 * This is a private class created in the PersonalObjectiveCard class.
	 * It represents a single objective of a personal objective card.
	 *
	 */
	private class BookshelfTileGoal {

		public final int row;
		public final int column;
		public final TileType tileType;
		
		/**
		 * This is the constructor of the private class BookshelfTileGoal
		 *  
		 * @param x
		 * @param y
		 * @param tile_type
		 */
		BookshelfTileGoal(int x, int y, TileType tile_type) {
			this.row = y;
			this.column = x;
			this.tileType = tile_type;
		}
	}
}
