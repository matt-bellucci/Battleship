package fr.insarouen.battleship.model;

import java.util.ArrayList;

public class Boats {
	
	private ArrayList<Boat> boat;
	
	public Boats(){
		this.boat = new ArrayList<Boat>();
	}
	
	public Boats(Boat ... boats){
		for( Boat b : boats){
		    boat.add(b);
		}
		
	}
	
	public Boat get(int id){
		return boat.get(id);
	}
	
	public boolean allSunk(){
		boolean allsunk = true ;
		int i =0;
		while (boat.get(i).isSunk())
			i++;  
		return (i == boat.size());	
	}

}
