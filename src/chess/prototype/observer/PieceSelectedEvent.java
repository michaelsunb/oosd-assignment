/*
 * Author: Michaelsun BALUYOS
 * Student Number: s3110401
 */
package chess.prototype.observer;

import java.awt.Component;
import chess.core.Piece;

public class PieceSelectedEvent implements ChessEvent {
	private Piece piece;
	private Component component;
	private int position;
	
	public PieceSelectedEvent(int currentPosition, Piece piece,
			Component component) {
		this.position = currentPosition;
		this.piece = piece;
		this.component = component;
	}

	public final Piece getPiece() {
		return piece;
	}

	public final Component getComponent() {
		return component;
	}

	public final int getPosition() {
		return position;
	}
}
