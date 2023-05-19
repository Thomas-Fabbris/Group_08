package model.shared;

import java.util.Comparator;

import model.Player;

public class PlayerScoreComparator implements Comparator<Player> {

	/*
	 * Returns a negative integer,zero, or a positive integer as the first argument
	 * is less than, equal to, or greater than the second.

	 */

	@Override
	public int compare(Player p1, Player p2) {
		int p1Points = p1.getPoints();
		int p2Points = p2.getPoints();
		
		if(p1Points < p2Points) {
			return 1;
		}
		
		if(p1Points > p2Points) {
			return -1;
		}


		return 0;
	}

}
