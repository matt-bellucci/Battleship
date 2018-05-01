package fr.insarouen.battleship.controler;

import java.net.InetAddress;

import fr.insarouen.battleship.model.DataServer;

public class BattleshipControler extends AbstractControler {

	InetAddress ip;
	
	public BattleshipControler(DataServer data, InetAddress ip) {
		super(data);
		this.ip = ip;
	}

	@Override
	public void newPlayer() {
		data.newPlayer(ip);
	}
	
	@Override
	public void newPlayer(String name) {
		data.newPlayer(name,ip);
	}

	@Override
	public void rm() {
		data.removePlayer(ip);
	}
	

}
