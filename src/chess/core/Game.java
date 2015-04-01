package chess.core;

import chess.prototype.decorator.*;

public class Game {
	private Player[] players;
	private IBoard board;
	
	public Game() {
		players[0] = new Player();
		players[1] = new Player();
		
		/*
		 * decorate the board with different pieces
		 */
		board = new PlayerPieceDecorator(new Board(), players[0]);
		board = new BarrierPieceDecorator(board);
		board = new PlayerPieceDecorator(board, players[1]);
	
	}
	
	public void swapPlayer() {
		for(Player p  : this.players)
		{
			p.setTurn(!p.getTurn());
		}
	}

	public Player getCurrentPlayer(){
		for(Player p: this.players)
		{
			if (p.getTurn()) return p;
		}
		
		return this.players[0];
	}

}
