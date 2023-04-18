package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class PersonalGameAreaWindow extends JFrame {
	private static PersonalGameAreaWindow instance = null;
	private final Dimension screen_size = Toolkit.getDefaultToolkit().getScreenSize();
	
	public PersonalGameAreaWindow() {
		super("MyShelfie"); //TODO: pass as parameter the name of the player who's playing
		this.setLayout(new FlowLayout());
		Init();
	}
	
	public static PersonalGameAreaWindow getInstance() {
		if(instance == null)
			instance = new PersonalGameAreaWindow();
		return instance;
	}
	
	private void Init() {
		//this.board_size = new Dimension(screen_size.getSize().height*2/3, screen_size.getSize().height*2/3);
		this.setResizable(false);
		this.setSize(screen_size.width/2, screen_size.height-40);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.DARK_GRAY);
		this.setLocation(0, 0);
	}
}
