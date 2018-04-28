package fr.insarouen.battleship.controler;

import fr.insarouen.battleship.model.DataServer;

public abstract class AbstractControler {
	
	protected DataServer data;
	
	public AbstractControler(DataServer data){
		this.data = data;
	}
	

	public String getPlayers() {
		return data.getPlayersList();
	}
	
	public abstract void newPlayer();

	public abstract void rm();
	
}
