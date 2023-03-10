package main;

public class Player {
	private String nome;
	private String cognome;
	final int id;
	
	public Player(String nome, String cognome, IdGenerator idgenerator) {
		this.nome = nome;
		this.cognome = cognome;
		this.id = idgenerator.getNewId();
	}
	
	@Override
	public boolean equals(Object object) {
		return this.id == ((Player)object).id;
	}
	
}
