
public class Bishop extends PieceDecorator implements Movable {

	public Bishop(Board ogBoard) {
		super(ogBoard);
	}

	@Override
	public void draw() {
		System.out.print("B");
	}

	@Override
	public int positon() {
		return this.originalBoard.positon();
	}

	@Override
	public void moves() {
		// TODO move any direction diagonal
	}

}
