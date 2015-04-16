package chess.prototype.observer;

import java.awt.Container;

import javax.swing.JMenuBar;

public class NewGameEvent implements ChessEvent {
	private Container contentPane;
	private JMenuBar menuBar;

	public NewGameEvent(Container contentPane, JMenuBar menuBar) {
		super();
		this.contentPane = contentPane;
		this.menuBar = menuBar;
	}
	
	public Container getContainer() {
		return contentPane;
	}
	
	public final JMenuBar getMenuBar() {
		return menuBar;
	}
}