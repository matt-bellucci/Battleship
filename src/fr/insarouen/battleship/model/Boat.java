package fr.insarouen.battleship.model;

public class Boat {

	private int id;
	private int length;
	private int nbTouched =0;
	
	
	public setId(int id){
		this.id = id;
		}
	
	public getId(){
		return this.id;
		}
	
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
