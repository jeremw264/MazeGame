package mazegame.item;

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
	 * Objet non utilisable donc pas de méthode use allouée
	 */
	@Override
	public void use() {
		return;
	}
	
	/**
	 * Retourne une representation de l'objet sous forme de chaine de caracteres
	 */
	public String toString() {
		return "piece";
	}
}
