
public class Square implements Board {

	int position;
	
	@Override
	public void draw() {
		System.out.print("");
	}
	
	public void setPosition(int pos)
	{
		this.position = pos;
	}

	@Override
	public int positon() {
		return this.position;
	}
}
