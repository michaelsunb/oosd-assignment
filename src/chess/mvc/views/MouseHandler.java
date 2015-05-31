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

	/**
	 * @pre.condition: Instantiate class with inputs of
	 * int and the GameController class
	 * @post.condition: Class is instantiated
	 */
	public MouseHandler(int pos, GameController controller) {
		this.game = Game.getInstance();
		this.position = pos;
		this.controller = controller;
	}

	/**
	 * @pre.condition: Chess board UI must be setup and
	 * user clicks on a sqaure on the board
	 * @post.condition: Mouse pressed state
	 */
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

	/**
	 * @pre.condition: Method above is called and right clicking
	 * on a sqaure.
	 * @post.condition: Returns piece moved/split event or null
	 */
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