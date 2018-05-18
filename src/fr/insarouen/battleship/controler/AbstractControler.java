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
	
	public abstract void removePlayer();

	public abstract void removeObserver(Observer obs);

	public abstract void remove(Observer obs);

	public abstract boolean isAvailableName(String string);
	
	public abstract void askNewGame(String string);

	public abstract void answerNewGame(String sdvName, String answer);

	public abstract void newGame(String string);

	public abstract void setIdGame(int id);
        
        public abstract void discover(int x, int y, String name);
	
}
