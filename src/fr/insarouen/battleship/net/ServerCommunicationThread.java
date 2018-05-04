package fr.insarouen.battleship.net;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;


import fr.insarouen.battleship.view.IHM;

public class ServerCommunicationThread extends CommunicationThread {

	private IHM ihm;
	private static final String SEP = ":";
	private boolean closeConnexion = true;
	
	public ServerCommunicationThread(Socket socket) throws IOException {
		super(socket);
	}
	
	public ServerCommunicationThread(String pHost, int pPort) throws UnknownHostException, IOException {
		super(pHost,pPort);
		try {
			writer = new PrintWriter(socket.getOutputStream(), true);
			reader = new BufferedInputStream(socket.getInputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	

	@Override
	public void run() {
		System.err.println("Lancement du traitement de la communication serveur");
		closeConnexion = false;
			
		//tant que la connexion est active, on traite les demandes
		while(!this.socket.isClosed()){
		    try {
				
				String message = receive();
				InetSocketAddress remote = (InetSocketAddress)socket.getRemoteSocketAddress();
		
				// Transformation de la chaîne de caractère reçue pour facilité le traitement
				ArrayList<String> commande = new ArrayList<String>();
				commande = decode(message);
						
				//On affiche quelques infos
				String debug = "";
				debug += "Message du server : " + remote.getAddress().getHostAddress() +".";
				debug += " Sur le port : " + remote.getPort() + ".\n";
				debug += "\t -> Commande reçue : " + message + "\n";
				System.err.println("\n" + debug);
				
				//On traite la demande du client en fonction de la commande envoyée
				process(commande);
			
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
	

	@Override
	public String receive() {
		String response = "";
    	int stream;
    	byte[] b = new byte[4096];
    	try {
			stream = reader.read(b);
	    	response = new String(b, 0, stream);
		} catch (IOException e) {
			e.printStackTrace();
		}catch (StringIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
    	return response;
	}

	@Override
	public void send(String msg) {
		   try {
	            //On envoie le message au serveur
	            writer.write(msg);
	            writer.flush();  

	            System.out.println("Message pour Server : " + msg );
	         } catch (NullPointerException e1) {
		            e1.printStackTrace();
		         }
	}

	@Override
	protected ArrayList<String> decode(String str) {
		ArrayList<String> res = new ArrayList<String>();
		int l1 =0;
		try {
		    int l2=str.indexOf(SEP,l1);
		    res.add(new String(str.substring(l1, l2)));
		    while (l2<str.length()){
			l1=l2+1;
			l2=str.indexOf(SEP, l1);
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

	@Override
	public void process(ArrayList<String> commande) {
		switch(commande.get(0).toUpperCase()){
		case "LIST":
		try {
			ihm.updateListPlayers(commande.get(1));
		} catch (NullPointerException e) {
			System.out.println("Erreur de listing");
		}
		    	 break;
		case "CLOSE":
		    closeConnexion = true;
		    break;
		default : 
			ihm.changeTitle("Nouveau titre");
			System.out.println("Traitement :" + commande.get(0));
		    break;
		}
	}

	public void setIHM(IHM ihm) {
		System.out.println("Done");
		this.ihm = ihm;
	}

}
