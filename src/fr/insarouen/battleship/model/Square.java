package fr.insarouen.battleship.model;

/**
* Describes a square of the grid.
*/

public class Square {

	private boolean discovered=false;
	private int id; 
	
	/**
	* Constructs a standard square by setting its id to -1 (means empty)
	*/
	
	public Square() {
		this.id = -1;
		}
	
	/**
	* Constructs a square according to the value of id (empty, touched or sunk)
	*/
	
	public Square(int id){
		this.id = id; 
	}	

	/**
	* Allows to initialize the id.
	*/
	
	public void setId(int id) {
		this.id = id;
		}
	
	/**
	* Returns the suqare's id.
	*/
	
	public int getId() {
		return this.id;
		}
	
	/**
	* Turns the square as a discovered one.
	*/
	
	public void discover() {
		this.discovered = true;
		}
	
	/**
	* Says wether the square has been discovered or not.
	*/
	
	public boolean isDiscovered() {
		return discovered;
	
        }
}
