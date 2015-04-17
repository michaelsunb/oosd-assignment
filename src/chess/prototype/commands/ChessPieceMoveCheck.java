/*
 * Author: Siang Ling
 * Number: s3295217
 */
package chess.prototype.commands;

import java.util.Arrays;

import chess.core.*;
/*
 * Sokun's comment: this class is so anti-pattern, 
 * we need to add appropriate methods to responsible class follow the GRASP principle
 */
public class ChessPieceMoveCheck {
	
	public static boolean checkPieceAgainstPlayer(int currPos){
		Game game = Game.getInstance();
		IBoard board = game.getBoardInstance();
		
		return (board.getPiece(currPos).getOwner() != game.getCurrentPlayer()) ? false : true;
	}

	public static boolean checkMoveValidity(int currPos, int targetPos){
		IBoard board = Game.getInstance().getBoardInstance();
		int[] movablePosition = board.getPiece(currPos).getMovablePositions(currPos);
		boolean moveValidity = Arrays.binarySearch(movablePosition, targetPos) >= 0;
		
		if(targetPos > board.getPieces().length){
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
