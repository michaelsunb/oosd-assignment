package chess.prototype.observer;

import java.util.Observable;
import java.util.Observer;

import chess.core.Board;
import chess.core.IBoard;
	
public class LegalMoveDisplay implements Observer, DisplayElement {
	private int prevPos;
	private int nextPos;

	private boolean isLegal;
	
	public LegalMoveDisplay(Observable observable) {
		observable.addObserver(this);
	}
	
	public void update(Observable obs, Object arg) {
		if (obs instanceof Board) {
			IBoard chessData = (IBoard)obs;
			this.prevPos = chessData.getPrevPosition();
			this.nextPos = chessData.getNextPosition();
			// TODO detect if move is legal
			isLegal = true;
			if(prevPos == nextPos) {
				isLegal = false;
			}
			display();
		}
	}
	
	public void display() {
		System.out.print("Position " + prevPos 
			+ " moves to " + nextPos + " is ");

		if(isLegal) {
			System.out.println(" valid. (Display Green)");
		} else {
			System.out.println(" invalid. (Display Red)");
		}
	}
}
