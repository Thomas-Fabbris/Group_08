package main;

import java.util.Random;

public class IdGenerator {
	private static int COUNTER = 0;
	private static int LAST_COMMON_OBJ_CARD_ID = 0;
	
	public IdGenerator() { }
	
	public int getNewId() {
		return COUNTER++;
	}
	
	/**
	 * Generates a new number between 1 and 12 to obtain a unique id
	 * @return a new unique card id
	 */
	public int getNewCommonObjCardId() {
		Random rd = new Random();
		int rand = rd.nextInt(Card.MAX_CARD_ID-1);
		
		if(LAST_COMMON_OBJ_CARD_ID >= 12)
			throw new IllegalStateException(); //You should not be able to generate a number over 12
		
		while(rand == LAST_COMMON_OBJ_CARD_ID)
			rand = rd.nextInt(Card.MAX_CARD_ID-1); //Keep generating until the number is different

		return rand+1;
	}
}
