package model.shared;

import java.util.Comparator;

import model.Player;

public class PlayerScoreComparator implements Comparator<Player> {

	/**
	 * This method returns a negative integer, zero, or a positive integer as the first argument
	 * is less than, equal to, or greater than the second.
	 * 
	 * @param p1
	 * @param p2
	 * 
	 * @throws NullPointerException
	 * @return -1
	 * @return 0 
	 * @return +1
	 */
	@Override
	public int compare(Player p1, Player p2) {
		if(p1 == null) {
			throw new NullPointerException("player1 cannot be set to null when calling PlayerScoreComparator.compare()");
		}
		if(p2 == null) {
			throw new NullPointerException("player2 cannot be set to null when calling PlayerScoreComparator.compare()");
		}
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
