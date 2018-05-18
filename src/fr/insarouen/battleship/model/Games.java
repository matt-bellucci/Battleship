package fr.insarouen.battleship.model;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Games {

private ArrayList<Game> game;
	
	public Games(){
		this.game = new ArrayList<Game>();
	}

	public void add(Game game){
		this.game.add(game);
	}

	public Game get(int idGame) {
		for (Game g : game){
			if (g.getIdGame()==idGame){
				return g;
			}
		}
		return null;
	}
	

}
