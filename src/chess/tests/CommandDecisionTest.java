package chess.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import chess.core.Board;
import chess.core.Piece;
import chess.core.Player;
import chess.mvc.models.PieceMovedEvent;
import chess.prototype.commands.PieceCapturedCommand;
import chess.prototype.commands.PieceJoinCommand;
import chess.prototype.commands.PieceMovedCommand;

public class CommandDecisionTest extends GameTestBase {
	private PieceMovedCommand commandMove;
	private PieceJoinCommand commandJoin;
	private PieceCapturedCommand commandCapture;
	
	@Before
	public void setUp() throws Exception {

		commandMove = new PieceMovedCommand();
		commandJoin = new PieceJoinCommand();
		commandCapture = new PieceCapturedCommand();
		
		eventMgr.addListener("PieceMovedEvent", commandMove);
		eventMgr.addListener("PieceMovedEvent", commandJoin);
		eventMgr.addListener("PieceMovedEvent", commandCapture);
	}
	
	@Test
	public void capture_enemy_piece() {
		// arrange
		// Player player1 = game.getPlayer(1);
		Player player2 = game.getPlayer(2);
		
		PieceMovedEvent event = new PieceMovedEvent(30,18);

		// act
		eventMgr.fireEvent(event);

		
		// assert
		assertEquals("Player 2 get 1 score",
				1, player2.getScore());
		
		int tempcount = 0;
		for(Piece p : board.getPieces()){
			if(p != null && p.getOwner() == null){
				tempcount++;
			}
		}
		
		assertEquals("Barrier pieces is reduced to 11",
				11, tempcount);
	}
	
	@Test
	public void merge_friendly_piece() {
		// arrange
		Piece rook = board.getPiece(0);
		Piece knight = board.getPiece(1);
		
		PieceMovedEvent event = new PieceMovedEvent(0,1);

		// act
		eventMgr.fireEvent(event);

		
		// assert
		assertNotEquals("combine piece should not equal rook's score",
				rook.getScore(), board.getPiece(1).getScore());

		assertEquals("Combine piece should equal rook + knight score",
				rook.getScore() + knight.getScore(),
				board.getPiece(1).getScore());

		assertEquals("Should be nothing at position zero",
				null,
				board.getPiece(0));
	}

	/**
	 *  TODO: When testing whole test package
	 *  this test case fails because for some reason
	 *  when in PieceMovedCommand it does not detect
	 *  the selected piece but then when in
	 *  PieceCapturedCommand selected piece is detected
	 *  and isSqureEmpty at new position is not true.
	 *  
	 *  UPDATE: Some reason event manager has another
	 *  instance of game and board.
	 *  
	 *  UPDATE2: Both move command and capture
	 *  command are being called and they have
	 *  different instances of Board.
	 */
	@Test
	public void move_piece() {
		// arrange
		PieceMovedEvent event = new PieceMovedEvent(1,8);
		
		// act
		eventMgr.fireEvent(event);
		
		// assert
		assertTrue("Rook moved to empty position",
				!((Board)board).isSqureEmpty(8));
		assertEquals("Nothing should be in previous position",
				null, board.getPiece(1));
	}
	
	@Test
	public void piece_did_not_move() {
		// arrange
		PieceMovedEvent event = new PieceMovedEvent(0,30);
		
		// act
		eventMgr.fireEvent(event);
		
		// assert
		assertTrue("Piece cannot be moved",
				!commandMove.commandMoveDecision(event));
		assertTrue("Piece cannot be joined",
				!commandJoin.commandMoveDecision(event));
		assertTrue("Piece cannot be captured",
				!commandCapture.commandMoveDecision(event));
	}
}
