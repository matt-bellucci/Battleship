package fr.insarouen.battleship.model;


import java.util.ArrayList;

import fr.insarouen.battleship.observer.Observable;
import fr.insarouen.battleship.observer.Observer;


public class Game implements Observable{

	private Player player1, player2;
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();
	
	private static int idGame =0;
	
	public Game(Player p1, Observer o1, Player p2, Observer o2) {
		idGame++;
		this.player1 = p1;
		this.listObserver.add(o1);
		this.player2 = p2;
		this.listObserver.add(o2);
	}
	
	public Player getPlayer(){
		return player1;
	}

	public int getIdGame() {
		return idGame;
	}

	public void removeObserver(Observer obs) {
		listObserver.remove(obs);
	}

	@Override
	public void addObserver(Observer obs) {
		listObserver.add(obs);
	}

	@Override
	public void notifyObserver(String str) {
		for (Observer obs : listObserver){
			obs.update(str);
		}
	}
	
}
