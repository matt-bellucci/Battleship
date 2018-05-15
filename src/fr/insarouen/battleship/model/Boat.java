package fr.insarouen.battleship.model;

public class Boat {
	private int length;
	private int nbTouched =0;
	
	public Boat(){
		this.length = 3;
	}
	
	public Boat(int length){
		this.length = length;
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
