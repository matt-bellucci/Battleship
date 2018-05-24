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
		return "Taille de la grille : "+getSize()"\n";
	}
		
}
