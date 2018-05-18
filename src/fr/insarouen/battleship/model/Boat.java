package fr.insarouen.battleship.model;

public class Boat {

	private int id;
	private int length;
	private int nbTouched =0;
	
	public void Boat(){
		this.length = 3;
	}
	
	public void Boat(int length){
		this.length = length;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return this.id;
	}
	
	
	public void touched(){
		this.nbTouched++;
	}
	
	public boolean isSunk() {
		return (length == nbTouched);
	}
	
	public int getLength(){
		return this.length;
	}
	
	protected int getNbTouched(){
		return this.nbTouched;
	}
}
