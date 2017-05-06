package gameLogic;

import java.awt.Point;
import java.util.ArrayList;

import gameGUI.Figure;
import gameGUI.GameBoard;

public class Logic {

	private GameBoard board;

	private Point firstClick;
	private int score;

	// mouseclicks

	public Logic(GameBoard board) {
		this.board = board;
	}

	public void play(int x, int y) {
		if (firstClick == null) {
			firstClick = new Point(x, y);
		} else {
			if (isMoveLegal(firstClick.x, firstClick.y, x, y)) {
				swapElements(firstClick.x, firstClick.y, x, y);
				modifyBoard(firstClick.x, firstClick.y, x, y);
			}
			firstClick = null;
		}
	}
	
	private boolean isMoveLegal(int firstX, int firstY, int secondX, int secondY) {
		if (Math.abs(firstX - secondX) == 1) {
			if (firstY == secondY) {
				return true;
			}
		} else if (Math.abs(firstY - secondY) == 1) {
			if (firstX == secondX) {
				return true;
			}
		}
		return false;
	}
	
	private void swapElements(int firstX, int firstY, int secondX, int secondY) {
		Figure firstFigure = board.getPlanetAt(firstX, firstY);
		Figure secondFigure = board.getPlanetAt(secondX, secondY);
		board.setPlanetAt(firstX, firstY, secondFigure);
		board.setPlanetAt(secondX, secondY, firstFigure);
	}

	private void modifyBoard(int currX, int currY, int x, int y) {
		ArrayList<Point> rightMatchFigures = getRightMatches();
		ArrayList<Point> downMatchFigures = getDownMatches();
		ArrayList<Point> combinedFigures = new ArrayList<Point>();
		
		combinedFigures.addAll(rightMatchFigures);
		combinedFigures.addAll(downMatchFigures);

		refillBoardOrSwapBack(combinedFigures, currX, currY, x, y);
	}

	private void refillBoardOrSwapBack(ArrayList<Point> coordinateList, int currX, int currY, int x, int y) {
		if (coordinateList.size() > 0) {
			clearMatches(coordinateList);
			board.reorder();
			board.refill();
		} else {
			// if no matches found, unswa
			swapElements(x, y, currX, currY);
		}
	}

	private void clearMatches(ArrayList<Point> rightMatchFigures) {
		// for each (collections)
		for (Point figureCoordinates : rightMatchFigures) {
			board.setPlanetAt(figureCoordinates.x, figureCoordinates.y, null);
		}
	}

	private ArrayList<Point> getRightMatches() {
		ArrayList<Point> matchedFigures = new ArrayList<>();
		for (int y = 0; y < 5; y++) {
			int counter = 0;
			for (int x = 0; x < 5; x++) {
				if (board.getPlanetAt(x, y).getType() == board.getPlanetAt(x + 1, y).getType()) {
					counter++;
				} else {
					if (counter >= 2) {
						for (int i = x - counter; i <= x; i++) {
							matchedFigures.add(new Point(i, y));
						}
					}
					counter = 0;
				}
			}
		}
		return matchedFigures;
	}

	private ArrayList<Point> getDownMatches() {
		ArrayList<Point> matchedFigures = new ArrayList<>();
		for (int y = 0; y < 5; y++) {
			int counter = 0;
			for (int x = 0; x < 5; x++) {
				if (board.getPlanetAt(x, y).getType() == board.getPlanetAt(x, y + 1).getType()) {
					counter++;
				} else {
					if (counter >= 2) {
						for (int i = x - counter; i <= x; i++) {
							matchedFigures.add(new Point(i, y));
						}
					}
					counter = 0;
				}
			}
		}
		return matchedFigures;
	}

	private boolean checkCounter(int counter) {
		if (counter >= 3) {
			return true;
		} else {
			return false;
		}
	}

	public int getScore(int counter) {

		// GameBoard.getlblScoreHolder().setText("" + counter*10);
		return score;
	}

//	public static void checkStartBoard(GameBoard board) {
//		for (int x = 1; x < GameBoard.getBoard_height(); x++) {
//			for (int y = 0; y < GameBoard.getBoard_width(); y++) {
//				if (board.getPlanetAt(x, y).getType() == board.getPlanetAt(x - 1, y).getType()) {
//
//				}
//			}
//		}
//
//	}

}
