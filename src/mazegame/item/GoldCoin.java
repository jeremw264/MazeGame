package mazegame.item;

import mazegame.character.Character;

/**
 * Classe GoldCoin
 *
 * Objet pièce d'or
 */
public class GoldCoin extends Item{

	/**
	 * Constructeur de l'objet Gold Coin
	 */
	public GoldCoin() {
		super(1,false,false);
	}


	/**
	 * Objet non utilisable donc pas de methode use allouee
	 */
	@Override
	public void use(Character character) {
		return ;
	}

	/**
	 * Retourne une representation de l'objet sous forme de chaine de caracteres
	 */
	@Override
	public String toString() {
		return "piece";
	}
}
