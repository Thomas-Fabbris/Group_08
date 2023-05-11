package model.personalgamearea;

// questi tre importati per convertire array in set
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import jdk.dynalink.linker.TypeBasedGuardingDynamicLinker;
import model.shared.Tile;
import model.shared.TileType;


// non considerate i nomi dei metodi, perchè mandano solo in confusione
// va strutturata in maniera diversa l'assegnazione dei punti
// ma inizio a fare uno switch con i metodi di controlli per i vare common goals

public class CommonGoals {

	public int award(Bookshelf player_bookshelf[][], int i, int j, int goal_id) {
		int points = 0;
		switch(goal_id) {
			case 0:{


			}
		}
		return points;

	}

	// WORKING
	public int Check_Common_Goal_01(Bookshelf pshelf) {

		int result = 0;

		for (int col = 0; col <= 4; col++) {

			TileType[] current_col = pshelf.getColumn(col);
			int nulls = Math.min(1, Count_NULL(current_col));
			Set<TileType> set = new HashSet<>(Arrays.asList(current_col));
			if (set.size() - nulls == 6) result++;

		}

		return Math.min(0, result - 1);

	}

	// TODO FARE QUESTO
	public int Check_Common_Goal_02(Bookshelf pshelf) {
		return 0;
	}


	// WORKING
	public int Check_Common_Goal_03(Bookshelf pshelf) {

		int result = 0;

		for (int col = 0; col <= 4; col++) {

			TileType[] current_col = pshelf.getColumn(col);
			int nulls = Math.min(1, Count_NULL(current_col));
			Set<TileType> set = new HashSet<>(Arrays.asList(current_col));
			if (set.size() - nulls <= 3) result++;

		}

		return Math.max(0, result - 2);

	}

	// WORKING
	public int Check_Common_Goal_04(Bookshelf pshelf) {

		int result = 0;

		for (int inverse = 0; inverse <= 1; inverse++) {

			int[] heights = new int[5];
			boolean wrong = false;

			for (int col = 0; col <= 4; col++) {

				heights[col] = Count_Tiles(pshelf.getColumn(Math.abs(4*inverse - col)));

			}

			for (int i = 0; i <= 3; i++)
			{

				if ((heights[i] - 1) != (heights[i+1])) {
					wrong = true;
					break;
				}

			}

			if (!wrong) result++;

		}
		return result;
	}

	// TODO FARE QUESTO
	public int Check_Common_Goal_05(Bookshelf pshelf) {
		return 0;
	}

	// WORKING
	public int Check_Common_Goal_06(Bookshelf pshelf) {

		int result = 0;

		for (TileType type : TileType.types) {

			int count = 0;
			int index = 0;
			int[] already_checked = {-1};

			for (int dshift = 0; dshift <= 4; dshift++) {

				for (int rshift = 0; rshift <= 3; rshift++) {

					// non andiamo a prendere il quadrato che abbiamo già trovato

					if (Coords_Check(already_checked, dshift, rshift)) continue;

					TileType checktile = pshelf.getTile(0 + rshift, 0 + dshift).getType();

					// se la prima tile del quadrato è diversa da quella che stiamo effettivamente
					// cercando andiamo oltre

					if (checktile != type) continue;

					boolean t1 = checktile == pshelf.getTile(1 + rshift, 0 + dshift).getType();
					boolean t2 = checktile == pshelf.getTile(0 + rshift, 1 + dshift).getType();
					boolean t3 = checktile == pshelf.getTile(1 + rshift, 1 + dshift).getType();

					if (t1 && t2 && t3) {
						already_checked[index] = (dshift * 3 + rshift * 5);
						index++;
						count++;
					}

				}

			}

			result += Math.max(count - 1, 0);

		}

		return result;

	}

	// WORKING
	public int Check_Common_Goal_07(Bookshelf pshelf) {

		int result = 0;

		for (TileType type : TileType.types) {

			int count = 0;

			for (int row = 0; row <= 4; row++) {
				for (int col = 0; col <= 5; col++) {
					if (pshelf.getTile(row, col).getType() == type) count++;

				}
			}

			if (count >= 8) result++;

		}

		return result;

	}

	// WORKING
	public int Check_Common_Goal_08(Bookshelf pshelf) {

		// Controlla il seguente pattern > 		X X
		// su tutta la shelf se è				 X
		// composto da tile uguali 				X X

		int result = 0;

		for (int rshift = 0; rshift <= 2; rshift++) {

			for (int dshift = 0; dshift <= 3; dshift++) {

				TileType checktile = pshelf.getTile(0 + dshift, 0 + rshift).getType();

				if (checktile == TileType.NULL) continue;

				boolean t1 = checktile == pshelf.getTile(1 + dshift, 1 + rshift).getType();
				boolean t2 = checktile == pshelf.getTile(2 + dshift, 0 + rshift).getType();
				boolean t3 = checktile == pshelf.getTile(0 + dshift, 2 + rshift).getType();
				boolean t4 = checktile == pshelf.getTile(2 + dshift, 2 + rshift).getType();

				if (t1 && t2 && t3 && t4) result++;

			}

		}

		return result;

	}

	// WORKING
	public int Check_Common_Goal_09(Bookshelf pshelf) {

		// Controlla la diagonale (y = x e y = -x) se è composta dalle stesse tile,
		// nel caso controlla quelle sotto (ce ne stanno 4 di diagonali così
		// nella shelf)

		int result = 0;

		for (int dshift = 0; dshift <= 1; dshift++) {

			for (int inverse = 0; inverse <= 1; inverse++) {

				TileType checktile = pshelf.getTile(0 + dshift, 0 + (4*inverse)).getType();

				if (checktile == TileType.NULL) continue;

				boolean t1 = checktile == pshelf.getTile(1 + dshift, 1 + (2*inverse)).getType();
				boolean t2 = checktile == pshelf.getTile(2 + dshift, 2              ).getType();
				boolean t3 = checktile == pshelf.getTile(3 + dshift, 3 - (2*inverse)).getType();
				boolean t4 = checktile == pshelf.getTile(4 + dshift, 4 - (4*inverse)).getType();

				if (t1 && t2 && t3 && t4) result++;

			}
		}

		return result;

	}

	// WORKING
	public int Check_Common_Goal_10(Bookshelf pshelf) {

		// Controlla se ci sono 2 righe della shelf composte interamente da tile diverse

		int result = 0;

		for (int row = 0; row <= 5; row++) {

			TileType[] current_row = pshelf.getRow(row);
			int nulls = Math.min(1, Count_NULL(current_row));
			Set<TileType> set = new HashSet<>(Arrays.asList(current_row));
			if (set.size() - nulls == 5) result++;

		}

		return Math.max(0, result - 1);

	}

	// WORKING
	public int Check_Common_Goal_11(Bookshelf pshelf) {

		int result = 0;

		for (int row = 0; row <= 5; row++) {

			TileType[] current_row = pshelf.getRow(row);
			int nulls = Math.min(1, Count_NULL(current_row));
			Set<TileType> set = new HashSet<>(Arrays.asList(current_row));
			if (set.size() - nulls <= 3) result++;

		}

		return Math.max(0, result - 3);


	}

	// WORKING
	public int Check_Common_Goal_12(Bookshelf pshelf) {

		// Controlla se i lati della shelf hanno tile uguali

		TileType checktile = pshelf.getTile(0, 0).getType();

		if (checktile == TileType.NULL) return 0;

		boolean t1 = checktile == pshelf.getTile(0, 4).getType();
		boolean t2 = checktile == pshelf.getTile(5, 0).getType();
		boolean t3 = checktile == pshelf.getTile(5, 4).getType();

		if (t1 && t2 && t3) return 1;
		return 0;

	}

	public int Count_NULL(TileType[] row_col) {

		int count = 0;

		for (TileType tile : row_col) {
			if (tile == TileType.NULL) count++;
		}

		return count;

	}

	public boolean Coords_Check(int[] coords, int row, int col) {

		for (int coord : coords) {

			boolean t1 = (row + 1) * 3 +  col      * 5 == coord;
			boolean t2 =  row * 3      + (col + 1) * 5 == coord;
			boolean t3 = (row + 1) * 3 + (col + 1) * 5 == coord;

			if (t1 || t2 || t3) return true;

		}

		return false;

	}

	public int Count_Tiles(TileType[] row_col) {

		int count = 0;

		for (TileType tile : row_col) {
			for (TileType type : TileType.types) {
				if (tile == type) {
					count++;
					break;
				}
			}
		}

		return count;

	}

}


