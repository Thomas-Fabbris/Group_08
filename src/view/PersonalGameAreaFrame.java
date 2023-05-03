package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

import view.personalgamearea.BookshelfLabel;

public class PersonalGameAreaFrame extends JFrame {
	
	Dimension screenSize;
	BookshelfLabel bookshelfLabel;
	
	public PersonalGameAreaFrame(Dimension screenSize) {
		super("MyShelfie");
		this.screenSize = screenSize;
		Init();
	}
	
	private void Init() {
		this.setResizable(false);
		this.setSize(screenSize.width/2, screenSize.height-40);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.DARK_GRAY);
		this.setLocation(screenSize.width/2, 0);
		this.setVisible(true);
	}
}
