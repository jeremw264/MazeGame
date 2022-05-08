package mazegame.challenge;

import java.util.List;

import mazegame.Hint;
import mazegame.character.Character;
import mazegame.character.Player;
import mazegame.item.GoldCoin;
import mazegame.item.Item;

public class GetGoldCoins extends Challenge {

	private int nbOfGoldCoinsToFinish;

	private int nbOfGoldCoinsCollected;

	private int tempNbOfPlayerGoldCoin;

	public GetGoldCoins(int nbOfGoldCoinsToFinish) {
		this.nbOfGoldCoinsToFinish = nbOfGoldCoinsToFinish;
		this.tempNbOfPlayerGoldCoin = 0;
	}

	@Override
	public boolean isFinish(Player player) {

		if (player.getCoins() > this.tempNbOfPlayerGoldCoin) {
			this.nbOfGoldCoinsCollected += player.getCoins() - this.tempNbOfPlayerGoldCoin;
			this.tempNbOfPlayerGoldCoin = player.getCoins();
		} else if (player.getCoins() < this.tempNbOfPlayerGoldCoin) {
			this.tempNbOfPlayerGoldCoin = player.getCoins();
		}

		return this.nbOfGoldCoinsCollected >= this.nbOfGoldCoinsToFinish;
	}

	@Override
	public boolean isPossible(List<Character> characters, List<Item> items) {
		int nbOfGoldCoins = 0;

		for (Item item : items) {
			if (item instanceof GoldCoin) {
				nbOfGoldCoins++;
			}
		}

		return nbOfGoldCoins >= this.nbOfGoldCoinsToFinish;
	}

	@Override
	public Hint getHint() {
		// TODO Auto-generated method stub
		return new Hint(new GoldCoin(),this.nbOfGoldCoinsToFinish);
	}

}
