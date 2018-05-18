package fr.insarouen.battleship.model;

public class Square {

	private boolean discovered=false;
	private int id; 
	
	public void Square() {
		this.id = -1;
		}
	
	public void Square(int id){
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
