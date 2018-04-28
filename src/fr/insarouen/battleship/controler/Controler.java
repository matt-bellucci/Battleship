package fr.insarouen.battleship.controler;

import java.net.InetAddress;

import fr.insarouen.battleship.model.DataServer;

public class Controler extends AbstractControler {

	InetAddress ip;
	
	public Controler(DataServer data, InetAddress ip){
		super(data);
		this.ip = ip;
	}
	
	
	public String getPlayers(){
		return data.getPlayersList();
	}
}
