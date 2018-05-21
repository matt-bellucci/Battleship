package fr.insarouen.battleship.model;
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
		
}
