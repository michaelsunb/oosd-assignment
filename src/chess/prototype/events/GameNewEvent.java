<<<<<<< HEAD:src/chess/prototype/events/GameNewEvent.java
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
=======
package chess.mvc.models;

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
>>>>>>> origin/stabilize-part-2:src/chess/mvc/models/GameNewEvent.java
}