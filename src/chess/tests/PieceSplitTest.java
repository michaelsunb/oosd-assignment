package chess.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import chess.core.Piece;
import chess.core.Player;
import chess.mvc.models.PieceMovedEvent;
import chess.mvc.models.PieceSplitEvent;
import chess.prototype.commands.*;
import chess.prototype.composite.CombinePiece;
import chess.prototype.composite.Rook;

public class PieceSplitTest extends GameTestBase {
	private CombinePiece combined;
	private Piece p0, p1, p2;

	@Before
	public void setUp() throws Exception {
		this.eventMgr().removeAll();
		
		this.eventMgr().addListener("PieceSplitEvent", new PieceSplitCommand());
		this.eventMgr().addListener("PieceMovedEvent", new PieceMovedCommand());
		this.eventMgr().addListener("PieceJoinEvent", new PieceJoinCommand());
		this.eventMgr().addListener("PieceCapturedEvent", new PieceCapturedCommand());
		
		this.getGame().reset(10);
		
		combined = new CombinePiece();
		p0 = this.getBoard().getPiece(0);
		p1 = this.getBoard().getPiece(1);
		p2 = this.getBoard().getPiece(2);
		
		combined.add(p0);
		combined.add(p1);
		combined.add(p2);
		
		combined.setOwner(p0.getOwner());
		
		this.getBoard().setPiece(0, combined);
		this.getBoard().setPiece(1, null);
		this.getBoard().setPiece(2, null);
	}

	@Test
	public void split_a_piece() {
		// arrange
		int totalPieces = this.getGame().getPlayerPieces(1).size();
		PieceSplitEvent event = new PieceSplitEvent(combined, p1, 1);
		
		// act
		this.eventMgr().fireEvent(event);

		// assert
		assertEquals("A piece is splitted", p1, this.getBoard().getPiece(1));
		assertEquals("Player move increased", 1, this.getGame().getPlayer(1).getNumberOfMove());
		assertEquals("Number of piece increase", totalPieces + 1, this.getGame().getPlayerPieces(1).size());
	}

	@Test
	public void split_combine_piece() {
		// arrange
		int totalPieces = this.getGame().getPlayerPieces(1).size();
		CombinePiece splitPieces = new CombinePiece();
		splitPieces.add(p0);
		splitPieces.add(p1);
		splitPieces.setOwner(p0.getOwner());
		
		PieceSplitEvent event = new PieceSplitEvent(combined, splitPieces, 1);
		
		// act
		this.eventMgr().fireEvent(event);
		
		// assert
		assertEquals("A piece is splitted", splitPieces, this.getBoard().getPiece(1));
		assertEquals("Player move increased", 1, this.getGame().getPlayer(1).getNumberOfMove());
		assertEquals("Remain piece degrade to base type", p2, this.getBoard().getPiece(0));
		assertEquals("Number of piece increase", totalPieces + 1, this.getGame().getPlayerPieces(1).size());
	}
	
	@Test
	public void split_a_piece_and_land_on_enemy_piece() {
		// arrange
		Player player1 = this.getGame().getPlayer(1);
		PieceSplitEvent event = new PieceSplitEvent(combined, p1, 12);
		
		// act
		this.eventMgr().fireEvent(event);

		// after act
		int totalBarriers = numOfBarrier();

		// assert
		assertEquals("A piece is splitted", p1,
				this.getBoard().getPiece(12));
		assertEquals("Number of Barriers reduced to 11",
				11, totalBarriers);
		assertEquals("Player 1: 1 score",
				1, player1.getScore());
		assertEquals("Player 1: move increased by 1", 
				1, player1.getNumberOfMove());
	}

	private int numOfBarrier() {
		int tempcount = 0;
		for(Piece p : this.getBoard().getPieces()){
			if(p != null && p.getOwner() == null){
				tempcount++;
			}
		}
		return tempcount;
	}

}
