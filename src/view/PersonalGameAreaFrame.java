package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

import view.personalgamearea.BookshelfLabel;
import view.personalgamearea.PlayerInfo;

public class PersonalGameAreaFrame extends JFrame {
	
	Dimension screenSize;
	PlayerInfo currentPlayerInfo;
	
	public PersonalGameAreaFrame(Dimension screenSize) {
		super("MyShelfie");
		this.screenSize = screenSize;
		Init();
		
		this.add(currentPlayerInfo.getPlayerInfoPanel());
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
