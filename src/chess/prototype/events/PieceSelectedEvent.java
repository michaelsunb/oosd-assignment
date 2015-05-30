<<<<<<< HEAD:src/chess/prototype/events/PieceSelectedEvent.java
/*
 * Author: Michaelsun BALUYOS
 * Student Number: s3110401
 */
package chess.prototype.events;

import chess.mvc.views.ChessboardViewPanel;

public class PieceSelectedEvent implements ChessEvent {
	private ChessboardViewPanel chessPane;
	private int position;

	public PieceSelectedEvent(int currentPosition, 
			ChessboardViewPanel component) {
		this.position = currentPosition;
		this.chessPane = component;
	}

	public final ChessboardViewPanel getChessboardViewPanel() {
		return chessPane;
	}

	public final int getPosition() {
		return position;
	}
}
=======
/*
 * Author: Michaelsun BALUYOS
 * Student Number: s3110401
 */
package chess.mvc.models;

import chess.mvc.views.MainFrame;
import chess.prototype.observer.ChessEvent;

public class PieceSelectedEvent implements ChessEvent {
	private MainFrame mainFrame;
	private int position;

	public PieceSelectedEvent(int currentPosition, 
			MainFrame mainFrame) {
		this.position = currentPosition;
		this.mainFrame = mainFrame;
	}

	public final MainFrame getMainFrame() {
		return mainFrame;
	}

	public final int getPosition() {
		return position;
	}
}
>>>>>>> origin/stabilize-part-2:src/chess/mvc/models/PieceSelectedEvent.java
