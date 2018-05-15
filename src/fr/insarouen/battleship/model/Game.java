package fr.insarouen.battleship.model;


import java.util.ArrayList;
import fr.insarouen.battleship.observer.Observer;


public class Game {

	private Player player1, player2;
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();
	
	public Game(Player p1, Observer o1, Player p2, Observer o2) {
		this.player1 = p1;
		this.listObserver.add(o1);
		this.player2 = p2;
		this.listObserver.add(o2);
	}
	
}
