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
	
	ArrayList<String> playerNames;
	BoardController boardController;
	
	public MainController(PersonalGameAreaFrame personalGameAreaFrame, CommonGameAreaFrame commonGameAreaFrame, ArrayList<String> playerNames, PersonalGameArea personalGameArea, CommonGameArea commonGameArea) {		
		this.playerNames = playerNames;
		
		this.boardController = new BoardController(commonGameArea.getBoard(), commonGameAreaFrame.getBoardLabel());
		initBoardController();
	}
	
	public void initBoardController() {
		this.boardController.addTiles();
	}

}
