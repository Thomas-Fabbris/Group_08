package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;


public class BoardFrame extends JFrame {
	final int tiles_config_level;
	private final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
	
	public BoardFrame(String title, int tiles_config_level) { //tiles_config_level refers to the number of players
		super(title);
		Init();
		this.tiles_config_level = tiles_config_level;
	}
	
	private void Init() {
		this.setResizable(false);
		this.setSize(size.width/2, size.height*2/3); //adjustment to prevent overlapping with windows taskbar
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.RED); //not working
	}
}
