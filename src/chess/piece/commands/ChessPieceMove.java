package chess.piece.commands;

import chess.core.Game;
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
			
			int[] allMovableSquares = tempEvent.getPiece().getMovablePositions(currPos);
			
			for(int i :allMovableSquares){
				if(i == targetPos){
					
				}
			}

			
//			if(currPos == targetPos){
//				System.out.println("Trying to move to yourself.");
//				moveValidity = false;
//			}
//			
//			return moveValidity;
			
			
			
//			if( ChessPieceMoveCheck.checkPieceAgainstPlayer(currPos)){

			
				Game.getInstance().getBoardInstance().getPieces()[targetPos] = Game.getInstance().getBoardInstance().getPiece(currPos);
				Game.getInstance().getBoardInstance().getPieces()[currPos] = null;
			}
//			}
//			else{
//				System.out.println("selected blank space/curent piece not your own piece.");
//			}
//		}
	}
}
