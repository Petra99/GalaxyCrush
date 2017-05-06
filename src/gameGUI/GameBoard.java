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
		Figure[][] board = new Figure[BOARD_HEIGHT][BOARD_WIDTH];
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
		for (int x = GameBoard.getBoard_height() - 1; x >= 0; x--) {
			for (int y = GameBoard.getBoard_width() - 1; y >= 0; y--) {
				if (figureArray[x][y] == null) {
					do {
						figureArray[x][y] = figureArray[x - 1][y];
						figureArray[x - 1][y] = null;
						x--;
					} while (x != 0);
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

	public void runBoard(GameBoard board) {
		gameWindow.runWindow();
		gameWindow.setBoard(board);
		gameWindow.showBoard(board);
	}

	public static int getBoard_width() {
		return BOARD_WIDTH;
	}

	public static int getBoard_height() {
		return BOARD_HEIGHT;
	}

	public void print() {
		for (int i = 0; i < figureArray.length; i++) {
			for (int j = 0; j < figureArray.length; j++) {
				System.out.print(figureArray[i][j].getType() );
			}
			System.out.println();
		}
		
	}

}
