package fr.insarouen.battleship.model;

import java.util.Random;
import java.util.HashMap;
import java.util.Collection;
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
		System.out.println("Decouvrir : " + x + y);
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
		for (Boat boat : playerBoats.values()){
			boolean place = false;
			while (!place){
				int x = randomGenerator.nextInt(getSize());
				int y = randomGenerator.nextInt(getSize());
				int sens = randomGenerator.nextInt(2);
				place = placeBoat(boat, sens, x, y);
			}
		}
	}
	
	private void placeBoatOnCase(Boat boat, int sens, int x, int y){
		for (int k = 0; k < boat.getLength(); k++){
			if (sens == 0) { this.grid[x+k][y].setId(boat.getId());}
			else { this.grid[x][y+k].setId(boat.getId());}
		}
	}
	
	private boolean testPlace(Boat boat, int sens, int x, int y){
		if ( ( x + boat.getLength() < getSize() ) && (sens==0) ){
			int j = 0;
			while ( (j <= boat.getLength()) && (this.grid[x+j][y].getId() < 0)){
				j++;
			}
			return ( (j+1) == boat.getLength()); 
		}
		
		else if ( ( y + boat.getLength() < getSize() ) && (sens==1) ){
			int j = 0;
			while ( (j <= boat.getLength()) && (this.grid[x][y+j].getId() < 0)){
				j++;
			}
			return ( (j+1) == boat.getLength()); 		
		}
		
		else{return false;}
	}
	
	private boolean placeBoat(Boat boat, int sens, int x, int y){
		boolean res = testPlace(boat, sens, x, y);
		if (res){ 
			placeBoatOnCase(boat, sens, x, y);
		}
		
		return res;
	}
	
	public String toString() {
		return "Taille de la grille : "+getSize()+"\n";
	}
		
}
