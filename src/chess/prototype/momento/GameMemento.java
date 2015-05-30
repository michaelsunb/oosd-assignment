package chess.prototype.momento;

import java.io.Serializable;

import chess.core.Piece;


public class GameMemento implements Serializable
{
	private final int oldPosition;
	private final int newPosition;

	private final Piece oldPiece;
	private final Piece newPiece;

	private final Integer[] numberOfMove;
	private final Integer[] playerScore;
	private final Boolean[] playerTurns;

	public GameMemento(int oldPosition,int newPosition,
			Piece oldPiece, Piece newPiece,
			Integer[] playerScores, Integer[] numberOfMove,
			Boolean[] playerTurns) {
		this.oldPosition = oldPosition;
		this.newPosition = newPosition;
		this.oldPiece = oldPiece;
		this.newPiece = newPiece;
		this.playerScore = playerScores;
		this.numberOfMove = numberOfMove;
		this.playerTurns = playerTurns;
	}

	public final int getNewPosition() {
		return newPosition;
	}

	public final int getOldPosition() {
		return oldPosition;
	}

	public final Piece getOldPiece() {
		return oldPiece;
	}

	public final Piece getNewPiece() {
		return newPiece;
	}

	public Boolean[] getPlayerTurns() {
		return playerTurns;
	}

	public Integer[] getPlayerScore() {
		return playerScore;
	}

	public Integer[] getNumberOfMove() {
		return numberOfMove;
	}
}