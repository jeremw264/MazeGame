package mazegame.generation;

import java.util.*;

import mazegame.*;

public class Prim extends GenerationAlgorithm{

    private final int x;
    private final int y;

    /*Génération d'une grille avec tous les murs placés*/
    public Grid generation(int width, int height){
        this.grid = new Grid(width, height);

        return this.grid;
    }
    
    /*Mise en place de coordonnées aléatoires pour le point de départ*/
    public Prim(){
        this.x = (int)Math.random()*(this.grid.width);
        this.y = (int)Math.random()*(this.grid.height);
    }

    /*Création d'une liste de cases frontières non traitées,
    *Et d'un ensemble LinkedHashSet pour éviter les doublons dès le départ*/
    List<Cell> neighbors = new List<Cell>();
    LinkedHashSet <Cell> neighbors2 = new LinkedHashSet<Cell>();

    /*Méthode pour avoir une direction aléatoire*/
    private Direction randomDirection(){
        Random rdm = new Random();
        int choice = rdm.nextInt(Direction.values().length);
        return Direction.values()[choice];
    }

    Cell start = new Cell(this.x,this.y,true);
    neighbors2.addAll(this.getUnvisitedNeighborsCells(start));
    neighbors.addAll(this.getUnvisitedNeighborsCells(start));
    
    /*Méthode de création d'un passage dans une direction aléatoire*/
    private void carveRandomPath(Cell cell1){
        Direction direction = randomDirection();
        Cell cell2 = new Cell();
        if (direction == N){
            cell2.x = cell1.x;
            cell2.y = cell1.y-1;
        }
        else if (direction == S){
            cell2.x = cell1.x;
            cell2.y = cell2.y+1;
        } 
        else if (direction == O){
            cell2.x = cell1.x-1;
            cell2.y = cell1.y;
        }
        else{
            cell2.x = cell1.x+1;
            cell2.y = cell2.y;
        }
        this.CarvePath(cell1,cell2);
    }

    /*Méthode de sélection d'une cellule traitable aléatoire*/
    private Cell getRandomUntreatedNeighbor(){
        Random rdm = new Random();
        int neighbors.get(rdm.nextInt(neighbors.size()));
    }

    List<Cell> emptyList = new List<Cell>;
    carveRandomPath(start);
    this.addToTreatment(start);
    while (neighbors != emptyList){
        Cell cell = getRandomUntreatedNeighbor();
        carveRandomPath(cell);
        if (neighbors2.addAll(this.getUnvisitedNeighborsCells(cell))) neighbors.addAll(this.getUnvisitedNeighborsCells(cell)); 
        neighbors2.addAll(this.getUnvisitedNeighborsCells(cell));
        this.addToTreatment(cell);
    }
 }