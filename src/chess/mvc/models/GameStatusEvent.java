package chess.mvc.models;

import chess.core.Player;
import chess.prototype.observer.ChessEvent;

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
