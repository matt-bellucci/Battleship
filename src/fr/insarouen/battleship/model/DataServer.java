package fr.insarouen.battleship.model;

import java.net.InetAddress;
import java.util.ArrayList;

import fr.insarouen.battleship.observer.Observable;
import fr.insarouen.battleship.observer.Observer;

public class DataServer implements Observable{
	private Players players;
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();
	private Games games;
	
	public DataServer(){
		this.players = new Players();
	}
	
	
	// 
	public String getPlayersList(){
		return players.list();
	}
	
	public void newPlayer(InetAddress ip){
		this.players.add(ip);
	}
	
	public void newPlayer(String name, InetAddress ip){
		this.players.add(name, ip);
	}
	
	public void removePlayer(InetAddress ip){
		this.players.remove(ip);
	}
	
	public Observer getObserverByName(String name) {
		int id = players.getId(players.getPlayerByName(name));
		return listObserver.get(id);
	}

	@Override
	public void addObserver(Observer obs) {
		this.listObserver.add(obs);
	}
	
	@Override
	public void removeObserver(Observer obs) {
		this.listObserver.remove(obs);
	}


	@Override
	public void notifyObserver(String str) {
		for (Observer obs : listObserver){
			obs.update(str);
		}
		
	}

	
	// A FAIRE
	public boolean isAvailableName(String name) {
		
		return true;
	}



	

	
}
