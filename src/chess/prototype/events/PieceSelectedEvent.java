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
