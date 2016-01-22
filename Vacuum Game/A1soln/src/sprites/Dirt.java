package sprites;

public class Dirt extends Sprite {
	protected int value;
	
	public Dirt(char symbol, int row,
			int column, int value) {
		super(symbol, row, column);
		this.value = value;
	}

	public int getValue() {
		return value;
	}



	
	
}
