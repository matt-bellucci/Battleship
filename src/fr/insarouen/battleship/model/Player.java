package fr.insarouen.battleship.model;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Player {
	
	private String name;
	private InetAddress ip;
	
	private stats = new Stats(); 
	
	public Player(){
		byte[] ip = {127,0,0,1};
		try {
			this.ip = InetAddress.getByAddress(ip);
		} catch (UnknownHostException e) {
		    e.printStackTrace();
		}
		this.name = new String("Anonyme");
	}
	
	

	public Player(InetAddress ip){
		this("Anonyme",ip);
	}
	
	public Player(String name, InetAddress ip){
		this.ip = ip;
		this.name = name;
	}
	
	
	public String getName(){
		return this.name;
	}

	public InetAddress getIP(){
		return this.ip;
	}
	
	
	public String toString(){
		return getName() + "-->"+ getIP().getHostAddress();
	}
}
