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
