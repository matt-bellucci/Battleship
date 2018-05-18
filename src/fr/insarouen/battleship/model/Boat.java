package fr.insarouen.battleship.model;

import java.lang.Enum;

public class Boat {

	/*public enum orientation { 
		Top, 
		Bot, 
		Left, 
		Right;
	} */

	// private int coordTete = new int[2];
	private int id;
	private int length;
	private int nbTouched =0;
	
	/*public orientation getOrientation(){
		return this.orientation;
	} */
	
	public Boat(){
		this.length = 3;
	}
	
	public Boat(int length){
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
