package mazegame.character.player;

import mazegame.character.Player;

public class Hero extends Player{

	public Hero(int seedX,int seedY) {
		super(seedX, seedY);
	}

	@Override
	public boolean canBeLeft() {
		return false;
	}
	
}
