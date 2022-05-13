package mazegame;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import mazegame.challenge.Challenge;
import mazegame.character.Character;
import mazegame.character.Npc;
import mazegame.character.Player;
import mazegame.character.npc.Imp;
import mazegame.character.npc.Vendor;
import mazegame.exception.GameBuilderException;
import mazegame.generation.GenerationAlgorithm;
import mazegame.item.Item;
import mazegame.item.Scroll;

/**
 * Classe GameBuilder
 *
 * Construit le jeu.
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

	// La liste des Challenges.
	private List<Challenge> listOfChallenges;

	// La liste des classe Npc à crée dans le jeu
	private List<Class<? extends Npc>> npcsClasses;

	// La liste des classe Npc à crée dans le jeu
	private List<Class<? extends Item>> itemsClasses;

	// La carte du jeu.
	private Map map;

	// Liste des indices du jeu
	private Stack<Hint> hints;

	// La liste de tous les personnage.
	private List<Character> characters;

	// La liste des objets dans le jeu.
	private List<Item> items;

	// Le joueur.
	private Player player;

	// La classe du player
	private Class<? extends Player> playerClass;

	// La quete du jeu.
	private Quest quest;

	private final int nbOfHint = 20;

	private final int nbItemVendor = 5;

	// Le nombre de personnages à mettre dans le jeu
	private int nbOfCharacters;

	// Le nombre d'objets à mettre dans le jeu
	private int nbOfItems;

	/**
	 * Constructeur de l'objet GameBuilder.
	 */
	public GameBuilder() {
		this.listOfChallenges = new LinkedList<>();
		this.characters = new LinkedList<>();
		this.items = new LinkedList<>();
		this.npcsClasses = new LinkedList<>();
		this.itemsClasses = new LinkedList<>();
		this.hints = new Stack<>();
	}

	/**
	 * Crée la carte du jeu avec l'algorithme passé en paramètre.
	 *
	 * @param width     La largueur de la carte.
	 * @param height    La hauteur de la carte.
	 * @param algorithm L'algorithme à utilisé pour la génération.
	 * @return L'instance courante du GameBuilder
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
	 * @return L'instance courante du GameBuilder
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
	 * @return L'instance courante du GameBuilder
	 *
	 * @see Lors de plusieurs appel, seul le dernier sera pris en compte.
	 */
	public GameBuilder setPlayer(Class<? extends Player> className) {
		this.playerClass = className;

		return this;
	}

	/**
	 * Ajoute un type de Npc qui sera présent dans le jeu.
	 *
	 * @param npcClass La classe du Npc choisie.
	 * @return L'instance courante du GameBuilder
	 */
	public GameBuilder setNpcClass(Class<? extends Npc> npcClass) {

		// Si la classe est déja présente on ne l'ajoute pas , car sinon on n'aura pas
		// les bonne proportion dans le jeu.
		if (!this.npcsClasses.contains(npcClass)) {
			this.npcsClasses.add(npcClass);
		}

		return this;
	}

	/**
	 * Ajoute un type d'Item qui sera présent dans le jeu.
	 *
	 * @param itemClass La classe de l'Item choisie.
	 * @return L'instance courante du GameBuilder
	 */
	public GameBuilder setItemClass(Class<? extends Item> itemClass) {

		if (!this.itemsClasses.contains(itemClass)) {
			this.itemsClasses.add(itemClass);
		}

		return this;
	}

	/**
	 * Construit une instance du jeu et la renvoie.
	 *
	 * @return Le jeu créé.
	 * @throws GameBuilderException Si il y a un problème pendant la construction.
	 */
	public Game build() throws GameBuilderException {

		this.verify();

		this.characters.add(this.player);

		this.generateHint();

		if (this.npcsClasses.size() > 0) {
			this.generateNpc();
		}
		if (this.itemsClasses.size() > 0) {
			this.generateItem();
			this.dispatchItemInMap();
		}
		
		this.generateHintForNpc();
		
		if (this.npcsClasses.contains(Vendor.class)) {
			this.fillVendorInventory();
		}

		this.checksChallengesAreAchievable();

		/*for (Cell cell : this.map.getListsOfCells()) {
			for (Item item : cell.getItemList()) {
				if (item instanceof Scroll) {
					System.err.println(cell);
				}
			}
		}*/

		this.quest = new Quest(this.player, this.listOfChallenges);

		return new Game(this.map, this.characters, this.player, this.quest);
	}

	private void fillVendorInventory() {

		List<Class<? extends Item>> sellableItemClasses = new LinkedList<>();

		Random random = new Random();

		for (Character character : characters) {
			if (character instanceof Vendor) {
				while (character.getListOfItems().size() < this.nbItemVendor) {
					Class<? extends Item> itemClass = this.itemsClasses.get(random.nextInt(this.itemsClasses.size()));
					if (itemClass != Scroll.class) {
						Item item = constructItem(itemClass);
						if (item.canSell()) {
							character.addInv(item);
						}
					}
				}
			}
		}

	}


	/**
	 * Répartie les objets sur la carte.
	 */
	private void dispatchItemInMap() {
		for (Item item : items) {
			Cell cell = this.getRandomCellInMap();
			cell.addItem(item);
		}
	}

	private void generateHintForNpc() {
		
		int i = 0;
		
		for (Character character : characters) {
			if (!(character instanceof Vendor || character instanceof Player)) {
				Npc npc = (Npc)character;
					npc.setHint(this.listOfChallenges.get(i%this.listOfChallenges.size()).getHint());
				}
		}
	}
	
	private void generateHint() {
		for (int i = 0; i < this.nbOfHint / this.listOfChallenges.size(); i++) {
			for (Challenge challenge : this.listOfChallenges) {
				hints.push(challenge.getHint());
			}
		}

	}

	/**
	 * Génére tous les NPC avec les bonne proportion, les place et les ajoute dans
	 * la liste des personnages.
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

	/**
	 * Génére tous les Item avec les bonne proportion, les place et les ajoute sur
	 * la carte.
	 */
	private void generateItem() {
		int numberOfEachItem = this.nbOfItems / this.itemsClasses.size();

		for (Class<? extends Item> itemClassName : this.itemsClasses) {
			for (int i = 0; i < numberOfEachItem; i++) {
				Item item = this.constructItem(itemClassName);

				items.add(item);
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
	 * @return L'instance du personnage, null si il y a un problème.
	 */
	private Character constructCharacter(Class<? extends Character> characterClass, int x, int y, Map map) {
		try {
			// Recupère le constructeur du personnage et crée une instance.
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
	private Item constructItem(Class<? extends Item> itemClass) {
		try {
			if (itemClass == Scroll.class) {
				Hint hint = this.hints.pop();
				return itemClass.getDeclaredConstructor(Hint.class).newInstance(hint);
			} else {
				return itemClass.getDeclaredConstructor().newInstance();
			}
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

	/**
	 * Vérifie si le jeu peu bien être construit.
	 *
	 * @throws GameBuilderException Si le jeu ne peut pas être construit.
	 */
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

	/**
	 * Verifie que tous les challenges sont réalisable.
	 *
	 * @return true si ils sont réalisable, false sinon.
	 * @throws GameBuilderException Déclanche une exception si un des challenges
	 *                              n'est pas réalisable.
	 */
	private void checksChallengesAreAchievable() throws GameBuilderException {
		for (Challenge challenge : this.listOfChallenges) {
			if (!challenge.isPossible(this.characters, this.items)) {
				throw new GameBuilderException("Le challenge " + challenge
						+ " n'est pas réalisable avec les personnages et les objets ajouté !");
			}
		}

	}
}
