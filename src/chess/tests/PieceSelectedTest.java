package chess.tests;

import static org.junit.Assert.*;

import org.junit.*;
import chess.core.*;
import chess.prototype.commands.*;
import chess.prototype.observer.*;

public class PieceSelectedTest {
	private ChessEventDispatcher eventMgr;
	private PieceSelectedCommand command;
	
	@Before
	public void setUp() throws Exception {
		//
		Game.getInstance().reset(10);
		eventMgr = ChessEventDispatcher.getInstance();
		
		command = new PieceSelectedCommand();
		
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
