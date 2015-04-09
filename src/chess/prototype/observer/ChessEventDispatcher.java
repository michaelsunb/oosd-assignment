/*
 * Author: Sokun CHORN
 * Student Number: s3338291
 */
package chess.prototype.observer;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class ChessEventDispatcher {
	private static ChessEventDispatcher instance = null;
	
	private Map<String, ArrayList<IObserver>> services;
	
	private ChessEventDispatcher()
	{
		super();
		services = new Hashtable<String, ArrayList<IObserver>>();
	}
	
	public static ChessEventDispatcher getInstance() {
		if (instance == null)
		{
			instance = new ChessEventDispatcher();
		}
		return instance;
	}
	
	
	public void addListener(String eventName, IObserver observer) {
		if (!this.services.containsKey(eventName)) {
			this.services.put(eventName, new ArrayList<IObserver>());
		}
		/*
		 * TODO: avoid register an observer to the same event multiple times
		 */
		this.services.get(eventName).add(observer);
	}
	
	public void removeListener(String eventName, IObserver observer) {
		/*
		 * TODO: remove an IObserver from listener list
		 */
	}

	/*
	 * e.g .notifyListeners(new PieceMovedEvent(...,...));
	 */
	public void fireEvent(ChessEvent event) {
		String eventName = event.getClass().getSimpleName();
		
		/*
		 * Nothing to notify
		 */
		if (!this.services.containsKey(eventName)) {
			return;
		}
		
		/*
		 * Only notify objects interested in @eventName 
		 */
		for(IObserver observer: this.services.get(eventName)) {
			observer.update(event);
		}
		
	}
}
