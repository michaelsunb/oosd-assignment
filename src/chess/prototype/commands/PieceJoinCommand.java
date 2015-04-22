/*
 * Author: Siang Ling
 * Number: s3295217
 */
package chess.prototype.commands;

import chess.core.*;
import chess.mvc.models.PieceCapturedEvent;
import chess.mvc.models.PieceJoinEvent;
import chess.prototype.composite.*;
import chess.prototype.observer.*;

public class PieceJoinCommand extends CommandBase {

	@Override
	public void update(ChessEvent event) {
		if (!(event instanceof PieceJoinEvent)) return;
		
		PieceCombined((PieceJoinEvent) event);
	}

	public void PieceCombined(PieceJoinEvent event) {
		Piece curPiece = event.getCurrentPiece();
		Piece tarPiece = event.getAugmentPiece();
		int position = event.getJoinPosition();

		CombinePiece piece = new CombinePiece();
		piece.add(curPiece);
		piece.add(tarPiece);

		piece.setOwner(curPiece.getOwner());

		board.setPiece(position, piece);
	}

}
