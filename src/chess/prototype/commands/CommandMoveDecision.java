package chess.prototype.commands;

import chess.mvc.models.PieceMovedEvent;


public interface CommandMoveDecision {
	public void update(PieceMovedEvent event);
	public boolean commandMoveDecision(PieceMovedEvent event);
}
