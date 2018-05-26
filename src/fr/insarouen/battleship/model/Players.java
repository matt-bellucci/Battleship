package fr.insarouen.battleship.model;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
* Contains all connected players. 
*/

public class Players {

	private ArrayList<Player> player;
	
	/**
	  * Constructs a list of Players.
	  */

	public Players(){
		this.player = new ArrayList<Player>();
	}
	
	/**
	  * Allows to recover a player knowing only his name.
	  */

	public Player getPlayerByName(String name) throws NoSuchElementException, ArrayIndexOutOfBoundsException {
		Iterator<Player> it = player.iterator();
		Player p = null;
		p= it.next();
		while ((it.hasNext()) && (name.compareTo(p.getName()) != 0)) {
			p= it.next();
		}
		
		return p;
	}
	
	/**
	  * Allows to recover a player knowing only his ip adress. 
	  */

	public Player getPlayerByIP(InetAddress ip) throws NoSuchElementException, ArrayIndexOutOfBoundsException{
		Iterator<Player> it = player.iterator();
		Player p = null;
		p = it.next();
		while ((it.hasNext()) && (ip.getHostAddress().compareTo(p.getIP().getHostAddress()) != 0)) {
			p= it.next();
		}
		
		return p;
	}
	
	/**
	  * Adds a new player to the current list, knowing his ip adress. 
	  */

	public void add(InetAddress ip) {
		player.add(new Player(ip));
	}
	
	/**
	  * Adds a new player to the current list, knowing both his name and ip adress. 
	  */

	public void add(String name, InetAddress ip) {
		player.add(new Player(name,ip));
	}

	/**
	  * Removes a player from the current list knowing his name. 
	  */

	public void remove(String name) {
		player.remove(getPlayerByName(name));	
	}

	/**
	  * Creates a list which contains the names of each players.
	  */

	public String list() {
		String str = new String("");
		for (Player p : player){
			str += p.getName() + "\n";
		}
		return str;
	}
	
	public String toString(){
		String str = new String(" ");
		for (Player p : player){
			str += p.toString() + "\n";
		}
		return str;
	}

	/**
	  * Returns the player's position in the current list of players.
	  */

	public int getId(Player p) {
		return player.indexOf(p);
	}

	/**
	  * Allows to know if a name is available or not (if someone else is already using it).
	  */

	public boolean isAvailableName(String name) {
		for (Player p : player){
			if (name.compareTo(p.getName())==0){
				return false;
			}
		}
		return true;
	}
		
}
