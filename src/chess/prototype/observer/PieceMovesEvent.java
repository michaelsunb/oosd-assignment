/*
 * Author: Michaelsun BALUYOS
 * Student Number: s3110401
 */
package chess.prototype.observer;

import java.awt.Color;
import java.awt.Component;
import chess.core.Piece;

public class PieceMovesEvent implements ChessEvent {
	private Piece piece;
	private Component component;
	private int position;
	
	public PieceMovesEvent(int currentPosition, Piece piece,
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
	
	public void displayMoves() {

		if(piece != null) {
			int movablePos[] = piece.getMovablePositions(position);

			for(int square : movablePos) {
				Component movableSource = component.getParent().getComponent(square);
				movableSource.setBackground(Color.RED);
			}
		}
	}
}
