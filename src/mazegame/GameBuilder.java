package mazegame;

import java.lang.reflect.Constructor;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import mazegame.character.Character;
import mazegame.challenge.Challenge;
import mazegame.challenge.WaitRound;
import mazegame.character.Npc;
import mazegame.character.Player;
import mazegame.generation.GenerationAlgorithm;
import mazegame.item.Item;

public class GameBuilder {

	public static Logger LOGGER = Logger.getLogger("TestGetConstrutor");
	// public static String NOM_CLASSE = "mazegame.character.npc.Vendor";

	// La carte du jeu.
	private Map map;

	// La liste des Challenges
	private List<Challenge> listOfChallenges;

	// La liste des noms de classe Npc à crée dans le jeu
	private List<String> npcClassName;

	// La liste des noms de classe Npc à crée dans le jeu
	private List<String> itemClassName;

	// La liste de tous les personnage.
	private List<Character> characters;

	private List<Item> items;

	private Player player;

	private Quest quest;

	private int nbOfCharacters;
	private int nbOfItems;

	public GameBuilder() {
		this.listOfChallenges = new LinkedList<Challenge>();
		this.npcClassName = new LinkedList<String>();
		this.characters = new LinkedList<Character>();
		this.itemClassName = new LinkedList<String>();
		this.items = new LinkedList<Item>();
	}

	public void test() {

		Map map = new Map(5, 5);

	}

	/**
	 * Crée la carte du jeu avec l'algorithme passé en paramètre.
	 * 
	 * @param width     La largueur de la carte.
	 * @param height    La hauteur de la carte.
	 * @param algorithm L'algorithme à utilisé pour la génération.
	 * @return L'instance courante.
	 * 
	 * @see Lors de plusieurs appel, seul le dernier sera pris en compte.
	 */
	public GameBuilder setMap(int width, int height, GenerationAlgorithm algorithm) {

		this.nbOfCharacters = (this.nbOfItems = width * height / 2) / 2;

		this.map = algorithm.generation(width, height);
		return this;
	}

	/**
	 * Ajoute un Challenge dans le jeu.
	 * 
	 * @param challenge Le challenge à ajouter.
	 * @return L'instance courante.
	 * 
	 */
	public GameBuilder setChallenge(Challenge challenge) {
		this.listOfChallenges.add(challenge);
		return this;
	}

	/**
	 * Ajoute un joueur dans le jeu.
	 * 
	 * @param className La classe du joueur à ajouter.
	 * @return L'instance courante.
	 * 
	 * @see Lors de plusieurs appel, seul le dernier sera pris en compte.
	 */
	public GameBuilder setPlayer(String className) {
		Character player = (Character) this.getInstanceOfCharacter(className, 0, 0, this.map);
		if (this.player != null) {
			this.characters.remove(this.player);
			this.player = null;
		}
		if (!(player instanceof Player)) {
			throw new Error("Le joueur doit être de type player");
		} else {

			this.characters.add(player);
			this.player = (Player) player;
		}
		return this;
	}
	
	public GameBuilder setItemClass(String className) {
		this.itemClassName.add(className);
		return this;
	}

	public GameBuilder setNpcClass(String className) {

		this.npcClassName.add(className);

		return this;
	}

	/**
	 * Renvoie une instance du jeu crée.
	 * 
	 * @return Le jeu créé.
	 */
	public Game build() {
		this.generateNpc();

		this.listOfChallenges.add(new WaitRound(player, 1));

		this.quest = new Quest(this.listOfChallenges);

		return new Game(map, this.characters, this.player, this.quest);
	}

	/**
	 * Génére tous les NPC avec les bonne proportion
	 */
	private void generateNpc() {

		int numberOfEachNpc = (this.nbOfCharacters - 1) / this.npcClassName.size();
		Random random = new Random();

		for (String npcClassName : this.npcClassName) {
			for (int i = 0; i < numberOfEachNpc; i++) {

				Character npcCharacter = (Character) this.getInstanceOfCharacter(npcClassName,
						random.nextInt(this.map.getWidth()), random.nextInt(this.map.getHeight()), this.map);

				this.characters.add(npcCharacter);

			}
		}
	}

	private void setItemInMap() {
		
		this.generateItem();

		Random random = new Random();

		for (Item item : this.items) {
			int x = random.nextInt(this.map.getWidth());
			int y = random.nextInt(this.map.getHeight());

			this.map.getCell(x, y).addItem(item);
		}
	}

	private void generateItem() {
		int numberOfEachItem = this.nbOfItems / this.npcClassName.size();

		for (String itemClassName : this.itemClassName) {
			for (int i = 0; i < numberOfEachItem; i++) {
				Item item = this.getInstanceOfItem(itemClassName);

				this.items.add(item);

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
	private Character getInstanceOfCharacter(String className, int x, int y, Map map) {
		// Source https://www.jmdoudoux.fr/java/dej/chap-introspection.htm

		Character character = null;

		try {
			Class<?> classe = Class.forName(className);
			Constructor<?> constructeur = classe.getConstructor(new Class[] { int.class, int.class, Map.class });
			character = (Character) constructeur.newInstance(new Object[] { x, y, map });
		} catch (ClassNotFoundException cnfe) {
			if (LOGGER.isLoggable(Level.SEVERE))
				LOGGER.log(Level.SEVERE, "La classe " + className + " n'existe pas", cnfe);
		} catch (NoSuchMethodException nme) {
			if (LOGGER.isLoggable(Level.SEVERE))
				LOGGER.log(Level.SEVERE, "Le constructeur de la classe " + className + " n'existe pas", nme);
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

		return character;
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
	private Item getInstanceOfItem(String className) {
		// Source https://www.jmdoudoux.fr/java/dej/chap-introspection.htm

		Item character = null;

		try {
			Class<?> classe = Class.forName(className);
			Constructor<?> constructeur = classe.getConstructor(new Class[] {});
			character = (Item) constructeur.newInstance(new Object[] {});
		} catch (ClassNotFoundException cnfe) {
			if (LOGGER.isLoggable(Level.SEVERE))
				LOGGER.log(Level.SEVERE, "La classe " + className + " n'existe pas", cnfe);
		} catch (NoSuchMethodException nme) {
			if (LOGGER.isLoggable(Level.SEVERE))
				LOGGER.log(Level.SEVERE, "Le constructeur de la classe " + className + " n'existe pas", nme);
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

		return character;
	}

}
