package fr.insarouen.battleship.model;

public class IronClad extends Boat {

	public IronClad() {
		super(3);
	}

	@Override
	public boolean isSunk(){
		return (this.getLength()*2 == this.getNbTouched());
	}
}
