package fr.insarouen.battleship.model;

import java.util.Random;

public class Grid {

	private int size;
	private Square grid[][];
	
	public Grid(){
		this(10);
	}
	
	public Grid(int size){
		this.size = size;
		grid = new Square[size][size];
		for (int i=0;i<this.size;i++){
			for (int j=0;j<this.size;j++){
				this.grid[i][j] = new Square();
				}
			}
	}
		
		
	public boolean isDiscovered(int x, int y) {
		return grid[x][y].isDiscovered();	
		}
		
	public void discover(int x, int y) {
		System.out.println("Decouvrir : "+x+y);
		this.grid[x][y].discover();
		}
		
	public int getId(int x, int y) {
		return  grid[x][y].getId();
		}
	
	public int getSize(){
		return this.size;
	}

	public void placeBoatsRandomly(Boats playerBoats) {
		for (int j = 0;j<playerBoats.getBoat(1).getLength();j++){
			this.grid[j][1].setId(1);
		}
		Random randomGenerator = new Random();
		for (int i=0; i< playerBoats.getNbBoats();i++){
			boolean place = false;
			while (!place){
				int x =randomGenerator.nextInt(getSize());
				int y =randomGenerator.nextInt(getSize());
				if (x+playerBoats.getBoat(i).getLength()<getSize()){
					for (int j = 0;j<playerBoats.getBoat(i).getLength();j++){
						this.grid[x+j][y].setId(i);
					}
					place = true;
				}
			}
		}
	}
	
	public String toString() {
		return "Taille de la grille : "+getSize()"\n";
	}
		
}
