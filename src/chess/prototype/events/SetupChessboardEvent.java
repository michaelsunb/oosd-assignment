/*
 * Author: Michaelsun BALUYOS
 * Student Number: s3110401
 */
package chess.prototype.events;

import java.util.ArrayList;

import chess.core.Piece;

/*
 * TODO: May be we can pass board->getPieces() to UI component (ChessboardViewPanel) so it can draw the board
 */
public class SetupChessboardEvent implements ChessEvent {
	private ArrayList<Piece> pieces;

	public SetupChessboardEvent(ArrayList<Piece> pieces) {
		super();
		this.pieces = pieces;
	}

	public ArrayList<Piece> getPieces() {
		return pieces;
	}

}