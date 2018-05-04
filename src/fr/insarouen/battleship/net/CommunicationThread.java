package fr.insarouen.battleship.net;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;


public abstract class CommunicationThread extends Thread {

	protected Socket socket = null;
    protected PrintWriter writer = null;
    protected BufferedInputStream reader = null;
    
	
	public CommunicationThread(Socket socket) throws IOException, UnknownHostException {
		super();
    	this.socket = socket;
		this.writer = new PrintWriter(socket.getOutputStream());
		this.reader = new BufferedInputStream(socket.getInputStream());
		
	}
	
	public CommunicationThread(String pHost, int pPort) throws IOException, UnknownHostException {
		super();
    	this.socket = new Socket(pHost,pPort);
		this.writer = new PrintWriter(socket.getOutputStream());
		this.reader = new BufferedInputStream(socket.getInputStream());
		
	}

	 @Override
	 public void start(){
		 super.start();
	 }
	 
	 @Override
	 public void run(){
		 
		 System.err.println("Lancement du traitement de la connexion cliente");		
		 while(!this.socket.isClosed()){
			  String message = receive();
			  ArrayList<String> commande = new ArrayList<String>();
			  commande = decode(message);
			  process(commande);
			 
		 }
		 try {
			 socket.close();
		 } catch (IOException e) {
			 e.printStackTrace();
		 }
	 }
	 
	 public abstract String receive();
	 public abstract void send(String str);
	 protected abstract ArrayList<String> decode(String str);
	 public abstract void process(ArrayList<String> commande);
	 
}
