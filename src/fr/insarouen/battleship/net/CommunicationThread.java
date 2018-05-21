package fr.insarouen.battleship.net;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * Managing socket connections
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
     * Constructs a new Thread of communication with the specific Socket connection
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
     * Constructs a new Thread of communication thanks to the IP address of the remote device and port communication.
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
	  * Manage the receipt and processing of messages
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
	  * Read on the channel the received message
	  */
	 public abstract String receive();
	 
	 /**
	  * Write on the channel the message to send
	  *	@return String
	  */
	 public abstract void send(String str);
	 
	 /**
	  * Modify the received string to be interpreted
	  * @param String
	  * @return ArrayList<String>
	  */
	 protected abstract ArrayList<String> decode(String str);
	 
	 /**
	  * Process the received message
	  * @param ArrayList<String>
	  */
	 public abstract void process(ArrayList<String> commande);
	 
}
