/*
 * Author: Sokun, CHORN
 * Number: S3455783
 */
package chess.core;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import chess.prototype.decorator.BarrierPieceDecorator;
import chess.prototype.decorator.PlayerPieceDecorator;
import chess.prototype.momento.GameCaretaker;

public class Game implements Serializable {
	private Player[] players;
	private Board board;
	private static Game instance;
	private int maxMoves = 10;
	private GameCaretaker caretaker;
	private int selectedPiece;
	
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
		reset(maxMove, true);
	}
	
	/**
	 * @pre.condition: an integer and boolean passed
	 * 
	 * @post.condition: game restarted, board re-decorated
	 */
	public void reset(int maxMove, boolean defaultBoard) {
		this.selectedPiece = -1;
		this.maxMoves = maxMove;
		board = new Board();
		board.init();
		/*
		 * creating players
		 */
		players = new Player[2];
		players[0] = new Player();
		players[0].setColour(Color.WHITE);
		players[0].setTurn(true);
		
		players[1] = new Player();
		players[1].setColour(Color.BLACK);
		
		// Create new caretaker
		caretaker = new GameCaretaker();
		
		if (defaultBoard) {
			new PlayerPieceDecorator(board, players[0]).init();
			new BarrierPieceDecorator(board).init();
			new PlayerPieceDecorator(board, players[1]).init();
		}
	}
	
	public GameCaretaker getCaretaker() {
		return caretaker;
	}

	/*
	 * @pre.condition: there are two player per game session
	 * 
	 * @post.condition: only a one user can take turn
	 */
	public void swapPlayer() {
		this.players[0].setTurn(!this.players[0].isTurn());
		this.players[1].setTurn(!this.players[1].isTurn());
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

	public Board getBoardInstance() {
		return this.board;
	}
	
	public void setBoardInstance(Board board) {
		this.board = board;
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

	/**
	 * @post.condition: game state saved to disk
	 */
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
			System.out.println(e.getMessage());
		}
		
		return false;
	}

	/**
	 * @pre.condition: game state file exists
	 * 
	 * @post.condition: game loaded to state stored on disk
	 */
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

	/**
	 * @pre.condition: Location of the piece must have an integer value
	 * @post.condition: return location of the piece
	 */
	public int getSelPosition() {
		return selectedPiece;
	}

	/**
	 * @pre.condition: Input integer value
	 * @post.condition: store the location of the piece
	 */
	public void setSelPosition(int pos) {
		this.selectedPiece = pos;
	}

	/**
	 * @pre.condition: Game and Board are set and players are playing
	 * @post.condition: Returns true/false if game is over
	 */
	public boolean isGameOver() {
		return
				this.players[0].getNumberOfMove() == this.maxMoves
				&& this.players[1].getNumberOfMove() == this.maxMoves 
				|| this.getPlayerPieces(1).size() == 0
				|| this.getPlayerPieces(2).size() == 0;
	}
}
