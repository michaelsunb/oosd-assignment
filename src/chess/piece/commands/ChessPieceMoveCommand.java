package chess.piece.commands;

import chess.core.Game;
import chess.prototype.observer.ChessEvent;
import chess.prototype.observer.IObserver;
import chess.prototype.observer.PieceMovedEvent;

public class ChessPieceMoveCommand implements IObserver{
	
	@Override
	public void update(ChessEvent event) {
		// TODO Auto-generated method stub
		if(event instanceof PieceMovedEvent){
			
			int currPos = ((PieceMovedEvent)event).getOldPosition();
			int targetPos = ((PieceMovedEvent)event).getNewPosition();
			System.out.println("trying to move");
			if(ChessPieceMoveCheck.checkMoveValidity(currPos, targetPos)){
				System.out.println("moved");
				Game.getInstance().getBoardInstance().getPieces()[targetPos] = Game.getInstance().getBoardInstance().getPiece(currPos);
				Game.getInstance().getBoardInstance().getPieces()[currPos] = null;
			}
		}
	}
}
