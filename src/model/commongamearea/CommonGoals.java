package model.commongamearea;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.NavigableMap;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import model.personalgamearea.Bookshelf;
import model.shared.TileType;
/**
 * 
 * The class {@code checkCommonGoal} check all the 12 common goals
 *
 */
public enum CommonGoals {

	CommonGoal1(1) {
		/**
		 * Due colonne formate ciascuna da 6 diversi tipi di tessere.
		 *
		 * @param pshelf
		 * @return boolean (vero la shelf soddisfa la condizione,
		 * falso se non la soddisfa)
		 */
		public boolean checkCommonGoal(Bookshelf pshelf) {

			int count = 0;

			for (int col = 0; col <= 4; col++) {

				TileType[] current_col = pshelf.getColumn(col);
				int nulls = Math.min(1, Count_NULL(current_col));
				Set<TileType> set = new HashSet<>(Arrays.asList(current_col));
				if (set.size() - nulls == 6) {
					count++;
					if (count == 2) return true;
				}
			}

			return false;

		}
	},

	CommonGoal2(2) {
		/**
		 * Quattro gruppi separati formati ciascuno
		 * da quattro tessere adiacenti dello stesso tipo
		 * (non necessariamente come mostrato in figura).
		 * Le tessere di un gruppo possono essere
		 * diverse da quelle di un altro gruppo.
		 *
		 * @param pshelf
		 * @return boolean (vero la shelf soddisfa la condizione,
		 * falso se non la soddisfa)
		 */
		@Override
		public boolean checkCommonGoal(Bookshelf pshelf) {

			int result = 0;
			int index = 0;
			int[] already_checked = new int[30];
			Arrays.fill(already_checked, -99);

			for (int row = 0; row <= 5; row++) {

				for (int col = 0; col <= 4; col++) {

					if (Coords_Check(already_checked, row, col))
						continue;

					if (pshelf.getTile(row, col).getType() == TileType.NULL)
						continue;

					boolean c1 = CheckRow(pshelf, row, col, 4);
					boolean c2 = CheckCol(pshelf, row, col, 4);

					if (c1 || c2) {

						if (c1)
						{
							already_checked[index+0] = toCoords(row, col + 0);
							already_checked[index+1] = toCoords(row, col + 1);
							already_checked[index+2] = toCoords(row, col + 2);
							already_checked[index+3] = toCoords(row, col + 3);
							index += 4;
						}

						else {
							already_checked[index+0] = toCoords(row + 0, col);
							already_checked[index+1] = toCoords(row + 1, col);
							already_checked[index+2] = toCoords(row + 2, col);
							already_checked[index+3] = toCoords(row + 3, col);
							index += 4;
						}

						result++;
						if (result == 4) return true;

					}

				}

			}

			return false;
		}

	},

	CommonGoal3(3) {
		/**
		 * Tre colonne formate ciascuna da 6 tessere di uno,
		 * due o tre tipi differenti. Colonne diverse
		 * possono avere combinazioni diverse di tipi di tessere
		 *
		 * @param pshelf
		 * @return boolean (vero la shelf soddisfa la condizione,
		 * falso se non la soddisfa)
		 */
		@Override
		public boolean checkCommonGoal(Bookshelf pshelf) {

			int result = 0;

			for (int col = 0; col <= 4; col++) {

				TileType[] current_col = pshelf.getColumn(col);
				int nulls = Math.min(1, Count_NULL(current_col));
				if (nulls > 0) continue;
				Set<TileType> set = new HashSet<>(Arrays.asList(current_col));
				if (set.size() - nulls <= 3) {

					result++;
					if (result == 3) {
						return true;
					}

				}
			}

			return false;

		}

	},

	CommonGoal4(4) {
		/**
		 * Cinque colonne di altezza crescente o decrescente:
		 * a partire dalla prima colonna a sinistra o a destra,
		 * ogni colonna successiva deve essere formata da una
		 * tessera in più. Le tessere possono essere di qualsiasi tipo.
		 *
		 * @param pshelf
		 * @return boolean (vero la shelf soddisfa la condizione,
		 * falso se non la soddisfa)
		 */
		@Override
		public boolean checkCommonGoal(Bookshelf pshelf) {

			for (int inverse = 0; inverse <= 1; inverse++) {

				int[] heights = new int[5];
				boolean wrong = false;

				for (int col = 0; col <= 4; col++) {

					heights[col] = Count_Tiles(pshelf.getColumn(Math.abs(4 * inverse - col)));

				}

				for (int i = 0; i <= 3; i++) {

					if ((heights[i] - 1) != (heights[i + 1])) {
						wrong = true;
						break;
					}

				}

				if (!wrong) return true;

			}
			
			return false;
		}
	},

	CommonGoal5(5) {
		/**
		 * Sei gruppi separati formati ciascuno da due tessere
		 * adiacenti dello stesso tipo. Le tessere di un gruppo
		 * possono essere diverse da quelle di un altro gruppo.
		 *
		 * @param pshelf
		 * @return (vero la shelf soddisfa la condizione,
		 * falso se non la soddisfa)
		 */
		@Override
		public boolean checkCommonGoal(Bookshelf pshelf) {

			int result = 0;
			int index = 0;
			int[] already_checked = new int[30];
			Arrays.fill(already_checked, -99);

			for (int row = 0; row <= 5; row++) {

				for (int col = 0; col <= 4; col++) {

					if (Coords_Check(already_checked, row, col))
						continue;

					if (pshelf.getTile(row, col).getType() == TileType.NULL)
						continue;

					boolean c1 = CheckRow(pshelf, row, col, 2);
					boolean c2 = CheckCol(pshelf, row, col, 2);

					if (c1 || c2) {

						if (c1)
						{
							already_checked[index+0] = toCoords(row, col + 0);
							already_checked[index+1] = toCoords(row, col + 1);
							index += 2;
						}

						else
						{
							already_checked[index+0] = toCoords(row + 0, col);
							already_checked[index+1] = toCoords(row + 1, col);
							index += 2;
						}

						result++;
						if (result == 6) return true;

					}

				}

			}

			return false;

		}

	},

	CommonGoal6(6) {
		/**
		 * Due gruppi separati di 4 tessere dello stesso
		 * tipo che formano un quadrato 2x2. Le
		 * tessere dei due gruppi devono essere dello stesso tipo.
		 *
		 * @param pshelf
		 * @return (vero la shelf soddisfa la condizione,
		 * falso se non la soddisfa)
		 */
		public boolean checkCommonGoal(Bookshelf pshelf) {

			int result = 0;

			for (TileType type : TileType.values) {

				int index = 0;
				int[] already_checked = new int[30];
				Arrays.fill(already_checked, -99);

				for (int row = 0; row <= 4; row++) {

					for (int col = 0; col <= 3; col++) {

						// non andiamo a prendere il quadrato che abbiamo già trovato
						if (Coords_Check(already_checked, row, col))
							continue;

						TileType checktile = pshelf.getTile(0 + row, 0 + col).getType();

						// se la prima tile del quadrato è diversa da quella che stiamo effettivamente
						// cercando andiamo oltre

						if (checktile != type)
							continue;

						boolean t1 = checktile == pshelf.getTile(1 + row, 0 + col).getType();
						boolean t2 = checktile == pshelf.getTile(0 + row, 1 + col).getType();
						boolean t3 = checktile == pshelf.getTile(1 + row, 1 + col).getType();

						if (t1 && t2 && t3) {

							already_checked[index+0] = toCoords(row + 0, col + 0);
							already_checked[index+1] = toCoords(row + 0, col + 1);
							already_checked[index+2] = toCoords(row + 1, col + 0);
							already_checked[index+3] = toCoords(row + 1, col + 1);
							index += 4;
							result++;

							if (result == 2)
							{
								return true;
							}

						}

					}

				}

			}
			return false;
		}

	},

	CommonGoal7(7) {
		/**
		 * Otto tessere dello stesso tipo. Non
		 * ci sono restrizioni sulla posizione
		 * di queste tessere.
		 *
		 * @param pshelf
		 * @return (vero la shelf soddisfa la condizione,
		 * falso se non la soddisfa)
		 */
		@Override
		public boolean checkCommonGoal(Bookshelf pshelf) {

			for (TileType type : TileType.values) {

				int count = 0;

				for (int row = 0; row <= 5; row++) {
					for (int col = 0; col <= 4; col++) {
						if (pshelf.getTile(row, col).getType() == type) {
							count++;
							if (count == 8) {
								return true;
							}
						}
					}
				}

			}

			return false;

		}

	},

	CommonGoal8(8) {
		/**
		 * Cinque tessere dello stesso tipo che formano una X
		 *
		 * @param pshelf
		 * @return (vero la shelf soddisfa la condizione,
		 * falso se non la soddisfa)
		 */
		@Override
		public boolean checkCommonGoal(Bookshelf pshelf) {

			for (int rshift = 0; rshift <= 2; rshift++) {

				for (int dshift = 0; dshift <= 3; dshift++) {

					TileType checktile = pshelf.getTile(0 + dshift, 0 + rshift).getType();

					if (checktile == TileType.NULL)
						continue;

					boolean t1 = checktile == pshelf.getTile(1 + dshift, 1 + rshift).getType();
					boolean t2 = checktile == pshelf.getTile(2 + dshift, 0 + rshift).getType();
					boolean t3 = checktile == pshelf.getTile(0 + dshift, 2 + rshift).getType();
					boolean t4 = checktile == pshelf.getTile(2 + dshift, 2 + rshift).getType();

					if (t1 && t2 && t3 && t4)
						return true;

				}

			}

			return false;

		}

	},

	CommonGoal9(9) {
		/**
		 * Cinque tessere dello stesso tipo che formano
		 * una diagonale
		 *
		 * @param pshelf
		 * @return (vero la shelf soddisfa la condizione,
		 * falso se non la soddisfa)
		 */
		@Override
		public boolean checkCommonGoal(Bookshelf pshelf) {

			for (int dshift = 0; dshift <= 1; dshift++) {

				for (int inverse = 0; inverse <= 1; inverse++) {

					TileType checktile = pshelf.getTile(0 + dshift, 0 + (4 * inverse)).getType();

					if (checktile == TileType.NULL)
						continue;

					boolean t1 = checktile == pshelf.getTile(1 + dshift, 1 + (2 * inverse)).getType();
					boolean t2 = checktile == pshelf.getTile(2 + dshift, 2                ).getType();
					boolean t3 = checktile == pshelf.getTile(3 + dshift, 3 - (2 * inverse)).getType();
					boolean t4 = checktile == pshelf.getTile(4 + dshift, 4 - (4 * inverse)).getType();

					if (t1 && t2 && t3 && t4)
						return true;

				}
			}

			return false;
		}

	},

	CommonGoal10(10) {
		/**
		 * Due righe formate ciascuna da 5 diversi tipi di tessere
		 *
		 * @param pshelf
		 * @return (vero la shelf soddisfa la condizione,
		 * falso se non la soddisfa)
		 */
		@Override
		public boolean checkCommonGoal(Bookshelf pshelf) {

			int result = 0;

			for (int row = 0; row <= 5; row++) {

				TileType[] current_row = pshelf.getRow(row);
				int nulls = Math.min(1, Count_NULL(current_row));
				if (nulls > 0) continue;
				Set<TileType> set = new HashSet<>(Arrays.asList(current_row));
				if (set.size() - nulls == 5)
				{
					result++;
					if (result == 2) return true;
				}

			}

			return false;
		}

	},

	CommonGoal11(11) {
		/**
		 * Quattro righe formate ciascuna da 5 tessere di
		 * uno, due o tre tipi differenti. Righe
		 * diverse possono avere combinazioni
		 * diverse di tipi di tessere.
		 *
		 * @param pshelf
		 * @return (vero la shelf soddisfa la condizione,
		 * falso se non la soddisfa)
		 */
		@Override
		public boolean checkCommonGoal(Bookshelf pshelf) {

			int result = 0;

			for (int row = 0; row <= 5; row++) {

				TileType[] current_row = pshelf.getRow(row);
				int nulls = Math.min(1, Count_NULL(current_row));
				if (nulls > 0) continue;
				Set<TileType> set = new HashSet<>(Arrays.asList(current_row));
				if (set.size() - nulls <= 3)
				{
					result++;
					if (result == 4)
					{
						return true;
					}
				}

			}

			return false;

		}
	},

	CommonGoal12(12) {
		/**
		 * Quattro tessere dello stesso tipo ai quattro angoli della Libreria.
		 *
		 * @param pshelf
		 * @return (vero la shelf soddisfa la condizione,
		 * falso se non la soddisfa)
		 */
		@Override
		public boolean checkCommonGoal(Bookshelf pshelf) {

			TileType checktile = pshelf.getTile(0, 0).getType();

			if (checktile == TileType.NULL)
				return false;

			boolean t1 = checktile == pshelf.getTile(0, 4).getType();
			boolean t2 = checktile == pshelf.getTile(5, 0).getType();
			boolean t3 = checktile == pshelf.getTile(5, 4).getType();

			if (t1 && t2 && t3) {
				return true;
			}

			return false;

		}

	};

	private int id;
	
	public abstract boolean checkCommonGoal(Bookshelf pshelf);

	private CommonGoals(int id) {
		this.id = id;
		if(StaticFields.pointsMap == null) {
			StaticFields.pointsMap = readGroupPointsFromFile(new File("Assets/Carte_Obiettivo_Comune/Punteggi_Gruppi_Tiles.csv"));
		}
	}

	/** The method {@code readGroupPointsFromFile} reads the csv file containing the number of points assigned if the player has groups of 3/4/5/6+ tiles of the same type in his bookshelf
	 * @param file
	 * @return
	 */
	private NavigableMap<Integer, Integer> readGroupPointsFromFile(File file){
		
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
 * The method {@code tileUpCount_NULL} counts all the tile with type NULL thath you can find in a row/column
 * @param row_col
 * @return the count of null tiles
 */
	private static int Count_NULL(TileType[] row_col) {

		// Metodo per contare quante tile di tipo NULL ci sono
		// in una riga / colonna

		int count = 0;

		for (TileType tile : row_col) {
			if (tile == TileType.NULL)
				count++;
		}

		return count;

	}

	public int toCoords(int row, int col)
	{
		return row * 10 + col;
	}
/**
 * The method {@code Coords_Check} controls if a tile is already in a group checked before
 * @param coords
 * @param row
 * @param col
 * @return
 */
	public boolean Coords_Check(int[] coords, int row, int col) {

		// Metodo unico (mode cambia il modo in cui funziona) per
		// controllare se una tile è già presente in un gruppo
		// controllato precedentemente

		for (int coord : coords) {

			if (coord == toCoords(row, col)) return true;

		}

		return false;

	}
/**
 * The method {@code Count_Tiles} counts the tiles in a row/column
 * @param row_col
 * @return
 */
	private static int Count_Tiles(TileType[] row_col) {

		// Metodo per contare le tile di una riga/colonna

		int count = 0;

		for (TileType tile : row_col) {
			for (TileType type : TileType.values) {
				if (tile == type) {
					count++;
				}
			}
		}

		return count;

	}
/**
 * The method {@code CheckRow} checks if there are 2 or 4 tiles of the same type adjacent in the same row
 * @param pshelf
 * @param row
 * @param col
 * @param length
 * @return
 */
	private static boolean CheckRow(Bookshelf pshelf, int row, int col, int length) {

		// Metodo unico (length cambia la lunghezza che si vuole cercare) per
		// controllare se ci siano 2 o 4 tile dello stesso tipo adiacenti
		// sulla stessa riga

		// in parole povere: [=][=]([=])([=])

		if (length == 4 && col > 1)
			return false;
		if (length == 2 && col > 3)
			return false;

		switch (length) {

			case 4 : {

				TileType checktile = pshelf.getTile(row, col).getType();

				boolean t1 = checktile == pshelf.getTile(row, col + 1).getType();
				boolean t2 = checktile == pshelf.getTile(row, col + 2).getType();
				boolean t3 = checktile == pshelf.getTile(row, col + 3).getType();

				return t1 && t2 && t3;

			}

			case 2 : {

				TileType checktile = pshelf.getTile(row, col).getType();

				return checktile == pshelf.getTile(row, col + 1).getType();

			}
		}

		return false;

	}
	/**
	 * The method {@code CheckCol} checks if there are 2 or 4 tiles of the same type adjacent in the same column
	 * @param pshelf
	 * @param row
	 * @param col
	 * @param length
	 * @return
	 */
	private static boolean CheckCol(Bookshelf pshelf, int row, int col, int length) {

		// Metodo unico (length cambia la lunghezza che si vuole cercare) per
		// controllare se ci siano 2 o 4 tile dello stesso tipo adiacenti
		// sulla stessa colonna

		// in parole povere: [=]
		// [=]
		// ([=])
		// ([=])

		// (?) Volendo si potrebbe fare un metodo unico di nome CheckRowCol

		if (length == 4 && row > 2)
			return false;
		if (length == 2 && row > 4)
			return false;

		if (length == 4) {

			TileType checktile = pshelf.getTile(row, col).getType();

			boolean t1 = checktile == pshelf.getTile(row + 1, col).getType();
			boolean t2 = checktile == pshelf.getTile(row + 2, col).getType();
			boolean t3 = checktile == pshelf.getTile(row + 3, col).getType();

			return t1 && t2 && t3;

		}

		if (length == 2) {

			TileType checktile = pshelf.getTile(row, col).getType();

			return checktile == pshelf.getTile(row + 1, col).getType();

		}

		return false;

	}

	public static final class StaticFields {
		 private static NavigableMap<Integer,Integer> pointsMap = new TreeMap<>();

		/**
		 * @return the pointsMap
		 */
		public static NavigableMap<Integer, Integer> getPointsMap() {
			return pointsMap;
		}
	 }
}