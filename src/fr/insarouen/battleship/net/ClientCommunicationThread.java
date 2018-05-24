package fr.insarouen.battleship.net;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.omg.CORBA.CTX_RESTRICT_SCOPE;


import fr.insarouen.battleship.view.IHM;
import fr.insarouen.battleship.view.StateIHM;

/**
 * Management of socket connexions (client side)
 *
 * @author David ALBERT
 * @version 0
 * 
 */


public class ClientCommunicationThread extends CommunicationThread {

	private IHM ihm;
	private static final String SEP = ":";
	private boolean closeConnexion = true;
	private boolean inRequest = false;
	private String request ="";
	
	 /**
     * Constructs a new Thread of communication with the server thanks to the specific Socket connexion
     * @param Socket
     * @throws IOException, UnknownHostException
     * 
     */
	public ClientCommunicationThread(Socket socket) throws IOException {
		super(socket);
	}
	
	public ClientCommunicationThread(IHM ihm, String pHost, int pPort) throws UnknownHostException, IOException {
		super(pHost,pPort);
		this.ihm = ihm;
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
		System.err.println("Lancement du traitement de la communication cliente");
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
					debug += "Message du server : " + remote.getAddress().getHostAddress() +".\n";
					//debug += " Sur le port : " + remote.getPort() + ".\n";
					debug += " -> Commande reçue : " + message + "\n";
					System.err.println("\n" + debug);
				
					//On traite la demande du client en fonction de la commande envoyée
					process(commande);
		    	
				//On ferme la connexion
				if(closeConnexion){
				    System.err.println("Fermeture connexion avec :"+remote.getAddress().getHostAddress() +".");
				    writer = null;
				    reader = null;
				    socket.close();
				    break;
				}
			
			// Gestion des erreurs
		    } catch (SocketException e) {
		    	System.err.println("Connexion interrompue");
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
	
	public String request(String msg){
		
		inRequest = true;
		send("REQUEST:"+msg);
		
		while (inRequest){
			
		}
		
		System.out.println( "Réponse server : " + request +".");
	
		return request;
	
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
			
			case "DEMANDEPARTIE":
				ihm.askNewGame(commande.get(1));
				break;
			
			case "REPONSEPARTIE":
				if (commande.get(2).equals("oui") ){
					ihm.battleInterface(commande.get(1));
				}
				else {
					ihm.requestRefused(commande.get(1), StateIHM.OPPONENT_CHOICE);
				}
				break;
	
			case "IDPARTIE":
					send("IDPARTIE:"+commande.get(1));
				break;
				
			case "AVAILABLENAME":
				if (commande.get(2).equals("yes")){
					ihm.setPlayerName(commande.get(1));
					ihm.opponentChoiceInterface();
					send("LIST");
				}
				else {
					ihm.requestRefused(commande.get(1), StateIHM.PLAYER_IDENTIFICATION);
				}
				
				break;
			case "GRID":
				System.out.println(commande.get(1) +" = "+ ihm.getPlayerName()+" ? "+(commande.get(1).equals(ihm.getPlayerName())));
				if (commande.get(1).equals(ihm.getPlayerName())) {
					ihm.setPersonalGrid(commande.get(2));
				}
				break;
			case "TIR":
				ihm.updateGrid(commande.get(1),commande.get(2),commande.get(3),commande.get(4));
			break;
			
			case "FINPARTIE":
					ihm.endGame(commande.get(1));
				break;
			
			case "CLOSE":
			    closeConnexion = true;
			    break;
			
			default : 
				System.out.println("Traitement :" + commande.get(0));
			    break;
			}
		}
	
	@Override
	public String toString(){
		return "Thread de communication coté client"; 
	}
	
}
