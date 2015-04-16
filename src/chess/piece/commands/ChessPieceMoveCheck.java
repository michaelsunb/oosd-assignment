package chess.piece.commands;

import chess.core.Game;

public class ChessPieceMoveCheck {
	
	public static boolean checkPieceAgainstPlayer(int currPos){
		if(Game.getInstance().getBoardInstance().getPiece(currPos).getOwner() != Game.getInstance().getCurrentPlayer())
			return false;
		else
			return true;
	}

	public static boolean checkMoveValidity(int currPos, int targetPos){
		boolean moveValidity = false;
		
		for(int i :Game.getInstance().getBoardInstance().getPiece(currPos).getMovablePositions(currPos)){
			if(i == targetPos){
				moveValidity = true;
			}
		}
		
		if(targetPos > Game.getInstance().getBoardInstance().getPieces().length){
			System.out.println("Out of bounds");
			moveValidity = false;
		}
		
		if(currPos == targetPos){
			System.out.println("Trying to move to yourself.");
			moveValidity = false;
		}
		
		return moveValidity;
	}
	
	
}
