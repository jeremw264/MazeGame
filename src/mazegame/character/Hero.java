package mazegame.character;

public class Hero extends Character{

	public Hero(int seedX,int seedY) {
		super(seedX, seedY);
	}

	@Override
	public boolean canBeLeft() {
		return false;
	}
	
}
