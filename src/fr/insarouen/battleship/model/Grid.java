package fr.insarouen.battleship.model;

import java.util.Random;

/**
* Describes the game grid.
*/

public class Grid {

	private int size;
	private Square grid[][];
	
	/**
	  * Constructs a new grid with 10*10 standard size.
	  */

	public Grid(){
		this(10);
	}
	
	/**
	  * Constructs a new grid with [size]*[size] size.
	  */

	public Grid(int size){
		this.size = size;
		grid = new Square[size][size];
		for (int i=0;i<this.size;i++){
			for (int j=0;j<this.size;j++){
				this.grid[i][j] = new Square();
				}
			}
	}
		
	
	/**
	  * Says wether a specific square of the grid has been discovered or not.
	  */

	public boolean isDiscovered(int x, int y) {
		return grid[x][y].isDiscovered();	
		}
	
	/**
	  * Allows to turn a square as a discovered one.
	  */

	public void discover(int x, int y) {
		System.out.println("Decouvrir : "+x+y);
		this.grid[x][y].discover();
		}
		
	/**
	  * Returns the id of a specific square of the grid.
	  */

	public int getId(int x, int y) {
		return  grid[x][y].getId();
		}
	
	/**
	  * Returns the grid' size.
	  */

	public int getSize(){
		return this.size;
	}

	/**
	  * Allows to place randomly every boats on each grid.
	  */

	public void placeBoatsRandomly(Boats playerBoats) {
		Random randomGenerator = new Random();
		for (int i=0; i< playerBoats.getNbBoats();i++){
			boolean place = false;
			while (!place){
				int x = randomGenerator.nextInt(getSize());
				int y = randomGenerator.nextInt(getSize());
				int sens = randomGenerator.nextInt(2);
				int compteur = 0;
				if ((x+playerBoats.getBoat(i).getLength()<getSize()) && (sens==0) ){
					for (int j = 0;j<playerBoats.getBoat(i).getLength();j++){
						if (grid[x+j][y].getId() < 0){
							compteur++;
						}	
						if (compteur == playerBoats.getBoat(i).getLength()){
							place = true;
							for (int k = 0;k<playerBoats.getBoat(i).getLength();k++)					
								this.grid[x+k][y].setId(i);
						}
					}
				}
				else if ((y+playerBoats.getBoat(i).getLength()<getSize()) && (sens==1)){
					for (int j = 0;j<playerBoats.getBoat(i).getLength();j++){
						if (grid[x][y+j].getId() < 0){
							compteur++;
						}
						if (compteur == playerBoats.getBoat(i).getLength()){
							place = true;
							for (int k = 0;k<playerBoats.getBoat(i).getLength();k++)					
								this.grid[x][y+k].setId(i);
						}
					}
				}
			
			}
		}
	}
	
	public String toString() {
		return "Taille de la grille : "+getSize()+"\n";
	}
		
}
