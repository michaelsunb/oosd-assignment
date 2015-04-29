package chess.tests;

import static org.junit.Assert.*;

import org.junit.*;

import chess.core.*;
import chess.prototype.events.*;
import chess.prototype.events.listener.PieceSelectedEventListener;

public class PieceSelectedTest {
	private ChessEventDispatcher eventMgr;
	private PieceSelectedEventListener command;
	
	@Before
	public void setUp() throws Exception {
		//
		Game.getInstance().reset(10);
		eventMgr = ChessEventDispatcher.getInstance();
		
		command = new PieceSelectedEventListener();
		
		eventMgr.addListener("PieceSelectedEvent", command);
	}

	@Test
	public void rook_selected() {
		// arrange
		// PieceSelectedEvent event = new PieceSelectedEvent(0, null, null);
		
		// act
		//eventMgr.fireEvent(event);
		
		// assert
		// check all board squares that render in red color
	}

}
