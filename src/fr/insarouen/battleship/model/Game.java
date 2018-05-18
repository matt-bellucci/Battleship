package fr.insarouen.battleship.model;


import java.util.ArrayList;

import fr.insarouen.battleship.observer.Observable;
import fr.insarouen.battleship.observer.Observer;


public class Game implements Observable{

	private PlayerInGame player1, player2;
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();
	
	private static int idGame =0;
	
	public Game(PlayerInGame p1, Observer o1, PlayerInGame p2, Observer o2) {
		idGame++;
		this.player1 = p1;
		this.listObserver.add(o1);
		this.player2 = p2;
		this.listObserver.add(o2);
	}
	
	public PlayerInGame getPlayerByName(String name){
            if ( name.equals(player1.getName()) ){return player1;}
            else{return player2;}
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
        
        public void discover(int x, int y, PlayerInGame player){
            String resultat = player.discover(x,y);
            notifyObserver("TIR:"+x+":"+y+":"+player.getName()+":"+resultat);
            
        }
	
}
