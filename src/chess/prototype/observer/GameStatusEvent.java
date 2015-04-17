package chess.prototype.observer;

import chess.core.Piece;
import chess.core.Player;

public class GameStatusEvent implements ChessEvent {
	
	private Player targetPlayer;
	private int numberOfMoves;

	public GameStatusEvent(Player tarPlayer, int numberOfMoves) {
		super();
		this.targetPlayer = tarPlayer;
		this.numberOfMoves = numberOfMoves;
	}

	public final Player getTargetPiece() {
		return targetPlayer;
	}

	public final int getNumberOfMoves() {
		return numberOfMoves;
	}
}
