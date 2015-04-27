package chess.prototype.commands;

import chess.mvc.models.PieceDelegateMovedEvent;


public interface PieceCommandDelegate {
	public boolean delegateCommand(PieceDelegateMovedEvent event);
}
