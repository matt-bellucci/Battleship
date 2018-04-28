package fr.insarouen.battleship.model;

import java.net.InetAddress;

public class DataServer {
	private Players players;
	
	public DataServer(){
		this.players = new Players();
	}
	
	public String getPlayersList(){
		return players.toString();
	}
	
	public void newPlayer(InetAddress ip){
		players.add(ip);
	}
	
	public void removePlayer(InetAddress ip){
		players.remove(ip);
	}
}
