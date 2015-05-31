package chess.mvc.controllers;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import chess.prototype.observer.ChessEvent;
import chess.prototype.observer.ChessEventDispatcher;

public abstract class ActionController extends AbstractAction {
	private final ChessEventDispatcher eventManager;
	protected ChessEvent event;
	
	public ActionController() {
		this.eventManager = ChessEventDispatcher.getInstance();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		eventManager.fireEvent(event);
	}
}
