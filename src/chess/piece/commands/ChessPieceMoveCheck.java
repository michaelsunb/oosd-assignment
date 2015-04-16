package chess.piece.commands;

import java.util.Arrays;

import chess.core.Game;

public class ChessPieceMoveCheck {

	public static boolean checkMoveValidity(int currPos, int targetPos){
		boolean moveValidity = false;
		
		if(targetPos > Game.getInstance().getBoardInstance().getPieces().length){
			moveValidity = false;
		}
		if(Game.getInstance().getBoardInstance().getPiece(currPos) == null){
			System.out.println("this piece tried to move: "+ Game.getInstance().getBoardInstance().getPiece(currPos).toString());
			System.out.println("null: "+ currPos);
		}
		for(int i :Game.getInstance().getBoardInstance().getPiece(currPos).getMovablePositions(currPos)){
			if(i == targetPos){
				moveValidity = true;
			}
		}
		
		return moveValidity;
	}
	
	public static boolean checkTargetSquareIfEmpty(int pos){
		if(Game.getInstance().getBoardInstance().getPiece(pos) == null){
			return true;
		}
		else{
			return false;
		}
	}
}
