package chess.mvc.views;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import chess.core.Game;
import chess.core.Piece;
import chess.mvc.controllers.GameController;
import chess.mvc.models.PieceMovedEvent;
import chess.mvc.models.PieceSelectedEvent;
import chess.prototype.observer.ChessEventDispatcher;

public class MouseHandler extends MouseAdapter {
	private int position;
	private GameController controller;
	
	public MouseHandler(int pos, GameController controller) {
		this.position = pos;
		this.controller = controller;
	}

	public void mousePressed(MouseEvent e) {
		if (SwingUtilities.isRightMouseButton(e)) {
			Piece piece = Game.getInstance().getSelectedPiece();
			if (piece == null) return;
			
			int current = Game.getInstance().getBoardInstance().getPiecePosition(piece);
			
			if (!piece.canMoveTo(current, this.position)) return;
			
			// TODO if we detect that user has select piece in (PieceViewPanel) instead of fire PieceMovedEvent
			// fire PieceSplitEvent. We might need to retrieve selected Piece from the JList box
			PieceMovedEvent movedEvent = new PieceMovedEvent(current, position);
			ChessEventDispatcher.getInstance().fireEvent(movedEvent);
			
		} else if (SwingUtilities.isLeftMouseButton(e)) {
			PieceSelectedEvent selEvent = new PieceSelectedEvent(this.position, controller.getView());
			ChessEventDispatcher.getInstance().fireEvent(selEvent);
		}
	}

}