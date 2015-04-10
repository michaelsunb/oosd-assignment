/*
 * Author: Sokun, CHORN
 * Student Number: S3455783
 */
package chess.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import chess.prototype.composite.*;
import chess.prototype.observer.*;

public class ChessEventDispatcherTest {
	
	@Test
	public void fire_an_event() {
		// arrange
		ChessEventDispatcher dispatcher = ChessEventDispatcher.getInstance();
		SimpleObserver listener1 = new SimpleObserver();
		String eventName = PieceMovedEvent.class.getSimpleName();
		
		dispatcher.addListener(eventName, listener1);
		PieceMovedEvent movedEvent = new PieceMovedEvent(1, 2, new Rook());
		
		// act
		dispatcher.fireEvent(movedEvent);
		
		// assert
		assertEquals(
				"SimpleObserver.update(..) method get called", 
				movedEvent.toString(), listener1.getTestString());
	}
	
	class SimpleObserver implements IObserver {

		private String testString;
		
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
