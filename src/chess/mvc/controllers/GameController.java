package chess.mvc.controllers;

import chess.core.Game;
import chess.core.IBoard;
import chess.mvc.views.MainFrame;
import chess.prototype.commands.NewGameCommand;
import chess.prototype.commands.PieceCapturedCommand;
import chess.prototype.commands.PieceJoinCommand;
import chess.prototype.commands.PieceMovedCommand;
import chess.prototype.commands.PieceSelectedCommand;
import chess.prototype.commands.PieceSplitCommand;
import chess.prototype.momento.UndoCommand;
import chess.prototype.observer.IObserver;

public class GameController {

	private MainFrame view;

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
}
