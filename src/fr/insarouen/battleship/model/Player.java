package fr.insarouen.battleship.model;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Represents a player with a name, and an ip adress. 
 */

public class Player {
	
	private String name;
	private InetAddress ip;
	
	private Stats stats = new Stats(); 
	
	/**
	  * Constructs a new player by recovering his ip adress, calling him "Anonyme".
	  */

	public Player(){
		byte[] ip = {127,0,0,1};
		try {
			this.ip = InetAddress.getByAddress(ip);
		} catch (UnknownHostException e) {
		    e.printStackTrace();
		}
		this.name = new String("Anonyme");
	}
	
	/**
	  * Constructs a player knowing his ip adress, calling him "Anonyme".
	  */

	public Player(InetAddress ip){
		this("Anonyme",ip);
	}
	
	/**
	  * Constructs a player knowing both his name and his ip adress.
	  */

	public Player(String name, InetAddress ip){
		this.ip = ip;
		this.name = name;
	}
	
	/**
	  * Returns the name of the player.
	  */
	
	public String getName(){
		return this.name;
	}

	/**
	  * Returns the player's ip adress.
	  */

	public InetAddress getIP(){
		return this.ip;
	}
	
	
	public String toString(){
		return getName() + "-->"+ getIP().getHostAddress();
	}
}
