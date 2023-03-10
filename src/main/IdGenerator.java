package main;

public class IdGenerator {
	private static int COUNTER = 0;
	
	public IdGenerator() { }
	
	public int getNewId() {
		return COUNTER++;
	}
	
}
