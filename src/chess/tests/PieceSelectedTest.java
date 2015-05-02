package chess.tests;

import static org.junit.Assert.*;

import org.junit.*;
import chess.core.*;
import chess.prototype.commands.*;
import chess.prototype.observer.*;

public class PieceSelectedTest extends GameTestBase {
	@Before
	public void setUp() throws Exception {	
		this.eventMgr().removeAll();
		this.eventMgr().addListener("PieceSelectedEvent", new PieceSelectedCommand());
		
		Game.getInstance().reset(10);
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
