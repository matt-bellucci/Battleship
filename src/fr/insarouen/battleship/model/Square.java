package fr.insarouen.battleship.model;

public class Square {

	private boolean discovered=false;
	private int id; 
	
	public Square() {
		this.id = -1;
		}
	
	public Square(int id){
		this.id = id; 
	}	

	public void setId(int id) {
		this.id = id;
		}
	
	public int getId() {
		return this.id;
		}
	
	public void discover() {
		this.discovered = true;
		}
	
	public boolean isDiscovered() {
		return discovered;
	
        }
}
