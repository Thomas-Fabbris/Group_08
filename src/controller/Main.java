package controller;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.ArrayList;

import model.CommonGameArea;
import model.PersonalGameArea;
import model.shared.Player;
import view.CommonGameAreaFrame;
import view.PersonalGameAreaFrame;
import view.sharedgamearea.MainMenuWindow;

public class Main {

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					
					ArrayList<Player> players = new ArrayList<>();
					MainMenuWindow mainMenu = new MainMenuWindow(players);
					
					players.forEach((p) -> System.out.println(p.getName() +" id: "+ p.id));
					
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					
					CommonGameAreaFrame commonGameAreaFrame = new CommonGameAreaFrame(screenSize);
					PersonalGameAreaFrame personalGameAreaFrame = new PersonalGameAreaFrame(screenSize);
					
					CommonGameArea commonGameArea = new CommonGameArea();
					PersonalGameArea personalGrameArea = new PersonalGameArea();
					
					MainController mainController = new MainController(personalGameAreaFrame, commonGameAreaFrame, players, personalGrameArea, commonGameArea);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}			
		});
	}
}