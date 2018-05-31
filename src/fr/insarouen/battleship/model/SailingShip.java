package fr.insarouen.battleship.model;

/**
* Special type of boat
*/

public class SailingShip extends Boat {
	public SailingShip(){
		super(1);
	}
	
	@Override
	public String toString() {
		return "Voilier : tient sur une case\n";
	}	
}
