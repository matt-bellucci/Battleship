package fr.insarouen.battleship.net;

import java.net.*;
import java.io.*;
import java.util.*;

import fr.insarouen.battleship.controler.*;


/**
 * Class ClientThread : Thread lancé côté server à chaque connexion d'un client. Il reçoit les messages, les traite, et renvoie une réponse.
 *
 * @author David ALBERT
 * @version 0
 * 
 */

public class ClientThread extends Thread {

    private static final String SEP = ":"; 
    private Socket socket = null;
    private PrintWriter writer = null;
    private BufferedInputStream reader = null;
    private AbstractControler controler;


    public ClientThread(Socket socket, AbstractControler controler) throws IOException{
	this.socket = socket;
	this.controler = controler;
    }
	
	
    @Override
    public void start(){
	super.start();
    }
	
    @Override
    public void run() {
			 
	System.err.println("Lancement du traitement de la connexion cliente");
	boolean closeConnexion = false;
		
	//tant que la connexion est active, on traite les demandes
	while(!socket.isClosed()){
	    try {
					
		writer = new PrintWriter(socket.getOutputStream());
		reader = new BufferedInputStream(socket.getInputStream());
				
		String response = read();
		InetSocketAddress remote = (InetSocketAddress)socket.getRemoteSocketAddress();

		// Transformation de la chaîne de caractère reçue pour facilité le traitement
		ArrayList<String> commande = new ArrayList<String>();
		commande = transform(response, SEP);
		for (String c : commande)
		    System.out.println(c);
				
				
		//On affiche quelques infos
		String debug = "";
		debug = "Thread : " + Thread.currentThread().getName() + ". ";
		debug += "Demande de l'adresse : " + remote.getAddress().getHostAddress() +".";
		debug += " Sur le port : " + remote.getPort() + ".\n";
		debug += "\t -> Commande reçue : " + response + "\n";
		System.err.println("\n" + debug);

		//On traite la demande du client en fonction de la commande envoyée
		String toSend = "";
		switch(commande.get(0).toUpperCase()){
		case "LIST":
		    toSend = controler.getPlayers();
		    break;
		case "NEW":
		    if (commande.size()>1) {
		    	controler.newPlayer(commande.get(1));
		    	toSend = "Nouveau Joueur : " +commande.get(1);
		    }
		    else {
		    	controler.newPlayer();
		    	toSend = "Nouveau Joueur";
		    }
		    break;
		case "0":
		    toSend = "PLOUF";
		    break;
		case "CLOSE":
		    toSend = "Communication terminée"; 
		    controler.rm();
		    closeConnexion = true;
		    break;
		default : 
		    toSend = "Commande inconnu !";                     
		    break;
		}

		//On envoie la réponse au client
		writer.write(toSend);
		writer.flush();
				
		//On ferme la connexion
		if(closeConnexion){
		    System.err.println("COMMANDE CLOSE DETECTEE ! ");
		    writer = null;
		    reader = null;
		    socket.close();
		    break;
		}
		
		// Gestion des erreurs
	    } catch (SocketException e) {
	    	System.err.println("LA CONNEXION A ETE INTERROMPUE");
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
		 
	}
    }
		
    private String read() throws IOException{  
	String response = "";
	int stream;
	byte[] b = new byte[4096];
	stream = reader.read(b);
	response = new String(b, 0, stream);
	return response;
    }
    
    private ArrayList<String> transform( String str, String sep){
	ArrayList<String> res = new ArrayList<String>();
	int l1 =0;
	try {
	    int l2=str.indexOf(sep,l1);
	    res.add(new String(str.substring(l1, l2)));
	    while (l2<str.length()){
		l1=l2+1;
		l2=str.indexOf(sep, l1);
		res.add(new String(str.substring(l1, l2)));
	    }
	} catch (StringIndexOutOfBoundsException e){
	    // System.err.println("Erreur");
	} catch (IndexOutOfBoundsException e){
	    // System.err.println("Erreur");
	}
	res.add(new String(str.substring(l1, str.length())));
		
	return res;
    }

}
