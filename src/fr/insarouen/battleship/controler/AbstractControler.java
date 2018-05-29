package fr.insarouen.battleship.controler;

import fr.insarouen.battleship.model.DataServer;
import fr.insarouen.battleship.observer.Observer;

/**
 * Controller which is the intermediate between the communication channel and the data
 *
 * @author David Albert, Henri Durozay, Matthieu Bellucci, Thomas Anquetil
 * @version 0
 * 
 */
public abstract class AbstractControler {
	
	/**
	 * Data used by the controler
	 */
	protected DataServer data;
	
	 /**
	  * <b> Constructor of {@link AbstractControler} </b>
	  * 
	  * Constructs a controller which can update data
	  *  @param data 
	  *  	data to be changed by the controler 
	  */
	public AbstractControler(DataServer data){
		this.data = data;
	}
	
	/**
	 * Return the player list for IHM
	 * @return list of players
	 */
	public String getPlayers() {
		return data.getPlayersList();
	}
	
	/**
	 * Tell to data to add a new player 
	 */
	public abstract void newPlayer();
	
	/**
	 * Tell to data to add a new player with a name
	 *  @param name
	 */
	public abstract void newPlayer(String name);
	
	/**
	 * Tell to data to remove the player 
	 */
	public abstract void removePlayer();

	/**
	 * Tell to data to remove the observer associated to the player
	 *  @param obs
	 */
	public abstract void removeObserver(Observer obs);

	/**
	 * Tell to data to remove the player and its associated observer
	 * @param obs
	 */
	public abstract void remove(Observer obs);

	/**
	 * Tell to data to verify that the name is not already used
	 * @param name
	 * @return is True if name is available, else False
	 */
	public abstract boolean isAvailableName(String name);
	
	/**
	 * Tell to data to ask to a player if he want to play 
	 * @param name
	 * 
	 */
	public abstract void askNewGame(String string);

	/**
	 * Tell to data to answer at the below player of my answer 
	 * @param advName, answer
	 */
	public abstract void answerNewGame(String advName, String answer);

	/**
	 * Tell to data to start a new game
	 * @param name
	 */
	public abstract void newGame(String name);

	/**
	 * Set the ID of the current game 
	 * @param id
	 */
	public abstract void setIdGame(int id);
        
	/**
	 * Tell to data to discover the (x,y) case
	 * @param x
	 * @param y
	 */
	public abstract void discover(int x, int y);
	
}
