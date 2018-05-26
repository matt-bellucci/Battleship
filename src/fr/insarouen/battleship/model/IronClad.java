package fr.insarouen.battleship.model;

/**
* Special type of boat
*/

public class IronClad extends Boat {

	public IronClad() {
		super(3);
	}

	@Override
	public boolean isSunk(){
		return (this.getLength()*2 == this.getNbTouched());
	}
	
	@Override
	public String toString() {
		return "Cuirassé : deux fois plus puissant\n";
	}
	
}
