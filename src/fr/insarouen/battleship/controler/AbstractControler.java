package fr.insarouen.battleship.controler;

import fr.insarouen.battleship.model.DataServer;
import fr.insarouen.battleship.observer.Observer;

/**
 * Controller which is the intermediate between the communication channel and the data
 *
 * @author David ALBERT
 * @version 0
 * 
 */
public abstract class AbstractControler {
	
	protected DataServer data;
	
	 /**
	  * Constructs a controller which can update data 
	  */
	public AbstractControler(DataServer data){
		this.data = data;
	}
	

	public String getPlayers() {
		return data.getPlayersList();
	}
	
	/**
	 * Tell to data to add a new player 
	 */
	public abstract void newPlayer();
	
	/**
	 * Tell to data to add a new player with a name
	 */
	public abstract void newPlayer(String name);
	
	/**
	 * Tell to data to remove the player 
	 */
	public abstract void removePlayer();

	/**
	 * Tell to data to remove the observer associated to the player
	 */
	public abstract void removeObserver(Observer obs);

	/**
	 * Tell to data to remove the player and its associated observer
	 */
	public abstract void remove(Observer obs);

	/**
	 * Tell to data to verify that the name is not already used
	 */
	public abstract boolean isAvailableName(String string);
	
	/**
	 * Tell to data to ask to a player if he want to play 
	 */
	public abstract void askNewGame(String string);

	/**
	 * Tell to data to answer at the below player of my answer 
	 */
	public abstract void answerNewGame(String sdvName, String answer);

	/**
	 * Tell to data to start a new game
	 */
	public abstract void newGame(String string);

	/**
	 * Set the ID of the current game 
	 */
	public abstract void setIdGame(int id);
        
	/**
	 * Tell to data to discover the (x,y) case
	 */
	public abstract void discover(int x, int y);
	
}
