package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;


public class BoardFrame extends JFrame {
	final int tiles_config_level;
	private final Dimension screen_size = Toolkit.getDefaultToolkit().getScreenSize();
//	private final Dimension screen_size = new Dimension(1280, 720); //used for debugging
	private final Board board;
	
	public BoardFrame(String title, int tiles_config_level) { //tiles_config_level refers to the number of players
		super(title);
		this.tiles_config_level = tiles_config_level;
		Init();
		this.board = new Board(this.getSize());
		this.add(board);
	}
	
	public Board getBoard() {
		return board;
	}
	
	private void Init() {
		this.setResizable(false);
		this.setSize(screen_size.width/2, screen_size.height*2/3);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.RED); //not working
	}
}
