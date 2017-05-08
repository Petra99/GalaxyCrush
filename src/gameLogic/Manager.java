package gameLogic;

import gameGUI.GameBoard;
import gameGUI.GameWindow;
import gameGUI.OnUserAction;

public class Manager {

	GameBoard board = new GameBoard();
	GameWindow window = board.getGameWindow();
	Logic logic = new Logic(board);

	OnUserAction listener = new OnUserAction() {

		@Override
		public void onButtonClicked(int x, int y) {
			logic.play(x, y);
			board.updateBoard();
		}
	};

	public void runGame() {
		board.runBoard();
		window.setListener(listener);
	}

}
