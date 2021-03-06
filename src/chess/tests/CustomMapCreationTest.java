package chess.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import chess.core.Board;
import chess.core.IBoard;
import chess.core.Player;
import chess.prototype.composite.Barrier;
import chess.prototype.composite.Bishop;
import chess.prototype.composite.Knight;
import chess.prototype.composite.Rook;
import chess.prototype.decorator.GameMapDecorator;

public class CustomMapCreationTest extends GameTestBase {
	@Before
	public void setUp() throws Exception {
		this.getGame();
	}
	
	@Test
	public void decorate_chessboard_using_map() {
		ArrayList<String> userInput = new ArrayList<String>();
		userInput.add("RBKRBKBR");
		userInput.add("SSSSSSSS");
		userInput.add("SSSSSSSS");
		userInput.add("XXXXXXXX");
				
		new GameMapDecorator(this.getBoard(), userInput).init();
			
		//assert correct piece at correct place
		assertEquals(new Rook().getClass(), this.getBoard().getPiece(0).getClass());
		assertEquals(new Bishop().getClass(), this.getBoard().getPiece(4).getClass());
		assertEquals(new Knight().getClass(), this.getBoard().getPiece(5).getClass());
		
		//mirroring done correctly
		assertEquals(new Rook().getClass(), this.getBoard().getPiece(56).getClass());
		assertEquals(new Bishop().getClass(), this.getBoard().getPiece(57).getClass());
		assertEquals(new Knight().getClass(), this.getBoard().getPiece(61).getClass());
		
		//correct owners
		assertEquals(this.getGame().getPlayer(2), this.getBoard().getPiece(61).getOwner());
		assertEquals(null, this.getBoard().getPiece(24).getOwner());
		assertEquals(this.getGame().getPlayer(1), this.getBoard().getPiece(0).getOwner());
		
		//nulls and barriers placed correctly
		assertEquals(null, this.getBoard().getPiece(8));
		assertEquals(null, this.getBoard().getPiece(23));
		assertEquals(null, this.getBoard().getPiece(40));
		assertEquals(null, this.getBoard().getPiece(55));
		assertEquals(new Barrier().getClass(), this.getBoard().getPiece(24).getClass());
		assertEquals(new Barrier().getClass(), this.getBoard().getPiece(39).getClass());
	}
	
	@Test
	public void map_validation() {
		ArrayList<String> userInput = new ArrayList<String>();
		userInput.add("RBKRBKBR");
		userInput.add("SSSSSSSS");
		userInput.add("SSSSSSSS");
		userInput.add("XXXXXXXX");
		
		ArrayList<String> userInput2 = new ArrayList<String>();
		userInput2.add("RBKRBKBR");
		userInput2.add("SSSSSSSS");
		userInput2.add("SSSSSSSS");
		userInput2.add("XXXXXXXX");
		userInput2.add("XXXXXXXX");
		
		//mapsquarevalidity function checks
		assertTrue("A valid chessboard", 
				GameMapDecorator.gameSquareMapValidity(userInput));
	}

}
