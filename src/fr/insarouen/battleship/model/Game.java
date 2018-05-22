package fr.insarouen.battleship.model;


import java.util.ArrayList;
import java.util.NoSuchElementException;

import fr.insarouen.battleship.observer.Observable;
import fr.insarouen.battleship.observer.Observer;


public class Game implements Observable{

	private PlayerInGame player1, player2;
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();
	private int idGame ;
	
	private static int numberOfGame =0;
	
	public Game(PlayerInGame p1, Observer o1, PlayerInGame p2, Observer o2) {
		numberOfGame++;
		this.idGame = numberOfGame;
		this.player1 = p1;
		addObserver(o1);
		System.out.println(o1.toString());
		this.player2 = p2;
		addObserver(o2);
		System.out.println(o2.toString());
		
		player1.placeBoatsRandomly();
		//notifyObserver("GRID:"+player1.getName()+":"+player1.getInitialGrid());
		player2.placeBoatsRandomly();
		//notifyObserver("GRID:"+player2.getName()+":"+player2.getInitialGrid());
	}
	
	public PlayerInGame getPlayerByName(String name){
            if ( name.equals(player1.getName()) ){return player1;}
            else{return player2;}
	}
	public PlayerInGame opponentTo(PlayerInGame player){
		if ( player.equals(player1) ){return player2;}
		else{return player1;}
	}

	public int getIdGame() {
		return idGame;
	}
    
	public void discover(int x, int y, PlayerInGame player){
		System.out.println(this.toString());
		String resultat = player.discover(x,y);
		notifyObserver("TIR:"+x+":"+y+":"+player.getName()+":"+resultat);
		if (player.hasLost()){notifyObserver("FINPARTIE:"+player.getName());}
	}
	
	@Override
	public void removeObserver(Observer obs) {
		listObserver.remove(obs);
	}

	@Override
	public void addObserver(Observer obs) {
		listObserver.add(obs);
	}

	@Override
	public void notifyObserver(String str) {
		System.out.println("Mise à jour observateur Game "+this.idGame+" (x"+this.listObserver.size()+")");
		System.out.println("-> Message : "+str);
		for (Observer obs : listObserver){
			obs.update(str);
		}
	}
	
	
	@Override
	public String toString(){
		String toStr ="";
		toStr += "Partie "+idGame+"\n------\n";
		toStr += "Observateurs :\n";
		for (Observer obs : listObserver){
			toStr+= obs.toString()+"\n";
		}
		return toStr;
	}
}
