package gameGUI;

import gameGUI.GameWindow;

public class GameBoard {

	private GameWindow gameWindow = new GameWindow(getBoard_width(), getBoard_height());
	private static final int BOARD_WIDTH = 6;
	private static final int BOARD_HEIGHT = 6;
	public Figure[][] figureArray;

	public GameWindow getGameWindow() {
		return gameWindow;
	}

	private GameFigures figures = new GameFigures();

	public GameBoard() {
		figureArray = generateBoard();
	}

	private Figure[][] generateBoard() {
		Figure[][] board = new Figure[BOARD_WIDTH][BOARD_HEIGHT];
		for (int x = 0; x < board[0].length; x++) {
			for (int y = 0; y < board.length; y++) {
				board[x][y] = figures.getRandomFigure();
			}
		}
		return board;
	}

	public void refill() {
		for (int x = GameBoard.getBoard_height() - 1; x >= 0; x--) {
			for (int y = GameBoard.getBoard_width() - 1; y >= 0; y--) {
				if (figureArray[x][y] == null) {
					figureArray[x][y] = figures.getRandomFigure();
				}
			}
		}

	}

	public void reorder() {
		for (int y = GameBoard.getBoard_height() - 1; y >= 0; y--) {
			for (int x = 0; x < GameBoard.getBoard_width(); x++) {
				int y2 = y;
				if (figureArray[x][y2] == null  && y2 > 0) {
					do {
						figureArray[x][y2] = figureArray[x][y2 - 1];
						figureArray[x][y2 - 1] = null;
						y2--;
					} while (y2 != 0);
				}
			}
		}
	}

	public Figure getPlanetAt(int x, int y) {
		return figureArray[x][y];
	}

	public void setPlanetAt(int x, int y, Figure figure) {
		figureArray[x][y] = figure;
	}

	public void runBoard() {
		gameWindow.runWindow();
		gameWindow.setBoard(this);
	}
	
	public void updateBoard() {
		gameWindow.showBoard(this);
	}

	public static int getBoard_width() {
		return BOARD_WIDTH;
	}

	public static int getBoard_height() {
		return BOARD_HEIGHT;
	}

	public void print() {
		for (int y = 0; y < BOARD_HEIGHT; y++) {
			for (int x = 0; x < BOARD_WIDTH; x++) {
				if(figureArray[x][y] == null) {
					System.out.print("X");
				} else {
					System.out.print(figureArray[x][y].getType() );
				}
			}
			System.out.println();
		}
		System.out.println("-----------------------------------------------");
	}

}
