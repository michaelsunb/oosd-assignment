package chess.mvc.controllers;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import chess.mvc.models.GameNewEvent;
import chess.prototype.observer.ChessEvent;
import chess.prototype.observer.ChessEventDispatcher;

public abstract class ActionController extends AbstractAction {
	private final ChessEventDispatcher eventManager;
	protected ChessEvent event;
	
	public ActionController(ChessEventDispatcher eventMgr) {
		this.eventManager = eventMgr;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		eventManager.fireEvent(event);
	}
}
