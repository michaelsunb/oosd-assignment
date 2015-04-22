/*
 * Author: Siang Ling
 * Number: s3295217
 */
package chess.prototype.commands;

import java.awt.*;
import chess.core.*;
import chess.mvc.models.*;
import chess.prototype.observer.*;

public class PieceMovedCommand extends CommandBase {

	@Override
	public void update(ChessEvent event) {
		if (!(event instanceof PieceMovedEvent)) return;

		PieceMoved((PieceMovedEvent) event);
	}

	public void PieceMoved(PieceMovedEvent event) {
		int targetPos = event.getNewPosition();
		Piece piece = event.getPreviousPiece();
		
		// can't move empty piece
		if (piece == null) return;
		/*
		event.setPreviousPiece(piece);

		int[] allMovableSquares = piece.getMovablePositions(currPos);

		for (int i : allMovableSquares) {
			if (i == targetPos) {
				ChessEvent pieceAction = event.returnAction();

				if (pieceAction == null)
					return;

				if (pieceAction instanceof PieceMovedEvent) {
					board.getPieces()[targetPos] = piece;
				} else {
					eventMgr.fireEvent(pieceAction);
				}

				board.getPieces()[currPos] = null;
				Game.getInstance().swapPlayer();
				break;
			}
		}
		*/
	}
	
	

}
