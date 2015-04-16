package chess.piece.commands;

import chess.core.Game;
import chess.core.Piece;
import chess.prototype.observer.ChessEvent;
import chess.prototype.observer.IObserver;
import chess.prototype.observer.PieceMovedEvent;

public class ChessPieceMove implements IObserver{
	
	@Override
	public void update(ChessEvent event) {
		// TODO Auto-generated method stub
		if(event instanceof PieceMovedEvent){
			PieceMovedEvent tempEvent = (PieceMovedEvent)event;
			int currPos = tempEvent.getOldPosition();
			int targetPos = tempEvent.getNewPosition();
			
			Piece piece = tempEvent.getPiece();
			if(piece != null){
				int[] allMovableSquares = tempEvent.getPiece().getMovablePositions(currPos);
				
				for(int i :allMovableSquares){
					if(i == targetPos){
						Game.getInstance().getBoardInstance().getPieces()[targetPos] = Game.getInstance().getBoardInstance().getPiece(currPos);
						Game.getInstance().getBoardInstance().getPieces()[currPos] = null;
					}
				}
			}
		}
	}
}
