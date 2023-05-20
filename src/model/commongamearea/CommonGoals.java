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
import model.personalgamearea.PathFind;
import model.shared.TileType;

public enum CommonGoals {

	CommonGoal1(1) {
		public boolean checkCommonGoal(Bookshelf pshelf) {

			// Due colonne formate ciascuna da 6 diversi tipi di tessere.

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

		@Override
		public boolean checkCommonGoal(Bookshelf pshelf) {

			// Quattro gruppi separati formati ciascuno
			// da quattro tessere adiacenti dello stesso tipo
			// (non necessariamente come mostrato in figura).
			// Le tessere di un gruppo possono essere
			// diverse da quelle di un altro gruppo.

			int result = 0;
			int index = 0;
			int[] already_checked = new int[30];
			Arrays.fill(already_checked, -99);

			for (int row = 0; row <= 5; row++) {

				for (int col = 0; col <= 4; col++) {

					if (Coords_Check(already_checked, row, col, 3))
						continue;

					if (pshelf.getTile(row, col).getType() == TileType.NULL)
						continue;

					boolean c1 = CheckRow(pshelf, row, col, 4);
					boolean c2 = CheckCol(pshelf, row, col, 4);

					if (c1 || c2) {
						already_checked[index] = row * 10 + col;
						index++;
						result++;

						if (result == 4)
						{
							return true;
						}

					}

				}

			}

			return false;
		}

	},

	CommonGoal3(3) {

		@Override
		public boolean checkCommonGoal(Bookshelf pshelf) {

			// Tre colonne formate ciascuna da 6 tessere di uno,
			// due o tre tipi differenti. Colonne diverse
			// possono avere combinazioni diverse di tipi di tessere,

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

		@Override
		public boolean checkCommonGoal(Bookshelf pshelf) {

			// Cinque colonne di altezza crescente o decrescente:
			// a partire dalla prima colonna a sinistra o a destra,
			// ogni colonna successiva deve essere formata da una
			// tessera in più. Le tessere possono essere di qualsiasi tipo.

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

		@Override
		public boolean checkCommonGoal(Bookshelf pshelf) {

			// Sei gruppi separati formati ciascuno da due tessere
			// adiacenti dello stesso tipo (non necessariamente
			// come mostrato in figura). Le tessere di un gruppo
			// possono essere diverse da quelle di un altro gruppo.

			int result = 0;
			int index = 0;
			int[] already_checked = new int[30];
			Arrays.fill(already_checked, -99);

			for (int row = 0; row <= 5; row++) {

				for (int col = 0; col <= 4; col++) {

					if (Coords_Check(already_checked, row, col, 2))
						continue;

					if (pshelf.getTile(row, col).getType() == TileType.NULL)
						continue;

					boolean c1 = CheckRow(pshelf, row, col, 2);
					boolean c2 = CheckCol(pshelf, row, col, 2);

					if (c1 || c2) {

						already_checked[index] = row * 10 + col;
						index++;
						result++;

						if (result == 6)
						{
							return true;
						}

					}

				}

			}

			return false;

		}

	},

	CommonGoal6(6) {
		public boolean checkCommonGoal(Bookshelf pshelf) {

			// Due gruppi separati di 4 tessere dello stesso
			// tipo che formano un quadrato 2x2. Le
			// tessere dei due gruppi devono essere dello stesso tipo.

			int result = 0;

			for (TileType type : TileType.types) {

				int index = 0;
				int[] already_checked = new int[30];
				Arrays.fill(already_checked, -99);

				for (int dshift = 0; dshift <= 4; dshift++) {

					for (int rshift = 0; rshift <= 3; rshift++) {

						// non andiamo a prendere il quadrato che abbiamo già trovato
						if (Coords_Check(already_checked, dshift, rshift, 1))
							continue;

						TileType checktile = pshelf.getTile(0 + rshift, 0 + dshift).getType();

						// se la prima tile del quadrato è diversa da quella che stiamo effettivamente
						// cercando andiamo oltre

						if (checktile != type)
							continue;

						boolean t1 = checktile == pshelf.getTile(1 + rshift, 0 + dshift).getType();
						boolean t2 = checktile == pshelf.getTile(0 + rshift, 1 + dshift).getType();
						boolean t3 = checktile == pshelf.getTile(1 + rshift, 1 + dshift).getType();

						if (t1 && t2 && t3) {
							already_checked[index] = (dshift * 10 + rshift);
							index++;
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

		@Override
		public boolean checkCommonGoal(Bookshelf pshelf) {

			// Otto tessere dello stesso tipo. Non
			// ci sono restrizioni sulla posizione
			// di queste tessere.

			for (TileType type : TileType.types) {

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

		@Override
		public boolean checkCommonGoal(Bookshelf pshelf) {

			// Cinque tessere dello stesso tipo che formano una X

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

		@Override
		public boolean checkCommonGoal(Bookshelf pshelf) {

			// Cinque tessere dello stesso tipo che formano
			// una diagonale

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

		@Override
		public boolean checkCommonGoal(Bookshelf pshelf) {

			// Due righe formate ciascuna da 5 diversi tipi di tessere

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

		@Override
		public boolean checkCommonGoal(Bookshelf pshelf) {
			// Quattro righe formate ciascuna da 5 tessere di
			// uno, due o tre tipi differenti. Righe
			// diverse possono avere combinazioni
			// diverse di tipi di tessere.

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

		@Override
		public boolean checkCommonGoal(Bookshelf pshelf) {

			// Quattro tessere dello stesso tipo ai quattro angoli della Libreria.

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

	/* This method reads the csv file containing the number of points assigned if the player has groups of 3/4/5/6+ tiles of the same type in his bookshelf
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

	public boolean Coords_Check(int[] coords, int row, int col, int mode) {

		// Metodo unico (mode cambia il modo in cui funziona) per
		// controllare se una tile è già presente in un gruppo
		// controllato precedentemente

		for (int coord : coords) {

			switch (mode) {

				// Gruppo di grandezza 2x2
				case 1 : {
					boolean t1 = (row - 1) * 10 + col == coord;
					boolean t2 = row * 10 + (col - 1) == coord;
					boolean t3 = (row - 1) * 10 + (col - 1) == coord;
					return t1 || t2 || t3;
				}

				// Gruppo di lunghezza 2
				case 2 : {
					boolean h1 = (row - 1) * 10 + col == coord;
					boolean v1 = row * 10 + (col - 1) == coord;
					return h1 || v1;
				}

				// Gruppo di lunghezza 4
				case 3 : {
					boolean h1 = (row - 1) * 10 + col == coord;
					boolean h2 = (row - 2) * 10 + col == coord;
					boolean h3 = (row - 3) * 10 + col == coord;
					boolean v1 = row * 10 + (col - 1) == coord;
					boolean v2 = row * 10 + (col - 2) == coord;
					boolean v3 = row * 10 + (col - 3) == coord;
					return (h1 || h2 || h3 || v1 || v2 || v3);
				}
			}
		}

		return false;

	}

	private static int Count_Tiles(TileType[] row_col) {

		// Metodo per contare le tile di una riga/colonna

		int count = 0;

		for (TileType tile : row_col) {
			for (TileType type : TileType.types) {
				if (tile == type) {
					count++;
				}
			}
		}

		return count;

	}

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

	public int[] PointsPathfinder(Bookshelf pshelf)
	{
		PathFind pathfinder = new PathFind(pshelf);
		return pathfinder.PointsPathfinding();
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