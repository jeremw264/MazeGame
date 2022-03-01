package mazegame.generation;

import java.util.*;

import mazegame.*;

public class Prim extends GenerationAlgorithm{

    private int x, y;
    /*Création d'une liste de cases frontières non traitées,*/
    List<Cell> neighbors;
    /* Cellule de départ choisi aléatoirement*/
    Cell start;

    public Prim(){
        
    	this.cellsTreat = new ArrayList<>();
        this.neighbors = new ArrayList<Cell>();
        

    }
    
    /*Génération d'une grille avec tous les murs placés*/
    public Grid generation(int width, int height){
        this.grid = new Grid(width, height);
        this.x = (int)Math.random()*(this.grid.getWidth());
        this.y = (int)Math.random()*(this.grid.getHeight());
        this.start = new Cell(this.x,this.y,true);
        System.out.println(this.x);
        System.out.println(this.y);
        this.neighbors.addAll(this.getUnvisitedNeighborsCells(start));
        
        this.init();
        return this.grid;
    }
    
    /*Mise en place de coordonnées aléatoires pour le point de départ*/
    

    /*Méthode de sélection d'une cellule traitable aléatoire*/
   private Cell getRandomUntreatedNeighbor() {
	   Collections.shuffle(neighbors);
	   return neighbors.get(0);
   }
   
   private Cell getRandomTreatedCell() {
	   Collections.shuffle(cellsTreat);
	   return cellsTreat.get(0);
   }


    public void init() {
    	List<Cell> emptyList = new ArrayList<Cell>();
    	Cell cell = getRandomUntreatedNeighbor();
    	this.carvePath(start,cell);
    	this.addToTreatment(start);
    	while (neighbors != emptyList){
        	Cell cell1 = getRandomTreatedCell();
        	System.out.println(cell);
        	 Cell cell2 = getRandomUntreatedNeighbor();
             this.carvePath(cell,cell2);
        	this.addToTreatment(cell);
        	this.addToTreatment(cell2);
        	if (cellsTreat.contains(cell)) {
        		neighbors.remove(cell);
        	}
    	}
    }
 }