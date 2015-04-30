package chess.prototype.decorator;

import java.util.ArrayList;
import java.util.Collections;

import chess.core.IBoard;
import chess.core.Piece;
import chess.core.Player;
import chess.prototype.composite.Barrier;
import chess.prototype.factory.ChessPieceFactory;

public class GameMapDecorator extends BoardDecorator{
	
	private ArrayList<String> userInput;
	private ChessPieceFactory pieceFactory= new ChessPieceFactory();
	private Player[] playerList;

	public GameMapDecorator(IBoard board, ArrayList<String> userInput, Player[] playerList) {
		super(board);
		this.userInput = userInput;
		this.playerList = playerList;
	}

	@Override
	public void init() {
		arrangePiecesOnChessBoard();
	}

	public static boolean gameSquareMapValidity(ArrayList<String> userInput){
		//assumption: user design half of the board and the other half is mirrored
		int boardHeight = userInput.size();
		int boardWidth = 0;
		if(boardHeight > 0)
			boardWidth = userInput.get(0).length();
		
		if(boardHeight < 0 && boardWidth < 0 ){
			return false;
		}
		if(boardHeight != boardWidth/2){
			return false;
		}
		
		for(String x:userInput){
			if(x.length() != boardWidth){
				return false;
			}
		}
		return true;
	}
	
	private void arrangePiecesOnChessBoard(){
		
		int currentPlayer = 0;
		int currPos = 0;
		for(String x:userInput){
			String[] tokenizedString = x.split("");
			for(String token:tokenizedString){
				Piece newPiece = pieceFactory.generateChessPiece(token.toLowerCase());
				if(newPiece != null && !(newPiece instanceof Barrier)){
					newPiece.setOwner(playerList[currentPlayer]);
				}
				board.setPiece(currPos, newPiece);
				currPos++;
			}
		}

		Collections.reverse(userInput);
		currentPlayer++;
		
		for(String x:userInput){
			String[] tokenizedString = x.split("");
			for(String token:tokenizedString){
				Piece newPiece = pieceFactory.generateChessPiece(token.toLowerCase());
				if(newPiece != null && !(newPiece instanceof Barrier)){
					newPiece.setOwner(playerList[currentPlayer]);
				}
				board.setPiece(currPos, newPiece);
				currPos++;
			}
		}
	}
}
