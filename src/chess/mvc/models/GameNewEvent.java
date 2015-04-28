package chess.mvc.models;

import java.awt.Container;

import javax.swing.JMenuBar;

import chess.mvc.views.MainFrame;
import chess.prototype.observer.ChessEvent;

public class GameNewEvent implements ChessEvent {
	private MainFrame mainFame;

	public GameNewEvent(MainFrame mainFrame) {
		super();
		this.mainFame = mainFrame;
	}

	public MainFrame getMainFame() {
		return this.mainFame;
	}
}