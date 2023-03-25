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
	
	public boolean equals(Player player) {
		return this.id == player.id;
	}
	
	public String getNome() {
		return this.nome;
	}	
}
