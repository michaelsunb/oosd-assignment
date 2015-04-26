package chess.prototype.commands;

import chess.mvc.models.PieceMovedEvent;


public interface CommandMoveDecision {
	public boolean commandMoveDecision(PieceMovedEvent event);
}
