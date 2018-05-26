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
	

	/**
	  * Constructs a boat with a standard length (3).
	  */
	public Boat(){
		this.length = 3;
	}
	
	/**
	  * Constructs a boat with a specific length.
	  */

	public Boat(int length){
		this.length = length;
	}
	
	/**
	  * Allows to initialize boat's id.
	  */

	public void setId(int id){
		this.id = id;
	}
	
	/**
	  * Returns the boat id. 
	  */

	public int getId(){
		return this.id;
	}
	
	/**
	  * Add 1 to nbTouched. 
	  */
	
	public void touched(){
		this.nbTouched++;
	}
	
	/**
	  * Says wether the boat is sunk or not.
	  */

	public boolean isSunk() {
		return (length == nbTouched);
	}
	
	/**
	  * Return the boat length. 
	  */

	public int getLength(){
		return this.length;
	}
	
	/**
	  * Return how many times the boat has been touched.
	  */

	protected int getNbTouched(){
		return this.nbTouched;
	}
	
	@Override
	public String toString() {
		return getId()+" : longueur("+getLength()+")\n";
	}
}
