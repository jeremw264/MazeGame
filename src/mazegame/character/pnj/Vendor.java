package mazegame.character.pnj;

import mazegame.Cell;
import mazegame.character.Pnj;
import mazegame.utils.Discussion;

public class Vendor extends Pnj{

	public Vendor(int seedX, int seedY) {
		super(seedX, seedY);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canBeLeft() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cell computeNextCell() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canSpeak() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Discussion speak() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canTrade() {
		// TODO Auto-generated method stub
		return false;
	}

}