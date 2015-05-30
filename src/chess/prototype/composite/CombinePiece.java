package chess.prototype.composite;

import java.util.*;

import chess.core.Piece;

public class CombinePiece extends Piece {
	private List<Piece> pieces = new ArrayList<Piece>();

	public boolean add(Piece piece) {
		if (piece == null)
			return false;
		if ((this.score + piece.getScore()) >= 20)
			return false;

		for (Piece p : pieces) {
			if (p.getClass().equals(piece.getClass())) {
				return false;
			}
		}

		if (piece instanceof CombinePiece) {
			for (Piece p : ((CombinePiece) piece).getPieces()) {
				pieces.add(p);
			}
		} else {
			this.pieces.add(piece);
		}

		this.score += piece.getScore();
		return true;
	}

	public Piece remove(Piece piece) {
		List<Integer> positions = new ArrayList<Integer>();

		if (piece instanceof CombinePiece) {
			List<Piece> splitPieces = ((CombinePiece) piece).getPieces();
			for (Piece fragment : splitPieces) {
				for (int i = 0; i < this.pieces.size(); i++) {
					Piece tmpPiece = this.pieces.get(i);
					// poor man check
					if (tmpPiece.getClass().equals(fragment.getClass())) {
						this.pieces.remove(tmpPiece);
						positions.add(i);
					}
				}
			}
		} else {
			for (int i = 0; i < this.pieces.size(); i++) {
				Piece tmpPiece = this.pieces.get(i);
				// poor man check
				if (tmpPiece.getClass().equals(piece.getClass())) {
					this.pieces.remove(tmpPiece);
					positions.add(i);
				}
			}
		}

		if (!positions.isEmpty())
			this.score -= 5 * positions.size();
		
		if (this.pieces.size() == 1) {
			return this.pieces.get(0);
		}

		return this;
	}

	@Override
	public int[] getMovablePositions(int currPos) {
		TreeSet<Integer> positions = new TreeSet<Integer>();

		for (Piece p : pieces) {
			for (int position : p.getMovablePositions(currPos)) {
				if (!positions.contains(position)) {
					positions.add(position);
				}
			}
		}

		int[] ret = new int[positions.size()];
		int i = 0;
		for (Integer pos : positions) {
			ret[i++] = pos;
		}

		return ret;
	}

	@Override
	public String getSymbol() {
		String symbols = "";
		for (Piece p : this.pieces) {
			symbols += p.getSymbol();
		}
		return symbols;
	}

	public List<Piece> getPieces() {
		return this.pieces;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof CombinePiece))
			return false;
		CombinePiece cp = (CombinePiece) obj;
		if (cp.getPieces().size() != this.pieces.size())
			return false;
		return true;
	}
}
