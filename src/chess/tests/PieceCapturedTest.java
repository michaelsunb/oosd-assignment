package chess.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import chess.core.Game;
import chess.core.Piece;
import chess.core.Player;
import chess.mvc.models.PieceMovedEvent;
import chess.prototype.commands.PieceCapturedCommand;
import chess.prototype.commands.PieceMovedCommand;
import chess.prototype.composite.Rook;

public class PieceCapturedTest extends GameTestBase {
	@Before
	public void setUp() throws Exception {
		this.eventMgr().removeAll();
		
		this.eventMgr().addListener("PieceMovedEvent", new PieceMovedCommand());
		this.eventMgr().addListener("PieceCapturedEvent", new PieceCapturedCommand());
		
		Game.getInstance().reset(10);
	}
	
	@Test
	public void capture_barrier() {
		// arrange
		Player player1 = this.getGame().getPlayer(1);
		
		PieceMovedEvent event = new PieceMovedEvent(0, 12);

		// act
		this.eventMgr().fireEvent(event);

		// assert
		int tempcount = numOfBarrier();
		
		assertEquals("Number of Barriers reduced to 11",
				11, tempcount);
		
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

	@Test
	public void capture_enemy_piece() {
		// arrange
		Player player1 = this.getGame().getPlayer(1);
		Rook rook = new Rook();
		rook.setOwner(this.getGame().getPlayer(2));
		this.getBoard().setPiece(6, rook);
		
		PieceMovedEvent event = new PieceMovedEvent(0, 6);
		int enemyPieces = this.getGame().getPlayerPieces(2).size();
		
		// act
		this.eventMgr().fireEvent(event);

		// assert
		assertEquals("Player 1: 5 score",
				5, player1.getScore());
		assertEquals("Player 1: move increased by 1", 
				1, player1.getNumberOfMove());
		assertEquals("Enemy piece reduced by 1", 
				6, (enemyPieces -1) );
	}

}
