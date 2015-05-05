package chess.mvc.controllers;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import chess.mvc.models.GameNewEvent;
import chess.mvc.views.*;
import chess.prototype.commands.*;
import chess.prototype.observer.*;

public class GameController extends AbstractAction {
	private MainFrame view;
	private ChessEventDispatcher eventMgr = ChessEventDispatcher.getInstance();
	
	public GameController() {
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// How can we intercept all command and dispatch it as event
		// May be we can use factory pattern to create different event
		switch(e.getActionCommand()) {
		case "NewGameEvent":
			// TODO: load a UI to take game option
			eventMgr.fireEvent(new GameNewEvent(view));
			break;
			default:
				this.view.setTitle(e.getActionCommand());
		}
	}
}
