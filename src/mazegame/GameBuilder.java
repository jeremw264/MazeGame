package mazegame;

import java.lang.reflect.Constructor;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import mazegame.character.Character;
import mazegame.challenge.Challenge;
import mazegame.character.Npc;
import mazegame.character.Player;
import mazegame.generation.GenerationAlgorithm;

public class GameBuilder {

	public static Logger LOGGER = Logger.getLogger("TestGetConstrutor");
	public static String NOM_CLASSE = "mazegame.character.npc.Vendor";

	private Map map;

	private List<Challenge> listOfChallenges;

	private List<String> npcClassName;

	private List<Character> characters;

	private Game game;
	
	private Player player;

	private int nbOfCharacters;
	private int nbOfItems;

	public GameBuilder() {
		this.listOfChallenges = new LinkedList<Challenge>();
		this.npcClassName = new LinkedList<String>();
	}

	// Source https://www.jmdoudoux.fr/java/dej/chap-introspection.htm
	public void test() {

		Map map = new Map(5, 5);

		Npc npc = this.getInstanceOfNpc(NOM_CLASSE, 0, 0, map);
		System.out.println(npc);

	}

	public GameBuilder setMap(int width, int height, GenerationAlgorithm algorithm) {

		this.nbOfCharacters = this.nbOfItems = width * height / 2;

		this.map = algorithm.generation(width, height);
		return this;
	}

	public GameBuilder setChallenge(Challenge challenge) {
		this.listOfChallenges.add(challenge);
		return this;
	}
	
	public GameBuilder setPlayer(Player player) {
		this.player = player;
		return this;
	}

	public GameBuilder setNpcClass(String className) {

		this.npcClassName.add(className);

		return this;
	}

	public Game build() {
		this.game = new Game(map);
		this.generateNpc();
		return this.game;
	}

	private void generateNpc() {

		int numberOfEachNpc = (this.nbOfCharacters - 1) / this.npcClassName.size();
		Random random = new Random();

		for (String npcClassName : this.npcClassName) {
			for (int i = 0; i < numberOfEachNpc; i++) {

				Character npcCharacter = (Character) this.getInstanceOfNpc(npcClassName,
						random.nextInt(this.map.getWidth()), random.nextInt(this.map.getHeight()), this.map);

				this.characters.add(npcCharacter);

			}
		}
	}

	/**
	 * Renvoie une instance de la classe avec le nom en paramètre
	 * 
	 * @param className Nom de classe | exemple : "mazegame.action.npc.Vendor"
	 * @param x         Position horizontale
	 * @param y         Position Vertical
	 * @param map       La carte sur laquelle il sera placer
	 * @return L'instance de la classe.
	 */
	private Npc getInstanceOfNpc(String className, int x, int y, Map map) {

		Npc npc = null;

		try {
			Class<?> classe = Class.forName(className);
			Constructor<?> constructeur = classe.getConstructor(new Class[] { int.class, int.class, Map.class });
			npc = (Npc) constructeur.newInstance(new Object[] { x, y, map });
		} catch (ClassNotFoundException cnfe) {
			if (LOGGER.isLoggable(Level.SEVERE))
				LOGGER.log(Level.SEVERE, "La classe " + className + " n'existe pas", cnfe);
		} catch (NoSuchMethodException nme) {
			if (LOGGER.isLoggable(Level.SEVERE))
				LOGGER.log(Level.SEVERE, "Le constructeur de la classe " + NOM_CLASSE + " n'existe pas", nme);
		} catch (InstantiationException ie) {
			if (LOGGER.isLoggable(Level.SEVERE))
				LOGGER.log(Level.SEVERE, "La classe " + className + " n'est pas instanciable", ie);
		} catch (IllegalAccessException iae) {
			if (LOGGER.isLoggable(Level.SEVERE))
				LOGGER.log(Level.SEVERE, "La classe " + className + " n'est pas accessible", iae);
		} catch (java.lang.reflect.InvocationTargetException ite) {
			if (LOGGER.isLoggable(Level.SEVERE))
				LOGGER.log(Level.SEVERE, "Le constructueur de la classe " + className + " a leve une exception", ite);
		} catch (IllegalArgumentException iae) {
			if (LOGGER.isLoggable(Level.SEVERE))
				LOGGER.log(Level.SEVERE,
						"Un parametre du constructueur de la classe " + className + " n'est pas du bon type", iae);
		}

		return npc;
	}

}
