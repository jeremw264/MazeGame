package mazegame.main;

import java.util.Scanner;

import mazegame.Direction;
import mazegame.Maze;
import mazegame.generation.*;

/**
 * Class MainGame
 */
public class MainGame {

	/**
	 * Class d'execution du programme
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Maze maze = new Maze(5, 5, new RecursiveBacktracker(0,0));
		
		System.out.println(maze);
		
		
		
		
		
		
		
		
		
		
		
		
		
		Scanner input = new Scanner(System.in);
	    System.out.println("Which tree would you like to test (BST, ST, RBT)? ");
	    String treeChoice = "";

	    while (treeChoice != "ST") {
	    	
	    	treeChoice = input.nextLine();
	    	if(treeChoice == "BST")
		    {
		    	System.out.println("BST");
		    }
		    else if(treeChoice == "ST")
		    {
		        //ST<Integer> myTree = new ST<Integer>();
		    }
		    else if(treeChoice == "RBT")
		    {
		        //RBT<Integer> myTree = new RBT<Integer>();
		    }
		    else
		    {
		        System.out.println("Invalid Entry");
		    }
		}
		
		//PerfectMaze verifyMaze = new PerfectMaze(maze);
		//verifyMaze.verify(0, 0);

		
	}

}
