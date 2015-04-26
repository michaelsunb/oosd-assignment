/*
 * Author: Siang Ling
 * Number: s3295217
 */
package chess.prototype.commands;

import chess.core.Piece;
import chess.mvc.models.PieceMovedEvent;
import chess.prototype.composite.CombinePiece;
import chess.prototype.observer.ChessEvent;

public class PieceJoinCommand extends CommandBase implements CommandMoveDecision {

	@Override
	public void update(ChessEvent event) {
		if (!(event instanceof PieceMovedEvent)) return;
		if (!commandMoveDecision((PieceMovedEvent) event)) return;
		
		pieceCombined((PieceMovedEvent) event);
	}

	public void pieceCombined(PieceMovedEvent event) {
		int newPos = event.getNewPosition();
		int oldPos = event.getPreviousPosition();

		Piece pieceSelected = board.getPiece(oldPos);
		Piece pieceTarget = board.getPiece(newPos);

		CombinePiece piece = new CombinePiece();
		piece.add(pieceSelected);
		piece.add(pieceTarget);

		piece.setOwner(pieceSelected.getOwner());

		board.setPiece(newPos, piece);
		board.setPiece(oldPos, null);
	}

	@Override
	public boolean commandMoveDecision(PieceMovedEvent event) {
		if(!isSelectedPieceValid(event)) return false;

		for(int i : allMovableSquares) {
			if (i == newPosition) {
				if(selectedOwner == targetOwner) {
					return true;
				}
			}
		}

		return false;
	}
}
