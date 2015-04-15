package chess.core;

import chess.prototype.decorator.*;

public class Game {
	private Player[] players;
	private IBoard board;
	private static Game instance;
	private int maxMoves = 10;
	
	private Game() {
		/**
		 * Just set up the board first
		 */
		board = new Board();
		board.init();
	}
	
	public static Game getInstance() {
		if (instance == null) {
			instance = new Game();
		}
		return instance;
	}
	
	public void reset(int maxMove) {
		
		this.maxMoves = maxMove;
		
		/*
		 * creating players
		 */
		players = new Player[2];
		players[0] = new Player();
		players[1] = new Player();
		
		new PlayerPieceDecorator(board, players[0]).init();
		new BarrierPieceDecorator(board).init();
		new PlayerPieceDecorator(board, players[1]).init();
	}
	
	/*
	 * postcondition: only a one user can take turn
	 */
	public void swapPlayer() {
		for(Player p  : this.players)
		{
			p.setTurn(!p.getTurn());
		}
	}

	/*
	 * postcondition: return a valid instance of player object
	 */
	public Player getCurrentPlayer(){
		for(Player p: this.players)
		{
			if (p.getTurn()) return p;
		}
		
		return this.players[0];
	}

	public IBoard getBoardInstance() {
		return this.board;
	}

	/*
	 * precondition: number of valid move can't be zero
	 */
	public int getMaxMoves() {
		return maxMoves;
	}

}
