package init;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.ArrayList;

import controller.MainController;
import model.CommonGameArea;
import view.CommonGameAreaFrame;
import view.PersonalGameAreaFrame;
import view.commongamearea.MainMenuWindow;
/**
 * 
 * The class {@code Main} is the core part of the program, it initializes all the classes of the game
 *
 */
public class Main {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {

					ArrayList<String> players = new ArrayList<>();

					MainMenuWindow mainMenu = new MainMenuWindow(players);

					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

					// Model
					CommonGameArea commonGameArea = new CommonGameArea(players.size());

					// View
					CommonGameAreaFrame commonGameAreaFrame = new CommonGameAreaFrame(screenSize);
					PersonalGameAreaFrame personalGameAreaFrame = new PersonalGameAreaFrame(screenSize);
					
					//Controller
					MainController mainController = new MainController(personalGameAreaFrame, commonGameAreaFrame,
							players, commonGameArea);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
