package gameLogic;

import java.awt.Point;
import java.util.ArrayList;

import gameGUI.Figure;
import gameGUI.GameBoard;

public class Logic {

	private GameBoard board;

	private int currX;
	private int currY;
	private int score;

	// mouseclicks

	public Logic(GameBoard board) {

		this.currX = -1;
		this.currY = -1;

		this.board = board;
	}

	public void play(int x, int y) {
		if (this.currX == -1 && this.currY == -1) {
			this.currX = x;
			this.currY = y;
		} else {
			if (isMoveLegal(currX, currY, x, y)) {
				swapElements(currX, currY, x, y);
				modifyBoard(currX, currY, x, y);
			}
			currX = -1;
			currY = -1;
		}

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

	private boolean checkIfDownMatch(int x, int y) {

		int counter = 1;
		while (x < 5 && board.getPlanetAt(x, y).getType() == board.getPlanetAt(x + 1, y).getType()) {
			counter++;
			x++;
		}
		if (checkCounter(counter)) {
			getScore(counter);
			for (int row = x; row < x + counter - 1; row++) {
				board.setPlanetAt(row, y, null);
			}
			return true;
		} else {
			return false;
		}

	}

	private boolean checkIfUpMatch(int x, int y) {
		ArrayList<Figure> upCheckList = new ArrayList<Figure>();
		// lokalen spisuk za suvpadeniqta ako duljinata e po golqma ot 3 gi
		// dobavqm kum gorniq spisuk
		// int counter = 0;
		while (x > 0 && board.getPlanetAt(x, y).getType() == board.getPlanetAt(x - 1, y).getType()) {
			x--;
			upCheckList.add(board.getPlanetAt(x, y));
		}
		// if (checkCounter(counter)) {
		int upCheckListSize = upCheckList.size();
		if (upCheckListSize >= 3) {
			getScore(upCheckListSize);
			// for (int row = x - upCheckListSize + 1; row < x; row++) {
			// board.setPlanetAt(row, y, null);
			// }
			return true;
		}
		return false;
	}

	private boolean checkIfLeftMatch(int x, int y) {
		int counter = 0;
		while (y > 0 && board.getPlanetAt(x, y).getType() == board.getPlanetAt(x, y - 1).getType()) {
			y--;
			counter++;
		}
		if (checkCounter(counter)) {
			getScore(counter);
			for (int col = y - counter + 1; col < y; col++) {
				board.setPlanetAt(x, col, null);
			}
			return true;
		}
		return false;
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
		// while (y < 5 && board.getPlanetAt(x, y).getType() ==
		// board.getPlanetAt(x, y + 1).getType()) {
		// y++;
		// counter++;
		// }
		// if (checkCounter(counter)) {
		// getScore(counter);
		// for (int col = y - counter + 1; col < y; col++) {
		// board.setPlanetAt(x, col, null);
		// }
		// return true;
		// }
		// return false;
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

	private static boolean isMoveLegal(int currx, int curry, int x, int y) {
		if (Math.abs(currx - x) == 1) {
			if (curry == y) {
				return true;
			}
		} else if (Math.abs(curry - y) == 1) {
			if (currx == x) {
				return true;
			}
		}
		return false;

	}

	public int getScore(int counter) {

		// GameBoard.getlblScoreHolder().setText("" + counter*10);
		return score;
	}

	public static void checkStartBoard(GameBoard board) {
		for (int x = 1; x < GameBoard.getBoard_height(); x++) {
			for (int y = 0; y < GameBoard.getBoard_width(); y++) {
				if (board.getPlanetAt(x, y).getType() == board.getPlanetAt(x - 1, y).getType()) {

				}
			}
		}

	}

	public void swapElements(int currX, int currY, int x, int y) {
		Figure currFigure = board.getPlanetAt(currX, currY);
		Figure nextFigure = board.getPlanetAt(x, y);
		board.setPlanetAt(currX, currY, nextFigure);
		board.setPlanetAt(x, y, currFigure);

	}

}
