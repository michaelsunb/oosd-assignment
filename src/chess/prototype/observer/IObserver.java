package chess.prototype.observer;

/*
 * A common interface for observer's listener
 * TODO: refactor code to use Generic
 */
public interface IObserver {
	public void update(ChessEvent event);
}
