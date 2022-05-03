package mazegame.generation;

import java.util.*;

import mazegame.*;
import mazegame.Map;


public class Prim extends GenerationAlgorithm{

    private int x, y;
    /*Création d'une liste de cases frontières non traitées,*/
    private List<Cell> neighbors;
    /* Cellule de départ choisi aléatoirement*/
    private Cell start;

    public Prim(){
        
    	this.cellsTreat = new ArrayList<>();
        this.neighbors = new ArrayList<Cell>();
        
        this.init();

    }
    
    /*Génération d'une grille avec tous les murs placés*/
    public Map generation(int width, int height){
        this.map = new Map(width, height);
        this.x = (int)Math.random()*(this.map.getWidth());
        this.y = (int)Math.random()*(this.map.getHeight());
        this.start = new Cell(this.x,this.y,true);

        this.neighbors.addAll(this.getUntreatedNeighborsCells(this.start));
        
        this.init();
        return this.map;
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
    	Cell cell = getRandomUntreatedNeighbor();
    	this.carvePath(start,cell);
    	this.addToTreatment(start);
    	while (! neighbors.isEmpty()){
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