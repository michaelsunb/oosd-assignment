/*
 * Author: Sokun CHORN
 * Student Number: s3338291
 */
package chess.core;

import java.util.Hashtable;
import java.util.Map;

import chess.prototype.events.ChessEvent;
import chess.prototype.events.listener.EventListener;

public class ChessEventDispatcher {
	private static ChessEventDispatcher instance = null;

	private Map<String, EventListener> services;

	private ChessEventDispatcher() {
		super();
		services = new Hashtable<String, EventListener>();
	}

	public static ChessEventDispatcher getInstance() {
		if (instance == null) {
			instance = new ChessEventDispatcher();
		}
		return instance;
	}

	public void addListener(String eventName, EventListener listener) {
		this.services.put(eventName, listener);
	}

	public void removeListener(String eventName, EventListener listener) {
		/*
		 * TODO: remove an IObserver from listener list
		 */
		if (!this.services.containsKey(eventName)) return;
		this.services.remove(eventName);
	}

	/*
	 * e.g .notifyListeners(new PieceMovedEvent(...,...));
	 */
	public void fireEvent(ChessEvent event) {
		String eventName = event.getClass().getSimpleName();

		/*
		 * Nothing to notify
		 */
		if (!this.services.containsKey(eventName)) return;

		/*
		 * Only notify objects interested in @eventName
		 */
		this.services.get(eventName).update(event);
	}
}
