package controller;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;

import model.CommonGameArea;
import model.PersonalGameArea;
import view.CommonGameAreaFrame;
import view.PersonalGameAreaFrame;
import view.commongamearea.MainMenuWindow;

public class Main {

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					
					ArrayList<String> players = new ArrayList<>();
					
					MainMenuWindow mainMenu = new MainMenuWindow(players);
					
					players.forEach((p) -> System.out.println(p));
					
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

					//Model
					CommonGameArea commonGameArea = new CommonGameArea();
					PersonalGameArea personalGrameArea = new PersonalGameArea();
					
					//View
					CommonGameAreaFrame commonGameAreaFrame = new CommonGameAreaFrame(screenSize);
					PersonalGameAreaFrame personalGameAreaFrame = new PersonalGameAreaFrame(screenSize);

					MainController mainController = new MainController(personalGameAreaFrame, commonGameAreaFrame, players, personalGrameArea, commonGameArea);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}			
		});
	}
}
