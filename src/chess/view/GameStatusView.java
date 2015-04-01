package chess.view;

import java.util.Observable;
import java.util.Observer;

import chess.core.Board;
import chess.prototype.observer.DisplayElement;

public class GameStatusView implements Observer, DisplayElement {
	private String currentPlayer;

	public GameStatusView(Observable observable) {
		observable.addObserver(this);
	}

	public void update(Observable observable, Object arg) {
		if (observable instanceof Board) {
			//
			display();
		}
	}

	public void display() {
		System.out.println("Player: " + currentPlayer);
	}
}
