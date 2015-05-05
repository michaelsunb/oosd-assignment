package chess.prototype.events;

import java.awt.Container;

import javax.swing.JMenuBar;

import chess.mvc.views.MainFrame;

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