package mazegame;

public class Hint {

	
	private String goodHint;
	private String badHint;
	
	/**
	 * Constructeur de l'objet Hint
	 */
	public Hint(String goodHint, String badHint) {
		this.goodHint = goodHint;
		this.badHint = badHint;
	}
	
	/**
	 * Renvoie le bon indice
	 * 
	 * @return String : good hint
	 */
	public String getGoodHint() {
		return this.goodHint;
	}
	
	/**
	 * Renvoie le mauvais indice
	 * 
	 * @return String : bad hint
	 */
	public String getBadHint() {
		return this.badHint;
	}
	
	
}
