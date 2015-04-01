package chess.prototype.observer;

import java.util.Observable;
import java.util.Observer;

import chess.core.Board;
import chess.core.IBoard;
import model.Square;

public class CollisionDisplay implements Observer, DisplayElement {
	
	private String pieceColour = "black";

	private boolean merge;
	private boolean split;
	private boolean kill;

	public CollisionDisplay(Observable observable) {
		observable.addObserver(this);
	}

	public void update(Observable observable, Object arg) {
		if (observable instanceof Board) {
			IBoard chessData = (IBoard)observable;

			// TODO for now just use this
			int nextPosition = chessData.getNextPosition();
			Square[] square = chessData.getBoard();
			String player = chessData.getPlayer();
			kill = false;
			split = false;
			merge = false;
			if(player != pieceColour) {
				kill = true;
			} else {
				if(square[nextPosition] == (new Square())) {
					merge = false;
				} else {
					split = false;
				}
			}
			display();
		}
	}

	public void display() {
		System.out.print("Piece was ");
		if(merge) {
			System.out.println("merged.");
		} else if (split) {
			System.out.println("split.");
		} else if (kill) {
			System.out.println("killed.");
		} else {
			System.out.println("moved.");
		}
	}
}
