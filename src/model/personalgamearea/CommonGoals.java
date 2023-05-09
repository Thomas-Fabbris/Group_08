package model.personalgamearea;

// questi tre importati per convertire array in set
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

// non considerate i nomi dei metodi, perchè mandano solo in confusione
// ma inizio a fare uno switch con i metodi di controlli per i vare common goals

public class CommonGoals {

	// maxrow e maxcol se usati in un loop for devono essere messi
	// con <= e non solamente con < se no si salta l'ultima colonna/riga.
	// è fatto in questo modo così che per le carte che hanno bisogno di
	// controllare gli angoli è più semplice mettere dentro max row/col

	//probabilmente sia 01 che 02 andranno iterati per ogni tipo di tessera
	public boolean Check_CommonGoal_01(Bookshelf player_bookshelf[][], int max_column, int max_row) {
		int consecutive_counter=0;
		int group_counter = 0;
		int group_dim = 2;
		for(int i = 0; i < max_row; i++) {
			for(int j=0, k = 1; j<max_column-1 && k < max_column; j++, k++) {
				if(player_bookshelf[i][j].equals(player_bookshelf[i][k])) {
					consecutive_counter++;
				}else if (player_bookshelf[i][j] != player_bookshelf[i][k]) {
					if (consecutive_counter >= group_dim - 1) {
						group_counter++;
						consecutive_counter = 0;
					}else {
						consecutive_counter = 0;
					}
				}
			}
		}
		for(int i = 0; i < max_column; i++) {
			for(int j=0, k = 1; j<max_row-1 && k < max_row; j++, k++) {
				if(player_bookshelf[i][j].equals(player_bookshelf[i][k])) {
					consecutive_counter++;
				}else if (player_bookshelf[i][j] != player_bookshelf[i][k]) {
					if (consecutive_counter >= group_dim - 1) {
						group_counter++;
						consecutive_counter = 0;
					}else {
						consecutive_counter = 0;
					}
				}
			}
		}
		if(group_counter >= 6) {
			return true;
		}else {
			return false;
		}
	}
	//il metodo 2 funziona solo se si considerano gruppi di file/colonne da 4 e non forme strane
	// i due metodi sono corretti solo se si considerano gruppi "doppioni"
	public boolean Check_CommonGoal_02(Bookshelf player_bookshelf[][], int max_column, int max_row) {
		int consecutive_counter=0;
		int group_counter = 0;
		int group_dim = 4;
		for(int i = 0; i < max_row; i++) {
			for(int j=0, k = 1; j<max_column-1 && k < max_column; j++, k++) {
				if(player_bookshelf[i][j].equals(player_bookshelf[i][k])) {
					consecutive_counter++;
				}else if (player_bookshelf[i][j] != player_bookshelf[i][k]) {
					if (consecutive_counter >= group_dim - 1) {
						group_counter++;
						consecutive_counter = 0;
					}else {
						consecutive_counter = 0;
					}
				}
			}
		}
		for(int i = 0; i < max_column; i++) {
			for(int j=0, k = 1; j<max_row-1 && k < max_row; j++, k++) {
				if(player_bookshelf[i][j].equals(player_bookshelf[i][k])) {
					consecutive_counter++;
				}else if (player_bookshelf[i][j] != player_bookshelf[i][k]) {
					if (consecutive_counter >= group_dim - 1) {
						group_counter++;
						consecutive_counter = 0;
					}else {
						consecutive_counter = 0;
					}
				}
			}
		}
		if(group_counter >= 4) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean Check_Common_Goal_03(Bookshelf player_bookshelf[][], int max_column, int max_row) {
		if (player_bookshelf[0][0].equals(player_bookshelf[0][max_row]) && player_bookshelf[0][0].equals(player_bookshelf[max_column][0]) && player_bookshelf[0][0].equals(player_bookshelf[max_column][max_row])) {
			return true;
		}else {
			return false;
		}

	}

	public boolean Check_Common_Goal_04(Bookshelf player_bookshelf[][], int max_column, int max_row) {

		return false;
	}

	public boolean Check_Common_Goal_05(Bookshelf player_bookshelf[][], int max_column, int max_row) {
		int differentTypesCount = 0;
		for(int k = 0; k < Bookshelf.ROWS; k++) {
			//BookshelfTile[] row = player_bookshelf[k];
		}
		return false;
	}

	public boolean Check_Common_Goal_06(Bookshelf player_bookshelf[][], int max_column, int max_row) {
		return false;
	}

	public boolean Check_Common_Goal_07(Bookshelf player_bookshelf[][], int max_column, int max_row) {
		return false;
	}

	public boolean Check_Common_Goal_08(Bookshelf player_bookshelf[][], int max_column, int max_row) {
		return false;
	}

	public boolean Check_Common_Goal_09(Bookshelf pshelf) {

		// Controlla la diagonale (y = -x) se è composta dalle stesse tile,
		// nel caso controlla quella sotto (ce ne stanno due di diagonali così
		// nella shelf)

		for (int i = 0; i < 2; i++)
		{
			boolean t1 = pshelf.getTile(i, 0) == pshelf.getTile(1 + i, 1);
			boolean t2 = pshelf.getTile(i, 0) == pshelf.getTile(2 + i, 2);
			boolean t3 = pshelf.getTile(i, 0) == pshelf.getTile(3 + i, 3);
			boolean t4 = pshelf.getTile(i, 0) == pshelf.getTile(4 + i, 4);

			if (t1 && t2 && t3 && t4) return true;

		}

		return false;

	}

	public boolean Check_Common_Goal_10(Bookshelf shelf) {
		//Refactored method 
		for (int k = 0; k < Bookshelf.ROWS; k++){
			
			BookshelfTile[] col = shelf.getColumn(k);
			 if(!Arrays.stream(col).allMatch(new HashSet<>()::add)){
				 return true;
			 }
			 
			}
		return false;
	}

	public boolean Check_Common_Goal_11(Bookshelf pshelf, int max_column, int max_row) {

		return false;

	}

	public boolean Check_Common_Goal_12(Bookshelf pshelf) {

		return false;

	}
}


