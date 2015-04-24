/*
 * Author: Sokun, CHORN
 * Student Number: S3455783
 */
package chess.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import chess.mvc.models.PieceMovedEvent;
import chess.prototype.observer.*;

public class ChessEventDispatcherTest {

	@Test
	public void is_singleton() {
		ChessEventDispatcher dispatcher1 = ChessEventDispatcher.getInstance();
		ChessEventDispatcher dispatcher2 = ChessEventDispatcher.getInstance();

		assertEquals(dispatcher1, dispatcher2);
	}

	@Test
	public void fire_an_event() {
		// arrange
		ChessEventDispatcher dispatcher = ChessEventDispatcher.getInstance();
		SimpleObserver listener1 = new SimpleObserver();
		String eventName = PieceMovedEvent.class.getSimpleName();

		dispatcher.addListener(eventName, listener1);
		PieceMovedEvent movedEvent = new PieceMovedEvent(1,1);

		// act
		dispatcher.fireEvent(movedEvent);

		// assert
		assertEquals("SimpleObserver.update(..) method get called",
				movedEvent.toString(), listener1.getTestString());
	}

	@Test
	public void unregister_event() {
		// arrange
		ChessEventDispatcher dispatcher = ChessEventDispatcher.getInstance();
		SimpleObserver listener1 = new SimpleObserver();
		String eventName = PieceMovedEvent.class.getSimpleName();

		dispatcher.addListener(eventName, listener1);
		PieceMovedEvent movedEvent = new PieceMovedEvent(1,1);

		// act
		dispatcher.removeListener(eventName, listener1);
		dispatcher.fireEvent(movedEvent);

		// assert
		assertEquals("SimpleObserver.update(..) method is not get called", "",
				listener1.getTestString());
	}

	class SimpleObserver implements IObserver {

		private String testString = "";

		@Override
		public void update(ChessEvent event) {
			if (event instanceof PieceMovedEvent) {
				this.testString = event.toString();
			}
		}

		public String getTestString() {
			return this.testString;
		}
	}
}
