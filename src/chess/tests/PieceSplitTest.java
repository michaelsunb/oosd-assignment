package chess.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import chess.core.Piece;
import chess.prototype.composite.CombinePiece;
import chess.prototype.events.PieceMovedEvent;
import chess.prototype.events.PieceSplitEvent;
import chess.prototype.events.listener.PieceCapturedEventListener;
import chess.prototype.events.listener.PieceJoinEventListener;
import chess.prototype.events.listener.PieceMovedEventListener;
import chess.prototype.events.listener.PieceSplitEventListener;
import chess.prototype.template_method.PieceMovedTemplateMethod;

public class PieceSplitTest extends GameTestBase {
	private PieceSplitEventListener command;
	private PieceMovedTemplateMethod commandMove;
	private PieceMovedTemplateMethod commandJoin;
	private PieceMovedTemplateMethod commandCapture;
	
	@Before
	public void setUp() throws Exception {
		command = new PieceSplitEventListener();

		commandMove = new PieceMovedEventListener();
		commandJoin = new PieceJoinEventListener();
		commandCapture = new PieceCapturedEventListener();
		
		eventMgr.addListener("PieceMovedEvent", commandMove);
		eventMgr.addListener("PieceMovedEvent", commandJoin);
		eventMgr.addListener("PieceMovedEvent", commandCapture);
		eventMgr.addListener("PieceSplitEvent", command);
	}
	
	@Test
	public void merge_friendly_piece() {
		// arrange
		Piece rook = board.getPiece(0);
		Piece knight = board.getPiece(1);
		eventMgr.fireEvent(new PieceMovedEvent(0,1));

		assertTrue("Check first if piece have combined",
				(board.getPiece(1) instanceof CombinePiece));

		// act
		PieceSplitEvent event = new PieceSplitEvent(rook, 1,7);
		eventMgr.fireEvent(event);
		
		// assert
		// TODO: Should this be true?
		assertTrue("Check now if piece is still combined",
				(board.getPiece(1) instanceof CombinePiece));
		
		assertTrue("Piece at position 1 should not equal knight + rook score",
				((knight.getScore() + rook.getScore()) !=
						board.getPiece(1).getScore()));

		assertEquals("Piece at position 7 should equal rook's score",
				rook.getScore(), board.getPiece(7).getScore());

		assertEquals("Rook should be at position 7",
				rook,
				board.getPiece(7));
	}
}
