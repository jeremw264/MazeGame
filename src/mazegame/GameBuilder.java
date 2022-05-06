package mazegame;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import mazegame.challenge.Challenge;
import mazegame.character.Character;
import mazegame.character.Npc;
import mazegame.character.Player;
import mazegame.exception.GameBuilderException;
import mazegame.generation.GenerationAlgorithm;
import mazegame.item.Item;

/**
 * Classe GameBuilder
 * 
 * Construit le jeu
 * 
 */
public class GameBuilder {

	/**
	 * Ressource utiliser pour Conception de la classe
	 * 
	 * Type Générique :
	 * https://www.fil.univ-lille.fr/~quinton/coo/cours/generics.pdf Création des
	 * instance : https://www.jmdoudoux.fr/java/dej/chap-introspection.htm
	 * 
	 */

	// La carte du jeu.
	private Map map;

	// La liste des Challenges.
	private List<Challenge> listOfChallenges;

	// La liste des classe Npc à crée dans le jeu
	private List<Class<? extends Npc>> npcsClasses;

	// La liste des classe Npc à crée dans le jeu
	private List<Class<? extends Item>> itemsClasses;

	// La liste de tous les personnage.
	private List<Character> characters;

	// Le joueur.
	private Player player;

	// La classe du player
	private Class<? extends Player> playerClass;

	// La quete du jeu.
	private Quest quest;

	// Le nombre de personnages à mettre dans le jeu

	private int nbOfCharacters;
	// Le nombre d'objets à mettre dans le jeu

	private int nbOfItems;

	public GameBuilder() {
		this.listOfChallenges = new LinkedList<Challenge>();
		this.characters = new LinkedList<>();
		this.npcsClasses = new LinkedList<>();
		this.itemsClasses = new LinkedList<>();
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

		this.nbOfCharacters = this.nbOfItems = (width * height / 2) / 2;

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
	 * @param <T>
	 * @param className La classe du joueur à ajouter.
	 * @return L'instance courante.
	 * 
	 * @see Lors de plusieurs appel, seul le dernier sera pris en compte.
	 */
	public GameBuilder setPlayer(Class<? extends Player> className) {
		this.playerClass = className;

		return this;
	}

	public GameBuilder setNpcClass(Class<? extends Npc> npcClass) {

		this.npcsClasses.add(npcClass);

		return this;
	}

	public GameBuilder setItemClass(Class<? extends Item> itemClass) {

		this.itemsClasses.add(itemClass);

		return this;
	}

	/**
	 * Renvoie une instance du jeu crée.
	 * 
	 * @return Le jeu créé.
	 * @throws GameBuilderException
	 */
	public Game build() throws GameBuilderException {

		this.verify();

		if (this.npcsClasses.size() > 0) {
			this.generateNpc();
		}
		if (this.itemsClasses.size() > 0) {
			this.generateItem();
		}

		this.quest = new Quest(this.listOfChallenges);

		return new Game(map, this.characters, this.player, this.quest);
	}

	/**
	 * Génére tous les NPC avec les bonne proportion
	 */
	private void generateNpc() {

		int numberOfEachNpc = (this.nbOfCharacters - 1) / this.npcsClasses.size();
		Random random = new Random();

		for (Class<? extends Character> npcClassName : this.npcsClasses) {
			for (int i = 0; i < numberOfEachNpc; i++) {

				Character npcCharacter = this.constructCharacter(npcClassName, random.nextInt(this.map.getWidth()),
						random.nextInt(this.map.getHeight()), this.map);

				this.characters.add(npcCharacter);

			}
		}
	}

	private void generateItem() {
		int numberOfEachItem = this.nbOfItems / this.itemsClasses.size();

		for (Class<? extends Item> itemClassName : this.itemsClasses) {
			for (int i = 0; i < numberOfEachItem; i++) {
				Item item = this.constructItem(itemClassName);
				Cell cell = this.getRandomCellInMap();
				cell.addItem(item);

			}
		}

	}

	/**
	 * Renvoie une instance d'un perssonage grâce à la classe.
	 * 
	 * @param characterClass Le nom de la classe qui hérite de Character.
	 * @param x              La position horizontale.
	 * @param y              La position vertical.
	 * @param map            La carte où le personnage sera placé.
	 * @return L'instance du personnage.
	 */
	public Character constructCharacter(Class<? extends Character> characterClass, int x, int y, Map map) {
		try {
			return characterClass.getDeclaredConstructor(int.class, int.class, Map.class).newInstance(x, y, map);
		} catch (NoSuchMethodException | InstantiationException | IllegalAccessException
				| InvocationTargetException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Renvoie une instance d'un objet du jeu grâce à la classe.
	 * 
	 * @param itemClass le nom de la classe qui hérite de item.
	 * @return L'instance de l'objet.
	 */
	public Item constructItem(Class<? extends Item> itemClass) {
		try {
			return itemClass.getDeclaredConstructor().newInstance();
		} catch (NoSuchMethodException | InstantiationException | IllegalAccessException
				| InvocationTargetException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Renvoie une cellule aleatoire dans la carte.
	 * 
	 * @return
	 */
	private Cell getRandomCellInMap() {
		Random random = new Random();
		int x = random.nextInt(this.map.getWidth());
		int y = random.nextInt(this.map.getHeight());

		return this.map.getCell(x, y);
	}

	private void verify() throws GameBuilderException {
		if (this.map == null) {
			throw new GameBuilderException("La carte n'a pas été ajouter au jeu ! (Utilisé setMap)");
		} else {
			this.player = (Player) this.constructCharacter(playerClass, 0, 0, this.map);
		}
		if (this.player == null) {
			throw new GameBuilderException("Aucun joueur n'a été ajouter au jeu ! (Utilisé setPlayer)");
		}
		if (this.listOfChallenges.isEmpty()) {
			throw new GameBuilderException("Aucun défis n'a été ajouter au jeu ! (Utilisé setChallenge)");
		}
	}

}
