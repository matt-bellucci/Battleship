package fr.insarouen.battleship.model;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;

public class Players {

	private ArrayList<Player> player;
	
	public Players(){
		this.player = new ArrayList<Player>();
	}
	
	public Player getPlayerByName(String name){
		Iterator<Player> it = player.iterator();
		Player p = it.next();
		while ((it.hasNext()) && (name.compareTo(p.getName()) != 0)) {
			p= it.next();
		}
		return p;
	}
	
	public Player getPlayerByIP(InetAddress ip){
		Iterator<Player> it = player.iterator();
		Player p = it.next();
		while ((it.hasNext()) && (ip.getHostAddress().compareTo(p.getIP().getHostAddress()) != 0)) {
			p= it.next();
		}
		return p;
	}
	
	public String toString(){
		String str = new String(" ");
		for (Player p : player){
			str += p.toString() + " \n";
		}
		return str;
	}

	public void add(InetAddress ip) {
		player.add(new Player(ip));
	}

	public void remove(InetAddress ip) {
		player.remove(getPlayerByIP(ip));	
	}
}
