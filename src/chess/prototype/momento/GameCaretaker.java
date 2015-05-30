package chess.prototype.momento;

import java.io.Serializable;
import java.util.Stack;

import chess.core.Game;
import chess.core.Piece;

//adapted by Caspar from http://en.wikipedia.org/wiki/Memento_pattern
//for OOSD sem 1, 2015
public class GameCaretaker implements Serializable
{
	private Stack<GameMemento> savedStates = new Stack<GameMemento>();

	public void addMemento(GameMemento memento) {
		savedStates.push(memento);
		if(savedStates.size() > 3)
			savedStates.removeElementAt(0);
	}

	public GameMemento getMemento() {
		return savedStates.pop();
	}
	
	public int count() {
		return savedStates.size();
	}

	public void test() {
		for(int i = 0; i < this.count(); i++) {
			this.restoreFromMemento(this.savedStates.peek());
			System.out.println("Player 1 turn: " + playerTurns[0]);
			System.out.println("Player 2 turn: " + playerTurns[1]);
			System.out.println("Player 1 score: " + playerScore[0]);
			System.out.println("Player 2 score: " + playerScore[1]);
			System.out.println("Player 1 number of moves: " + numberOfMove[0]);
			System.out.println("Player 2 number of moves: " + numberOfMove[1]);
			System.out.println("Player 1 position: " + newPosition);
			System.out.println("Player 2 position: " + oldPosition);
		}
	}

	private int newPosition;
	private int oldPosition;

	private Boolean[] playerTurns;
	private Integer[] playerScore;
	private Integer[] numberOfMove;

	public void restoreFromMemento(GameMemento momento) {
		this.oldPosition = momento.getOldPosition();
		this.newPosition = momento.getNewPosition();
		this.playerTurns = momento.getPlayerTurns();
		this.playerScore = momento.getPlayerScore();
		this.numberOfMove = momento.getNumberOfMove();
	}
}