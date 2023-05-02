package model.shared;

import java.util.Random;

import model.commongamearea.CommonObjectiveCard;
import model.personalgamearea.PersonalObjectiveCard;

public class IdGenerator {
	private static int COUNTER = 0;
	private static int[] available_common_objective_card_ids = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
	private static int[] available_personal_objective_card_ids = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
	
	public IdGenerator() { }
	
	public int getNewId() {
		return COUNTER++;
	}
	
	/**
	 * Generates a number between 1 and 12 that hans't been extracted yet to obtain a unique id for the new card
	 * @return a new unique card id
	 */
	public int getNewCommonObjectiveCardId() {
		Random rd = new Random();
		int random_index;
		int id;
		
		do {
			random_index = rd.nextInt(CommonObjectiveCard.MAX_CARD_ID);  //number between 0 and 11
			id = available_common_objective_card_ids[random_index];
		} while (id == -1);
		
		available_common_objective_card_ids[random_index] = -1;
		return id;
	}
	
	/**
	 * Generates a number between 1 and 12 that hans't been extracted yet to obtain a unique id for the new card
	 * @return a new unique card id
	 */
	public int getNewPersonalObjectiveCardId() {
		Random rd = new Random();
		int random_index;
		int id;
		
		do {
			random_index = rd.nextInt(PersonalObjectiveCard.MAX_CARD_ID);  //number between 0 and 11
			id = available_personal_objective_card_ids[random_index];
		} while (id == -1);
		
		available_personal_objective_card_ids[random_index] = -1;
		return id;
	}
}
