package fr.insarouen.battleship.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Boats {
	
	private HashMap<Integer, Boat> mapBoats;
	private Integer nextKey;
	
	public Boats(){
		this.mapBoats = new HashMap<>();
	}
	
	public Boats(int firstKey, Boat ... boats){
		int key=firstKey;
		this.mapBoats = new HashMap<>();
		for( Boat b : boats){
			mapBoats.put(key, b);
			b.setId(key);
			key++;
		}
		this.nextKey = key;
	}
	
	public void addBoat(Boat boat){
            this.mapBoats.put(nextKey, boat);
            this.nextKey++;
        }
        
	public void removeBoat(int id){
		this.mapBoats.remove(id);
	}
        
	public Boat getBoat(int id){
		return mapBoats.get(id);
	}
	
	public int getNbBoats(){
		return mapBoats.size();
	}
	
	public boolean allSunk(){
		boolean allsunk = true ;
                Integer key;
		Set<Integer> keys = mapBoats.keySet();
                Iterator<Integer> keyIt = keys.iterator();
		while (keyIt.hasNext() && allsunk){
                    key = keyIt.next();
                    allsunk = mapBoats.get(key).isSunk();
                }
		return allsunk;	
	}

}
