package fr.insarouen.battleship.model;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Players {

	private ArrayList<Player> player;
	
	public Players(){
		this.player = new ArrayList<Player>();
	}
	
	public Player getPlayerByName(String name) throws NoSuchElementException, ArrayIndexOutOfBoundsException {
		Iterator<Player> it = player.iterator();
		Player p = null;
		p= it.next();
		while ((it.hasNext()) && (name.compareTo(p.getName()) != 0)) {
			p= it.next();
		}
		
		return p;
	}
	
	public Player getPlayerByIP(InetAddress ip) throws NoSuchElementException, ArrayIndexOutOfBoundsException{
		Iterator<Player> it = player.iterator();
		Player p = null;
		p = it.next();
		while ((it.hasNext()) && (ip.getHostAddress().compareTo(p.getIP().getHostAddress()) != 0)) {
			p= it.next();
		}
		
		return p;
	}
	
	

	public void add(InetAddress ip) {
		player.add(new Player(ip));
	}
	
	public void add(String name, InetAddress ip) {
		player.add(new Player(name,ip));
	}

	public void remove(String name) {
		player.remove(getPlayerByName(name));	
	}

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

	public int getId(Player p) {
		return player.indexOf(p);
	}

	public boolean isAvailableName(String name) {
		for (Player p : player){
			if (name.compareTo(p.getName())==0){
				return false;
			}
		}
		return true;
	}
}
