package mazegame.item;

/**
 * Classe GoldCoin
 * 
 * Objet pi�ce d'or
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
	public String use() {
		return "";
	}
	
	/**
	 * Retourne une representation de l'objet sous forme de chaine de caracteres
	 */
	public String toString() {
		return "piece";
	}
}
