package chess.prototype.observer;

import chess.core.Piece;
import chess.core.Player;

public class GameStatusEvent implements ChessEvent {
	
	private Player curPlayer;
	private Piece curPiece;
	private int numberOfMoves;

	public GameStatusEvent(Player tarPlayer, Piece curPiece, int numberOfMoves) {
		super();
		this.curPlayer = tarPlayer;
		this.curPiece = curPiece;
		this.numberOfMoves = numberOfMoves;
	}

	public final Player getTargetPiece() {
		return curPlayer;
	}

	public final Piece getCurPiece() {
		return curPiece;
	}

	public final int getNumberOfMoves() {
		return numberOfMoves;
	}
}
