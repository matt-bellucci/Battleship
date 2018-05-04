package fr.insarouen.battleship.controler;

import fr.insarouen.battleship.model.DataServer;
import fr.insarouen.battleship.observer.Observer;

public abstract class AbstractControler {
	
	protected DataServer data;
	
	public AbstractControler(DataServer data){
		this.data = data;
	}
	

	public String getPlayers() {
		return data.getPlayersList();
	}
	
	public abstract void newPlayer();
	
	public abstract void newPlayer(String name);
	
	public abstract void rm();


	public abstract void removeObserver(Observer obs);
	
}
