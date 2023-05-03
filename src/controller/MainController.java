package controller;

import java.util.ArrayList;

import model.CommonGameArea;
import model.PersonalGameArea;
import model.commongamearea.Board;
import model.commongamearea.CommonObjectiveCard;
import model.shared.Player;
import view.CommonGameAreaFrame;
import view.PersonalGameAreaFrame;
import view.commongamearea.MainMenuWindow;

public class MainController {
	
	Board board;
	ArrayList<String> playerNames;
	CommonObjectiveCard card1;
	CommonObjectiveCard card2;
	
	public MainController(PersonalGameAreaFrame personalGameAreaFrame, CommonGameAreaFrame commonGameAreaFrame, ArrayList<String> playerNames, PersonalGameArea personalGameArea, CommonGameArea commonGameArea) {		
		this.playerNames = playerNames;
		
	}
}
