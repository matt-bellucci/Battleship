package fr.insarouen.battleship.net;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * Gestion des connexions sockets
 *
 * @author David ALBERT
 * @version 0
 * 
 */

public abstract class CommunicationThread extends Thread {

	protected Socket socket = null;
    protected PrintWriter writer = null;
    protected BufferedInputStream reader = null;
    
    /**
     * 
     * @param Socket
     * @throws IOException, UnknownHostException
     * 
     */
	public CommunicationThread(Socket socket) throws IOException, UnknownHostException {
		super();
    	this.socket = socket;
		this.writer = new PrintWriter(socket.getOutputStream());
		this.reader = new BufferedInputStream(socket.getInputStream());
		
	}
	
	/**
     * 
     * @param Socket
     * @throws IOException, UnknownHostException
     * 
     */
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
	 
	 /**
	  * Gere la reception et le traitement des messages
	  * 
	  */
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
	 
	 /**
	  * Lit sur le canal de communication le message recu
	  */
	 public abstract String receive();
	 
	 /**
	  * Ecrit sur le canal de communication le message a envoyer
	  *	@return String
	  */
	 public abstract void send(String str);
	 
	 /**
	  * Modifie la chaine de caractere recu pour qu'elle puisse etre interpretee
	  * @param String
	  * @return ArrayList<String>
	  */
	 protected abstract ArrayList<String> decode(String str);
	 
	 /**
	  * Traite le message recu
	  * @param ArrayList<String>
	  */
	 public abstract void process(ArrayList<String> commande);
	 
}
