/*
 * Author: Siang Ling
 * Number: s3295217
 */
package chess.prototype.commands;

import chess.core.*;
import chess.core.Piece;
import chess.prototype.composite.*;
import chess.prototype.observer.*;

public class ChessPieceAction implements IObserver {
	private Game game;
	private IBoard board;
	
	int position = 0;
	Piece piece = null;
	
	@Override
	public void update(ChessEvent event) {
		/*
		 * Store object references for code short handing
		 */
		this.game = Game.getInstance();
		this.board = game.getBoardInstance();
		
		if(event instanceof PieceCapturedEvent){
			PieceCaptured((PieceCapturedEvent)event);
		}
		else if(event instanceof PieceJoinEvent) {
			PieceCombined((PieceJoinEvent)event);
		}
		
	}
	
	public void PieceCaptured(PieceCapturedEvent event) {
		piece = event.getCapturedPiece();
		position = event.getCapturedPosition();
		Player player = this.game.getCurrentPlayer();
		
		player.addScore(this.board.getPiece(position).getScore());
		
		replacePiece(piece);
		System.out.println("captured");
	}
		
	public void PieceCombined(PieceJoinEvent event){
		Piece curPiece = event.getCurrentPiece();
		Piece tarPiece = event.getAugmentPiece();
		position = event.getJoinPosition();

		CombinePiece piece = new CombinePiece();
		piece.add(curPiece);
		piece.add(tarPiece);
		
		piece.setOwner(curPiece.getOwner());
		
		replacePiece(piece);
	}
	
	public void replacePiece(Piece piece){
		this.board.getPieces()[position] = piece;
	}

}
