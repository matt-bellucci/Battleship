package fr.insarouen.battleship.model;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import fr.insarouen.battleship.observer.Observable;
import fr.insarouen.battleship.observer.Observer;

public class DataServer implements Observable{

	private ArrayList<Observer> listObserver = new ArrayList<Observer>();
	
	private Players players;
	private Games games;
	
	public DataServer(){
		this.players = new Players();
		this.games = new Games();
	}
	
	
	// METHODES RELATIVES A PLAYERS
	
	public String getPlayersList(){
		return players.list();
	}
	
	public void newPlayer(InetAddress ip){
		this.players.add(ip);
	}
	
	public void newPlayer(String name, InetAddress ip){
		this.players.add(name,ip);
	}
	
	public void removePlayer(String name){
		this.players.remove(name);
	}
	
	public boolean isAvailableName(String name) {
		return players.isAvailableName(name);
	}
	
	// METHODES RELATIVES AUX PARTIES
	
	public void newGame(String name1, String name2){
		
		Observer obs1 = getObserverByName(name1), obs2=getObserverByName(name2);
		
		Game g = new Game(	new PlayerInGame(players.getPlayerByName(name1)),
							obs1, 
							new PlayerInGame(players.getPlayerByName(name2)), 
							obs2);		
		
		this.games.add(g);
		
		obs1.update("IDPARTIE:"+g.getIdGame());
		removeObserver(getObserverByName(name1));
		removePlayer(name1);

		obs2.update("IDPARTIE:"+g.getIdGame());
		removeObserver(getObserverByName(name2));
		removePlayer(name2);
	}

	public Game getGame(int idGame) {
		return games.get(idGame);
	}

	
	// METHODES RELATIVES AUX OBSERVATEURS
	
	public Observer getObserverByName(String name) throws NoSuchElementException, ArrayIndexOutOfBoundsException{
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
	
	



	

	
}
