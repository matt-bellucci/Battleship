package fr.insarouen.battleship.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Collection;

/**
 * Class used to represent collection of all boats. 
 */

public class Boats {
	
	private HashMap<Integer, Boat> mapBoats;
	private Integer nextKey;
	

	/**
	  * Creates a new collection of boats (using HashMap) 
	  */

	public Boats(){
		this.mapBoats = new HashMap<>();
	}
	
	/**
	  * Create a new collection of boats, giving an id to each of them.
	  */

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
	
	/**
	  * Adds a boat to the collection.
	  */

	public void addBoat(Boat boat){
            this.mapBoats.put(nextKey, boat);
            this.nextKey++;
        }
        
    /**
	  * Removes a boat from the collection 
	  */    

	public void removeBoat(int id){
		this.mapBoats.remove(id);
	}
        
	/**
	  * Return a specific boat from the collection
	  */

	public Boat getBoat(int id){
		return mapBoats.get(id);
	}
	
	/**
	  * Returns the number of boats remaining.
	  */

	public int getNbBoats(){
		return mapBoats.size();
	}

	/**
	  * Says wether all boats are sunk or not. 
	  */

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
	
	@Override
	public String toString() {
		String toStr = "Liste de bateaux :\n";
		/*for (Boat b : mapBoats){
			toStr += b.toString()+"\n";
		}*/
		return toStr;
	}
        
        /**
         * returns an arrayList of all the boats 
         * so we can iterate through all the boats
         */
	public ArrayList<Boat> values(){
		ArrayList<Boat> list = new ArrayList<>(mapBoats.values());
                return list;
	}


}
