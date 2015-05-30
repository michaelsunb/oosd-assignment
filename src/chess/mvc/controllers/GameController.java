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

<<<<<<< HEAD
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import chess.core.ChessEventDispatcher;
import chess.mvc.views.*;
import chess.prototype.events.*;
import chess.prototype.events.listener.EventListener;
import chess.prototype.events.listener.NewGameEventListener;
import chess.prototype.events.listener.PieceSelectedEventListener;

public class GameController extends AbstractAction {
	private MainFrame view;
	private ChessEventDispatcher eventMgr = ChessEventDispatcher.getInstance();
	
	public GameController() {
	}
	
	public void init(MainFrame view) {
		this.view = view;
		this.view.setVisible(true);
	}
	
	public EventListener newGame() {
		return new NewGameEventListener();
	}
	
	public EventListener pieceSelected() {
		return new PieceSelectedEventListener();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// How can we intercept all command and dispatch it as event
		// May be we can use factory pattern to create different event
		switch(e.getActionCommand()) {
		case "NewGameEvent":
			eventMgr.fireEvent(new GameNewEvent(view));
			break;
			default:
				this.view.setTitle(e.getActionCommand());
		}
	}
}
=======
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
>>>>>>> origin/stabilize-part-2
