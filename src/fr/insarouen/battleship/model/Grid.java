package fr.insarouen.battleship.model;

public class Grid {

	private int size;
	private Square grid[][];
	
	public void Grid(){
	
		this.size = 10;
		for (int i=0;i<this.size;i++){
			for (int j=0;j<this.size;j++){
				this.grid[i][j] = new Square();
				}
			}
		
		}
		
		
	public boolean isDiscovered(int x, int y) {
		return grid[x][y].Square.isDiscovered();	
		}
		
	public void discover(int x, int y) {
		this.grid[x][y].Square.discover();
		}
		
	public int getId(int x, int y) {
		return  grid[x][y].Square.getId();
		}
		
}
