package chess.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import chess.mvc.models.*;
import chess.prototype.commands.CommandDecisionMake;

public class CommandDecisionTest extends GameTestBase {
	private CommandDecisionMake command;
	
	@Before
	public void setUp() throws Exception {
		//
		game.reset(10);

		command = new CommandDecisionMake();
		
		eventMgr.addListener("PieceCommandDecisionEvent", command);
	}
	
	@Test
	public void capture_barrier() {
		// arrange
		PieceCommandDecisionEvent event = new PieceCommandDecisionEvent(0,12);
		
		// act
		//eventMgr.fireEvent(event);
		
		// assert
		assertTrue((command.commandDecision(event) instanceof PieceCapturedEvent));
	}
	
	@Test
	public void join_knight() {
		// arrange
		PieceCommandDecisionEvent event = new PieceCommandDecisionEvent(0,1);
		
		// act
		//eventMgr.fireEvent(event);
		
		// assert
		assertTrue((command.commandDecision(event) instanceof PieceJoinEvent));
	}
	
	@Test
	public void move_piece() {
		// arrange
		PieceCommandDecisionEvent event = new PieceCommandDecisionEvent(0,6);
		
		// act
		//eventMgr.fireEvent(event);
		
		// assert
		assertTrue((command.commandDecision(event) instanceof PieceMovedEvent));
	}
}
