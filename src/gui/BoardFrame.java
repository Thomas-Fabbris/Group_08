package gui;

import javax.swing.JFrame;

public class BoardFrame extends JFrame {
	final int tiles_config_level;
	
	public BoardFrame(String title, int tiles_config_level) { //tiles_config_level refers to the number of players
		super(title);
		Init();
		this.tiles_config_level = tiles_config_level;	
	}
	
	private void Init() {
		this.setSize(1000, 1000);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

}
