package controller;

import java.util.ArrayList;

import controller.commongamearea.BoardController;
import controller.commongamearea.CommonObjectiveCardsController;
import model.CommonGameArea;
import model.PersonalGameArea;
import view.CommonGameAreaFrame;
import view.PersonalGameAreaFrame;

public class MainController {
	
	ArrayList<String> playerNames;
	BoardController boardController;
	CommonObjectiveCardsController commonObjectiveCardsController;
	
	public MainController(PersonalGameAreaFrame personalGameAreaFrame, CommonGameAreaFrame commonGameAreaFrame, ArrayList<String> playerNames, PersonalGameArea personalGameArea, CommonGameArea commonGameArea) {		
		this.playerNames = playerNames;
		
		this.boardController = new BoardController(commonGameArea.getBoard(), commonGameAreaFrame.getBoardLabel());
		initBoardController();
		
		this.commonObjectiveCardsController = new CommonObjectiveCardsController(commonGameArea.getCard1(), commonGameArea.getCard2(), commonGameAreaFrame.getCard1Label(), commonGameAreaFrame.getCard2Label());
	}
	
	public void initBoardController() {
		this.boardController.addTiles();
	}

}
