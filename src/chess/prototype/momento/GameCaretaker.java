package chess.prototype.momento;

import java.io.Serializable;
import java.util.Stack;

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
}