/*
 * Author: Sokun CHORN
 * Student Number: s3338291
 */
package chess.core;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import chess.prototype.events.ChessEvent;
import chess.prototype.events.listener.EventListener;

public class ChessEventDispatcher {
	private static ChessEventDispatcher instance = null;

	private Map<String, ArrayList<EventListener>> services;

	private ChessEventDispatcher() {
		super();
		services = new Hashtable<String, ArrayList<EventListener>>();
	}

	public static ChessEventDispatcher getInstance() {
		if (instance == null) {
			instance = new ChessEventDispatcher();
		}
		return instance;
	}

	public void addListener(String eventName, EventListener listener) {
		if (!this.services.containsKey(eventName)) {
			this.services.put(eventName, new ArrayList<EventListener>());
		}

		for (EventListener oldListener : this.services.get(eventName)) {
			if(oldListener.equals(listener)) {
				oldListener = listener; // replace for now
				return;
			}
		}

		this.services.get(eventName).add(listener);
	}

	public void removeListener(String eventName, EventListener listener) {
		/*
		 * TODO: remove an IObserver from listener list
		 */
		if (!this.services.containsKey(eventName)) {
			return;
		}
		this.services.get(eventName).remove(listener);
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
		for (EventListener listener : this.services.get(eventName)) {
			listener.update(event);
		}

	}
}
