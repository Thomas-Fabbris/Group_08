package personalgamearea;
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
	//probabilmente sia 01 che 02 andranno iterati per ogni tipo di tessera
	public boolean Check_CommonGoal_01(Bookshelf player_bookshelf[][], int max_column, int max_row) {
		int consecutive_counter=0;
		int group_counter = 0;
		int group_dim = 2;
		for(int i = 0; i < max_row; i++) {
			for(int j=0, k = 1; j<max_column-1 && k < max_column; j++, k++) {
				if(player_bookshelf[i][j] == player_bookshelf[i][k]) {// qui non è corretto perchè bisogna confrontare il tipo delle tile
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
				if(player_bookshelf[i][j] == player_bookshelf[i][k]) {// qui non è corretto perchè bisogna confrontare il tipo delle tile
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
				if(player_bookshelf[i][j] == player_bookshelf[i][k]) {// qui non è corretto perchè bisogna confrontare il tipo delle tile
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
				if(player_bookshelf[i][j] == player_bookshelf[i][k]) {// qui non è corretto perchè bisogna confrontare il tipo delle tile
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
	
	
}


