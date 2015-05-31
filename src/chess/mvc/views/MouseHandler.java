package chess.mvc.views;

import java.awt.event.*;
import javax.swing.SwingUtilities;
import chess.core.*;
import chess.mvc.controllers.GameController;
import chess.mvc.models.*;
import chess.prototype.composite.*;
import chess.prototype.observer.*;

public class MouseHandler extends MouseAdapter {
	private final Game game;
	private int position;
	private GameController controller;
	
	public MouseHandler(int pos, GameController controller) {
		this.game = Game.getInstance();
		this.position = pos;
		this.controller = controller;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		ChessEvent event = null;

		if (SwingUtilities.isRightMouseButton(e)) {
			event = createMoveEvent();
		} else if (SwingUtilities.isLeftMouseButton(e)) {
			event = new PieceSelectedEvent(this.position, controller.getView());
		}

		if (event == null) return;
		
		ChessEventDispatcher.getInstance().fireEvent(event);
	}

	private ChessEvent createMoveEvent() {
		ChessEvent event;
		int selPos = game.getSelPosition();
		Piece piece = game.getBoardInstance().getPiece(selPos);

		if (piece == null) return null;
		
		int current = game.getBoardInstance().getPiecePosition(piece);
		
		if (!piece.canMoveTo(current, this.position)) return null;
		
		PieceViewPanel pvp = controller.getView().getPieceViewPane();

		if (pvp.needSplit()) {
			event = new PieceSplitEvent((CombinePiece)piece, 
					pvp.getSelectedPieces(), this.position);
		} else {
			event = new PieceMovedEvent(current, position);
		}
		
		return event;
	}

}