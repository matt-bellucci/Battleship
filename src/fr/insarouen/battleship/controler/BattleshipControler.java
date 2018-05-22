package fr.insarouen.battleship.controler;

import java.net.InetAddress;
import java.util.NoSuchElementException;

import fr.insarouen.battleship.model.DataServer;
import fr.insarouen.battleship.observer.Observer;
import fr.insarouen.battleship.model.Game;
import fr.insarouen.battleship.model.PlayerInGame;

public class BattleshipControler extends AbstractControler {

	private InetAddress ip;
	private String name = "Inconnu";
	private int idGame = -1;
	
	/**
	 * Constructs a controller for BattleshipNet with data and IP player
	 */
	public BattleshipControler(DataServer data, InetAddress ip) {
		super(data);
		this.ip = ip;
	}

	@Override
	public void newPlayer() {
		data.newPlayer(ip);
		data.notifyObserver("LIST:"+data.getPlayersList());
		
	}
	
	@Override
	public void newPlayer(String name) {
		this.name = name;
		data.newPlayer(name,ip);
		data.notifyObserver("LIST:"+data.getPlayersList());
	}

	@Override
	public void removePlayer() {
		if (idGame == -1){
			data.removePlayer(name);
			data.notifyObserver("LIST:"+data.getPlayersList());
		}
		else {
			data.getGame(idGame).notifyObserver("FINPARTIE");
		}
	}
	
	@Override
	public void removeObserver(Observer obs){
		if (idGame == -1){
			data.removeObserver(obs);
		}
		else {
			data.getGame(idGame).removeObserver(obs);
		}
	}
	
	@Override
	public void askNewGame(String p) {
		Observer obs = data.getObserverByName(p);
		obs.update("DEMANDEPARTIE:"+this.name);
	}

	@Override
	public void answerNewGame(String advName,String answer) {
		Observer obs = data.getObserverByName(advName);
		obs.update("REPONSEPARTIE:"+this.name+":"+answer);
	}

	@Override
	public boolean isAvailableName(String name) {
		return data.isAvailableName(name);
	}

	@Override
	public void newGame(String nameP2) {

		System.out.println("newGame :"+name + " vs "+ nameP2);
		data.newGame(name,nameP2);
		System.out.println("Nouvelle Partie : "+idGame);
		data.notifyObserver("LIST:"+data.getPlayersList());
	}

	@Override
	public void remove(Observer obs) {
		try {
			removeObserver(obs);
			removePlayer();
		} catch (NoSuchElementException e){
			System.out.println("Element inconnu dans la liste");
		} catch (ArrayIndexOutOfBoundsException e){
			System.out.println("Element inconnu dans la liste");
		}

	}

	@Override
	public void setIdGame(int id) {
		this.idGame = id;
		
	}
        
	@Override
	public void discover(int x, int y){
		Game game = data.getGame(idGame);
		if (game != null){
			PlayerInGame opp= game.opponentTo(game.getPlayerByName(name));
			game.discover(x,y,opp);
		}
		else {
			System.out.println("Erreur : Game inconnu");
		}
		
	}
	
	@Override
	public String toString(){
		return "Controller coté server de "+ this.name +" "+this.ip;
	}


}
