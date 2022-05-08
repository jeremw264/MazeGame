package mazegame.generation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mazegame.Cell;
import mazegame.Map;


public class Prim extends GenerationAlgorithm{

    private int x, y;
    /*Création d'une liste de cases frontières non traitées,*/
    private List<Cell> neighbors;
    /* Cellule de départ choisi aléatoirement*/
    private Cell start;

    public Prim(){

    	this.cellsTreat = new ArrayList<>();
        this.neighbors = new ArrayList<>();

    }

    /*Génération d'une grille avec tous les murs placés*/
    @Override
	public Map generation(int width, int height){
        this.map = new Map(width, height);
        this.x = (int)Math.random()*(this.map.getWidth());
        this.y = (int)Math.random()*(this.map.getHeight());
        this.start = this.map.getCell(this.x, this.y);

        this.neighbors.addAll(this.getUntreatedNeighborsCells(this.start));

        this.init();
        return this.map;
    }

    /*Mise en place de coordonnées aléatoires pour le point de départ*/


    /*Méthode de sélection d'une cellule traitable aléatoire*/
   private Cell getRandomUntreatedNeighbor(Cell cell) {
	   Collections.shuffle(this.getUntreatedNeighborsCells(cell));
	   return this.getUntreatedNeighborsCells(cell).get(0);
   }

   private Cell getRandomTreatedCell() {
	   Collections.shuffle(cellsTreat);
	   return cellsTreat.get(0);
   }


    public void init() {
    	Cell cell = getRandomUntreatedNeighbor(this.start);
    	this.carvePath(this.start,cell);
    	this.addToTreatment(this.start);
    	while (! neighbors.isEmpty()){
    		Cell cell1 = getRandomTreatedCell();
        	Cell cell2 = getRandomUntreatedNeighbor(cell1);
            this.carvePath(cell1,cell2);
        	this.addToTreatment(cell1);
        	this.addToTreatment(cell2);
        	if (cellsTreat.contains(cell1)) {
        		neighbors.remove(cell1);
        	}
    	}
    }
 }