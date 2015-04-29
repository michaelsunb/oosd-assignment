package chess.mvc.controllers;

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
