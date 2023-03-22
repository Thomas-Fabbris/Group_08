package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import main.Board;


public class BoardWindow extends JFrame {
	private final Dimension screen_size = Toolkit.getDefaultToolkit().getScreenSize();
//	private final Dimension screen_size = new Dimension(1280, 720); //used for debugging
	private final Board board;
	
	public BoardWindow(String title) {
		super(title);
		Init();
		this.board = new Board(this.getSize());
		this.add(board.getLabel());
	}
	
	public Board getBoard() {
		return board;
	}
	
	private void Init() {
		this.setResizable(false);
		this.setSize(screen_size.width/2, screen_size.height*2/3);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.getContentPane().setBackground(Color.DARK_GRAY);
		this.getContentPane().setBackground(Color.RED);
	}
}
