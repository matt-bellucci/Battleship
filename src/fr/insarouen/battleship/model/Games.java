package fr.insarouen.battleship.model;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
* List of all games.
*/

public class Games {

private ArrayList<Game> game;
	
	/**
	  * Constructs a new list of games.
	  */

	public Games(){
		this.game = new ArrayList<Game>();
	}

	/**
	  * Adds a new game to the current list.
	  */

	public void add(Game game){
		this.game.add(game);
		System.out.println(this.toString());
	}

	/**
	  * Return a game of which the id is known.
	  */

	public Game get(int idGame) {
		for (Game g : game){
			if (g.getIdGame()==idGame){
				return g;
			}
		}
		return null;
	}

	public String toString(){
		String str="Games : \n";
		for (Game g : game){
			str += g.toString();
		}
		return str;
	}

}
