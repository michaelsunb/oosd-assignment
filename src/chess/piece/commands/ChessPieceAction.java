package chess.piece.commands;

import chess.core.Game;
import chess.core.Piece;
import chess.prototype.composite.CombinePiece;
import chess.prototype.observer.ChessEvent;
import chess.prototype.observer.IObserver;
import chess.prototype.observer.PieceCapturedEvent;
import chess.prototype.observer.PieceJoinEvent;

public class ChessPieceAction implements IObserver {

	@Override
	public void update(ChessEvent event) {
		int position = 0;
		Piece piece = null;
		if(event instanceof PieceCapturedEvent) {
			piece = ((PieceCapturedEvent)event).getCapturedPiece();
			position = ((PieceCapturedEvent)event).getCapturedPosition();
			
		} else if(event instanceof PieceJoinEvent) {
			Piece curPiece = ((PieceJoinEvent)event).getCurrentPiece();
			Piece tarPiece = ((PieceJoinEvent)event).getAugmentPiece();
			position = ((PieceJoinEvent)event).getJoinPosition();

			piece = new CombinePiece();
			
			// act
			((CombinePiece)piece).add(curPiece);
			((CombinePiece)piece).add(tarPiece);
		}
		Game.getInstance().getBoardInstance().getPieces()[position] = piece;
	}

}
