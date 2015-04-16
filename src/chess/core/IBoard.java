package chess.core;

public interface IBoard {

	public abstract void init();

	/*
		private void positionsChanged() {
			setChanged();
			notifyObservers();
		}
	 */
	public abstract Piece[] getPieces();

	public abstract Piece getPiece(int pos);
	
	public int getHeight();
	
	public int getWidth();
	
	public boolean checkTargetSquareIfEmpty(int pos);

}