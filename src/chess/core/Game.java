/*
 * Author: Sokun, CHORN
 * Number: S3455783
 */
package chess.core;

import java.io.*;
import java.util.*;

import chess.prototype.decorator.*;

public class Game implements Serializable {
	private Player[] players;
	private Board board;
	private static Game instance;
	private int maxMoves = 10;

	private Game() {
		reset(maxMoves);
	}

	public static Game getInstance() {
		if (instance == null) {
			instance = new Game();
		}
		return instance;
	}

	public void reset(int maxMove) {

		this.maxMoves = maxMove;
		board = new Board();
		board.init();
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
	 * @pre.condition: there are two player per game session
	 * 
	 * @post.condition: only a one user can take turn
	 */
	public void swapPlayer() {
		for (Player p : this.players) {
			p.setTurn(!p.isTurn());
		}
	}

	/*
	 * @pre.condition: p is 1 or 2
	 * 
	 * @post.condition: return a player instance
	 */
	public Player getPlayer(int p) {
		if (p < 1 || p > 2)
			return null;
		return this.players[p - 1];
	}

	/*
	 * postcondition: return a valid instance of player object
	 */
	public Player getCurrentPlayer() {
		for (Player p : this.players) {
			if (p.isTurn())
				return p;
		}

		return this.players[0];
	}

	public IBoard getBoardInstance() {
		return this.board;
	}

	/*
	 * pre.condition: number of valid move can't be zero
	 */
	public int getMaxMoves() {
		return maxMoves;
	}
	
	public List<Piece> getPlayerPieces(int p_num) {
		Player player = this.getPlayer(p_num);
		
		List<Piece> pieces = new ArrayList<Piece>();

		for(Piece p: this.board.getPieces()) {
			if (p == null || p.getOwner() == null) continue;
			if (p.getOwner().equals(player))
			{
				pieces.add(p);
			}
		}
		return pieces;
	}
	
	public boolean save() {
		File file = new File("game.state");
		if (file.exists()) file.delete();
		
		try {
			file.createNewFile();
		} catch (IOException e1) {
			// ignore
		}
		
		try(FileOutputStream fos = new FileOutputStream(file);
				ObjectOutputStream out = new ObjectOutputStream(fos) 
				) {

			out.writeObject(instance);
			out.close();

			return true;
		} catch (IOException e) {
			// ignore
		}
		
		return false;
	}
	
	public boolean restore() {
		File file = new File("game.state");
		if (!file.exists()) {
			return false;
		}

		try(FileInputStream fis = new FileInputStream(file);
				ObjectInputStream in = new ObjectInputStream(fis) 
				) {
			
			// update instance
			instance = (Game)in.readObject();
			in.close();
			return true;
		} catch (ClassNotFoundException | IOException e) {
			System.out.println(e.getMessage());
			// ignore
		}
		
		return false;
	}
}
