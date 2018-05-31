package fr.insarouen.battleship.model;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import fr.insarouen.battleship.observer.Observable;
import fr.insarouen.battleship.observer.Observer;


/**
 * Data of BattleshipNetworks. Contains players without game and games 
 *
 * @author David, Henri, Matthieu, Thomas
 * @version 0
 * 
 */

public class DataServer implements Observable{

	private ArrayList<Observer> listObserver = new ArrayList<Observer>();
	
	private Players players; // Joueurs en attente de partie
	private Games games;	 // Parties en cours
	
	/**
	  *  Constructs a list of players and a list of games.
	  */

	public DataServer(){
		this.players = new Players();
		this.games = new Games();
	}
	
	
	// METHODES RELATIVES AUX JOUEURS HORS PARTIE
	

	/**
	  * Returns a list that contains the name of each player.
	  */

	public String getPlayersList(){
		return players.list();
	}
	
	/**
	  * Adds a new player, knowing only his ip adress. 
	  */

	public void newPlayer(InetAddress ip){
		this.players.add(ip);
		System.out.println("Nouveau Joueur"+ip);
	}
	
	/**
	  * Adds a new player, knowing both his name and ip adress.
	  */

	public void newPlayer(String name, InetAddress ip){
		this.players.add(name,ip);
		System.out.println("Nouveau Joueur"+name+"/"+ip);
	}
	
	/**
	  * Removes a player knowing his name.
	  */

	public void removePlayer(String name){
		this.players.remove(name);
		System.out.println("Suppression Joueur : "+name);
	}
	
	/**
	  * Says if a specific name is available or not.
	  */

	public boolean isAvailableName(String name) {
		return players.isAvailableName(name);
	}
	
	// METHODES RELATIVES AUX PARTIES
	
	public void newGame(String name1, String name2){
		int i=0;
		for (Observer o : listObserver){
			System.out.println("Obs "+(i++) +" : "+o.toString()+"\n");
				
		}
		System.out.println("---------------CREATION PARTIE --------------\n");
		
		Observer obs1 = getObserverByName(name1), obs2=getObserverByName(name2);

		Game g = new Game(	new PlayerInGame(players.getPlayerByName(name1)),
							obs1, 
							new PlayerInGame(players.getPlayerByName(name2)), 
							obs2);		
		
		obs1.update("IDPARTIE:"+g.getIdGame());

		obs2.update("IDPARTIE:"+g.getIdGame());
		
		this.games.add(g);

		removeObserver(obs1);
		removePlayer(name1);
		removeObserver(obs2);
		removePlayer(name2);
		System.out.println(g.toString());
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		g.init();
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
		System.out.println("Mise à jour observateur Data x"+this.listObserver.size());
		System.out.println("-> Message : "+str);
		for (Observer obs : listObserver){
			obs.update(str);
		}
	}
	
	@Override
	public String toString() {
		return players.toString()+"\n"+games.toString()+"\n";
	}
	
		
}
