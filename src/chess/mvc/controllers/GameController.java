package chess.mvc.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import chess.core.Board;
import chess.core.Game;
import chess.core.IBoard;
import chess.core.Piece;
import chess.mvc.models.GameNewEvent;
import chess.mvc.models.PieceMovedEvent;
import chess.mvc.models.PieceSelectedEvent;
import chess.mvc.models.PieceSplitEvent;
import chess.mvc.models.UndoEvent;
import chess.mvc.models.UpdateUIEvent;
import chess.mvc.views.MainFrame;
import chess.prototype.commands.CommandBase;
import chess.prototype.commands.NewGameCommand;
import chess.prototype.commands.PieceCapturedCommand;
import chess.prototype.commands.PieceJoinCommand;
import chess.prototype.commands.PieceMovedCommand;
import chess.prototype.commands.PieceSelectedCommand;
import chess.prototype.commands.PieceSplitCommand;
import chess.prototype.composite.CombinePiece;
import chess.prototype.momento.UndoCommand;
import chess.prototype.observer.ChessEvent;
import chess.prototype.observer.ChessEventDispatcher;
import chess.prototype.observer.IObserver;

public class GameController extends AbstractAction {
	private static final ChessEventDispatcher eventMgr = ChessEventDispatcher
			.getInstance();

	private MainFrame view;

	public GameController() {
	}

	public IBoard getBoard() {
		return Game.getInstance().getBoardInstance();
	}

	public void init(MainFrame view) {
		this.view = view;
		this.view.setVisible(true);
	}

	public IObserver newGame() {
		return new NewGameCommand();
	}

	public IObserver pieceSelected() {
		return new PieceSelectedCommand();
	}

	public IObserver pieceSplit() {
		return new PieceSplitCommand();
	}

	public IObserver pieceMoved() {
		return  new PieceMovedCommand();
	}

	public IObserver pieceCapture() {
		return new PieceCapturedCommand();
	}

	public IObserver pieceJoin() {
		return new PieceJoinCommand();
	}

	public IObserver undoGame() {
		return new UndoCommand();
	}

	public MainFrame getView() {
		return this.view;
	}
	
	// Actions
	public class NewGameAction extends ActionController {
		public NewGameAction() {
			super(eventMgr);
			event = new GameNewEvent(view);
		}
	}

	public class UndoGameAction extends ActionController {
		public UndoGameAction(int numOfReverts) {
			super(eventMgr);
			event = new UndoEvent(numOfReverts);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "PieceSelectedEvent":
			eventMgr.fireEvent(new GameNewEvent(view));
			break;
		default:
			break;
		}
	}
}
