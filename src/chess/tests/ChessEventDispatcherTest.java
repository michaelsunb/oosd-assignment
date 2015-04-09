package chess.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import chess.prototype.observer.ChessEvent;
import chess.prototype.observer.ChessEventDispatcher;
import chess.prototype.observer.IObserver;
import chess.prototype.observer.PieceMovedEvent;

public class ChessEventDispatcherTest {
	
	@Test
	public void fire_an_event() {
		// arrange
		ChessEventDispatcher dispatcher = ChessEventDispatcher.getInstance();
		SimpleObserver listener1 = new SimpleObserver();
		String eventName = PieceMovedEvent.class.getSimpleName();
		
		dispatcher.addListener(eventName, listener1);
		
		// act
		dispatcher.fireEvent(new PieceMovedEvent());
		
		// assert
		assertEquals("SimpleObserver.update(..) method get called", "changed", listener1.getName());
	}

	
	class SimpleObserver implements IObserver {

		private String name;
		
		@Override
		public void update(ChessEvent event) {
			this.name = "changed";
		}
		
		public String getName() {
			return this.name;
		}
	}
}
