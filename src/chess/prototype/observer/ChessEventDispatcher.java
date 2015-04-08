/*
 * Author: Sokun CHORN
 * Student Number: s3338291
 */
package chess.prototype.observer;

import java.util.ArrayList;

/*
 * Singleton Pattern
 */
public class ChessEventDispatcher {
	private static ChessEventDispatcher instance = null;
	private ArrayList<ChessEvent> listeners;
	
	private ChessEventDispatcher()
	{
		super();
		this.listeners = new ArrayList<ChessEvent>();
	}
	
	public static ChessEventDispatcher getInstance() {
		if (instance == null)
		{
			instance = new ChessEventDispatcher();
		}
		return instance;
	}
	
	
	public void subscribe(ChessEvent listener) {
		this.listeners.add(listener);
	}
	
	public void unsubscribe(ChessEvent listener) {
		this.listeners.remove(listener);
	}

	
}
