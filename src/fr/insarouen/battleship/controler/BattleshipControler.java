package fr.insarouen.battleship.controler;

import java.net.InetAddress;

import fr.insarouen.battleship.model.DataServer;
import fr.insarouen.battleship.observer.Observer;

public class BattleshipControler extends AbstractControler {

	InetAddress ip;
	String name = "Inconnu";
	
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
	public void rm() {
		data.removePlayer(ip);
		data.notifyObserver("LIST:"+data.getPlayersList());
	}
	
	@Override
	public void removeObserver(Observer obs){
		data.removeObserver(obs);
		data.notifyObserver("LIST:"+data.getPlayersList());
	}

	@Override
	public void askNewGame(String name) {
		Observer obs = data.getObserverByName(name);
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


}
